package tabuleiro;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import casas.Casa;
import casas.CasaMagica;
import casas.CasaPerdeRodada;
import casas.CasaSorte;
import casas.CasaSurpresa;
import casas.CasaVoltaInicio;


public class Tabuleiro {

    private ArrayList<Casa> casas;

    public Tabuleiro() {
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
            case 5, 15, 30 -> new CasaSorte(posicao);
            case 10, 25, 38 -> new CasaPerdeRodada(posicao);
            case 13 -> new CasaSurpresa(posicao);
            case 17, 27 -> new CasaVoltaInicio(posicao);
            case 20, 35 -> new CasaMagica(posicao);
            default -> new Casa(posicao);
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
