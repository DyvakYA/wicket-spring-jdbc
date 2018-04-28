package dao;

import entity.User;

import java.util.List;

public interface UserRepository {

	void create(User user);

	User getUser(Integer id);

	List<User> listUsers();

	void delete(Integer id);

	void update(Integer id, User user);

    boolean findByLogin(String email1);

    List<User> findByName(String value);
}
