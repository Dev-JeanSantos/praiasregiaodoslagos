package br.com.praiaregiaodoslagos.requesties;

import br.com.praiaregiaodoslagos.entities.Praia;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PraiaRequest {

    private Long id;
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

    public PraiaRequest(
                        @NotBlank String nome,
                        @NotBlank String tipoDePraia,
                        @NotBlank String aguaEOnda,
                        @NotBlank String faixaDeAreia,
                        @NotBlank String descricao,
                        @NotBlank String coordenadas,
                        @NotNull Long cidadeId) {
        this.nome = nome;
        this.tipoDePraia = tipoDePraia;
        this.aguaEOnda = aguaEOnda;
        this.faixaDeAreia = faixaDeAreia;
        this.descricao = descricao;
        this.coordenadas = coordenadas;
        this.cidadeId = cidadeId;
    }

    public PraiaRequest(Praia entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.tipoDePraia = entity.getTipoDePraia();
        this.aguaEOnda = entity.getAguaEOnda();
        this.faixaDeAreia = entity.getFaixaDeAreia();
        this.descricao = entity.getDescricao();
        this.coordenadas = entity.getCoordenadas();
        this.cidadeId = entity.getCidade().getId();
    }

    public Long getId() {return id;}

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

    public Long getCidadeId() {
        return cidadeId;
    }

}
