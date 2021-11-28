package ua.com.alevel.service;

import ua.com.alevel.ProductNotFoundException;
import ua.com.alevel.ShopNotFoundException;
import ua.com.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseService<ENTITY extends BaseEntity> {
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(String id);
    ENTITY findById(String id) throws ShopNotFoundException, ProductNotFoundException;
    List<ENTITY> findAll();
}