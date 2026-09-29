package jogo;

import casas.Casa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import jogadores.Jogador;
import jogadores.JogadorAzarado;
import jogadores.JogadorNormal;
import jogadores.JogadorSortudo;
import tabuleiro.Tabuleiro;

public class Jogo {

    private static final String[] CORES_DISPONIVEIS =
            { "Azul", "Verde", "Amarelo", "Branco", "Vermelho", "Preto" };

    private final List<Jogador> jogadores;
    private final Tabuleiro tabuleiro;
    private final Scanner scanner;
    private boolean modoDebug;
    private int quantidadeDeJogadores;
    private boolean continuarJogando = true;
    public Jogo() {
        this.jogadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
        this.scanner = new Scanner(System.in);
    }

    // ---------------- Menu / início ----------------

    public void iniciar() {
        //esse while nunca acaba
        while (this.continuarJogando) {
            escolherModo();
            if(this.continuarJogando==false){
                break;
            }
            escolherQuantidadeDeJogadores();
            jogadores.clear();
            criarJogadores();
            jogarPartida();

            System.out.println("Voltar para início? [pressione Enter]");
            scanner.nextLine();
        }
    }

    private void escolherModo() {
        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Normal");
        System.out.println("2 - Debug");
        System.out.println("3 - Sair");
        int opcao = lerInteiroEntre(1, 3);
        this.modoDebug = (opcao == 2);
        if(opcao==3){
            this.continuarJogando = false;
        }
    }

    private void escolherQuantidadeDeJogadores() {
        System.out.println("Quantos jogadores vão participar? (2 a 6)");
        this.quantidadeDeJogadores = lerInteiroEntre(2, 6);
    }

    private int lerInteiroEntre(int min, int max) {
        while (true) {
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException ignorado) {
                // cai no println abaixo
            }
            System.out.println("Valor inválido. Digite um número entre " + min + " e " + max + ":");
        }
    }

    // ---------------- Criação dos jogadores ----------------

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
            tipos.add(opcao - 1); // 0=Normal, 1=Sortudo, 2=Azarado
        }

        boolean somenteUmTipo = tipos.stream().distinct().count() == 1;
        if (somenteUmTipo) {
            System.out.println("É necessário pelo menos 2 tipos diferentes de jogador. "
                    + "Escolha novamente o tipo de um dos jogadores:");
            corrigirTipoRepetido(tipos);
        }

        return tipos;
    }

    private void corrigirTipoRepetido(List<Integer> tipos) {
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

    // ---------------- Loop principal ----------------

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

            boolean dadosIguaisNestaJogada = false;

            if (modoDebug) {
                int posicaoEscolhida = perguntarPosicaoDebug();
                jogadorAtual.jogar(posicaoEscolhida); // jogar(int) fixa a posição direto
            } else {
                jogadorAtual.jogar(); // rola dados e move via mover() interno
                System.out.println("Soma dos dados: " + (jogadorAtual.getDado1() + jogadorAtual.getDado2()));
                dadosIguaisNestaJogada = jogadorAtual.dadosIguais();
            }

            if (jogadorAtual.getPosicao() >= 40) {
                declararVencedor(jogadorAtual);
                exibirResultadosFinais();
                return; // encerra a partida
            }

            // aplica o efeito da casa; a própria casa decide se mexe na posição de novo
            Casa casa = tabuleiro.getCasa(jogadorAtual.getPosicao());
            Jogador jogadorAtualizado = casa.executarEfeito(jogadorAtual, jogadores);
            jogadores.set(indiceAtual, jogadorAtualizado);

            // o efeito da casa (ex.: CasaSorte) pode ter empurrado o jogador para >= 40
            if (jogadorAtualizado.getPosicao() >= 40) {
                declararVencedor(jogadorAtualizado);
                exibirResultadosFinais();
                return;
            }

            exibirPosicoes();

            boolean jogaDeNovo = dadosIguaisNestaJogada; // só se aplica ao modo normal
            if (!jogaDeNovo) {
                indiceAtual = proximoIndice(indiceAtual);
            }
        }
    }

    private int proximoIndice(int indiceAtual) {
        return (indiceAtual + 1) % jogadores.size();
    }

    private int perguntarPosicaoDebug() {
        System.out.println("[DEBUG] Digite a casa para onde o jogador deve ir (0 a 40):");
        return lerInteiroEntre(0, 40);
    }

    private void exibirPosicoes() {
        StringBuilder sb = new StringBuilder();
        for (Jogador j : jogadores) {
            sb.append(j.getCor()).append(" na casa ").append(j.getPosicao()).append(", ");
        }
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }
        System.out.println(sb);
    }

    // ---------------- Encerramento ----------------

    private void declararVencedor(Jogador jogador) {
        System.out.println("[            PARTIDA ENCERRADA            ]");
        System.out.println("Jogador " + jogador.getCor() + " venceu o jogo!");
    }

    private void exibirResultadosFinais() {
        System.out.println("---- Resultado final ----");
        for (Jogador j : jogadores) {
            System.out.println(j.getCor()
                    + " - posição: " + j.getPosicao()
                    + " - jogadas: " + j.getQuantidadeDeJogadas());
        }
    }

    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }
}