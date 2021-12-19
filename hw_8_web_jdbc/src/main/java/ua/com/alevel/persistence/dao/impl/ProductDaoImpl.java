package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ProductDaoImpl implements ProductDao {

    JpaConfig jpaConfig;

    ProductDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private static final String CREATE_PRODUCT_QUERY = "insert into products values(default, ?,?,?,?,?,?)";
    private static final String UPDATE_PRODUCT_QUERY = "update products set price = ?,updated = ? where id = ";
    private static final String DELETE_PRODUCT_QUERY = "delete from products where id = ";
    private final static String EXIST_PRODUCT_BY_ID_QUERY = "select count(*) from products where id = ";
    private final static String FIND_ALL_PRODUCTS_COUNT = "select count(*) from products";
    private final static String FIND_PRODUCT_BY_ID_QUERY = "select *,count(shop_id) as shopCount from products left join product_shop ps on products.id = ps.product_id where id = ";
    private final static String FIND_ALL_PRODUCTS_QUERY = "select *,count(shop_id) as shopCount from products left join product_shop ps on products.id = ps.product_id group by products.id order by ";
    private final static String FIND_ALL_PRODUCT_BY_SHOP_QUERY = "select * from products as p left join product_shop as psh on p.id = psh.product_id where psh.shop_id = ";

    @Override
    public void create(Product entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_PRODUCT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getBrand());
            preparedStatement.setInt(6, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_PRODUCT_QUERY + entity.getId())) {
            preparedStatement.setInt(1, entity.getPrice());
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_PRODUCT_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_PRODUCT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Product findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_PRODUCT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return convertResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product convertResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String productName = resultSet.getString("product_name");
        String brand = resultSet.getString("brand");
        Integer price = resultSet.getInt("price");
        Integer count = resultSet.getInt("shopCount");
        product.setId(id);
        product.setName(productName);
        product.setBrand(brand);
        product.setPrice(price);
        product.setShopCount(count);
        product.setVisible(visible);
        product.setCreated(new Date(created.getTime()));
        product.setUpdated(new Date(updated.getTime()));
        return product;
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        List<Product> products = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sqlFindWithParams = FIND_ALL_PRODUCTS_QUERY +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sqlFindWithParams)) {
            while (resultSet.next()) {
                products.add(convertResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Product> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(products);
        return dataTableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PRODUCTS_COUNT)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Map<Long, String> findAllByShopId(Long shopId) {
        Map<Long, String> map = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PRODUCT_BY_SHOP_QUERY + shopId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("product_name");
                map.put(id, name);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    public void createRelationship(Product p, List<Integer> shopsId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("insert into product_shop values (LAST_INSERT_ID(),?)")) {
            for (Integer integer : shopsId) {
                preparedStatement.setLong(1, integer);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
