package Entities;

public class Operario {
    private int id;
    private String nome;
    private String funcao;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Operario{id=" + id + ", nome='" + nome + "', funcao='" + funcao + "'}";
    }
}
