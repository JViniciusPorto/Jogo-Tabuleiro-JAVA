package tabuleiro;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import casas.Casa;
import casas.CasaMagica;
import casas.CasaNormal;
import casas.CasaPerdeRodada;
import casas.CasaSorte;
import casas.CasaSurpresa;

public class Tabuleiro {

    private ArrayList<Casa> casas;

    public Tabuleiro() {
        casas = new ArrayList<>();

        criarCasas();
    }

    private void criarCasas() {

        for (int i = 0; i < 40; i++) {

            if (i == 5 || i == 15 || i == 30) {
                casas.add(new CasaSorte());

            } else if (i == 10 || i == 25 || i == 38) {
                casas.add(new CasaPerdeRodada());

            } else if (i == 13) {
                casas.add(new CasaSurpresa());

            } else if (i == 17 || i == 27) {
                casas.add(new CasaVoltaInicio());

            } else if (i == 20 || i == 35) {
                casas.add(new CasaMagica());

            } else {
                casas.add(new Casa());
            }
        }
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
