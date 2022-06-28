package com.joceano.kafkaconsumer.models;

import com.joceano.kafkaconsumer.models.enums.PedidoStatus;
import java.math.BigDecimal;

public class Pedido {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private PedidoStatus status;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + id +
            ", descricao='" + descricao + '\'' +
            ", valor=" + valor +
            ", status=" + status +
            '}';
    }
}
