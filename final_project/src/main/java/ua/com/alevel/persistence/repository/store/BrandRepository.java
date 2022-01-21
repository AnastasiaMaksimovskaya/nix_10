package ua.com.alevel.persistence.repository.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.tmp.BrandTmp;

import java.util.List;

@Repository
public interface BrandRepository extends BaseRepository<Brand> {

    @Query("select new ua.com.alevel.persistence.tmp.BrandTmp(b.id, b.name) from Brand b")
    List<BrandTmp> findAllBrandTmp();
}
