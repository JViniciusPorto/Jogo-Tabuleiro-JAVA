package casas;
import java.util.List;
import jogadores.*;

public class CasaSorte extends Casa {
    
    public CasaSorte(int posicao){
        super(posicao);
    }
    
    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
    if(jogador.temSorte()){
        jogador.setPosicao(jogador.getPosicao() + 3);
        setMensagem(jogador.getCor() + " caiu na Casa Sorte e avançou 3 casas!");
    } else {
        setMensagem(jogador.getCor() + " caiu na Casa Sorte, mas não teve sorte - nada mudou!");
    }
    return jogador;
}


}