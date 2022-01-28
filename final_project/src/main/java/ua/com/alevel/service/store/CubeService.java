package ua.com.alevel.service.store;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.BaseService;

import java.util.Map;

@Service
public interface CubeService extends BaseService<Cube> {
    Map<Long, String> findAllShopsByProductId(Long productId);
}
