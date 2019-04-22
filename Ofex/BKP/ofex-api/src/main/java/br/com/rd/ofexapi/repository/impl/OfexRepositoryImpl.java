package br.com.rd.ofexapi.repository.impl;

import br.com.rd.ofexapi.repository.OfexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;

@Repository
public class OfexRepositoryImpl implements OfexRepository {

    public OfexRepositoryImpl() {
    }

    public OfexRepositoryImpl(Query query, EntityManager em) {
        this.em = em;
        this.query = query;
    }

    @PersistenceContext
    private EntityManager em;

    private Query query;

    public static String queryString = "SELECT MAX(a.pc_desconto)" +
            "  FROM A_RAIABD.tb_campanha_conversao a, A_RAIABD.tb_produto p" +
            " WHERE a.dt_visualizacao = TRUNC(SYSDATE)" +
            "   AND a.id_cliente = :idCliente" +
            "   AND a.cd_filial = :cdFilial" +
            "   AND a.cd_campanha_acao = :cdCampanhaAcao" +
            "   AND a.cd_grupo = p.cd_grupo" +
            "   AND a.cd_categoria = p.cd_categoria" +
            "   AND a.cd_produto_marca = p.cd_produto_marca" +
            "   AND p.cd_produto = :cdProduto";

    @Override
    public BigDecimal findOfex(long idCliente, int cdFilial, int cdCampanhaAcao, long cdProduto){
        query = em.createNativeQuery(queryString);
        query.setParameter("idCliente", idCliente);
        query.setParameter("cdFilial", cdFilial);
        query.setParameter("cdCampanhaAcao", cdCampanhaAcao);
        query.setParameter("cdProduto", cdProduto);
        BigDecimal percDesconto = (BigDecimal) query.getSingleResult();
        return percDesconto;

    }
}
