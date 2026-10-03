package casas;
import java.util.List;
import java.util.Random;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import jogadores.*;



public class CasaSurpresa extends Casa{
    private int carta;
    private Random sorteio;


    public CasaSurpresa(int posicao){
        super(posicao);
        sorteio = new Random();

    }
    
    @Override 
    public Jogador executarEfeito(Jogador jogador, List<Jogador> todosJogadores){
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Você caiu na Casa Surpresa. Puxar carta?");
        confirmacao.getButtonTypes().setAll(ButtonType.OK); // substitui a lista de botões, deixando só o OK
        confirmacao.showAndWait(); // bloqueia até o usuário clicar OK/Cancelar

        carta = sorteio.nextInt(3);
        Jogador jogadorAtualizado = switch (carta) {
            case 0 -> new JogadorNormal(jogador);
            case 1 -> new JogadorSortudo(jogador);
            default -> new JogadorAzarado(jogador);
        };

        setMensagem("Você agora é um Jogador " + jogadorAtualizado.getClass().getSimpleName().replace("Jogador", ""));

        return jogadorAtualizado;
}

    public int getCarta(){
        return this.carta;
    }

}