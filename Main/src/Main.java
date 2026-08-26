import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class Main {


    public static List<String> inverterTexto() {
        List<String> fraseInvertida = new ArrayList<>();
        try {
            Path path = Path.of("C:/teste.txt");
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                StringBuilder textoInvertido = new StringBuilder(line).reverse();
                fraseInvertida.add(textoInvertido.toString());
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo");
        }
        return fraseInvertida;
    }

    public static String cifraCesar(String frase, int deslocamento) {
        try {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < frase.length(); i++) {
                char c = frase.charAt(i);
                if (Character.isLowerCase(c)) {
                    char deslocado = (char) (((c - 'a' + deslocamento) % 26) + 'a');
                    s.append(deslocado);
                } else if (Character.isUpperCase(c)) {
                    char deslocado = (char) (((c - 'A' + deslocamento) % 26) + 'A');
                    s.append(deslocado);
                } else {
                    s.append(c);
                }
            }
            return s.toString();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo");
        }
        return frase;
    }

    public static void main(String[] args) {
        // 1. Executa a leitura e inversão do arquivo
        List<String> linhasInvertidas = inverterTexto();

        System.out.println("\n--- Lendo a lista retornada no main ---");
        for (String line : linhasInvertidas) {
            System.out.println(line);
        }

        // 2. Demonstração opcional da Cifra de César
        System.out.println("\n--- Teste da Cifra de César ---");
        String original = "Exemplo Java 2026";
        String cifrado = cifraCesar(original, 3);
        System.out.println("Original: " + original);
        System.out.println("Cifrado:  " + cifrado);
    }
}
