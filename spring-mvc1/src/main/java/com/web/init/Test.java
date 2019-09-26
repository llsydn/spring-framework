package com.web.init;

import org.apache.catalina.LifecycleException;

public class Test {
	public static void main(String[] args) {
		try {
			SpringApplicationLi.run();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
}
