package Interfaces;

import java.sql.SQLException;

public interface IProject<T> {
    void listarEngenheirosEOperariosPorProjeto(T obj) throws SQLException;
}
