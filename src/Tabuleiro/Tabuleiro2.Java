import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Tabuleiro2 {

    private ArrayList<Casa> casas;

    public Tabuleiro2() {
        casas = new ArrayList<>();
        criarCasas();
    }

    private void criarCasas() {
        for (int posicao = 0; posicao < 40; posicao++) {
            casas.add(criarCasa(posicao));
        }
    }

    private Casa criarCasa(int posicao) {

        return switch (posicao) {
            case 5, 15, 30 -> new CasaSorte();
            case 10, 25, 38 -> new CasaPerdeRodada();
            case 13 -> new CasaSurpresa();
            case 17, 27 -> new CasaVoltaInicio();
            case 20, 35 -> new CasaMagica();
            default -> new CasaNormal();
        };
    }

    public Casa getCasa(int posicao) {
        return casas.get(posicao);
    }

    /*Nesse caso, o método getCasas() retorna uma lista não modificável das casas do tabuleiro,
      para ninguém (nem por engano) alterar a lista de casas do tabuleiro a partir de fora, assim
      protegendo o encapsulamento
     */
    public List<Casa> getCasas() {
        return Collections.unmodifiableList(casas);
    }
}