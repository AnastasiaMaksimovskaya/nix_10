package ua.com.alevel.persistence.repository.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface CubeRepository extends BaseRepository<Cube> {

    Page<Cube> findByBrandNameIn(List<String> names, Pageable pageable);
    Page<Cube> findByNameContaining(String cubeName, Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = "delete from shop_product where cube_id = :id",nativeQuery = true)
    void detachCubeFromShop(@Param("id") Long id);
}
