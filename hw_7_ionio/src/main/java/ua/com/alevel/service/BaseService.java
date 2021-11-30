package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.exception.ProductNotFoundException;
import ua.com.alevel.exception.ShopNotFoundException;

import java.util.List;

public interface BaseService<ENTITY extends BaseEntity> {
    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(String id);

    ENTITY findById(String id) throws ShopNotFoundException, ProductNotFoundException;

    List<ENTITY> findAll();
}