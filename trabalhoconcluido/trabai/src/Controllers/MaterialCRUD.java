package Controllers;

import DAO.MaterialDAO;
import Entities.Material;

import java.sql.SQLException;
import java.util.List;

public class MaterialCRUD {
    MaterialDAO materialDAO = new MaterialDAO();

    public void execute() {
        this.insertMateriais();
        this.editMateriais();
        this.listMateriais();
        this.getMaterial(3);
        this.deleteMaterial(4);
    }

    private void insertMateriais() {
        Material material1 = new Material();
        material1.setNome("Capacete");
        material1.setQuantidade(3);

        Material material2 = new Material();
        material2.setNome("Cimento");
        material2.setQuantidade(50);

        Material material3 = new Material();
        material3.setNome("Tijolos");
        material3.setQuantidade(100);

        try {
            materialDAO.inserir(material1);
            materialDAO.inserir(material2);
            materialDAO.inserir(material3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void editMateriais() {
        try {
            Material material = materialDAO.listarPorId(1);
            if (material != null) {
                material.setNome("Capacete de Segurança");
                material.setQuantidade(5);
                materialDAO.atualizar(material);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listMateriais() {
        try {
            List<Material> materiais = materialDAO.listarTodos();
            for (Material material : materiais) {
                System.out.println("ID: " + material.getId() + ", Nome: " + material.getNome() +
                        ", Quantidade: " + material.getQuantidade());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getMaterial(int id) {
        try {
            Material material = materialDAO.listarPorId(id);
            if (material != null) {
                System.out.println("ID: " + material.getId() + ", Nome: " + material.getNome() +
                        ", Quantidade: " + material.getQuantidade());
            } else {
                System.out.println("Material não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteMaterial(int id) {
        try {
            materialDAO.excluir(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
