package br.com.rd.ofexapi.unit.service;

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

public class OfexServiceTests {

    @Mock
    private OfexRepository ofexRepository;

    private OfexService ofexService;

    @Mock
    private Query query;

    @Mock
    private EntityManager em;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        ofexService = new OfexServiceImpl(ofexRepository);
    }

    @Test
    public void getOfexByidClienteAndCdFilialAndCdCampanhaAcaoAndCdProduto_comParametrosNumericosInValidos_naoDeveRetornarNenhumPercDesconto404() {
        when(ofexRepository.findOfex(anyLong(), anyInt(), anyInt(), anyLong())).thenReturn(null);
        expectedException.expect(NoSuchElementException.class);
        ofexService.findOfex(anyLong(), anyInt(), anyInt(), anyLong());
    }

}