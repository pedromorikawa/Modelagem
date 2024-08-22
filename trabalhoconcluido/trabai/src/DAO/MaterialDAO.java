package DAO;

import Entities.Material;
import Interfaces.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MaterialDAO implements IDAO<Material> {
    private Connection connection;

    public MaterialDAO() {
        this.connection = ConexaoBD.getInstancia().getConexao();
    }

    @Override
    public void inserir(Material material) throws SQLException {
        String sql = "INSERT INTO materiais (nome, quantidade) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, material.getNome());
            ps.setInt(2, material.getQuantidade());
            ps.executeUpdate();
        }
    }

    @Override
    public void atualizar(Material material) throws SQLException {
        String sql = "UPDATE materiais SET nome = ?, quantidade = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, material.getNome());
            ps.setInt(2, material.getQuantidade());
            ps.setInt(3, material.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM materiais WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Material listarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM materiais WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setNome(rs.getString("nome"));
                material.setQuantidade(rs.getInt("quantidade"));
                return material;
            }
        }
        return null;
    }

    @Override
    public List<Material> listarTodos() throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM materiais";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setNome(rs.getString("nome"));
                material.setQuantidade(rs.getInt("quantidade"));
                materiais.add(material);
            }
        }
        return materiais;
    }
}
