package Controllers;

import DAO.EquipamentoDAO;
import Entities.Equipamento;

import java.sql.SQLException;
import java.util.List;

public class EquipamentosCRUD {
    EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

    public void execute() {
        this.insertEquipamentos();
        this.editEquipamentos();
        this.listEquipamentos();
        this.getEquipamento(3);
        this.deleteEquipamento(4);
    }

    private void insertEquipamentos() {
        Equipamento equipamento1 = new Equipamento();
        equipamento1.setNome("Furadeira");
        equipamento1.setTipo("Ferramenta elétrica");

        Equipamento equipamento2 = new Equipamento();
        equipamento2.setNome("Guindaste");
        equipamento2.setTipo("Máquina pesada");

        Equipamento equipamento3 = new Equipamento();
        equipamento3.setNome("Scanner 3D");
        equipamento3.setTipo("Equipamento de medição");

        try {
            equipamentoDAO.inserir(equipamento1);
            equipamentoDAO.inserir(equipamento2);
            equipamentoDAO.inserir(equipamento3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void editEquipamentos() {
        try {
            Equipamento equipamento = equipamentoDAO.listarPorId(1);
            if (equipamento != null) {
                equipamento.setNome("Furadeira Industrial");
                equipamento.setTipo("Ferramenta elétrica avançada");
                equipamentoDAO.atualizar(equipamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listEquipamentos() {
        try {
            List<Equipamento> equipamentos = equipamentoDAO.listarTodos();
            for (Equipamento equipamento : equipamentos) {
                System.out.println("ID: " + equipamento.getId() + ", Nome: " + equipamento.getNome() +
                        ", Tipo: " + equipamento.getTipo());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEquipamento(int id) {
        try {
            Equipamento equipamento = equipamentoDAO.listarPorId(id);
            if (equipamento != null) {
                System.out.println("ID: " + equipamento.getId() + ", Nome: " + equipamento.getNome() +
                        ", Tipo: " + equipamento.getTipo());
            } else {
                System.out.println("Equipamento não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteEquipamento(int id) {
        try {
            equipamentoDAO.excluir(id);
            System.out.println("Equipamento exluido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
