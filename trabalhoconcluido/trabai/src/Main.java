
import Controllers.*;
import DAO.*;
import Entities.Projeto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TableDAO tables = new TableDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        MaterialDAO materialDAO = new MaterialDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        OperariosCRUD operariosCRUD = new OperariosCRUD();
        EngenheirosCRUD engenheirosCRUD = new EngenheirosCRUD();
        MaterialCRUD materialCRUD = new MaterialCRUD();
        EquipamentosCRUD equipamentosCRUD = new EquipamentosCRUD();
        ProjetoCRUD projetoCRUD = new ProjetoCRUD();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        try {
            //cria tabelas do banco caso ainda não tenha.
            tables.inserirTabelas();

            //popula tabelas EXECUTE APENAS UMA VEZ!
            operariosCRUD.execute();
            engenheirosCRUD.execute();
            materialCRUD.execute();
            equipamentosCRUD.execute();

            Projeto projeto1 = new Projeto();
            projeto1.setNome("Projeto Residencial");
            projeto1.setDescricao("Construção de uma residência unifamiliar.");
            projeto1.setLocal("Joinville");
            projeto1.setDataInicio(new Date(System.currentTimeMillis()));
            projeto1.setDataTermino(Date.valueOf("2025-12-31"));
            List engenheirosList1 = new ArrayList();
            List materiaisList1 = new ArrayList();
            List operariosList1 = new ArrayList();
            List equipamentosList1 = new ArrayList();
            try {
                engenheirosList1.add(engenheiroDAO.listarPorId(1));
                engenheirosList1.add(engenheiroDAO.listarPorId(2));
                materiaisList1.add(materialDAO.listarPorId(1));
                materiaisList1.add(materialDAO.listarPorId(2));
                operariosList1.add(operarioDAO.listarPorId(1));
                operariosList1.add(operarioDAO.listarPorId(2));
                equipamentosList1.add(equipamentoDAO.listarPorId(1));
                equipamentosList1.add(equipamentoDAO.listarPorId(2));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            projeto1.setEngenheiros(engenheirosList1);
            projeto1.setOperarios(operariosList1);
            projeto1.setEquipamentos(equipamentosList1);
            projeto1.setMateriais(materiaisList1);

            projetoDAO.inserir(projeto1);

            //insere as dependencias do projeto
            projetoDAO.inserirEngenheirosProjeto(1, projeto1.getEngenheiros());
            projetoDAO.inserirOperariosProjeto(1, projeto1.getOperarios());
            projetoDAO.inserirEquipamentosProjeto(1, projeto1.getEquipamentos());
            projetoDAO.inserirMateriaisProjeto(1, projeto1.getMateriais(), 7);

            projetoDAO.listarEngenheirosOperariosProjeto(1);
            projetoDAO.listarEquipamentosMateriaisProjeto(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}