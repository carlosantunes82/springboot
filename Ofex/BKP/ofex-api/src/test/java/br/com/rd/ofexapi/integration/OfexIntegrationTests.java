package br.com.rd.ofexapi.integration;

import br.com.rd.ofexapi.dto.OfexDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfexIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUpBefore() throws Exception {
      jdbcTemplate.update("delete from tb_campanha_conversao WHERE ID_CLIENTE = 50433822 AND CD_FILIAL = 1314 AND CD_CAMPANHA_OFERTA = 917 AND CD_CAMPANHA_SECAO = 3");
      jdbcTemplate.update("insert into tb_campanha_conversao values \n" +
                               " (50433822, trunc(sysdate), 917, 3, trunc(sysdate), 1314, 3, 3, 1, 11, 111, 21.00, 0, 0, 0, 345, null, 5, null, 33027, null, 911, 1, 1, 35, \n" +
                               " null, 4701092018311020181355001511115000027612615000, 1790)");
    }

    @After
    public void setUpAfter() throws Exception {
        jdbcTemplate.update("delete from tb_campanha_conversao WHERE ID_CLIENTE = 50433822 AND CD_FILIAL = 1314 AND CD_CAMPANHA_OFERTA = 917 AND CD_CAMPANHA_SECAO = 3");
    }

    @Test
    public void getOfexByidClienteAndCdFilialAndCdCampanhaAcaoAndCdProduto_comParametrosValidos_deveRetornarPercDesconto_200() {

        ResponseEntity<OfexDTO> responseEntity = restTemplate.getForEntity("/v1/api/ofex/"
                + 50433822 + "/" + 1314 +  "/" + 3 + "/" + 33027, OfexDTO.class);

        assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
        OfexDTO ofex = responseEntity.getBody();
        assertNotNull(ofex);
        assertEquals(ofex.getPercDesconto().intValue(), 21);
    }

    @Test
    public void getOfexByidClienteAndCdFilialAndCdCampanhaAcaoAndCdProduto_comParametrosStringInValidos_naoDeveRetornarNenhumPercDesconto_400() {
        ResponseEntity<OfexDTO> responseEntity = restTemplate.getForEntity("/v1/api/ofex/"
                + "aaa" + "/" + "aaa" +  "/" + "aaa" + "/" + "aaa", OfexDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(),responseEntity.getStatusCodeValue());
        OfexDTO ofex = responseEntity.getBody();
        assertNull(ofex);
    }

    @Test
    public void getOfexByidClienteAndCdFilialAndCdCampanhaAcaoAndCdProduto_comParametrosNumericosInValidos_naoDeveRetornarNenhumPercDesconto_404() {
        ResponseEntity<OfexDTO> responseEntity = restTemplate.getForEntity("/v1/api/ofex/"
                + 73475760 + "/" + 1047 +  "/" + 3 + "/" + 14, OfexDTO.class);

        assertEquals(HttpStatus.NOT_FOUND.value(),responseEntity.getStatusCodeValue());
        OfexDTO ofex = responseEntity.getBody();
        assertNull(ofex);
    }

}