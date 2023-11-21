package dao;

import entity.Category;
import entity.Employees;
import utils.ConnectionManager;
import utils.StatementUtil;

import javax.swing.event.ListDataEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmployeesDao implements Dao<Long, Employees> {
    private static final EmployeesDao INSTANCE = new EmployeesDao();
    private static final String FIND_ALL = """
                        select
                         id,last_name,name,middle_name,date_birth,phone_number,address,rank_id
                        from employees
            """;
    private static final String FIND_BY_ID = """
                        select
                         id,last_name,name,middle_name,date_birth,phone_number,address,rank_id
                        from employees
                        where id=?
            """;
    private static final String SAVE = """
                        insert into employees (last_name,name,middle_name,date_birth,phone_number,address,rank_id)
                        values(?,?,?,?,?,?,?)
            """;
    private static final String UPDATE = """
                        update employees
                        set
                        last_name=?,
                        name=?,
                        middle_name=?,
                        date_birth=?,
                        phone_number=?,
                        address=?,
                        rank_id=?
                        where id=?
            """;
    private static final String DELETE = """
                        delete from employees
                        where id=?
            """;

    private EmployeesDao() {
    }

    public static EmployeesDao getInstance() {
        return INSTANCE;
    }

    private Employees buildEmployee(ResultSet result) throws SQLException {
        return new Employees(
                result.getLong("id"),
                result.getString("last_name"),
                result.getString("name"),
                result.getString("middle_name"),
                (result.getTimestamp("date_birth")).toLocalDateTime().toLocalDate(),
                result.getString("phone_number"),
                result.getString("address"),
                result.getLong("rank_id")
        );

    }

    @Override
    public boolean update(Employees employees) {
        try(var statement = StatementUtil.getStatement(UPDATE)) {
            statement.setString(1, employees.getLastName());
            statement.setString(2, employees.getName());
            statement.setString(3, employees.getMiddleName());
            statement.setObject(4, employees.getDateBirth());
            statement.setString(5, employees.getPhoneNumber());
            statement.setString(6, employees.getAddress());
            statement.setLong(7, employees.getRankId());
            statement.setLong(8, employees.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employees> findAll() {
        try (var statement = StatementUtil.getStatement(FIND_ALL)) {
            List<Employees> employees = new ArrayList<>();
            var result = statement.executeQuery();

            while (result.next()) {
                employees.add(buildEmployee(result));
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employees> findById(Long id) {
        try(var statement = StatementUtil.getStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Employees employee = null;
            if (result.next()) {
                employee = buildEmployee(result);
            }
            return Optional.ofNullable(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employees save(Employees employee) {
        try(var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getMiddleName());
            statement.setObject(4, employee.getDateBirth());
            statement.setString(5, employee.getPhoneNumber());
            statement.setString(6, employee.getAddress());
            statement.setLong(7, employee.getRankId());
            statement.executeUpdate();

            var key = statement.getGeneratedKeys();
            if (key.next()) {
                employee.setId(key.getLong("id"));
            }
            return employee;
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
