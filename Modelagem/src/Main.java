import dao.AutorDAO;
import entity.Autor;
import dao.LivroDAO;
import entity.Livro;

import java.sql.*;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        AutorDAO autordao = new AutorDAO();
        LivroDAO livrodao = new LivroDAO();

        String url = "jdbc:sqlite:meu_banco.db";
        try (Connection con = DriverManager.getConnection(url)) {
            String sqlAutor = """
                CREATE TABLE IF NOT EXISTS autor (
                    id_autor INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    nacionalidade TEXT NOT NULL
                );
                """;

            String sqlLivro = """
                CREATE TABLE IF NOT EXISTS livro (
                    idlivro INTEGER PRIMARY KEY AUTOINCREMENT,
                    nomelivro TEXT NOT NULL,
                    ano INTEGER NOT NULL,
                    id_autor INTEGER,
                    FOREIGN KEY (id_autor) REFERENCES autor(id_autor));
                """;
            try (Statement pre = con.createStatement()) {
                pre.execute(sqlLivro);
                pre.execute(sqlAutor);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        Autor autor = new Autor("Rafaela","brasileira");
        autordao.inserir(autor);
        Autor autoratualizar = new Autor("Rafa","Brasil");
        autordao.atualizar(1,autoratualizar);
        Autor autordeletar = autordao.getAutor(1);
        autordao.excluir(autordeletar);
        List<Autor> autortodos = autordao.obterTodos();
        System.out.println(autortodos);

        Livro livro = new Livro(2,"1984",1984,20);
        livrodao.inserir(livro);
        Livro livroatualizar = new Livro(2,"é assim que acaba",2018,10);
        livrodao.atualizar(2,livroatualizar);
        Livro livrodeletar = livrodao.getlivro(1);
        livrodao.excluir(livrodeletar);
        List<Livro> livrotodos = livrodao.obterTodos();
        System.out.println(livrotodos);
        List<Livro> livropautor = livrodao.ListaPorAutor(20);
        System.out.println(livropautor);
    }
}




