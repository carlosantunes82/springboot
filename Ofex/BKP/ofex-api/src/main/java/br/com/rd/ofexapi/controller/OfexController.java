package br.com.rd.ofexapi.controller;

import br.com.rd.ofexapi.dto.OfexDTO;
import br.com.rd.ofexapi.service.impl.OfexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Api(value = "", description = "Operações (get, post e put) do Ofex.")
public class OfexController {

    @Autowired
    private OfexService ofexService;

    @ApiOperation(value = "Buscar oferta exclusiva para um produto especifico.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
    })
    @GetMapping("v1/api/ofex/{idCliente}/{cdFilial}/{cdCampanhaAcao}/{cdProduto}")
    public ResponseEntity getOfex(@PathVariable long idCliente,
                                      @PathVariable int cdFilial,
                                      @PathVariable int cdCampanhaAcao,
                                      @PathVariable long cdProduto) {

        OfexDTO ofexDTO = ofexService.findOfex(idCliente, cdFilial, cdCampanhaAcao, cdProduto);

        return ResponseEntity.ok().body(ofexDTO);
    }

}
