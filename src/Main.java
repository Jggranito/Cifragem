import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class Main {

    public static List<String> readText() {
        try {
            Path path = Path.of("C:/teste.txt");
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
            return lines;

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public static void main(String[] args) {
        List<String> text = readText();

        System.out.println("\n--- Lendo a lista retornada no main ---");
        for (String line : text) {
            System.out.println(line);
        }
    }
}
