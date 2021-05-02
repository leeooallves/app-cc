/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import model.Cliente;
import org.junit.*;
import java.sql.*;
import java.util.*;
import model.Cliente;
import java.sql.Connection;
import static org.junit.Assert.*;

/**
 *
 * @author leoal
 */
public class TesListarClientes {

    Cliente cliente, cliente1;
    private ResultSet rs = null;

    @Before
    public void criarNovosClientes() {
        cliente = new Cliente();
        cliente1 = new Cliente();

    }

    @After
    public void limparObjetos() {
        cliente = null;
        cliente1 = null;
    }

    @Test
    public void localizarCliente() throws SQLException {
        //Localiza o cliente com o CPF: 0
        cliente.setNome("adm");
        // Variável recebe o resultado da consulta 
        rs = ClienteDao.consultarClientes();
        //Variável local que recebe o valor booleano
        boolean existeCadastro = false;

        while (rs.next()) {
            if (rs.getString("nome_completo").equals(cliente.getNome())) {
                existeCadastro = true;
            }
        }

        if (existeCadastro == true) {
            assertTrue(existeCadastro);
        } else {
            Assert.fail("Não possui nenhum usuário cadastrado");
        }
    }

}
