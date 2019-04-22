package br.com.rd.ofexapi.repository;

import java.math.BigDecimal;

public interface OfexRepository {

    BigDecimal findOfex(long idCliente, int cdFilial, int cdCampanhaAcao, long cdProduto);

}
