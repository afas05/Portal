package DBservice.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Игорь on 02.11.2016.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
