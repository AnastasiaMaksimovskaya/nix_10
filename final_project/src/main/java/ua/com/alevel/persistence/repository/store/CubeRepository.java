package ua.com.alevel.persistence.repository.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface CubeRepository extends BaseRepository<Cube> {

    @Query(value = "select c from Cube as c join Brand as b on c.brand.id = b.id where b.name in :brandNames")
    List<Cube> findByBrandNames(@Param("brandNames") List<String> brandNames);
    Page<Cube> findByBrandNameIn(List<String> names, Pageable pageable);
    List<Cube> findByBrandName(String brand);
    List<Cube> findByNameContaining(String cubeName);
    Page<Cube> findByNameContaining(String cubeName, Pageable pageable);
}
