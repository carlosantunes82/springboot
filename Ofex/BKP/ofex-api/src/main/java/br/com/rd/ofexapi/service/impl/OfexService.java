package br.com.rd.ofexapi.service.impl;

import br.com.rd.ofexapi.dto.OfexDTO;

public interface OfexService {

    OfexDTO findOfex(long idCliente, int cdFilial, int cdCampanhaAcao, long cdProduto);
}
