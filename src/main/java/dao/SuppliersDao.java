package dao;

import entity.Category;
import entity.Suppliers;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuppliersDao implements Dao<Long, Suppliers> {
    private static final SuppliersDao INSTANCE = new SuppliersDao();

    private static final String FIND_ALL = """
                        select
                        id,name,address,e_mail,phone_number
                        from suppliers
            """;
    private static final String FIND_BY_ID = """
                        select
                        id,name,address,e_mail,phone_number
                        from suppliers
                        where id = ?
            """;
    private static final String SAVE = """
                        insert into suppliers
                        (name,address,e_mail,phone_number)
                        values(?,?,?,?);
            """;
    private static final String UPDATE = """
                        update suppliers
                        set name =?,
                        address=?,
                        e_mail=?,
                       phone_number=?
                       where id = ?
            """;
    private static final String DELETE = """
                        delete from suppliers
                        where id =?
            """;

    private SuppliersDao() {
    }

    public static SuppliersDao getInstance() {
        return INSTANCE;
    }

    private Suppliers buildSupplier(ResultSet result) throws SQLException {
        return new Suppliers(
                result.getLong("id"),
                result.getString("name"),
                result.getString("address"),
                result.getString("e_mail"),
                result.getString("phone_number")
        );
    }

    @Override
    public boolean update(Suppliers suppliers) {
        try(var statement = StatementUtil.getStatement(UPDATE)) {
            statement.setString(1, suppliers.getName());
            statement.setString(2, suppliers.getAddress());
            statement.setString(3, suppliers.getEmail());
            statement.setString(4, suppliers.getPhoneNumber());
            statement.setLong(5, suppliers.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Suppliers> findAll() {
        try (var statement = StatementUtil.getStatement(FIND_ALL)){
            List<Suppliers> suppliers = new ArrayList<>();
            var results = statement.executeQuery();
            while (results.next()) {
                suppliers.add(buildSupplier(results));
            }
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Suppliers> findById(Long id) {
        try ( var statement = StatementUtil.getStatement(FIND_BY_ID)){
            statement.setLong(1, id);
            Suppliers supplier = null;
            var result = statement.executeQuery();
            if (result.next()) {
                supplier = buildSupplier(result);
            }
            return Optional.ofNullable(supplier);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Suppliers save(Suppliers suppliers) {
        try(var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, suppliers.getName());
            statement.setString(2, suppliers.getAddress());
            statement.setString(3, suppliers.getEmail());
            statement.setString(4, suppliers.getPhoneNumber());
            statement.executeUpdate();

            var key = statement.getGeneratedKeys();
            if (key.next()) {
                suppliers.setId(key.getLong("id"));
            }
            return suppliers;
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
