package jogadores;
import java.util.Random;
public abstract class Jogador {
    private String cor;
    private int posicao;
    private int quantidadeDeJogadas;
    protected int dado1;
    protected int dado2;
    protected Random rolardados;
    private boolean isPerdeProximaRodada = false;
    public Jogador(String cor){
        this.cor = cor;
        this.posicao = 0;
        this.quantidadeDeJogadas = 0;
        rolardados = new Random();
    }

    public Jogador (Jogador jogador){
        this.cor = jogador.getCor();
        this.posicao = jogador.getPosicao();
        this.quantidadeDeJogadas = jogador.getQuantidadeDeJogadas();
        this.dado1 = jogador.getDado1();
        this.dado2 = jogador.getDado2();
        this.rolardados = new Random();
    }
    public abstract boolean temSorte();
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

    public void jogar(int quantidadeDeCasas){
        this.posicao = quantidadeDeCasas;
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
    public boolean getIsPerdeProximaRodada(){
        return this.isPerdeProximaRodada;
    }

    public void setPosicao(int posicao){
        this.posicao = posicao;
    }

    public void setIsPerdeProximaRodada(boolean estado){
        this.isPerdeProximaRodada = estado;
    }
}
