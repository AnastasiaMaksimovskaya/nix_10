package ua.com.alevel.persistence.repository.store;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface CubeRepository extends BaseRepository<Cube> {
    List<Cube> findByBrand(Brand brand);
    List<Cube> findByNameContaining(String cubeName);
}
