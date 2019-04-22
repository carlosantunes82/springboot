package br.com.rd.ofexapi.dto;

import java.math.BigDecimal;

public class OfexDTO {

    public OfexDTO(){
    }

    public OfexDTO(BigDecimal percDesconto){
        this.percDesconto = percDesconto;
    }

    private BigDecimal percDesconto;

    public BigDecimal getPercDesconto() {
        return percDesconto;
    }

    public void setPercDesconto(BigDecimal percDesconto) {
        this.percDesconto = percDesconto;
    }
}