import java.util.Random;
import Jogadores.Jogador;
public class CasaSupresa extends Casa{
    private int carta;
    private Random sorteio;

    public CasaSupresa(int posicao){
        super(posicao);
        sorteio = new Random();
    }
    
    @Override 
    public void executarEfeito(Jogador jogador){
        carta = sorteio.nextInt(3);
    }

    public int getCarta(){
        return this.carta;
    }

}
