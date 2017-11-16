package com.example.testassured;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Leilao {

    private Long id;
    private String nome;
    private Integer valorInicial;
    private Usuario usuario;
    private boolean usado;

    public Leilao() {
    }

    public Leilao(Long id, String nome, Integer valorInicial, Usuario usuario, boolean usado) {
        this.id = id;
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.usuario = usuario;
        this.usado = usado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Integer valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leilao leilao = (Leilao) o;

        if (usado != leilao.usado) return false;
        if (id != null ? !id.equals(leilao.id) : leilao.id != null) return false;
        if (nome != null ? !nome.equals(leilao.nome) : leilao.nome != null) return false;
        if (valorInicial != null ? !valorInicial.equals(leilao.valorInicial) : leilao.valorInicial != null)
            return false;
        return usuario != null ? usuario.equals(leilao.usuario) : leilao.usuario == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (valorInicial != null ? valorInicial.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (usado ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Leilao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorInicial=" + valorInicial +
                ", usuario=" + usuario +
                ", usado=" + usado +
                '}';
    }
}
