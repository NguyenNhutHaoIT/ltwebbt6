package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.User;

public interface UserDao {
    User login(String username, String password);
    User findById(int id);
    List<User> findAll();
    void create(User user);
    void update(User user);
    void delete(int id);
    List<User> search(String keyword);
}