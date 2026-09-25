package Jogadores;
import java.util.Random;
public abstract class Jogador {
    private String cor;
    private int posicao;
    private int quantidadeDeJogadas;
    protected int dado1;
    protected int dado2;
    protected Random rolardados;
    public Jogador(String cor){
        this.cor = cor;
        this.posicao = 0;
        this.quantidadeDeJogadas = 0;
        rolardados = new Random();
    }

    public abstract int jogarDados();

    public boolean dadosIguais(){
        return this.dado1==this.dado2;
    }

    private void mover(int quantidade){
        this.posicao += quantidade;
    }

    public void jogar(){
        int casasParaAndar = jogarDados();
        mover(casasParaAndar);
        this.quantidadeDeJogadas += 1;
    }

    public String getCor(){
        return this.cor;
    }

    public int getPosicao(){
        return this.posicao;
    }

    public int getQuantidadeDeJogadas(){
        return this.quantidadeDeJogadas;
    }

    public int getDado1(){
        return this.dado1;
    }

    public int getDado2(){
        return this.dado2;
    }
}
