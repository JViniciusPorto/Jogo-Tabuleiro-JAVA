import Jogadores.Jogador;
public class CasaSorte extends Casa {
    
    public CasaSorte(int posicao){
        super(posicao);
    }
    
    @Override 
    public void executarEfeito(Jogador jogador){
        if(jogador.temSorte()){
            jogador.setPosicao(jogador.getPosicao() + 3);
        }
    }


}
