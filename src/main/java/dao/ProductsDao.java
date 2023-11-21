package dao;

import entity.Products;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsDao implements Dao<Long, Products> {
    private static final ProductsDao INSTANCE = new ProductsDao();
    private static final String FIND_ALL = """
                        select
                        id,supplier_id,name,count,price_for_one,category_id
                        from products
            """;
    private static final String FIND_BY_ID = """
                        select
                        id,supplier_id,name,count,price_for_one,category_id
                        from products
                        where id=?
            """;
    private static final String SAVE = """
                        insert into products (supplier_id,name,count,price_for_one,category_id)
                        values(?,?,?,?,?);
            """;
    private static final String UPDATE = """
                        update products
                        set
                        supplier_id=?,
                        name=?,
                        count=?,
                        price_for_one=?,
                        category_id=?
                        where id=?
            """;
    private static final String DELETE = """
                        delete from products
                        where id=?
            """;

    private ProductsDao() {
    }

    public static ProductsDao getInstance() {
        return INSTANCE;
    }

    private Products buildProducts(ResultSet result) throws SQLException {
        return new Products(
                result.getLong("id"),
                result.getLong("supplier_id"),
                result.getString("name"),
                result.getLong("count"),
                result.getLong("price_for_one"),
                result.getLong("category_id")
        );
    }


    @Override
    public boolean update(Products products) {
        try (var statement = StatementUtil.getStatement(UPDATE)){
            statement.setLong(1, products.getSupplierId());
            statement.setString(2, products.getName());
            statement.setLong(3, products.getCount());
            statement.setLong(4, products.getPriceForOne());
            statement.setLong(5, products.getCategoryId());
            statement.setLong(6, products.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Products> findAll() {
        try(var statement = StatementUtil.getStatement(FIND_ALL)) {
             var result = statement.executeQuery();
             List<Products> products = new ArrayList<>();
             while (result.next()){
                 products.add(buildProducts(result));
             }
             return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Products> findById(Long id) {
        try(var statement = StatementUtil.getStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Products product = null;
            if (result.next()) {
                product = buildProducts(result);
            }
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Products save(Products products) {
        try(var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, products.getSupplierId());
            statement.setString(2, products.getName());
            statement.setLong(3, products.getCount());
            statement.setLong(4, products.getPriceForOne());
            statement.setLong(5, products.getCategoryId());
            statement.executeUpdate();

            var key = statement.getGeneratedKeys();
            if (key.next()) {
                products.setId(key.getLong("id"));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try(var statement = StatementUtil.getStatement(DELETE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
