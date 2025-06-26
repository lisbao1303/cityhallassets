package br.com.elisbao.cityhallassets.dto;

public class ConfirmacaoDTO {
    public String codigo;
    public Boolean confirmacao;
    public String observacao;

    public ConfirmacaoDTO(String codigo, Boolean confirmacao, String observacao) {
        this.codigo = codigo;
        this.confirmacao = confirmacao;
        this.observacao = observacao;
    }

    public ConfirmacaoDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
