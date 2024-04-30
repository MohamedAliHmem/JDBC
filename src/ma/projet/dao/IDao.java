package ma.projet.dao;

import java.util.List;

import ma.projet.beans.client;

public interface IDao<T> {
	boolean create(client o);
	boolean delete(T o);
	boolean update(T o);
	T findById(int id);
	List<T> findAll();
}
