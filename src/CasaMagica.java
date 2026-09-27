import Jogadores.Jogador;
public class CasaMagica extends Casa {
    private Jogador jogadorMaisAtras;

    public CasaMagica(int posicao){
        super(posicao);
    }

    public void setJogadorMaisAtras(Jogador jogadorMaisAtras){
        this.jogadorMaisAtras = jogadorMaisAtras;
    }

    public Jogador getJogadorMaisAtras(){
        return this.jogadorMaisAtras;
    }

    @Override 
    public void executarEfeito(Jogador jogador){
        int posicaoJogador = jogador.getPosicao();
        jogador.setPosicao(getJogadorMaisAtras().getPosicao());
        getJogadorMaisAtras().setPosicao(posicaoJogador);
    }

}
