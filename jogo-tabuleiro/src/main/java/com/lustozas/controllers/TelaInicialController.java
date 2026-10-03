package com.lustozas.controllers;

import com.lustozas.NavegadorDeTelas;
import jogo.Jogo;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private RadioButton radioDebug;

    @FXML
    private ChoiceBox<String> choiceQuantidade;

    @FXML
    private Label labelErro;

    private final Jogo jogo = new Jogo();

    @FXML
    private void aoClicarAvancar() {
        if (choiceQuantidade.getValue() == null) {
            labelErro.setText("Escolha a quantidade de jogadores.");
            labelErro.setVisible(true);
            return;
        }
        labelErro.setVisible(false);

        jogo.configurarModo(radioDebug.isSelected());
        int quantidade = Integer.parseInt(choiceQuantidade.getValue());

        try {
            TelaTipoJogadorController controller =
                    NavegadorDeTelas.trocarTelaComControlador("/tela-tipo-jogador.fxml");
            controller.configurar(jogo, quantidade);
        } catch (IOException e) {
            labelErro.setText("Erro ao carregar a próxima tela.");
            labelErro.setVisible(true);
        }
    }

    @FXML
    private void aoClicarSair() {
        System.exit(0);
    }
}