package casas;
import java.util.List;
import jogadores.*;

public class Casa{
    protected int posicao;
    private String ultimaMensagem = "";

    public Casa(int posicao){
        this.posicao = posicao;
    }

    public int getPosicao(){
        return this.posicao;
    }

    protected void setMensagem(String mensagem){
        this.ultimaMensagem = mensagem;
    }

    public String getUltimaMensagem(){
        return this.ultimaMensagem;
    }

    public Jogador executarEfeito(Jogador jogadorAtual, List<Jogador> todosJogadores){
        setMensagem(""); // casa normal não tem mensagem
        return jogadorAtual;
    }
}