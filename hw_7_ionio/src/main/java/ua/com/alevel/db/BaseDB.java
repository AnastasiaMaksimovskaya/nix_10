package ua.com.alevel.db;

import ua.com.alevel.exception.ProductNotFoundException;
import ua.com.alevel.exception.ShopNotFoundException;
import ua.com.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(String id);

    ENTITY findById(String id) throws ProductNotFoundException, ShopNotFoundException;

    List<ENTITY> findAll();
}