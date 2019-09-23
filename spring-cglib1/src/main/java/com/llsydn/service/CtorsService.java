package com.llsydn.service;

import org.springframework.stereotype.Service;

@Service
public class CtorsService {

//	public CtorsService() {
//
//	}
//
//	public CtorsService(MyCtors myCtors) {
//
//	}
//
//	public CtorsService(int age) {
//
//	}

	Class clazz;

	public CtorsService(Class clazz) {
		this.clazz = clazz;
	}

}
