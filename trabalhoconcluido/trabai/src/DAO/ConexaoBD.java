package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConexaoBD {
        private static ConexaoBD instancia;
        private Connection conexao;
        private static final String URL = "jdbc:sqlite:mydb.db";

        private ConexaoBD() {
            try {
                conexao = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static synchronized ConexaoBD getInstancia() {
            if (instancia == null) {
                instancia = new ConexaoBD();
            }
            return instancia;
        }

        public Connection getConexao() {
            return conexao;
        }
    }
