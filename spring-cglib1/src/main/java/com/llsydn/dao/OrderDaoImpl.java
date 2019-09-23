package com.llsydn.dao;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Override
	public void query() {
		System.out.println("order dao impl");
	}
}
