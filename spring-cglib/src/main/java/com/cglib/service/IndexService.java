package com.cglib.service;

import org.springframework.stereotype.Service;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 10:02
 */
@Service
public class IndexService {

	public IndexService() {
		System.out.println("indexService init ...");
	}

	public void query() {
		System.out.println("indexService query...");
	}

}
