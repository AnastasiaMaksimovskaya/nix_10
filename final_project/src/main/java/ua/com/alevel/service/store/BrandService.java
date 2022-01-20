package ua.com.alevel.service.store;

import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface BrandService extends BaseService<Brand> {
    List<Brand> findAll();
}
