package DAO;

import Entities.Engenheiro;
import Entities.Projeto;
import Interfaces.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO implements IDAO<Engenheiro> {
    private Connection connection;

    public EngenheiroDAO(){
        this.connection = ConexaoBD.getInstancia().getConexao();
    }
    public void inserir(Engenheiro engenheiro) throws SQLException{
        String sql = "INSERT INTO engenheiros (nome, especialidade) VALUES (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, engenheiro.getNome());
            ps.setString(2, engenheiro.getEspecialidade());
            ps.executeUpdate();
        }
    }

    public void atualizar(Engenheiro engenheiro) throws SQLException {
        String sql = "UPDATE engenheiros SET nome = ?, especialidade = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, engenheiro.getNome());
            ps.setString(2, engenheiro.getEspecialidade());
            ps.setInt(3, engenheiro.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM engenheiros WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Engenheiro listarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM engenheiros WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setId(rs.getInt("id"));
                engenheiro.setNome(rs.getString("nome"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));
                return engenheiro;
            }
        }
        return null;
    }

    public List<Engenheiro> listarTodos() throws SQLException {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM engenheiros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setId(rs.getInt("id"));
                engenheiro.setNome(rs.getString("nome"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));
                engenheiros.add(engenheiro);
            }
        }
        return engenheiros;
    }

}
