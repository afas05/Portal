package DBservice.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by Игорь on 02.11.2016.
 */
public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void exectUpd(String upd) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.execute(upd);
        stmt.close();
    }

    public <T> T exectQuery(String query, ResultHandler<T> handler) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet resultSet = stmt.getResultSet();
        T value = handler.handle(resultSet);
        resultSet.close();
        stmt.close();

        return value;
    }
}
