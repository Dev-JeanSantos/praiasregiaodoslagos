package br.com.praiaregiaodoslagos.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "tb_praia")
public class Praia implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String tipoDePraia;
    @NotBlank
    @Column(name = "agua_e_onda")
    private String aguaEOnda;
    @NotBlank
    private String faixaDeAreia;
    @NotBlank
    private String descricao;
    @NotBlank
    private String coordenadas;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Deprecated
    public Praia() {
    }

    public Praia(String nome,
                 String tipoDePraia,
                 String aguaEOnda,
                 String faixaDeAreia,
                 String descricao,
                 String coordenadas,
                 Cidade cidade) {
        this.nome = nome;
        this.tipoDePraia = tipoDePraia;
        this.aguaEOnda = aguaEOnda;
        this.faixaDeAreia = faixaDeAreia;
        this.descricao = descricao;
        this.coordenadas = coordenadas;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDePraia() {
        return tipoDePraia;
    }

    public void setTipoDePraia(String tipoDePraia) {
        this.tipoDePraia = tipoDePraia;
    }

    public String getAguaEOnda() {
        return aguaEOnda;
    }

    public void setAguaEOnda(String aguaEOnda) {
        this.aguaEOnda = aguaEOnda;
    }

    public String getFaixaDeAreia() {
        return faixaDeAreia;
    }

    public void setFaixaDeAreia(String faixaDeAreia) {
        this.faixaDeAreia = faixaDeAreia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Praia{" +
                "nome='" + nome + '\'' +
                ", tipoDePraia='" + tipoDePraia + '\'' +
                ", aguaEOnda='" + aguaEOnda + '\'' +
                ", faixaDeAreia='" + faixaDeAreia + '\'' +
                ", descricao='" + descricao + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", cidade=" + cidade +
                '}';
    }
}
