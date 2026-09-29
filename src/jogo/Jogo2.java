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
    private static String[] CoresDisponiveis = {
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

    private void corrigirTipoRepetido(List<Integer> tipos){
        //tipos.stream() retorna um objeto da lista,
        //tipos.stream().distinct() retorna só os objetos diferentes
        //tipos.stream().distinct().count() conta quantos objetos são diferentes
        while(tipos.stream().distinct().count()==1){
            System.out.println("Escolha o novo tipo do jogador "+Co)
        }
    }

}
