package com.lustozas.controllers;

import com.lustozas.NavegadorDeTelas;
import jogo.Jogo;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaTipoJogadorController {

    @FXML
    private VBox containerJogadores;

    @FXML
    private Label labelErro;

    private Jogo jogo;
    private final List<ChoiceBox<String>> seletoresDeTipo = new ArrayList<>();

    private static final String[] CORES = {
            "Vermelho", "Azul", "Amarelo", "Verde", "Branco", "Preto"
    };

    public void configurar(Jogo jogo, int quantidadeDeJogadores) {
        this.jogo = jogo;
        seletoresDeTipo.clear();
        containerJogadores.getChildren().clear();

        for (int i = 0; i < quantidadeDeJogadores; i++) {
            Label label = new Label("Jogador [" + CORES[i] + "]:");
            label.getStyleClass().add("subtitulo");
            
            ChoiceBox<String> seletor = new ChoiceBox<>();
            seletor.getItems().addAll("Normal", "Sortudo", "Azarado");
            seletor.setValue("Normal");

            HBox linha = new HBox(12, label, seletor);
            containerJogadores.getChildren().add(linha);
            seletoresDeTipo.add(seletor);
        }
    }

    @FXML
    private void aoClicarJogar() {
        List<Integer> tipos = new ArrayList<>();
        for (ChoiceBox<String> seletor : seletoresDeTipo) {
            tipos.add(converterTipo(seletor.getValue()));
        }

        if (tipos.stream().distinct().count() < 2) {
            labelErro.setText("Escolha pelo menos 2 tipos diferentes de jogador.");
            labelErro.setVisible(true);
            return;
        }
        labelErro.setVisible(false);

        jogo.criarJogadores(tipos);

        try {
            TelaPartidaController controller =
                    NavegadorDeTelas.trocarTelaComControlador("/tela-partida.fxml");
            controller.configurar(jogo);
        } catch (IOException e) {
            labelErro.setText("Erro ao carregar a tela da partida.");
            labelErro.setVisible(true);
        }
    }

    private int converterTipo(String texto) {
        return switch (texto) {
            case "Normal" -> 0;
            case "Sortudo" -> 1;
            default -> 2;
        };
    }
}