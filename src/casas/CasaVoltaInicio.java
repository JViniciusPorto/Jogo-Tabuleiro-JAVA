package casas;
import java.util.List;
import java.util.Scanner;
import jogadores.Jogador;

public class CasaVoltaInicio extends Casa {
    
    public CasaVoltaInicio(int posicao){
        super(posicao);
    }

    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        escolherJogadorInicio(jogador, todosJogadores);

        return jogador;
    }

    private void escolherJogadorInicio(Jogador jogador,List<Jogador> todosJogadores){
        Scanner scanner = new Scanner(System.in);

        System.out.println("EScolha um jogador para voltar para o inicio");

         for (int i = 0; i < todosJogadores.size(); i++) {
            Jogador j = todosJogadores.get(i);

            if(j != jogador){
                System.out.println((i + 1)+ "  -  " + j.getCor());
            }
        }

        int escolha;

        do{
            System.out.print("Digite o número do competidor: ");
            escolha = scanner.nextInt();

             if (escolha < 1 || escolha > todosJogadores.size()) {
                System.out.println("Opção inválida");
            } else if (todosJogadores.get(escolha - 1) == jogador) {
                System.out.println("Você não pode escolher a si mesmo.");

            } else{
                break;
            }

        } while(true);

        Jogador jogadorEscolhido = todosJogadores.get(escolha - 1);

        jogadorEscolhido.setPosicao(0);

        System.out.println(jogadorEscolhido.getCor() + " Voltou para o inicio");
        
    }

    
}
