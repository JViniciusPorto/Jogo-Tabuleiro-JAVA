import java.util.List;

import Jogadores.Jogador;

public class CasaMagica extends Casa {

    public CasaMagica(int posicao){
        super(posicao);
    }

    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        
        Jogador maisAtras = encontrarMaisAtras(todosJogadores, jogador);

        if(maisAtras != jogador){
        int posicaoJogador = jogador.getPosicao();

        jogador.setPosicao(maisAtras.getPosicao());
        maisAtras.setPosicao(posicaoJogador);
        }

        return jogador;
        
    }

    private Jogador encontrarMaisAtras(List<Jogador> jogadores, Jogador atual){

    Jogador maisAtras = atual;

    for(Jogador j : jogadores){
        if(j.getPosicao() < maisAtras.getPosicao()){
            maisAtras = j;
        }
    }

    return maisAtras;

  }

}
