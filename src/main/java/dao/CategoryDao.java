package dao;

import entity.Category;
import utils.ConnectionManager;
import utils.StatementUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao implements Dao<Long, Category> {
    private static final CategoryDao INSTANCE = new CategoryDao();
    private static final String FIND_ALL = """
                        select category,category_name
                        from category
            """;
    private static final String FIND_BY_ID = """
                        select category,category_name
                        from category
                        where category=?
            """;
    private static final String SAVE = """
                        INSERT INTO category (category,category_name)
                        VALUES(?,?);
            """;
    private static final String UPDATE = """
                        update category
                        set category_name=?
                        where category=?
            """;
    private static final String DELETE = """
            delete from category
            where category=? 
            """;

    private CategoryDao() {
    }

    public static CategoryDao getInstance() {
        return INSTANCE;
    }

    private Category buildCategory(ResultSet result) throws SQLException {
        return new Category(
                result.getLong("category"),
                result.getString("category_name")
        );
    }

    @Override
    public boolean update(Category category) {
        try(var statement = StatementUtil.getStatement(UPDATE)) {
            statement.setString(1, category.getCategoryName());
            statement.setLong(2, category.getCategory());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAll() {
        try (var statement = StatementUtil.getStatement(FIND_ALL)) {
            List<Category> categories = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                categories.add(buildCategory(result));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Category> findById(Long id) {
        try (var statement = StatementUtil.getStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Category category = null;
            if (result.next()) {
                category = buildCategory(result);
            }
            return Optional.ofNullable(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category save(Category category) {
        try (var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1,category.getCategory());
            statement.setString(2, category.getCategoryName());
            statement.executeUpdate();

            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                category.setCategory(keys.getLong("category"));
            }
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var statement = StatementUtil.getStatement(DELETE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}