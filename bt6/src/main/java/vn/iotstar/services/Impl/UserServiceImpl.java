package vn.iotstar.services.Impl;

import java.util.List;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.Impl.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void create(User user) {
		userDao.create(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public List<User> search(String keyword) {
		return userDao.search(keyword);
	}
}
