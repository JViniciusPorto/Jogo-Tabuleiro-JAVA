package jogo;
//importa todas as classes que existem nos pacotes
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import casas.Casa;
import jogadores.*;
import tabuleiro.Tabuleiro;

public class Jogo {

    private List<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private final String[] CORES_DISPONIVEIS = {
            "Vermelho", "Azul", "Amarelo", "Verde", "Branco", "Preto"
    };
    private boolean isModoDebug;
    private int indiceAtual = 0;
    private String ultimaMensagem = "";

    public Jogo() {
        this.jogadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
    }

    public void configurarModo(boolean modoDebug) {
        this.isModoDebug = modoDebug;
    }

    public boolean isModoDebug() {
        return this.isModoDebug;
    }

    public void criarJogadores(List<Integer> tiposEscolhidos) {
        jogadores.clear();
        for (int i = 0; i < tiposEscolhidos.size(); i++) {
            jogadores.add(instanciarPorTipo(tiposEscolhidos.get(i), CORES_DISPONIVEIS[i]));
        }
        indiceAtual = 0;
    }

    private Jogador instanciarPorTipo(int tipo, String cor) {
        return switch (tipo) {
            case 0 -> new JogadorNormal(cor);
            case 1 -> new JogadorSortudo(cor);
            default -> new JogadorAzarado(cor);
        };
    }

    public Jogador jogarTurno(Integer posicaoDebugOpcional) {
        while (jogadores.get(indiceAtual).getIsPerdeProximaRodada()) {
            jogadores.get(indiceAtual).setIsPerdeProximaRodada(false);
            indiceAtual = proximoIndice(indiceAtual);
        }

        Jogador jogadorAtual = jogadores.get(indiceAtual);
        ultimaMensagem = "";

        if (isModoDebug) {
            jogadorAtual.jogar(posicaoDebugOpcional);
        } else {
            jogadorAtual.jogar();
        }

        if (jogadorAtual.getPosicao() >= 40) {
            return jogadorAtual;
        }

        Casa casa = tabuleiro.getCasa(jogadorAtual.getPosicao());
        Jogador jogadorAtualizado = casa.executarEfeito(jogadorAtual, jogadores);
        ultimaMensagem = casa.getUltimaMensagem();
        jogadores.set(indiceAtual, jogadorAtualizado);

        if (jogadorAtualizado.getPosicao() >= 40) {
            return jogadorAtualizado;
        }

        boolean jogaDeNovo = !isModoDebug && jogadorAtualizado.dadosIguais();
        if (!jogaDeNovo) {
            indiceAtual = proximoIndice(indiceAtual);
        }

        return null;
    }

    private int proximoIndice(int indice) {
        return (indice + 1) % jogadores.size();
    }

    public Jogador getJogadorDaVez() {
        return jogadores.get(indiceAtual);
    }

    public String getUltimaMensagem() {
        return this.ultimaMensagem;
    }

    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }

    // NOVO: necessário pra tela da partida desenhar o tabuleiro e colorir cada casa por tipo
    public List<Casa> getCasas() {
        return tabuleiro.getCasas();
    }
}