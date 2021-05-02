/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import DAO.ConexaoBD;
import app.CadastroPessoa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class CadastroPessoaController implements Initializable {

    @FXML
    private TextField txRua;

    @FXML
    private TextField txSenha;

    @FXML
    private TextField txPais;

    @FXML
    private TextField txNome;

    @FXML
    private TextField txCpf;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txTelefone;

    @FXML
    private TextField txNumero;

    @FXML
    private TextField txUsuario;

    @FXML
    private TextField txEstado;

    @FXML
    private TextField txCidade;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField txRenda;
    @FXML
    private TextField txBairro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btCancelar.setOnMouseClicked((t) -> {
            fechar();

        });
        btCadastrar.setOnMouseClicked((t) -> {
            cadastrarPessoa();

        });
    }

    public void fechar() {
        CadastroPessoa.getStage().close();
    }

    public void cadastrarPessoa() {
        Cliente cli = new Cliente();
        ClienteDao cadastrarCliente = new ClienteDao();

        cli.setNome(txNome.getText());
        cli.setUsuario(txUsuario.getText());
        cli.setSenha(txSenha.getText());
        cli.setCpf(txCpf.getText());
        cli.setTelefone(Integer.parseInt(txTelefone.getText()));
        cli.setEmail(txEmail.getText());
        cli.setRenda(Double.parseDouble(txRenda.getText()));
        cli.setBairro(txBairro.getText());
        cli.setEstado(txEstado.getText());
        cli.setPais(txPais.getText());
        cli.setRua(txRua.getText());
        cli.setNumero(Integer.parseInt(txNumero.getText()));
        cli.setCidade(txCidade.getText());
        

        System.out.println("teste, cadastrou: " + cli.getUsuario());
        cadastrarCliente.adicionaCliente(cli);

   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pendente");
        alert.setContentText("Seu cadastro foi efetuado com sucesso, aguarde a liberacao da sua conta!");
        alert.show();
        fechar();
    }

}
