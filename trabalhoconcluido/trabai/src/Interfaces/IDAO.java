package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void inserir(T obj) throws SQLException;
    void atualizar(T obj) throws SQLException;
    void excluir(int id) throws SQLException;
    T listarPorId(int id) throws SQLException;
    List<T> listarTodos() throws SQLException;
}
