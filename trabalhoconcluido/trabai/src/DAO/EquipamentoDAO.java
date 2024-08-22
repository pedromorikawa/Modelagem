package DAO;

import DAO.ConexaoBD;
import Entities.Equipamento;
import Interfaces.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO implements IDAO<Equipamento> {
    private Connection connection;

    public EquipamentoDAO() {
        this.connection = ConexaoBD.getInstancia().getConexao();
    }

    @Override
    public void inserir(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO equipamentos (nome, tipo) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, equipamento.getNome());
            ps.setString(2, equipamento.getTipo());
            ps.executeUpdate();
        }
    }

    @Override
    public void atualizar(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE equipamentos SET nome = ?, tipo = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, equipamento.getNome());
            ps.setString(2, equipamento.getTipo());
            ps.setInt(3, equipamento.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM equipamentos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Equipamento listarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM equipamentos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rs.getInt("id"));
                equipamento.setNome(rs.getString("nome"));
                equipamento.setTipo(rs.getString("tipo"));
                return equipamento;
            }
        }
        return null;
    }

    @Override
    public List<Equipamento> listarTodos() throws SQLException {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM equipamentos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rs.getInt("id"));
                equipamento.setNome(rs.getString("nome"));
                equipamento.setTipo(rs.getString("tipo"));
                equipamentos.add(equipamento);
            }
        }
        return equipamentos;
    }
}
