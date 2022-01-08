package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.UserDao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        DataTableResponse<User> dataTableResponse = userDao.findAll(request);
        long count = userDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        return userDao.findAllAccountsByUserId(userId);
    }
}
