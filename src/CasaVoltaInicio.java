import Jogadores.Jogador;
public class CasaVoltaInicio extends Casa {
    
    private int jogadorEscolhido;

    public CasaVoltaInicio(int posicao){
        super(posicao);
    }

    @Override 
    public void executarEfeito(Jogador jogador){

    }

    public void setJogadorEscolhido(int jogadorEscolhido){
        this.jogadorEscolhido = jogadorEscolhido;
    }

    public int getJogadorEscolhido(){
        return this.jogadorEscolhido;
    }

}
