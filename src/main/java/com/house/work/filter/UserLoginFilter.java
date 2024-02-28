package com.house.work.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.house.work.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录权限验证过滤器
 * @author robin
 *
 */
@WebFilter(filterName = "loginFilter",urlPatterns = "/housework/*")//过滤包含该路径的所有请求
public class UserLoginFilter implements Filter {

	@Autowired
	private LoginService loginService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//忽略请求
		String uri = request.getRequestURI();
		//chain.doFilter(request, response);
        if(loginService.getUserInfo(request) != null){
            // 再跳转一次当前URL，以便去掉URL中token参数
            chain.doFilter(request, response);
        }else{
            redirectLogin(request, response);
        }
	}

    /**
     * 跳转登录
     *
     * @param request 请求
     * @param response 响应
     * @throws IOException 异常
     */
    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("success",false);
        json.put("message","未登录或已超时");
        writer.write(JSON.toJSONString(json));
        writer.flush();
        writer.close();
    }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {

	}

}
