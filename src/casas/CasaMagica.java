package casas;
import java.util.List;
import jogadores.*;

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
        System.out.println(jogador.getCor() + " caiu na Casa Mágica e trocou de posição com "+ maisAtras.getCor() + ", que estava mais atrás!");
        } else{
            System.out.println(jogador.getCor() + " caiu na Casa Mágica, mas já era o último - nada mudou!");
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