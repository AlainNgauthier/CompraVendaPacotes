package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Purchase")
public class Purchase extends AbstractEntity<Long>{

    @NotNull(message = "{NotNull.compra.preco}")
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
    private BigDecimal preco;
    
    @NotNull(message = "{NotNull.compra.cliente}")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client cliente;

    @NotNull(message = "{NotNull.compra.pacote}")
    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Client getCliente() {
        return this.cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Pacote getPacote() {
        return this.pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

}
