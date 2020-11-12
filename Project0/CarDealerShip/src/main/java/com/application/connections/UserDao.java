package com.application.connections;

import java.util.List;

import com.application.models.Customer;
import com.application.models.Employee;

public interface UserDao<T, I> extends MasterDao<T, I>{
	T findByUsername(String s, String p);
	
}
