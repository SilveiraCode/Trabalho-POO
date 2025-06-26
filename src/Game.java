import java.util.Scanner;

public class Game extends Jogo {
    Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        String resposta;
        do {
            inicializarJogo();
            executar();

            System.out.print("Deseja ver o ranking? (s/n): ");
            String verRanking = scanner.nextLine().trim().toLowerCase();
            if (verRanking.equals("s")) {
                Registro.mostrarRanking();
            }

            System.out.print("Deseja jogar novamente? (s/n): ");
            resposta = scanner.nextLine().trim().toLowerCase();
        } while (resposta.equals("s"));

        System.out.println("Obrigado por jogar!");
    }


    @Override
    public void executar() {
        do {
            tela();
            verificarJogador();
            jogada();
            verificarPartida();
        } while (parada == 1);
    }

    private void inicializarJogo() {
        tabuleiro = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tabuleiro[i][j] = " ";

        jogador = 1;  // Para que ao chamar verificarJogador, ele volte para o jogador 0
        cont = 0;
        parada = 1;
    }

    private void tela() {
        System.out.println("   0  1  2");
        for (int i = 0; i < 3; i++)
            System.out.println(i + ": " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
    }

    private void verificarJogador() {
        jogador = (jogador == 0) ? 1 : 0;
        System.out.println("Vez do jogador " + (jogador == 0 ? "0" : "X"));
    }

    private void jogada() {
        int linhaJogada = -1, colunaJogada = -1;

        do {
            try {
                System.out.println("Digite a linha escolhida (0, 1 ou 2): ");
                linhaJogada = scanner.nextInt();

                System.out.println("Digite a coluna escolhida (0, 1 ou 2): ");
                colunaJogada = scanner.nextInt();

                scanner.nextLine(); // limpa o buffer

                if (linhaJogada >= 0 && linhaJogada < 3 &&
                        colunaJogada >= 0 && colunaJogada < 3 &&
                        tabuleiro[linhaJogada][colunaJogada].equals(" ")) {

                    preencherTabuleiro(linhaJogada, colunaJogada);
                    break;
                } else {
                    System.out.println("Jogada inválida! Posição ocupada ou fora do tabuleiro. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite apenas números entre 0 e 2.");
                scanner.nextLine(); // limpa a entrada incorreta
            }
        } while (true);
    }

    private void preencherTabuleiro(int linha, int coluna) {
        tabuleiro[linha][coluna] = jogador == 0 ? "0" : "X";
    }

    private void verificarPartida() {
        if (cont < 9) {
            for (int i = 0; i < 3; i++) {
                if (linhaIgual(i) || colunaIgual(i)) {
                    anunciarVencedor();
                    return;
                }
            }

            if (diagonalPrincipal() || diagonalSecundaria()) {
                anunciarVencedor();
                return;
            }

            cont++;
            if (cont == 9) {
                tela();
                System.out.println("Empate!");
                Registro.salvarResultado("Empate");
                parada = 0;
            }
        }
    }

    private boolean linhaIgual(int i) {
        return tabuleiro[i][0].equals(tabuleiro[i][1]) &&
                tabuleiro[i][0].equals(tabuleiro[i][2]) &&
                !tabuleiro[i][0].equals(" ");
    }

    private boolean colunaIgual(int i) {
        return tabuleiro[0][i].equals(tabuleiro[1][i]) &&
                tabuleiro[0][i].equals(tabuleiro[2][i]) &&
                !tabuleiro[0][i].equals(" ");
    }

    private boolean diagonalPrincipal() {
        return tabuleiro[0][0].equals(tabuleiro[1][1]) &&
                tabuleiro[0][0].equals(tabuleiro[2][2]) &&
                !tabuleiro[0][0].equals(" ");
    }

    private boolean diagonalSecundaria() {
        return tabuleiro[2][0].equals(tabuleiro[1][1]) &&
                tabuleiro[2][0].equals(tabuleiro[0][2]) &&
                !tabuleiro[2][0].equals(" ");
    }

    private void anunciarVencedor() {
        tela();
        String vencedor = jogador == 0 ? "0" : "X";
        System.out.println("Jogador '" + vencedor + "' venceu!");
        Registro.salvarResultado("Vencedor: " + vencedor);
        parada = 0;
    }
}
