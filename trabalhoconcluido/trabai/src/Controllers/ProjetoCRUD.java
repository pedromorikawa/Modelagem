package Controllers;

import DAO.*;
import Entities.Projeto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoCRUD {
    ProjetoDAO projetoDAO = new ProjetoDAO();
    EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
    OperarioDAO operarioDAO = new OperarioDAO();
    EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
    MaterialDAO materialDAO = new MaterialDAO();

    public void execute() {
        this.editProjetos();
        this.listProjetos();
        this.getProjeto(2);
        this.deleteProjeto(3);
    }

    private void editProjetos() {
        try {
            Projeto projeto = projetoDAO.listarPorId(1);
            if (projeto != null) {
                projeto.setNome("Projeto Residencial Atualizado");
                projeto.setDescricao("Construção de uma residência unifamiliar com piscina.");
                projeto.setDataTermino(Date.valueOf("2025-11-30"));
                projetoDAO.atualizar(projeto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listProjetos() {
        try {
            List<Projeto> projetos = projetoDAO.listarTodos();
            for (Projeto projeto : projetos) {
                System.out.println("ID: " + projeto.getId() + ", Nome: " + projeto.getNome() +
                        ", Descrição: " + projeto.getDescricao() + ", Data de Início: " + projeto.getDataInicio() +
                        ", Data de Término: " + projeto.getDataTermino());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getProjeto(int id) {
        try {
            Projeto projeto = projetoDAO.listarPorId(id);
            if (projeto != null) {
                System.out.println("ID: " + projeto.getId() + ", Nome: " + projeto.getNome() +
                        ", Descrição: " + projeto.getDescricao() + ", Data de Início: " + projeto.getDataInicio() +
                        ", Data de Término: " + projeto.getDataTermino());
            } else {
                System.out.println("Projeto não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteProjeto(int id) {
        try {
            projetoDAO.excluir(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
