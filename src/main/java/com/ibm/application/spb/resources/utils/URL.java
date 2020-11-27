package com.ibm.application.spb.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Long> toList(String param) {
		String[] vect = param.split(",");
		List<Long> list = new ArrayList<>();

		for (int i = 0; i < vect.length; i++) {
			list.add(Long.parseLong(vect[i]));
		}

		return list;
	}

	public static String decodeParam(String url) {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
