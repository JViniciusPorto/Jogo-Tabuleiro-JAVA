package jogo;
//importa todas as classes que existem nos pacotes
import casas.*;
import jogadores.*;
import tabuleiro.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Jogo2 {
    private List<Jogador> jogadores;
    private Scanner teclado;
    private Tabuleiro tabuleiro;
    private final String[] CORES_DISPONIVEIS = {
            "Vermelho", "Azul", "Amarelo", "Verde", "Branco", "Preto"
    };
    private int quantidadeDeJogadores;
    private boolean isModoDebug = false;
    private boolean isJogoEncerrado = false;
    public Jogo2(){
        jogadores = new ArrayList<>();
        teclado = new Scanner(System.in);
        tabuleiro = new Tabuleiro();
        
    }
    public void iniciarJogo(){
        while(true){
            escolherModo();
            if(this.isJogoEncerrado){
                break;
            }
            
            escolherQuantidadeJogadores();
            this.jogadores.clear();
            criarJogadores();
            jogarPartida();

            System.out.println("Voltar para início? [pressione Enter]");
            scanner.nextLine();

        }
    }

    private void escolherModo(){
        System.out.println("Escolha o modo de jogo");
        System.out.println("1 - Normal");
        System.out.println("2 - Modo Debug");
        System.out.println("3 - Sair");
        int opcao=lerInteiroEntre(1, 3);
        if(opcao==2){
            this.isModoDebug = true;
        }else if(opcao==3){
            this.isJogoEncerrado = true;
        }
    }

    private void escolherQuantidadeJogadores(){
        System.out.println("Quantos jogadores vão participar?");
        this.quantidadeDeJogadores = lerInteiroEntre(2, 6);
    }

    private int lerInteiroEntre(int min, int max){
        int entrada;
        while(true){
            if(!teclado.hasNextInt()){
                System.out.println("Digite um inteiro!");
                teclado.nextLine();
                System.out.println("Digite novamente!");
                continue;
            }
            entrada = teclado.nextInt();
            if(entrada<min || entrada>max){
                System.out.println("Digite um inteiro entre "+min+" e "+max+"1");
                System.out.println("Digite novamente");
                continue;
            }
            break;

        }
        return entrada;
    }

    private void criarJogadores() {
        List<Integer> tipos = escolherTipos(quantidadeDeJogadores);

        for (int i = 0; i < quantidadeDeJogadores; i++) {
            String cor = CORES_DISPONIVEIS[i];
            jogadores.add(instanciarPorTipo(tipos.get(i), cor));
        }
    }

    private List<Integer> escolherTipos(int quantidade) {
        List<Integer> tipos = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Escolha o tipo do jogador [" + CORES_DISPONIVEIS[i] + "]:");
            System.out.println("1 - Normal");
            System.out.println("2 - Sortudo");
            System.out.println("3 - Azarado");
            int opcao = lerInteiroEntre(1, 3);
            tipos.add(opcao - 1);
        }

        boolean somenteUmTipo = tipos.stream().distinct().count() == 1;
        if (somenteUmTipo) {
            System.out.println("É necessário pelo menos 2 tipos diferentes de jogador. "
                    + "Escolha novamente o tipo de um dos jogadores:");
            corrigirTipoRepetido(tipos);
        }

        return tipos;
    }
    
    private void corrigirTipoRepetido(List<Integer> tipos){
        //tipos.stream() retorna um objeto da lista,
        //tipos.stream().distinct() retorna só os objetos diferentes
        //tipos.stream().distinct().count() conta quantos objetos são diferentes
        while (tipos.stream().distinct().count() == 1) {
            System.out.println("Escolha o novo tipo do jogador [" + CORES_DISPONIVEIS[0] + "]:");
            System.out.println("1 - Normal");
            System.out.println("2 - Sortudo");
            System.out.println("3 - Azarado");
            int opcao = lerInteiroEntre(1, 3);
            tipos.set(0, opcao - 1);
        }
    }

    private Jogador instanciarPorTipo(int tipo, String cor) {
        return switch (tipo) {
            case 0 -> new JogadorNormal(cor);
            case 1 -> new JogadorSortudo(cor);
            default -> new JogadorAzarado(cor);
        };
    }

    private void jogarPartida() {
        int indiceAtual = 0;

        while (true) {
            Jogador jogadorAtual = jogadores.get(indiceAtual);

            if (jogadorAtual.getIsPerdeProximaRodada()) {
                jogadorAtual.setIsPerdeProximaRodada(false);
                indiceAtual = proximoIndice(indiceAtual);
                continue;
            }

            System.out.println("\nVez do jogador " + jogadorAtual.getCor() + ". Jogar dados? [pressione Enter]");
            scanner.nextLine();


            if (modoDebug) {
                System.out.println("[DEBUG] Digite a casa para onde o jogador deve ir (0 a 40):");
                int posicaoEscolhida = lerInteiroEntre(0,40);
                jogadorAtual.jogar(posicaoEscolhida);
            } else {
                jogadorAtual.jogar();
                System.out.println("Soma dos dados: " + (jogadorAtual.getDado1() + jogadorAtual.getDado2()));
            }

            if (jogadorAtual.getPosicao() >= 40) {
                declararVencedor(jogadorAtual);
                exibirResultadosFinais();
                return;
            }

            Casa casa = tabuleiro.getCasa(jogadorAtual.getPosicao());
            Jogador jogadorAtualizado = casa.executarEfeito(jogadorAtual, jogadores);
            jogadores.set(indiceAtual, jogadorAtualizado);

            if (jogadorAtualizado.getPosicao() >= 40) {
                declararVencedor(jogadorAtualizado);
                exibirResultadosFinais();
                return;
            }

            exibirPosicoes();

            if (!jogadorAtualizado.dadosIguais()) {
                indiceAtual = proximoIndice(indiceAtual);
            }
        }
    }
    private int proximoIndice(int indiceAtual) {
        indiceAtual += 1;
        if(indiceAtual>=jogadores.size()){
            indiceAtual = 0;
        }
        return indiceAtual;
    }

    private void exibirPosicoes() {
        for (Jogador j : jogadores) {
            System.out.printf("%s na casa %d, ",j.getCor(),j.getPosicao());
        }
        System.out.println();
    }
    private void declararVencedor(Jogador jogador) {
        System.out.println("[            PARTIDA ENCERRADA            ]");
        System.out.println("Jogador " + jogador.getCor() + " venceu o jogo!");
    }

    private void exibirResultadosFinais() {
        System.out.println("---- Resultado final ----");
        for (Jogador j : jogadores){
            System.out.printf("%d  - Posição: %d Jogadas: %d\n",j,getCor(),j.getPosicao(),j.getQuantidadeDeJogadas());
        }
    }
}
