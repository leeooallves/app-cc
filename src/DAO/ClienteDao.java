/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import app.Login;
import java.sql.*;
import java.util.*;
import model.Cliente;
import java.sql.SQLException;
import java.sql.Connection;
import model.CartaoCredito;
import model.Compra;

public class ClienteDao {

    public static void excluirCompra(Compra compra) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM compra_cliente WHERE nome_loja=? AND id_cliente=? ");
            stmt.setString(1, compra.nomeLoja);
            stmt.setInt(2, compra.idCliente);
//            stmt.setInt(3, compra.idCartao);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e);
        } finally {
            bd.fechaConexao(con);
        }

    }

    public void adicionaCliente(Cliente cliente) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO `usuario` (`id_usuario`, `senha`, `nome_completo`, `cpf`, `email`, `renda`, `rua`, `numero_rua`, `bairro`, `cidade`, `estado`, `pais`, `telefone`, `status_cadastro`, `login_usuario`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getSenha());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setDouble(6, cliente.getRenda());
            stmt.setString(7, cliente.getRua());
            stmt.setInt(8, cliente.getNumero());
            stmt.setString(9, cliente.getBairro());
            stmt.setString(10, cliente.getCidade());
            stmt.setString(11, cliente.getEstado());
            stmt.setString(12, cliente.getPais());
            stmt.setInt(13, cliente.getTelefone());
            stmt.setInt(14, 0);
            stmt.setString(15, cliente.getUsuario());

            //EXECUTA
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }
    }

    public void adicionaCartao(CartaoCredito cartao) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "INSERT INTO `cartao_credito` (`id_cartao`, `numero_cartao`, `senha_cartao`, `bandeira`, `categoria`, `codigo_cvv`, `variante`, `id_cliente`, `status_cartao`, `data_emissao`, `data_fechamento`, `data_vencimento`, `valor_limite`, `nome_cartao`, `validade_cartao`, `valor_disponivel`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)"
            );
            stmt.setInt(1, cartao.getIdCartao());
            stmt.setInt(2, cartao.getNumeroCartao());
            stmt.setInt(3, cartao.getSenha());
            stmt.setString(4, cartao.getBandeira());
            stmt.setString(5, cartao.getCategoria());
            stmt.setInt(6, cartao.getCvv());
            stmt.setString(7, cartao.getVariante());
            stmt.setInt(8, cartao.getIdUsuario());
            stmt.setInt(9, cartao.getStatusCartao());
            stmt.setString(10, cartao.getDataEmissao());
            stmt.setString(11, cartao.getDataFechamento());
            stmt.setString(12, cartao.getDataVencimento());
            stmt.setDouble(13, cartao.getValorLimite());
            stmt.setString(14, cartao.getNomeCartao());
            stmt.setString(15, cartao.getValidadeCartao());
            stmt.setDouble(16, cartao.getValorDisponivel());

            //EXECUTA
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }
    }

    public void buscar(Cliente cli) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM `usuario` WHERE login_usuario LIKE '?' AND senha like '?'");
            stmt.setString(0, cli.getUsuario());
            stmt.setString(1, cli.getSenha());
            rs = stmt.executeQuery();
            System.out.println("deu certo consulta!");
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            bd.fechaConexao(con);

        }
    }

    public ResultSet logar(Cliente cli) throws SQLException {

        Connection con = new ConexaoBD().getConexao();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM `usuario` WHERE login_usuario LIKE '" + cli.getUsuario() + "' AND senha LIKE '" + cli.getSenha() + "'");

        ResultSet resultSet = stmt.executeQuery();

        return resultSet;

    }

    public static void comprar(Cliente cli, Compra compra) {
//        Cliente cliente = new Cliente();
//        cliente.setId(Login.idCliente);
//        Compra compra=new Compra();

        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `compra_cliente` "
                    + "(`id_compra`, `nome_loja`, `valor_compra`, `data_compra`, `id_cliente`, `id_cartao_cliente`) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setInt(1, compra.getIdCompra());
            stmt.setString(2, compra.getNomeLoja());
            stmt.setDouble(3, compra.getValorCompra());
            stmt.setString(4, compra.getDataCompra());
            stmt.setInt(5, cli.getId());
            stmt.setInt(6, cli.getIdCartao());
            stmt.executeUpdate();
            stmt.close();
            //subtrairSaldoDisponivel(cli, compra);

        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void subtrairSaldoDisponivel(Cliente cli, Compra compra) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CartaoCredito cc = new CartaoCredito();
        double valor = 0.0;

        try {
            stmt = con.prepareStatement("UPDATE `cartao_credito` SET `valor_disponivel` = '?' WHERE `cartao_credito`.`id_cartao` = ?;");

            double x = compra.getValorCompra();

            try {
                stmt = con.prepareStatement("SELECT `valor_disponivel` FROM `cartao_credito` where `id_cartao` = ?");
                //stmt.setDouble(0, cc.getValorDisponivel());
                stmt.setInt(0, cli.getIdCartao());
                rs = stmt.executeQuery();
                valor = rs.getDouble("valor_disponivel");

            } catch (Exception e) {
            }
            double result = valor - compra.getValorCompra();
            stmt.setDouble(1, result);
            stmt.setInt(2, cli.getIdCartao());
            stmt.executeUpdate();
            stmt.close();
            //comprar(cli, compra);

        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void buscarAprovacoes() {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM `usuario`");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setId(rs.getInt("id_usuario"));
                cli.setNome(rs.getString("nome_completo"));
                cli.setCpf(rs.getString("cpf"));
                cli.setRenda(Double.parseDouble(rs.getString("renda")));

                clientes.add(cli);
            }

        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void bloquearCartao(CartaoCredito cc) {
        //Cria conexão com o banco
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE cartao_credito SET status_cartao = " + 1 + " WHERE cartao_credito.id_cartao = " + cc.getIdCartao() + " AND cartao_credito.id_cliente = " + cc.getIdUsuario());
//            stmt.setInt(1, cc.getIdCartao());
//            stmt.setInt(2, cc.getIdUsuario());
            System.out.println("bloqueou cartão");

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void desbloquearCartao(CartaoCredito cc) {
        //Cria conexão com o banco
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE cartao_credito SET status_cartao = " + 0 + " WHERE cartao_credito.id_cartao = " + cc.getIdCartao() + " AND cartao_credito.id_cliente = " + cc.getIdUsuario());
//            stmt.setInt(1, cc.getIdCartao());
//            stmt.setInt(2, cc.getIdUsuario());
            System.out.println("bloqueou cartão");

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public Cliente buscarCpf(Cliente cli) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT id_usuario, id_cartao FROM usuario WHERE cpf LIKE ?");

            stmt.setString(1, cli.getCpf());
            rs = stmt.executeQuery();
            rs.next();
            cli.setId(rs.getInt("id_usuario"));
            cli.setIdCartao(rs.getInt("id_cartao"));

        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }
        return cli;
    }

    public void mudaStatus(int id) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET status_cadastro = 1 WHERE id_usuario = ? ");
            stmt.setInt(1, id);
            //EXECUTA
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void pagarFatura(int id, double valor) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE cartao_credito SET valor_limite = valor WHERE id_usuario = id ");
            if (valor <= Double.parseDouble(rs.getString("valor_limite"))) {
                double result = (Double.parseDouble(rs.getString("valor_limite")) - valor);
                stmt.setDouble(1, result);
                stmt.setInt(2, id);
                stmt.executeUpdate();
                stmt.close();

            } else {
                System.out.println("Erro");
            }

            //EXECUTA
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void recusaCliente(int id) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM `usuario` WHERE `usuario`.`id_usuario` = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public void cancelarCartao(int id) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM `cartao_credito` WHERE `cartao_credito`.`id_cartao` = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        } finally {
            ConexaoBD.fechaConexao(con);
        }

    }

    public boolean verificaSaldo(Double valor) {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        return true;
    }

    public boolean verificaPessoaCartao() throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_cartao IS Null AND id_usuario=?");
            stmt.setInt(1, Login.idCliente);
            rs = stmt.executeQuery();
            rs.next();
        } catch (Exception e) {
        }
        if (rs.getInt("id_cartao") == 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean verificaStatusCartao() throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM cartao_credito WHERE status_cartao=0 AND id_cliente=?");
            stmt.setInt(1, Login.idCliente);

            rs = stmt.executeQuery();
            rs.next();
        } catch (Exception e) {
        }

        if (rs.getInt("status_cartao") == 1) {
            System.out.println("cartão cancelado");
            return true;

        } else {
            System.out.println("cartão não cancelado");
            return false;
        }

    }

    public static ResultSet consultarClientes() {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        Cliente cli = new Cliente();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE status_cadastro = 1");
            rs = stmt.executeQuery();

        } catch (Exception e) {
            System.out.println("erro> " + e);
        }

        return rs;

    }

}
