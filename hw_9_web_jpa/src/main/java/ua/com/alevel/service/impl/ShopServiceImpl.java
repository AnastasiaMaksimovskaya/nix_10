package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.ShopDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Shop;
import ua.com.alevel.service.ShopService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopDao shopDao;

    public ShopServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public void create(Shop entity) {
        shopDao.create(entity);
    }

    @Override
    public void update(Shop entity) {
        shopDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        shopDao.delete(id);
    }

    @Override
    public Shop findById(Long id) {
        return shopDao.findById(id);
    }

    @Override
    public DataTableResponse<Shop> findAll(DataTableRequest request) {
        DataTableResponse<Shop> dataTableResponse = shopDao.findAll(request);
        long count = shopDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllProductsByShopId(Long productId) {
        return shopDao.findAllProductsByShopId(productId);
    }
}
