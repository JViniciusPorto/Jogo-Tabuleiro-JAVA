package casas;
import java.util.List;
import jogadores.*;

public class Casa{
    protected int posicao;
    
    public Casa(int posicao){
        this.posicao = posicao;
    }

    public int getPosicao(){
        return this.posicao;
    }
    
    public Jogador executarEfeito(Jogador jogadorAtual, List<Jogador> todosJogadores){
        
        return jogadorAtual;
    }
}