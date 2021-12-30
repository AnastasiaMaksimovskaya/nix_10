package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
}
