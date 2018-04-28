package service;

import entity.User;

import java.util.List;

/**
 * Created by UserPage on 4/22/2018.
 */
public interface UserService {

    void createUser(User user);

    List<User> findAll();

    User findUser(Integer id);

    void deleteUser(Integer id);
}
