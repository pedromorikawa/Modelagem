package Entities;

public class Equipamento {
    private int id;
    private String nome;
    private String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return "Equipamento {id=" + id + ", nome='" + nome + "', tipo='" + tipo + "'}";
    }
}
