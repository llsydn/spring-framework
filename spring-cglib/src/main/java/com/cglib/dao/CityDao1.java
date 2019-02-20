package com.cglib.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 15:03
 */
public interface CityDao1 {

	@Select("select * from city")
	List<Map<String, Object>> query();
}
