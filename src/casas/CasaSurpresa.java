package casas;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import jogadores.*;



public class CasaSurpresa extends Casa{
    private int carta;
    private Random sorteio;
    private final Scanner scanner;

    public CasaSurpresa(int posicao){
        super(posicao);
        sorteio = new Random();
        scanner = new Scanner(System.in);
    }
    
    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        System.out.println("Você caiu na Casa Surpresa. Puxar carta? [pressione Enter]");
        scanner.nextLine();    
        
        carta = sorteio.nextInt(3);
        switch(carta){
            case 0:
                System.out.println("Você agora é um Jogador normal");
                return new JogadorNormal(jogador);
                
            case 1:
                System.out.println("Você agora é um Jogador Sortudo");
                return new JogadorSortudo(jogador);

            default: 
                System.out.println("Você agora é um Jogador Azarado");
                return new JogadorAzarado(jogador);
        }

    }

    public int getCarta(){
        return this.carta;
    }

}