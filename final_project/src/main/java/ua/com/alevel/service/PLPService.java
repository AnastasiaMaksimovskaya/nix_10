package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.store.Cube;

import java.util.List;
import java.util.Map;

public interface PLPService {
    List<Cube> search(Map<String, Object> queryMap);
}
