package casas;
import java.util.List;
import jogadores.Jogador;
public class CasaPerdeRodada extends Casa {
    
    private Jogador jogadorPerdeRodada;

    public CasaPerdeRodada(int posicao){
        super(posicao);
    }
    
    public Jogador getJogadorPerdeRodada(){
        return this.jogadorPerdeRodada;
    }

    @Override
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        this.jogadorPerdeRodada = jogador;
    
        return jogador;
    }
}
