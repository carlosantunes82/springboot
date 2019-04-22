package br.com.rd.ofexapi.service;

import br.com.rd.ofexapi.dto.OfexDTO;
import br.com.rd.ofexapi.repository.OfexRepository;
import br.com.rd.ofexapi.service.impl.OfexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class OfexServiceImpl implements OfexService {

    @Autowired
    private OfexRepository ofexRepository;

    public OfexServiceImpl(OfexRepository ofexRepository){
        this.ofexRepository = ofexRepository;
    }

    @Override
    public OfexDTO findOfex(long idCliente, int cdFilial, int cdCampanhaAcao, long cdProduto){

        BigDecimal percDesconto = ofexRepository.findOfex(idCliente, cdFilial, cdCampanhaAcao, cdProduto);
        if (percDesconto == null) {
            throw new NoSuchElementException();
        }
        OfexDTO ofexDTO = new OfexDTO();
        ofexDTO.setPercDesconto(percDesconto);

        return ofexDTO;
    }
}
