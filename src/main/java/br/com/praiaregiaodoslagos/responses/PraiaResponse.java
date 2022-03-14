package br.com.praiaregiaodoslagos.responses;

import br.com.praiaregiaodoslagos.entities.Cidade;
import br.com.praiaregiaodoslagos.entities.Praia;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PraiaResponse {

    @NotBlank
    private String nome;
    @NotBlank
    private String tipoDePraia;
    @NotBlank
    private String aguaEOnda;
    @NotBlank
    private String faixaDeAreia;
    @NotBlank
    private String descricao;
    @NotBlank
    private String coordenadas;
    @NotNull
    private Long cidadeId;

    private CidadeResponse response;
    @Deprecated
    public PraiaResponse() {
    }

    public PraiaResponse(String nome,
                         String tipoDePraia,
                         String aguaEOnda,
                         String faixaDeAreia,
                         String descricao,
                         String coordenadas,
                         Long cidadeId,
                         Cidade cidade) {
        this.nome = nome;
        this.tipoDePraia = tipoDePraia;
        this.aguaEOnda = aguaEOnda;
        this.faixaDeAreia = faixaDeAreia;
        this.descricao = descricao;
        this.coordenadas = coordenadas;
        this.cidadeId = cidadeId;
        this.response = new CidadeResponse(cidade);
    }

    public PraiaResponse(Praia entity) {
        nome = entity.getNome();
        tipoDePraia = entity.getTipoDePraia();
        aguaEOnda = entity.getAguaEOnda();
        faixaDeAreia = entity.getFaixaDeAreia();
        descricao = entity.getDescricao();
        coordenadas = entity.getCoordenadas();
        cidadeId = entity.getId();
        response = new CidadeResponse(entity.getCidade());
    }

    public String getNome() {
        return nome;
    }

    public String getTipoDePraia() {
        return tipoDePraia;
    }

    public String getAguaEOnda() {
        return aguaEOnda;
    }

    public String getFaixaDeAreia() {
        return faixaDeAreia;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    @Override
    public String toString() {
        return "PraiaResponse{" +
                "nome='" + nome + '\'' +
                ", tipoDePraia='" + tipoDePraia + '\'' +
                ", aguaEOnda='" + aguaEOnda + '\'' +
                ", faixaDeAreia='" + faixaDeAreia + '\'' +
                ", descricao='" + descricao + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", cidadeId=" + cidadeId +
                '}';
    }
}
