package casas;

import java.util.List;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
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

    private void escolherJogadorInicio(Jogador jogador, List<Jogador> todosJogadores) {
        List<Jogador> opcoes = todosJogadores.stream()
                .filter(j -> j != jogador)
                .toList();

        ChoiceDialog<Jogador> dialogo = new ChoiceDialog<>(opcoes.get(0), opcoes);
        dialogo.setHeaderText(null);
        dialogo.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
        dialogo.setContentText("Escolha um jogador para voltar ao início:");

        dialogo.showAndWait().ifPresent(escolhido -> {
            escolhido.setPosicao(0);
            setMensagem(escolhido.getCor() + " voltou para o início!");
        });
    }
}