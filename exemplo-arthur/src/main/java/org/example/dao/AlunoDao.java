package org.example.dao;

import org.example.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {
    private final List<Aluno> tabela = new ArrayList<>();
    private long proximoId = 1;

    public AlunoDao() {
        salvar(new Aluno(2, "Arthur", "Sistemas"));
    }

    public List<Aluno> listarTodos() {
        return tabela;
    }
    public Aluno salvar(Aluno aluno) {
        aluno.setId(proximoId++);
        tabela.add(aluno);
        return aluno;
    }
}
