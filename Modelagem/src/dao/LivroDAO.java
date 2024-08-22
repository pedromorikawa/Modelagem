package dao;

import entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO extends BaseDAO{

    public void inserir(Livro l){
        String sql = """
                insert into livro(nomelivro, ano, id_autor) values(?, ?, ?);
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,l.getNomelivro());
            pre.setInt(2,l.getAno());
            pre.setInt(3,l.getId_autor());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(int idlivro, Livro l){
        String sql = """
                update livro set nomelivro = ?, ano = ?, id_autor = ? where idlivro = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,l.getNomelivro());
            pre.setInt(2,l.getAno());
            pre.setInt(3,l.getId_autor());
            pre.setInt(4,idlivro);
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Livro getlivro(int idlivro) {
        String sql = """
                select * from livro where idlivro = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, idlivro);
            pre.execute();
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Livro livro = new Livro(rs.getInt("idlivro"), rs.getString("nomelivro"), rs.getInt("ano"), rs.getInt("id_autor"));
                    return livro;
                } else {
                    System.out.println("Livro não encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Livro l){
        String sql = """
                delete from livro where idlivro = ?;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,l.getIdlivro());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Livro> obterTodos(){
        List<Livro> lista = new ArrayList<>();
        String sql = """
                select idlivro, nomelivro, ano, id_autor from livro
                order by nomelivro asc;
                """;
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Livro livro = new Livro(rs.getInt("idlivro"),rs.getString("nomelivro"),rs.getInt("ano"),rs.getInt("id_autor"));
                lista.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return lista;
}

public List<Livro> ListaPorAutor(int id_autor){
        String sql = """
                select * from livro where id_autor = ?;
                """;
        List<Livro> Livros = new ArrayList<>();
    try(Connection con = con();
        PreparedStatement pre = con.prepareStatement(sql)) {
        pre.setInt(1,id_autor);
        ResultSet rs = pre.executeQuery();
        while(rs.next()){
            Livro livro = new Livro(rs.getInt("idlivro"),rs.getString("nomelivro"),rs.getInt("ano"),rs.getInt("id_autor"));
            Livros.add(livro);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return Livros;
    }
}