package dao;

import entity.Category;
import entity.Ranks;
import utils.ConnectionManager;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RanksDao implements Dao<Long, Ranks> {
    private static final RanksDao INSTANCE = new RanksDao();
    private static final String FIND_ALL = """
                        select
                        id,rank_name,salary
                        from ranks
            """;
    private static final String FIND_BY_ID = """
                        select
                        id,rank_name,salary
                        from ranks
                        where id=?
            """;
    private static final String SAVE = """
                        insert into ranks (rank_name,salary)
                        values(?,?);
            """;
    private static final String UPDATE = """
                        update ranks
                        set
                        rank_name=?,
                        salary=?
                        where id=?
            """;
    private static final String DELETE = """
                        delete from ranks
                        where id=?
            """;

    private RanksDao() {
    }

    public static RanksDao getInstance() {
        return INSTANCE;
    }

    private Ranks buildRank(ResultSet result) throws SQLException {
        return new Ranks(
                result.getLong("id"),
                result.getString("rank_name"),
                result.getLong("salary")
        );
    }

    @Override
    public boolean update(Ranks ranks) {
//        try(var statement = StatementUtil.getStatement(UPDATE)) {
//            statement.setString(1, ranks.getRankName());
//            statement.setLong(2, ranks.getSalary());
//            statement.setLong(3, ranks.getId());
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }

    @Override
    public List<Ranks> findAll() {
//        try(var statement = StatementUtil.getStatement(FIND_ALL)) {
//            List<Ranks> ranks = new ArrayList<>();
//            var result = statement.executeQuery();
//            while (result.next()) {
//                ranks.add(buildRank(result));
//            }
//            return ranks;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public Optional<Ranks> findById(Long id) {
//        try(var statement = StatementUtil.getStatement(FIND_BY_ID)) {
//            statement.setLong(1,id);
//            var result = statement.executeQuery();
//            Ranks rank = null;
//            if (result.next()) {
//                rank = buildRank(result);
//            }
//            return Optional.ofNullable(rank);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public Ranks save(Ranks rank) {
//        try(var statement = StatementUtil.getStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, rank.getRankName());
//            statement.setLong(2, rank.getSalary());
//            statement.executeUpdate();
//
//            var key = statement.getGeneratedKeys();
//            if (key.next()) {
//                rank.setId(key.getLong("id"));
//            }
//            return rank;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
//        try(var statement = StatementUtil.getStatement(DELETE)) {
//            statement.setLong(1, id);
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }
}
