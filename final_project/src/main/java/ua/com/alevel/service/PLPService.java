package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Cube;

import java.util.Map;

public interface PLPService {
    DataTableResponse<Cube> search(Map<String, Object> queryMap,DataTableRequest dataTableRequest);
}
