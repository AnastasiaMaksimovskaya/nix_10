package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.entity.Shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public interface ShopRepository extends BaseRepository<Shop> {
}
