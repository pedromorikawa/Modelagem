package Entities;

public class Engenheiro {
    private int id;
    private String nome;
    private String especialidade;

    // Getters e Setters
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Engenheiro{id=" + id + ", nome='" + nome + "', especialidade='" + especialidade + "'}";
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
