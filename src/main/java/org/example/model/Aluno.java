package org.example.model;

public class Aluno {
    private long id;
    private String nome;
    private String curso;

    public Aluno(){

    }

    public Aluno(long id, String nome, String curso) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
