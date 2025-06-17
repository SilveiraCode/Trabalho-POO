import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Registro {
    public static void salvarResultado(String resultado) {
        try (FileWriter fw = new FileWriter("resultados.txt", true)) {
            fw.write(LocalDateTime.now() + " - " + resultado + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar resultado: " + e.getMessage());
        }
    }
}
