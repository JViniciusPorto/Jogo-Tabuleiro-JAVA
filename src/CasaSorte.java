import java.util.List;

import Jogadores.Jogador;
public class CasaSorte extends Casa {
    
    public CasaSorte(int posicao){
        super(posicao);
    }
    
    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        if(jogador.temSorte()){
            jogador.setPosicao(jogador.getPosicao() + 3);
        }
        
        return jogador;
    }


}
