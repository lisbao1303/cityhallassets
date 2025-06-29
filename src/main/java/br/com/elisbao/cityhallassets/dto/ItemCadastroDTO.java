package br.com.elisbao.cityhallassets.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemCadastroDTO {
    public Long empresaId;

    public String numeroPatrimonio;
    public String descricao;
    public String marca;
    public String modelo;
    public String estadoConservacao;
    public String localizacao;
    public String responsavel;
    public String fotoUrl;
    public BigDecimal valor;
    public LocalDate dataAquisicao;
    public String manutUser;
}
