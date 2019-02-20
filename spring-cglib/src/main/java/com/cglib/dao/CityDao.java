package com.cglib.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lilinshen
 * @title 请填写标题
 * @description 请填写相关描述
 * @date 2019/2/19 15:03
 */
public interface CityDao {

	@Select("select * from city")
	List<Map<String, Object>> query();
}
