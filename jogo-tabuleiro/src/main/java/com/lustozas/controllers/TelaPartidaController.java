package com.lustozas.controllers;

import casas.*;
import com.lustozas.NavegadorDeTelas;
import jogadores.*;
import jogo.Jogo;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class TelaPartidaController {

    private static final int COLUNAS = 8;
    private static final int TOTAL_CASAS = 40;

    @FXML private Label labelVezDe;
    @FXML private GridPane tabuleiroGrid;
    @FXML private HBox caixaDebug;
    @FXML private Spinner<Integer> spinnerPosicaoDebug;
    @FXML private Button botaoJogarDados;
    @FXML private TextArea areaLog;

    private Jogo jogo;
    private final Text[] marcadores = new Text[TOTAL_CASAS];

    public void configurar(Jogo jogo) {
        this.jogo = jogo;
        montarTabuleiro();
        configurarModoDebug();
        atualizarMarcadoresNoTabuleiro();
        atualizarLabelDeTurno();
    }

    private void montarTabuleiro() {
        List<Casa> casas = jogo.getCasas();

        for (int posicao = 0; posicao < TOTAL_CASAS; posicao++) {
            Casa casa = casas.get(posicao);

            Label numero = new Label(String.valueOf(posicao));
            numero.getStyleClass().add("numero-casa");

            Text marcador = new Text("");
            marcador.getStyleClass().add("marcador-jogador");
            marcadores[posicao] = marcador;

            VBox celula = new VBox(2, numero, marcador);
            celula.getStyleClass().addAll("casa", classeCssDaCasa(casa));
            celula.setPrefSize(68, 52);
            celula.setAlignment(Pos.CENTER);

            tabuleiroGrid.add(celula, posicao % COLUNAS, posicao / COLUNAS);
        }
    }

    private String classeCssDaCasa(Casa casa) {
        return switch (casa.getClass().getSimpleName()) {
            case "CasaSorte" -> "casa-sorte";
            case "CasaPerdeRodada" -> "casa-perde-rodada";
            case "CasaSurpresa" -> "casa-surpresa";
            case "CasaVoltaInicio" -> "casa-volta-inicio";
            case "CasaMagica" -> "casa-magica";
            default -> "casa-normal";
        };
    }

    private void configurarModoDebug() {
        boolean modoDebug = jogo.isModoDebug();
        caixaDebug.setVisible(modoDebug);
        caixaDebug.setManaged(modoDebug);
        if (modoDebug) {
            spinnerPosicaoDebug.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 40, 0));
        }
    }

    @FXML
    private void aoClicarJogarDados() {
        try {
            Integer posicaoDebug = jogo.isModoDebug() ? spinnerPosicaoDebug.getValue() : null;
            Jogador jogadorQueJogou = jogo.getJogadorDaVez();

            Jogador vencedor = jogo.jogarTurno(posicaoDebug);

            registrarLog(jogadorQueJogou);
            atualizarMarcadoresNoTabuleiro();

            if (vencedor != null) {
                irParaTelaDeResultado(vencedor);
            } else {
                atualizarLabelDeTurno();
            }
        } catch (Exception e) {
            mostrarErro("Ocorreu um erro ao processar a jogada: " + e.getMessage());
        }
    }

    private void registrarLog(Jogador jogadorQueJogou) {
    StringBuilder log = new StringBuilder();
    log.append("» ").append(jogadorQueJogou.getCor())
       .append(" jogou e foi para a casa ")
       .append(jogadorQueJogou.getPosicao())
       .append(".\n");

    String mensagemCasa = jogo.getUltimaMensagem();
    if (mensagemCasa != null && !mensagemCasa.isBlank()) {
        log.append(mensagemCasa).append("\n");
    }

    for (Jogador j : jogo.getJogadores()) {
        log.append("   ").append(j.getCor()).append(": casa ").append(j.getPosicao()).append("\n");
    }

    log.append("――――――――――――――――――\n\n");
    areaLog.appendText(log.toString());
}

    private void atualizarMarcadoresNoTabuleiro() {
        for (Text marcador : marcadores) {
            marcador.setText("");
        }
        for (Jogador j : jogo.getJogadores()) {
            int posicao = Math.min(j.getPosicao(), TOTAL_CASAS - 1);
            marcadores[posicao].setText(marcadores[posicao].getText() + inicialDoJogador(j) + " ");
        }
    }

    private String inicialDoJogador(Jogador jogador) {
        return jogador.getCor().substring(0, 1).toUpperCase();
    }

    private void atualizarLabelDeTurno() {
        labelVezDe.setText("Vez do jogador: " + jogo.getJogadorDaVez().getCor());
    }

    private void irParaTelaDeResultado(Jogador vencedor) {
        try {
            TelaResultadoController controller =
                    NavegadorDeTelas.trocarTelaComControlador("/tela-resultado.fxml");
            controller.configurar(jogo, vencedor);
        } catch (IOException e) {
            mostrarErro("Erro ao carregar a tela de resultado.");
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}