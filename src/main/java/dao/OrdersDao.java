package dao;

import entity.Category;
import entity.Orders;
import utils.ConnectionManager;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDao implements Dao<Long, Orders> {
    private static final OrdersDao INSTANCE = new OrdersDao();
    private static final String FIND_ALL = """
                        select
                        id,supplier_id,name_product,count_product,price_product,date_order
                        from orders
            """;
    private static final String FIND_BY_ID = """
                        select
                        id,supplier_id,name_product,count_product,price_product,date_order
                        from orders
                        where id=?
            """;
    private static final String SAVE = """
                        insert into orders (supplier_id,name_product,count_product,price_product,date_order)
                        values(?,?,?,?,?);
            """;
    private static final String UPDATE = """
                        update orders
                        set
                        supplier_id=?,
                        name_product=?,
                        count_product=?,
                        price_product=?,
                        date_order=?
                        where id=?
            """;
    private static final String DELETE = """
                        delete from orders
                        where id=?
            """;

    private OrdersDao() {
    }

    public static OrdersDao getInstance() {
        return INSTANCE;
    }

//    private Orders buildOrder(ResultSet result) throws SQLException {
//        return new Orders(
//                result.getLong("id"),
//                result.getLong("supplier_id"),
//                result.getString("name_product"),
//                result.getLong("count_product"),
//                result.getLong("price_product"),
//                (result.getTimestamp("date_order")).toLocalDateTime().toLocalDate()
//        );
//
//    }

    @Override
    public boolean update(Orders orders) {
//        try(var statement = StatementUtil.getStatement(UPDATE)) {
//            statement.setLong(1, orders.getSupplierId());
//            statement.setString(2, orders.getNameProduct());
//            statement.setLong(3, orders.getCountProduct());
//            statement.setLong(4, orders.getPriceProduct());
//            statement.setObject(5, orders.getDateOrder());
//            statement.setLong(6, orders.getId());
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }

    @Override
    public List<Orders> findAll() {

        return null;
    }

    @Override
    public Optional<Orders> findById(Long id) {
//        try(var statement = StatementUtil.getStatement(FIND_BY_ID)) {
//            statement.setLong(1, id);
//            var result = statement.executeQuery();
//            Orders order = null;
//            if (result.next()) {
//                order = buildOrder(result);
//            }
//            return Optional.ofNullable(order);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public Orders save(Orders orders) {
//        try(var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setLong(1, orders.getSupplierId());
//            statement.setString(2, orders.getNameProduct());
//            statement.setLong(3, orders.getCountProduct());
//            statement.setLong(4, orders.getPriceProduct());
//            statement.setObject(5, orders.getDateOrder());
//            statement.executeUpdate();
//
//            var key = statement.getGeneratedKeys();
//            if (key.next()) {
//                orders.setId(key.getLong("id"));
//            }
//            return orders;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
//        try( var statement = StatementUtil.getStatement(DELETE)) {
//            statement.setLong(1, id);
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }
}
