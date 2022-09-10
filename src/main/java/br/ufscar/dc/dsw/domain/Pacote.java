package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Pacote")
public class Pacote  extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.pacote.nome}")
    @Column(nullable = false, length = 60)
    private String nome;

    @NotNull(message = "{NotNull.pacote.agencia}")
    @ManyToOne
    @JoinColumn(name = "agencia_id")
	private Agencia agencia;

    @NotBlank(message = "{NotBlank.pacote.data}")
    @Column(nullable = false, length = 12)
    private String data;

    @NotNull(message = "{NotNull.pacote.duracao}")
    @Column(nullable = false)
    private Integer duracao;

    @NotNull(message = "{NotNull.pacote.preco}")
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
    private BigDecimal preco;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false, length = 255)
    private String destinos;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDestinos() {
        return this.destinos;
    }

    public void setDestinos(String destinos) {
        this.destinos = destinos;
    }
}
