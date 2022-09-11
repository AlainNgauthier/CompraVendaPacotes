package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends User{

    @UniqueCPF(message = "{Unique.cliente.cpf}")
    @NotBlank
    @Size(min = 14, max = 14, message = "{Size.cliente.cpf}")
    @Column(nullable = false, length = 50)
    private String cpf;
    
    @NotBlank
    @Column(nullable = false, length = 60)
    private String nome;
    
    @Column(nullable = true, length = 15)
    private String telefone;

    @Column(nullable = true, length = 50)
    private String sexo;

    @Column(nullable = true, length = 12)
    private String dataNasc;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Compra> getCompra(){
        return this.compras;
    }

    public void setCompra(List<Compra> compras){
        this.compras = compras;
    }

}
