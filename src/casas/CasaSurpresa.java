package casas;
import java.util.List;
import java.util.Random;
import jogadores.Jogador;
import jogadores.JogadorAzarado;
import jogadores.JogadorNormal;
import jogadores.JogadorSortudo;

public class CasaSurpresa extends Casa{
    private int carta;
    private Random sorteio;

    public CasaSurpresa(int posicao){
        super(posicao);
        sorteio = new Random();
    }
    
    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        carta = sorteio.nextInt(3);
        switch(carta){
            case 0:
                return new JogadorNormal(jogador);
                
            case 1:
                return new JogadorSortudo(jogador);

            default: 
                return new JogadorAzarado(jogador);
        }
        
    }

    public int getCarta(){
        return this.carta;
    }

}