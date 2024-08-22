package dao;

import java.sql.Connection;
import java.sql.SQLException;

class BaseDAO {
    // DAO -> Data Access Object

    protected Connection con() throws SQLException {
        return ConexaoDB.getInstancia().getConnection();
    }
}
