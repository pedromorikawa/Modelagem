package dao;

import entity.Autor;
import entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO extends BaseDAO{

    public void inserir (Autor a){
        String sql = """
                insert into autor(nome, nacionalidade) values(?, ?);
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,a.getNome());
            pre.setString(2,a.getNacionalidade());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(int id_autor, Autor a){
        String sql = """
                update autor set nome = ?, nacionalidade = ? where id_autor = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,a.getNome());
            pre.setString(2,a.getNacionalidade());
            pre.setInt(3,id_autor);
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Autor getAutor(int id_autor) {
        String sql = """
                select * from autor where id_autor = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id_autor);
            pre.execute();
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor(rs.getInt("id_autor"),rs.getString("nome"),rs.getString("nacionalidade"));
                    return autor;
                } else {
                    System.out.println("Autor não encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Autor a){
        String sql = """
                delete from autor where id_autor = ?;
                """;
        try(Connection con = con();
        PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,a.getId_autor());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Autor> obterTodos(){
        List<Autor> lista = new ArrayList<>();
        String sql = """
                select id_autor, nome, nacionalidade from autor
                order by nome asc;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getInt("id_autor"),rs.getString("nome"),rs.getString("nacionalidade"));
                lista.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}