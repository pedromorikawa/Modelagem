package entity;

public class Livro {
    private int idlivro;
    private String nomelivro;
    private int ano;
    private int id_autor;

    public Livro(int idlivro, String nomelivro, int ano, int id_autor) {
        this.idlivro = idlivro;
        this.nomelivro = nomelivro;
        this.ano = ano;
        this.id_autor = id_autor;
    }

    public int getIdlivro(){
        return idlivro;
    }

    public void setIdlivro(int idlivro) {
        this.idlivro = idlivro;
    }

    public String getNomelivro(){
        return nomelivro;
    }

    public void setNomelivro(String nomelivro){
        this.nomelivro = nomelivro;
    }

    public int getAno(){
        return ano;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor){
        this.id_autor = id_autor;
    }

    @Override
    public String toString(){
        return "Livro{" + "id_livro= " + idlivro + ", nome do livro= " + nomelivro + ", ano de publicação= " + ano + ", id_autor= " + id_autor + '}';
    }
}
