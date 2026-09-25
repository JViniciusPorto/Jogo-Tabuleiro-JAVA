package Jogadores;

public class JogadorSortudo extends Jogador{
    public JogadorSortudo(String cor){
        super(cor);
    }
    @Override
    public int jogarDados() {
        int soma;
        do{
            this.dado1 = rolardados.nextInt(6)+1;
            this.dado2 = rolardados.nextInt(6)+1;
            soma = this.dado1 + this.dado2;
        }while(soma<7);
        return soma;
    }
}
