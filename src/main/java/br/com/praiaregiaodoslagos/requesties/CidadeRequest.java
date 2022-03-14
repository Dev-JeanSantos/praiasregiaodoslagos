package br.com.praiaregiaodoslagos.requesties;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.validacoes.UniqueValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CidadeRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    private  Long id;
    @NotBlank(message = "campo vazio")
    private String nome;
    @NotNull
    private Double populacao;
    @NotNull
    private  Double area;
    @NotNull
    private Double densidadeDemografica;
    @NotNull
    @UniqueValue(domainClass = Cidade.class, fieldName = "codigoIbge")
    private Long codigoIbge;
    @NotBlank
    private String linkImage;

    public CidadeRequest(String nome, Double populacao, Double area, Double densidadeDemografica, Long codigoIbge, String linkImage) {
        this.nome = nome;
        this.populacao = populacao;
        this.area = area;
        this.densidadeDemografica = densidadeDemografica;
        this.codigoIbge = codigoIbge;
        this.linkImage = linkImage;
    }
    public CidadeRequest(Cidade entity) {
        this.nome = entity.getNome();
        this.populacao = entity.getPopulacao();
        this.area = entity.getArea();
        this.densidadeDemografica = entity.getDensidadeDemografica();
        this.codigoIbge = entity.getCodigoIbge();
        this.linkImage = entity.getLinkImage();
    }

    public Long getId() {
        return id;
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

    public Double getDensidadeDemografica() {
        return densidadeDemografica;
    }

    public Long getCodigoIbge() {
        return codigoIbge;
    }

    public String getLinkImage() {
        return linkImage;
    }

    @Override
    public String toString() {
        return "CidadeRequest{" +
                "nome='" + nome + '\'' +
                ", populacao=" + populacao +
                ", area=" + area +
                ", densidadeDemografica=" + densidadeDemografica +
                ", codigoIbge=" + codigoIbge +
                ", linkImage='" + linkImage + '\'' +
                '}';
    }
}
