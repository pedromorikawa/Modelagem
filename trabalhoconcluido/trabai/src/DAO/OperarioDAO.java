package DAO;

import Entities.Operario;
import Interfaces.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO implements IDAO<Operario> {
    private Connection connection;

    public OperarioDAO() {
        this.connection = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Operario operario) throws SQLException {
        String sql = "INSERT INTO operarios (nome, funcao) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, operario.getNome());
            ps.setString(2, operario.getFuncao());
            ps.executeUpdate();
        }
    }

    public void atualizar(Operario operario) throws SQLException {
        String sql = "UPDATE operarios SET nome = ?, funcao = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, operario.getNome());
            ps.setString(2, operario.getFuncao());
            ps.setInt(3, operario.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM operarios WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Operario listarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM operarios WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Operario operario = new Operario();
                operario.setId(rs.getInt("id"));
                operario.setNome(rs.getString("nome"));
                operario.setFuncao(rs.getString("funcao"));
                return operario;
            }
        }
        return null;
    }

    public List<Operario> listarTodos() throws SQLException {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM operarios";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Operario operario = new Operario();
                operario.setId(rs.getInt("id"));
                operario.setNome(rs.getString("nome"));
                operario.setFuncao(rs.getString("funcao"));
                operarios.add(operario);
            }
        }
        return operarios;
    }
}
