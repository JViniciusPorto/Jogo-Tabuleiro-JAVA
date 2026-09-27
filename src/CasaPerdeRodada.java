import Jogadores.Jogador;
public class CasaPerdeRodada extends Casa {
    
    private Jogador jogadorPerdeRodada;

    public CasaPerdeRodada(int posicao){
        super(posicao);
    }
    
    public Jogador getJogadorPerdeRodada(){
        return this.jogadorPerdeRodada;
    }

    @Override
    public void executarEfeito(Jogador jogador){
        this.jogadorPerdeRodada = jogador;
    }
}
