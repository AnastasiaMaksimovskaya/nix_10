package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.ShopDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Shop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ShopDaoImpl implements ShopDao {

    JpaConfig jpaConfig;

    ShopDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }
    private static final String CREATE_SHOP_QUERY = "insert into shops values(default, ?,?,?,?,?)";
    private static final String UPDATE_SHOP_QUERY = "update shops set shop_name = ?,updated = ? where id = ";
    private static final String DELETE_SHOP_QUERY = "delete from shops where id = ";
    private final static String EXIST_SHOP_BY_ID_QUERY = "select count(*) from shops where id = ";
    private final static String FIND_SHOP_BY_ID_QUERY = "select * from shops as sh left join product_shop as p on sh.id = p.shop_id where sh.id = ";
    private final static String FIND_ALL_SHOPS_QUERY = "select * from shops";
    private final static String FIND_ALL_SHOPS_BY_PRODUCT_QUERY = "select * from shops as sh left join product_shop as psh on sh.id = psh.shop_id where psh.product_id = ";
    private final static String DELETE_PRODUCTS_AFTER_DELETING_SHOP = "delete p from products as p left join product_shop as psh on p.id = psh.product_id where psh.product_id IS NULL";





    @Override
    public void create(Shop entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_SHOP_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getAddress());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shop entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_SHOP_QUERY+entity.getId())) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_SHOP_QUERY + id)) {
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_PRODUCTS_AFTER_DELETING_SHOP)) {
            System.out.println("ShopDaoImpl.delete");
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_SHOP_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Shop findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_SHOP_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return convertResultSetToShop(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Shop> findAll(DataTableRequest request) {
        List<Shop> shops = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SHOPS_QUERY)) {
            while (resultSet.next()) {
                shops.add(convertResultSetToShop(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Shop> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(shops);
        return dataTableResponse;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<Long, String> findAllByProductId(Long productId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SHOPS_BY_PRODUCT_QUERY + productId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("shop_name");
                map.put(id, name);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    private Shop convertResultSetToShop(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String shopName = resultSet.getString("shop_name");
        String address = resultSet.getString("address");
        Shop shop = new Shop();
        shop.setId(id);
        shop.setName(shopName);
        shop.setAddress(address);
        shop.setVisible(visible);
        shop.setCreated(new Date(created.getTime()));
        shop.setUpdated(new Date(updated.getTime()));
        return shop;
    }
}
