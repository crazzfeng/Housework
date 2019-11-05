package com.house.work.util;

import java.util.UUID;

public class Seq {

	public static String createSeqS() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}

	public static long createSeqN(){
		return System.currentTimeMillis();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
