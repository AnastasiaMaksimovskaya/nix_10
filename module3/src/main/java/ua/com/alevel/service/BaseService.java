package ua.com.alevel.service;

import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.datatable.DataTableRequest;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(Long id);

    ENTITY findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);
}
