package Controllers;

import DAO.EngenheiroDAO;
import Entities.Engenheiro;

import java.sql.SQLException;
import java.util.List;

public class EngenheirosCRUD {
    EngenheiroDAO engenheiroDAO = new EngenheiroDAO();

    public void execute() {
        this.insertEngenheiros();
        this.editEngenheiros();
        this.listEngenheiros();
        this.getEngenheiro(2);
        this.deleteEngenheiro(8);
    }

    private void insertEngenheiros() {
        Engenheiro engenheiro1 = new Engenheiro();
        engenheiro1.setNome("João");
        engenheiro1.setEspecialidade("Engenharia acústica");

        Engenheiro engenheiro2 = new Engenheiro();
        engenheiro2.setNome("Maria");
        engenheiro2.setEspecialidade("Engenharia civil");

        Engenheiro engenheiro3 = new Engenheiro();
        engenheiro3.setNome("Carlos");
        engenheiro3.setEspecialidade("Engenharia elétrica");

        Engenheiro engenheiro4 = new Engenheiro();
        engenheiro4.setNome("Ana");
        engenheiro4.setEspecialidade("Engenharia mecânica");

        Engenheiro engenheiro5 = new Engenheiro();
        engenheiro5.setNome("Lucas");
        engenheiro5.setEspecialidade("Engenharia de software");

        try {
            engenheiroDAO.inserir(engenheiro1);
            engenheiroDAO.inserir(engenheiro2);
            engenheiroDAO.inserir(engenheiro3);
            engenheiroDAO.inserir(engenheiro4);
            engenheiroDAO.inserir(engenheiro5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void editEngenheiros() {
        try {
            Engenheiro engenheiro = engenheiroDAO.listarPorId(1);
            if (engenheiro != null) {
                engenheiro.setNome("João Silva");
                engenheiro.setEspecialidade("Engenharia acústica e ambiental");
                engenheiroDAO.atualizar(engenheiro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listEngenheiros() {
        try {
            List<Engenheiro> engenheiros = engenheiroDAO.listarTodos();
            for (Engenheiro engenheiro : engenheiros) {
                System.out.println("ID: " + engenheiro.getId() + ", Nome: " + engenheiro.getNome() +
                        ", Especialidade: " + engenheiro.getEspecialidade());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEngenheiro(int engenheiroId) {
        try {
            Engenheiro engenheiro = engenheiroDAO.listarPorId(engenheiroId);
            System.out.println("ID: " + engenheiro.getId() + ", Nome: " + engenheiro.getNome() + ", Especialidade: " + engenheiro.getEspecialidade());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteEngenheiro(int id) {
        try {
            engenheiroDAO.excluir(id);
            System.out.println("Engenheiro excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
