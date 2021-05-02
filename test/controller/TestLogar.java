package controller;

import DAO.ClienteDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import model.*;
import static org.junit.Assert.assertEquals;

public class TestLogar {

    Cliente cliente, cliente1;

    @Before
    public void instanciarClienteTeste() {
        //Método para testar login de cliente
        cliente = new Cliente();
        cliente1 = new Cliente();
    }

    @After
    public void limparObjetos() {
        cliente = null;
        cliente1 = null;
    }

    @Test
    public void logarCliente() throws SQLException {
        cliente.setUsuario("teste");
        cliente.setSenha("teste");
        ClienteDao bd = new ClienteDao();
        ResultSet rs = bd.logar(cliente);

        cliente1.setTipoCadastro(0);

        if (rs.next()) {
            cliente.setTipoCadastro(rs.getInt("tipo_cadastro"));
        }
        if (cliente.getTipoCadastro() == 0) {
            assertEquals(cliente1.getTipoCadastro(), cliente.getTipoCadastro());
        } else {
            Assert.fail("Não possuí nenhum usuário com esse login/senha");
        }
    }

    @Test
    public void logarEmpresa() throws SQLException {
        cliente.setUsuario("adm");
        cliente.setSenha("123");
        cliente1.setTipoCadastro(1);
        ClienteDao bd = new ClienteDao();
        ResultSet rs = bd.logar(cliente);

        if (rs.next()) {
            cliente.setTipoCadastro(rs.getInt("tipo_cadastro"));
        }
        if (cliente.getTipoCadastro() == 1) {
            assertEquals(cliente1.getTipoCadastro(), cliente.getTipoCadastro());
        } else {
            Assert.fail("Não possuí nenhum login com esse usuário");
        }
    }
}
