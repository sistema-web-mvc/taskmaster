package com.taskmaster.mvp.repository;

import com.taskmaster.mvp.model.Tarefa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TarefaRepository {

    private final JdbcTemplate jdbcTemplate;

    public TarefaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tarefa(
            rs.getLong("id"),
            rs.getString("titulo"),
            rs.getString("descricao"),
            rs.getString("status")
        );
    }

    public List<Tarefa> findAll() {
        String sql = "SELECT id, titulo, descricao, status FROM tarefa ORDER BY id";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Optional<Tarefa> findById(Long id) {
        String sql = "SELECT id, titulo, descricao, status FROM tarefa WHERE id = ?";
        List<Tarefa> resultados = jdbcTemplate.query(sql, this::mapRow, id);
        return resultados.stream().findFirst();
    }
}