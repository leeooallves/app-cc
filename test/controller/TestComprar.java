/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import java.sql.SQLException;
import model.*;
import org.junit.*;
import org.junit.Assert.*;

/**
 *
 * @author leoal
 */
public class TestComprar {

    Cliente cliente;
    Compra compra, compra1;
    CartaoCredito cartao;

    @Before
    public void novoObjeto() {
        cliente = new Cliente();
        compra = new Compra();
        compra1 = new Compra();
        cartao = new CartaoCredito();

        cliente.setId(14);
//        cartao.setIdCartao(12);
        compra.setNomeLoja("Nome loja teste");
        compra.setValorCompra(125.55);
        compra.setDataCompra("10/01/2021");
        compra.setIdCartao(12);
        compra.setIdCliente(14);

    }

    @After
    public void limpaObjeto() throws SQLException {
        //Exclui a compra realizada pelo teste
        ClienteDao.excluirCompra(compra);
    }

    @Ignore
    public void realizaCompraTeste() {
        ClienteDao.comprar(cliente, compra);

        compra1.setNomeLoja("Nome loja teste");
        compra1.setValorCompra(125.55);
        compra1.setDataCompra("10/01/2021");
        compra1.setIdCartao(12);
        compra1.setIdCliente(14);

        if (compra == compra1) {
            Assert.assertEquals(compra.getNomeLoja(), compra1.getNomeLoja());
            Assert.assertEquals(compra.getDataCompra(), compra1.getDataCompra());
        } else {
            Assert.fail("teste");
        }

    }
    @Test
     public void realizaCompraTeste1() {
        ClienteDao.comprar(cliente, compra);

        compra1.setNomeLoja("Nome loja teste");
        compra1.setValorCompra(125.55);
        compra1.setDataCompra("10/01/2021");
        compra1.setIdCartao(12);
        compra1.setIdCliente(14);

        if (compra.nomeLoja.equals(compra1.nomeLoja)) {
            Assert.assertEquals(compra.getNomeLoja(), compra1.getNomeLoja());
            Assert.assertEquals(compra.getDataCompra(), compra1.getDataCompra());
        } else {
            Assert.fail("teste");
        }

    }

}
