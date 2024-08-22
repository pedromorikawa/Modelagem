package Entities;

import java.util.List;

public class Projeto {
    private int id;
    private String nome;
    private String local;
    private String descricao;
    private java.sql.Date dataInicio;
    private java.sql.Date dataTermino;
    private List<Engenheiro> engenheiros;
    private List<Operario> operarios;
    private List<Equipamento> equipamentos;
    private List<Material> materiais;

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

    public String getLocal() {
        return local;
    }
    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setEngenheiros(List<Engenheiro> engenheiros) { this.engenheiros = engenheiros; }

    public List<Engenheiro> getEngenheiros() { return this.engenheiros; }

    public List<Equipamento> getEquipamentos() { return this.equipamentos; }

    public List<Material> getMateriais() { return this.materiais; }

    public List<Operario> getOperarios() { return this.operarios; }

    public void setOperarios(List<Operario> operarios) { this.operarios = operarios; }

    public void setEquipamentos(List<Equipamento> equipamentos) { this.equipamentos = equipamentos; }

    public void setMateriais(List<Material> materiais) { this.materiais = materiais; }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(java.sql.Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public java.sql.Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(java.sql.Date dataTermino) {
        this.dataTermino = dataTermino;
    }
}
