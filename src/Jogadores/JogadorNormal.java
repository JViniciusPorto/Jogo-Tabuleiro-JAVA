package Jogadores;

public class JogadorNormal extends Jogador{
    public JogadorNormal(String cor){
        super(cor);
    }
    @Override
    public int jogarDados() {
        this.dado1 = rolardados.nextInt(6)+1;
        this.dado2 = rolardados.nextInt(6)+1;
        return this.dado1 + this.dado2;
    }
}
