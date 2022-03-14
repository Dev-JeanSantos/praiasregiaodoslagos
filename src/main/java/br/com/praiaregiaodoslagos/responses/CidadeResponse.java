package br.com.praiaregiaodoslagos.responses;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.entities.Praia;

import java.util.ArrayList;
import java.util.List;

public class CidadeResponse {

    private Long id;
    private String nome;
    private Double populacao;
    private Double area;
    private Double densidadeDemografica;
    private Long codigoIbge;
    private String linkImage;

    private List<PraiaResponse> praias = new ArrayList<>();

    @Deprecated
    public CidadeResponse() {
    }

    public CidadeResponse(Cidade entity) {
        id = entity.getId();
        nome = entity.getNome();
        populacao = entity.getPopulacao();
        area = entity.getArea();
        densidadeDemografica = entity.getDensidadeDemografica();
        codigoIbge = entity.getCodigoIbge();
        linkImage = entity.getLinkImage();
    }

    public CidadeResponse(Cidade entity, List<Praia> list) {
        this(entity);
        list.forEach(x -> this.praias.add(new PraiaResponse(x)));
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

    public Double getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Double populacao) {
        this.populacao = populacao;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getDensidadeDemografica() {
        return densidadeDemografica;
    }

    public void setDensidadeDemografica(Double densidadeDemografica) {
        this.densidadeDemografica = densidadeDemografica;
    }

    public Long getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Long codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public List<PraiaResponse> getPraias() {
        return praias;
    }

    public void setPraias(List<PraiaResponse> praias) {
        this.praias = praias;
    }
}
