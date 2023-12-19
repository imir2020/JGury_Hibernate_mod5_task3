package dao;

import entity.Category;
import entity.Sales;
import utils.ConnectionManager;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalesDao implements Dao<Long, Sales> {
    private static final SalesDao INSTANCE = new SalesDao();
    private static final String FIND_ALL = """
                        select
                        id,product_id,count,employee_id,date_sales
                        from sales
            """;
    private static final String FIND_BY_ID = """
                        select
                        id,product_id,count,employee_id,date_sales
                        from sales
                        where id=?
            """;
    private static final String SAVE = """
                        insert into sales (product_id,count,employee_id,date_sales)
                        values(?,?,?,?);
            """;
    private static final String UPDATE = """
                        update sales
                        set
                        product_id=?,
                        count=?,
                        employee_id=?,
                        date_sales=?
                        where id=?
            """;
    private static final String DELETE = """
                     delete from sales
                     where id=?   
            """;

    private SalesDao() {
    }

    public static SalesDao getInstance() {
        return INSTANCE;
    }

//    private Sales buildSales(ResultSet result) throws SQLException {
//        return new Sales(
//                result.getLong("id"),
//                result.getLong("product_id"),
//                result.getLong("count"),
//                result.getLong("employee_id"),
//                (result.getTimestamp("date_sales")).toLocalDateTime().toLocalDate()
//        );
//    }

    @Override
    public boolean update(Sales sale) {
//        try (var statement = StatementUtil.getStatement(UPDATE)) {
//            statement.setLong(1, sale.getProductId());
//            statement.setLong(2, sale.getCount());
//            statement.setLong(3, sale.getEmployeeId());
//            statement.setObject(4, sale.getDateSales());
//            statement.setLong(5, sale.getId());
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }

    @Override
    public List<Sales> findAll() {
        return null;
    }

    @Override
    public Optional<Sales> findById(Long id) {
//        try (var statement = StatementUtil.getStatement(FIND_BY_ID)) {
//            statement.setLong(1, id);
//            var result = statement.executeQuery();
//            Sales sale = null;
//            if (result.next()) {
//                sale = buildSales(result);
//            }
//            return Optional.ofNullable(sale);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        return null;
    }

    @Override
    public Sales save(Sales sale) {
//        try (var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
//
//            statement.setLong(1, sale.getProductId());
//            statement.setLong(2, sale.getCount());
//            statement.setLong(3, sale.getEmployeeId());
//            statement.setObject(4, sale.getDateSales());
//            statement.executeUpdate();
//
//            var key = statement.getGeneratedKeys();
//            if (key.next()) {
//                sale.setId(key.getLong("id"));
//            }
//            return sale;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    return null;
    }

    @Override
    public boolean delete(Long id) {
//        try (var statement = StatementUtil.getStatement(DELETE)) {
//            statement.setLong(1, id);
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        return false;
    }
}
