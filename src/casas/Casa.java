package casas;
import java.util.List;
import jogadores.Jogador;

public abstract class Casa{
    protected int posicao;
    
    public Casa(int posicao){
        this.posicao = posicao;
    }

    public int getPosicao(){
        return this.posicao;
    }
    
    public abstract Jogador executarEfeito(Jogador jogadorAtual, List<Jogador> todosJogadores);

     
}