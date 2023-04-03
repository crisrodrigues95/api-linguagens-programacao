package com.cristhian.linguagensApi;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class URL {
	
	public static String decomeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	

}
