/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import model.CartaoCredito;
import org.junit.*;
import org.junit.Assert.*;
import java.sql.*;

/**
 *
 * @author leoal
 */
public class TestBloquearCartao {

    CartaoCredito cc, cc1;
    ClienteDao dao;

    @Before
    public void instanciarCartao() {
        cc = new CartaoCredito();
        cc1 = new CartaoCredito();
        dao = new ClienteDao();

    }

    @After
    public void limparObjetos() {
        cc = null;
        cc1 = null;

        dao.cancelarCartao(cc.getIdCartao());
    }

    @Test
    public void testAdicionaNovoCartao() {
        cc.setIdCartao(36);
        cc.setIdUsuario(6);
        cc.setNumeroCartao(26254455);
        cc.setSenha(1243);
        cc.setBandeira("Visa");
        cc.setCategoria("Internacional");
        cc.setCvv(123);
        cc.setValidadeCartao("10/02/2025");
        cc.setVariante("gold");
        cc.setDataEmissao("10/02/2020");
        cc.setIdUsuario(6);
        cc.setStatusCartao(1);
        cc.setDataFechamento("01");
        cc.setDataVencimento("101010");
        cc.setNomeCartao("teste");
        cc.setValorDisponivel(100);

        dao.adicionaCartao(cc);

    }

    @Test
    public void testRealizaBloqueio() throws SQLException {
        dao.bloquearCartao(cc);
        ResultSet rs = dao.consultaCartao(cc);
        while (rs.next()) {
            cc1.setIdCartao(rs.getInt("id_cartao"));
            System.out.println("id cartao: "+cc1.getIdCartao());
        }
        if (cc1.getIdCartao() == cc.getIdCartao()) {
            Assert.assertEquals(cc1.getIdCartao(), cc.getIdCartao());
        } else {
            Assert.fail("cartão não bloqueado");
        }

    }

    @Test
    public void testRealizaDesbloqueio() throws SQLException {
        dao.desbloquearCartao(cc);
        dao.bloquearCartao(cc);
        ResultSet rs = dao.consultaCartao(cc);
        while (rs.next()) {
            cc1.setIdCartao(rs.getInt("id_cartao"));
            System.out.println("id cartao: "+cc1.getIdCartao());
        }
        if (cc1.getIdCartao() == cc.getIdCartao()) {
            Assert.assertEquals(cc1.getIdCartao(), cc.getIdCartao());
        } else {
            Assert.fail("cartão não desbloqueado");
        }

    }

}
