import Jogadores.Jogador;
public abstract class Casa{
    protected int posicao;
    
    public Casa(int posicao){
        this.posicao = posicao;
    }

    public int getPosicao(){
        return this.posicao;
    }
    
    public abstract void executarEfeito(Jogador jogador);

     
}