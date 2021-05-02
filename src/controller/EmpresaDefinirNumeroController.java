/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import app.Empresa;
import app.EmpresaDefinirNumero;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CartaoCredito;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class EmpresaDefinirNumeroController implements Initializable {

    @FXML
    private TextField txCC;

    @FXML
    private TextField txNomeCliente;

    @FXML
    private TextField txValidade;

    @FXML
    private TextField txVencimento;

    @FXML
    private TextField txValorLimite;

    @FXML
    private TextField txDataEmissao;

    @FXML
    private TextField txSenha;
    @FXML
    private TextField txIdCliente;

    @FXML
    private Button btCadastrar;

    @FXML
    private TextField txDataFechamento;

    @FXML
    private Button btSair;

    @FXML
    private TextField txNmCartao;

    @FXML
    private TextField txVariante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btSair.setOnMouseClicked((t) -> {
            Empresa emp = new Empresa();
            fechar();
            try {
                emp.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });
        btCadastrar.setOnMouseClicked((t) -> {
           
            cadastrarCartao();
        });
        
        
        // TODO
    }
      public void cadastrarCartao() {
        CartaoCredito cli = new CartaoCredito();

        ClienteDao cadastrarCartao = new ClienteDao();
        
        cli.setIdUsuario(Integer.parseInt(txIdCliente.getText()));
        cli.setNumeroCartao(Integer.parseInt(txNmCartao.getText()));
        cli.setSenha(Integer.parseInt(txSenha.getText()));
        cli.setValidadeCartao(txValidade.getText());
        cli.setCvv(Integer.parseInt(txCC.getText()));
        cli.setVariante(txVariante.getText());
        cli.setNomeCartao(txNomeCliente.getText());
        cli.setDataEmissao(txDataEmissao.getText());
        cli.setDataFechamento(txDataFechamento.getText());
        cli.setDataVencimento(txVencimento.getText());
        cli.setValorLimite(Double.parseDouble(txValorLimite.getText()));
        cli.setValorDisponivel(cli.getValorLimite());
        System.out.println("teste, cadastrou: " + cli.getIdUsuario());
        
        cadastrarCartao.mudaStatus(cli.getIdUsuario());
        cadastrarCartao.adicionaCartao(cli);
       

        //CRIAR TODAS AS VERIFICAÇÕES  
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Seu cadastro foi efetuado com sucesso");
        alert.show();
        fechar();
    }
      

    public void fechar() {
        EmpresaDefinirNumero.getStage().close();
    }

}
