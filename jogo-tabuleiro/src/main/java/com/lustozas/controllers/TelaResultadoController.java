package com.lustozas.controllers;

import com.lustozas.NavegadorDeTelas;
import jogadores.Jogador;
import jogo.Jogo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TelaResultadoController {

    @FXML private Label labelVencedor;
    @FXML private VBox containerResultados;

    public void configurar(Jogo jogo, Jogador vencedor) {
        labelVencedor.setText("Jogador " + vencedor.getCor() + " venceu a partida!");

        containerResultados.getChildren().clear();
        for (Jogador j : jogo.getJogadores()) {
            Label linha = new Label(String.format("%s — posição: %d — jogadas: %d",
                    j.getCor(), j.getPosicao(), j.getQuantidadeDeJogadas()));
            linha.getStyleClass().add("subtitulo");
            containerResultados.getChildren().add(linha);
        }
    }

    @FXML
    private void aoClicarVoltar() {
        try {
            NavegadorDeTelas.trocarTela("/tela-inicial.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}