package com.cglib.service;

import com.cglib.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 15:06
 */
@Service
public class CityService1 {
	@Autowired
	private CityDao cityDao;

	public List<Map<String, Object>> query() {
		return cityDao.query();
	}

}
