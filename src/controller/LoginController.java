package controller;

import DAO.ClienteDao;
import app.CadastroPessoa;
import app.Empresa;
import app.Login;
import app.PrincipalCliente;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.*;

public class LoginController implements Initializable {

    @FXML
    private Button btAcessar;
    @FXML
    private PasswordField txSenha;
    @FXML
    private Button btSair;
    @FXML
    private TextField txEmail;
    @FXML
    private Button btSolicitar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btAcessar.setOnMouseClicked((t) -> {
            try {
                logar();
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        btSair.setOnMouseClicked((t) -> {
            fechar();

        });
        btSair.setOnKeyPressed((t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                fechar();
            }

        });
        btAcessar.setOnKeyPressed((t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                try {
                    logar();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        txSenha.setOnKeyPressed((t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                try {
                    logar();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        btSolicitar.setOnMouseClicked((t) -> {
            cadastrarPessoa();

        });

    }

    public void fechar() {
        Login.getStage().close();
    }

    public void logar() throws SQLException, IOException {
        Cliente cliente = new Cliente();
        cliente.setUsuario(txEmail.getText());
        cliente.setSenha(txSenha.getText());
        ClienteDao bd = new ClienteDao();

        ResultSet rs = bd.logar(cliente);
//
        if (rs.next()) {
            Login.idCliente = rs.getInt("id_usuario");
//            int teste = rs.getInt("tipo_cadastro");
            if (rs.getInt("tipo_cadastro") == 0) {

                if (rs.getInt("status_cadastro") == 0) {

                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Erro");
                    alert.setTitle("erro");
                    alert.setContentText("Conta em analise");
                    alert.show();
                } else {
                    PrincipalCliente p = new PrincipalCliente();
                    fechar();
                    p.start(new Stage());

                }

            } else {
                Empresa pEmpresa = new Empresa();
                fechar();
                pEmpresa.start(new Stage());
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Erro");
            alert.setTitle("Login Invalido");
            alert.setContentText("O erro aconteceu devido ao usuario ser invalido");
            alert.show();
        }

    }

    public void cadastrarPessoa() {

        CadastroPessoa cp = new CadastroPessoa();
        fechar();
        try {
            cp.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public boolean verificarLogin(Cliente cliente) throws SQLException {
//        ClienteDao bd = new ClienteDao();
//        ResultSet rs = bd.logar(cliente);
//        rs.next();
//        Login.idCliente = rs.getInt("id_usuario");
//        if (rs.getInt("tipo_cadastro") == 0 && rs.getInt("status_cadastro") == 0) {
//            return true;
//        } else {
//            if (rs.getInt("tipo_cadastro") == 1 && rs.getInt("status_cadastro") == 0) {
//                return false;
//            }
//        }

    }
