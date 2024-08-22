package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableDAO {
    private Connection connection;

    public TableDAO() {
        this.connection = ConexaoBD.getInstancia().getConexao();
    }
    public void inserirTabelas() throws SQLException{
        inserirTblEngenheiros();
        inserirTblMateriais();
        inserirTblOperarios();
        inserirTblEquipamentos();
        inserirTblProjetos();
        inserirTblAlocacao_Operario();
        inserirTblConsumo_Material();
        inserirTblUso_Equipamento();
        inserirTblAlocacao_Engenheiro();
    }
    public void inserirTblEngenheiros() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS engenheiros (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            especialidade TEXT NOT NULL);
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblEquipamentos() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS equipamentos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    tipo TEXT NOT NULL);
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblMateriais() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS materiais (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    quantidade INTEGER NOT NULL
                );
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblOperarios() throws SQLException {
        String sql = """
                    CREATE TABLE IF NOT EXISTS operarios (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nome TEXT NOT NULL,
                            funcao TEXT NOT NULL
                    );
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblProjetos() throws SQLException {
        String sql = """
                    CREATE TABLE IF NOT EXISTS projetos (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nome TEXT NOT NULL,
                            descricao TEXT NOT NULL,
                            local TEXT NOT NULL,
                            data_inicio DATE NOT NULL,
                            data_termino DATE
                    );
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblAlocacao_Engenheiro() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS alocacao_engenheiro (
                id_projeto INTEGER,
                id_engenheiro INTEGER,
                PRIMARY KEY (id_projeto, id_engenheiro),
                FOREIGN KEY (id_projeto) REFERENCES projeto (id) ON DELETE CASCADE,
                FOREIGN KEY (id_engenheiro) REFERENCES engenheiro (id) ON DELETE CASCADE);
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblAlocacao_Operario() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS alocacao_operario (
                    id_projeto INTEGER,
                    id_operario INTEGER,
                    PRIMARY KEY (id_projeto, id_operario),
                    FOREIGN KEY (id_projeto) REFERENCES projeto (id) ON DELETE CASCADE,
                    FOREIGN KEY (id_operario) REFERENCES operario (id) ON DELETE CASCADE
                );
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblUso_Equipamento() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS uso_equipamento (
                    id_projeto INTEGER,
                    id_equipamento INTEGER,
                    PRIMARY KEY (id_projeto, id_equipamento),
                    FOREIGN KEY (id_projeto) REFERENCES projeto (id) ON DELETE CASCADE,
                    FOREIGN KEY (id_equipamento) REFERENCES equipamento (id) ON DELETE CASCADE
                );
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
    public void inserirTblConsumo_Material() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS consumo_material (
                    id_projeto INTEGER,
                    id_material INTEGER,
                    quantidade_consumida INTEGER NOT NULL,
                    PRIMARY KEY (id_projeto, id_material),
                    FOREIGN KEY (id_projeto) REFERENCES projeto (id) ON DELETE CASCADE,
                    FOREIGN KEY (id_material) REFERENCES material (id) ON DELETE CASCADE
                );
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

}
