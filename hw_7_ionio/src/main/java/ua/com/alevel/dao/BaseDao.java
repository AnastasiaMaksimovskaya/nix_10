package ua.com.alevel.dao;

import ua.com.alevel.service.ProductNotFoundException;
import ua.com.alevel.service.ShopNotFoundException;
import ua.com.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id) throws ShopNotFoundException, ProductNotFoundException;
    List<ENTITY> findAll();
}