package com.joceano.kafkaconsumer.models;

public class Item {

    private Long id;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + id +
            ", descricao='" + descricao + '\'' +
            '}';
    }
}
