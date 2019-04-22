package br.com.rd.ofexapi.unit.resource;

import br.com.rd.ofexapi.dto.OfexDTO;
import br.com.rd.ofexapi.repository.OfexRepository;
import br.com.rd.ofexapi.repository.impl.OfexRepositoryImpl;
import br.com.rd.ofexapi.service.OfexServiceImpl;
import br.com.rd.ofexapi.service.impl.OfexService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OfexRepositoryTests {

    private OfexService ofexService;

    @Mock
    private Query query;

    @Mock
    private EntityManager em;

    private OfexRepository ofexRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private long idCliente = 50433822;
    private int cdFilial = 1314;
    private int  cdCampanhaAcao = 3;
    private long  cdProduto = 33027;
    private long cdProdutoInexistente = 212123;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        ofexRepository = new OfexRepositoryImpl(query, em);
    }

    @Test
    public void getOfexByidClienteAndCdFilialAndCdCampanhaAcaoAndCdProduto_comParametrosValidos_deveRetornarPercDesconto() {

        when(em.createNativeQuery(OfexRepositoryImpl.queryString)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(new BigDecimal(21));
        BigDecimal percDesconto = (BigDecimal) ofexRepository.findOfex(idCliente, cdFilial, cdCampanhaAcao, cdProduto);

        verify(query, atLeastOnce()).getSingleResult();
        assertNotNull(percDesconto);
        assertEquals(percDesconto.intValue(), 21);
    }

    @Test
    public void getOfexByidClienteAndCdFilialAndCdCampanhaAcaoAndCdProduto_comParametrosNumericosInValidos_naoDeveRetornarNenhumPercDesconto() {

        when(em.createNativeQuery(OfexRepositoryImpl.queryString)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(null);

        BigDecimal percDesconto = (BigDecimal) ofexRepository.findOfex(idCliente, cdFilial, cdCampanhaAcao, cdProdutoInexistente);

        verify(query, atLeastOnce()).getSingleResult();
        assertNull(percDesconto);
    }

}