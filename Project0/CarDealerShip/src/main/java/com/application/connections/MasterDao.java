package com.application.connections;

import java.util.List;

public interface MasterDao<T, I> {
	List<T> findAll();
	T findById(I i);
	T update(T t);
	T create(T t);
	int delete(I i);
}
