package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.store.Brand;

import java.util.List;

public interface BrandFacade {
    List<Brand> findAll();
}
