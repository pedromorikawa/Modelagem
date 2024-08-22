package DAO;

import Entities.*;
import Interfaces.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO implements IDAO<Projeto> {
    private Connection connection;

    public ProjetoDAO() {
        this.connection = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO projetos (nome, descricao, local, data_inicio, data_termino) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, projeto.getNome());
            ps.setString(2, projeto.getDescricao());
            ps.setString(3, projeto.getLocal());
            ps.setDate(4, projeto.getDataInicio());
            ps.setDate(4, projeto.getDataTermino());
            ps.executeUpdate();
        }
    }

    private boolean isEngenheiroAlocado(int projetoId, int engenheiroId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM alocacao_engenheiro WHERE id_projeto = ? AND id_engenheiro = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projetoId);
            ps.setInt(2, engenheiroId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.getInt(1) > 0;
            }
        }
    }

    public void inserirEngenheirosProjeto(int projetoId, List<Engenheiro> engenheiros) throws SQLException {
        String sql = "INSERT INTO alocacao_engenheiro (id_projeto, id_engenheiro) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Engenheiro engenheiro : engenheiros) {
                if (isEngenheiroAlocado(projetoId, engenheiro.getId())) {
                    System.out.println("Engenheiro com ID " + engenheiro.getId() + " já está alocado no projeto " + projetoId);
                } else {
                    ps.setInt(1, projetoId);
                    ps.setInt(2, engenheiro.getId());
                    ps.executeUpdate();
                    System.out.println("Engenheiro com ID " + engenheiro.getId() + " alocado ao projeto " + projetoId);
                }
            }
        }
    }

    private boolean isOperarioAlocado(int projetoId, int operarioId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM alocacao_operario WHERE id_projeto = ? AND id_operario = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projetoId);
            ps.setInt(2, operarioId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.getInt(1) > 0;
            }
        }
    }

    public void inserirOperariosProjeto(int projetoId, List<Operario> operarios) throws SQLException {
        String sql = "INSERT INTO alocacao_operario (id_projeto, id_operario) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Operario operario : operarios) {
                if (isOperarioAlocado(projetoId, operario.getId())) {
                    System.out.println("Operário com ID " + operario.getId() + " já está alocado no projeto " + projetoId);
                } else {
                    ps.setInt(1, projetoId);
                    ps.setInt(2, operario.getId());
                    ps.executeUpdate();
                    System.out.println("Operário com ID " + operario.getId() + " alocado ao projeto " + projetoId);
                }
            }
        }
    }

    private boolean isEquipamentoAlocado(int projetoId, int equipamentoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM uso_equipamento WHERE id_projeto = ? AND id_equipamento = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projetoId);
            ps.setInt(2, equipamentoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.getInt(1) > 0;
            }
        }
    }

    public void inserirEquipamentosProjeto(int projetoId, List<Equipamento> equipamentos) throws SQLException {
        String sql = "INSERT INTO uso_equipamento (id_projeto, id_equipamento) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Equipamento equipamento : equipamentos) {
                if (isEquipamentoAlocado(projetoId, equipamento.getId())) {
                    System.out.println("Equipamento com ID " + equipamento.getId() + " já está alocado no projeto " + projetoId);
                } else {
                    ps.setInt(1, projetoId);
                    ps.setInt(2, equipamento.getId());
                    ps.executeUpdate();
                    System.out.println("Equipamento com ID " + equipamento.getId() + " alocado ao projeto " + projetoId);
                }
            }
        }
    }

    private boolean isMaterialAlocado(int projetoId, int materialId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consumo_material WHERE id_projeto = ? AND id_material = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projetoId);
            ps.setInt(2, materialId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.getInt(1) > 0;
            }
        }
    }

    public void inserirMateriaisProjeto(int projetoId, List<Material> materiais, int quantidadeMateriais) throws SQLException {
        String sql = "INSERT INTO consumo_material (id_projeto, id_material, quantidade_consumida) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Material material : materiais) {
                if (isMaterialAlocado(projetoId, material.getId())) {
                    System.out.println("Material com ID " + material.getId() + " já está alocado no projeto " + projetoId);
                } else {
                    ps.setInt(1, projetoId);
                    ps.setInt(2, material.getId());
                    ps.setInt(3, quantidadeMateriais);
                    ps.executeUpdate();
                    System.out.println("Material com ID " + material.getId() + " alocado ao projeto " + projetoId);
                }
            }
        }
    }


    public void listarEngenheirosOperariosProjeto(int projetoId) throws SQLException {
        String sql = """
            SELECT e.id AS engenheiro_id, e.nome AS engenheiro_nome, o.id AS operario_id, o.nome AS operario_nome
            FROM projetos p
            LEFT JOIN alocacao_engenheiro ae ON p.id = ae.id_projeto
            LEFT JOIN engenheiros e ON ae.id_engenheiro = e.id
            LEFT JOIN alocacao_operario ao ON p.id = ao.id_projeto
            LEFT JOIN operarios o ON ao.id_operario = o.id
            WHERE p.id = ?
            """;

        ResultSet resultSet;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projetoId);

            // Executa a consulta
            resultSet = ps.executeQuery();

            // Processa os resultados
            List<Engenheiro> engenheiros = new ArrayList<>();
            List<Operario> operarios = new ArrayList<>();

            while (resultSet.next()) {
                // Verifica se tem engenheiro
                int engenheiroId = resultSet.getInt("engenheiro_id");
                String engenheiroNome = resultSet.getString("engenheiro_nome");
                if (engenheiroId != 0) {
                    Engenheiro engenheiro = new Engenheiro();
                    engenheiro.setId(engenheiroId);
                    engenheiro.setNome(engenheiroNome);
                    engenheiros.add(engenheiro);
                }

                // Verifica se tem operário
                int operarioId = resultSet.getInt("operario_id");
                String operarioNome = resultSet.getString("operario_nome");
                if (operarioId != 0) {
                    Operario operario = new Operario();
                    operario.setId(operarioId);
                    operario.setNome(operarioNome);
                    operarios.add(operario);
                }
            }

            // printa resultados
            System.out.println("Engenheiros alocados ao projeto ID " + projetoId + ": " + engenheiros);
            System.out.println("Operários alocados ao projeto ID " + projetoId + ": " + operarios);
        }
    }

    public void listarEquipamentosMateriaisProjeto(int projetoId) throws SQLException {
        String sql = """
                SELECT e.id AS equipamento_id, e.nome AS equipamento_nome, m.id AS material_id, m.nome AS material_nome, cm.quantidade_consumida
                FROM projetos p
                LEFT JOIN uso_equipamento ue ON p.id = ue.id_projeto
                LEFT JOIN equipamentos e ON ue.id_equipamento = e.id
                LEFT JOIN consumo_material cm ON p.id = cm.id_projeto
                LEFT JOIN materiais m ON cm.id_material = m.id
                WHERE p.id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projetoId);

            // Executa a consulta
            ResultSet resultSet = ps.executeQuery();

            // Processa os resultados
            List<Equipamento> equipamentos = new ArrayList<>();
            List<Material> materiais = new ArrayList<>();

            while (resultSet.next()) {
                // Verifica se tem equipamento
                int equipamentoId = resultSet.getInt("equipamento_id");
                String equipamentoNome = resultSet.getString("equipamento_nome");
                if (equipamentoId != 0) {
                    Equipamento equipamento = new Equipamento();
                    equipamento.setId(equipamentoId);
                    equipamento.setNome(equipamentoNome);
                    equipamentos.add(equipamento);
                }

                // Verifica se tem material
                int materialId = resultSet.getInt("material_id");
                String materialNome = resultSet.getString("material_nome");
                int quantidadeConsumida = resultSet.getInt("quantidade_consumida");
                if (materialId != 0) {
                    Material material = new Material();
                    material.setId(materialId);
                    material.setNome(materialNome);
                    material.setQuantidade(quantidadeConsumida);  // Assumindo que a classe Material tem um campo para quantidade
                    materiais.add(material);
                }
            }

            // printa resultados
            System.out.println("Equipamentos utilizados no projeto ID " + projetoId + ": " + equipamentos);
            System.out.println("Materiais utilizados no projeto ID " + projetoId + ": " + materiais);
        }
    }

    public void atualizar(Projeto projeto) throws SQLException {
        String sql = "UPDATE projetos SET nome = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, projeto.getNome());
            ps.setString(2, projeto.getDescricao());
            ps.setInt(3, projeto.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM projetos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Projeto listarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM projetos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                return projeto;
            }
        }
        return null;
    }

    public List<Projeto> listarTodos() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }


}
