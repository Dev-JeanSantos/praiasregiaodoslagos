package br.com.praiaregiaodoslagos.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_cidade")
public class Cidade implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String nome;
    private @NotNull Double populacao;
    private @NotNull Double area;
    private @NotNull Long codigoIbge;
    private @NotNull Double densidadeDemografica;
    private @NotBlank String linkImage;

    @OneToMany(mappedBy = "cidade",  fetch = FetchType.EAGER)
    private List<Praia> praias = new ArrayList<>();

    @Deprecated
    public Cidade() {
    }

    public Cidade( String nome, Double populacao, Double area, Long codigoIbge, Double densidadeDemografica, String linkImage) {
        this.nome = nome;
        this.populacao = populacao;
        this.area = area;
        this.codigoIbge = codigoIbge;
        this.densidadeDemografica = densidadeDemografica;
        this.linkImage = linkImage;
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

    public Double getPopulacao() {
        return populacao;
    }

    public Double getArea() {
        return area;
    }

    public Long getCodigoIbge() {
        return codigoIbge;
    }

    public Double getDensidadeDemografica() {
        return densidadeDemografica;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPopulacao(Double populacao) {
        this.populacao = populacao;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setCodigoIbge(Long codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public void setDensidadeDemografica(Double densidadeDemografica) {
        this.densidadeDemografica = densidadeDemografica;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public List<Praia> getPraias() {
        return praias;
    }
}
