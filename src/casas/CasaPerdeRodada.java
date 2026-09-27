package casas;
import java.util.List;
import jogadores.Jogador;

public class CasaPerdeRodada extends Casa {
    
    

    public CasaPerdeRodada(int posicao){
        super(posicao);
    }
    
    @Override
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        if(jogador.getIsPerdeProximaRodada() == false){
            jogador.setIsPerdeProximaRodada(true);
        }
        return jogador;
    }
}