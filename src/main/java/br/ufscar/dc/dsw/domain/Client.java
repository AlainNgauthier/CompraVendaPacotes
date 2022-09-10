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
@Table(name = "Client")
public class Client extends User{

    @UniqueCPF(message = "{Unique.client.cpf}")
    @NotBlank
    @Size(min = 14, max = 14, message = "{Size.client.cpf}")
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
    private List<Purchase> compras;

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

    public List<Purchase> getCompra(){
        return this.compras;
    }

    public void setCompra(List<Purchase> compras){
        this.compras = compras;
    }

}
