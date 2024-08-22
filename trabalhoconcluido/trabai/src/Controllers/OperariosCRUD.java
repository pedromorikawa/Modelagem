package Controllers;

import DAO.OperarioDAO;
import Entities.Operario;

import java.sql.SQLException;
import java.util.List;

public class OperariosCRUD {
    OperarioDAO operarioDAO = new OperarioDAO();

    public void execute() {
        this.insertOperarios();
        this.editOperarios();
        this.listOperarios();
        this.getOperario(3);
        this.deleteOperario(4);
    }

    private void insertOperarios() {
        Operario operario1 = new Operario();
        operario1.setNome("João");
        operario1.setFuncao("Pedreiro");

        Operario operario2 = new Operario();
        operario2.setNome("Maria");
        operario2.setFuncao("Eletricista");

        Operario operario3 = new Operario();
        operario3.setNome("Carlos");
        operario3.setFuncao("Encanador");

        try {
            operarioDAO.inserir(operario1);
            operarioDAO.inserir(operario2);
            operarioDAO.inserir(operario3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void editOperarios() {
        try {
            Operario operario = operarioDAO.listarPorId(1);
            if (operario != null) {
                operario.setNome("João Silva");
                operario.setFuncao("Mestre de Obras");
                operarioDAO.atualizar(operario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listOperarios() {
        try {
            List<Operario> operarios = operarioDAO.listarTodos();
            for (Operario operario : operarios) {
                System.out.println("ID: " + operario.getId() + ", Nome: " + operario.getNome() +
                        ", Função: " + operario.getFuncao());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getOperario(int id) {
        try {
            Operario operario = operarioDAO.listarPorId(id);
            if (operario != null) {
                System.out.println("ID: " + operario.getId() + ", Nome: " + operario.getNome() +
                        ", Função: " + operario.getFuncao());
            } else {
                System.out.println("Operário não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteOperario(int id) {
        try {
            operarioDAO.excluir(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
