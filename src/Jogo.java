public abstract class Jogo {
    protected String[][] tabuleiro = new String[3][3];
    protected int jogador = 0;
    protected int parada = 1;
    protected int cont = 1;

    public Jogo() {
        iniciarTabuleiro();
    }

    protected void iniciarTabuleiro() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tabuleiro[i][j] = " ";
    }

    public abstract void   executar();
}
