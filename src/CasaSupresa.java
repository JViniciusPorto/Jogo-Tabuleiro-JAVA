import java.util.List;
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
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        carta = sorteio.nextInt(3);
        Switch(carta){
            case 0:
                return new jogadorNormal(jogador);
                
            case 1:
                return new jogadorSortudo(jogador);

            default: 
                return new jogadorAzarado(jogador);
        }
        
    }

    public int getCarta(){
        return this.carta;
    }

}
