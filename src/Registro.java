import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Registro {

    public static void salvarResultado(String resultado) {
        try (FileWriter fw = new FileWriter("resultados.txt", true)) {
            fw.write(LocalDateTime.now() + " - " + resultado + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar resultado: " + e.getMessage());
        }
    }

    public static void mostrarRanking() {
        Map<String, Integer> placar = new HashMap<>();
        placar.put("X", 0);
        placar.put("0", 0);
        placar.put("Empate", 0);

        try (BufferedReader br = new BufferedReader(new FileReader("resultados.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains("Vencedor: X")) {
                    placar.put("X", placar.get("X") + 1);
                } else if (linha.contains("Vencedor: 0")) {
                    placar.put("0", placar.get("0") + 1);
                } else if (linha.contains("Empate")) {
                    placar.put("Empate", placar.get("Empate") + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler resultados: " + e.getMessage());
        }

        System.out.println("\n===== RANKING =====");
        System.out.println("Vitórias de X: " + placar.get("X"));
        System.out.println("Vitórias de 0: " + placar.get("0"));
        System.out.println("Empates: " + placar.get("Empate"));
        System.out.println("===================\n");
    }
}

