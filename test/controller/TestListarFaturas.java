/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import DAO.*;
import model.*;
import org.junit.*;
import org.junit.Assert.*;

/**
 *
 * @author leoal
 */
public class TestListarFaturas {

    Compra compra, compra1;
    Cliente cliente;
    ClienteDao dao;

    @Before
    public void instaciaNovaFatura() {
        compra = new Compra();
        cliente = new Cliente();
        dao = new ClienteDao();
        compra1=new Compra();
    }

    @After
    public void limparObjetos() {
        compra = null;
        cliente = null;
    }

    @Test
    public void selecionaCompras() throws SQLException {
        cliente.setId(11);
        compra1.setNomeLoja("Americanas");
        ResultSet rs = dao.consultaCompras(cliente);
        while (rs.next()) {
            if (rs.getString("nome_loja").equals(compra.getNomeLoja())) {
                Assert.assertEquals(compra1.getNomeLoja(), compra.getNomeLoja());
                 System.out.println("compra: " + rs.getString("nome_loja"));
            } 
        }

    }

}
