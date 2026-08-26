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
            System.out.println("Erro ao processar o texto");
        }
        return frase;
    }

    public static List<String> cifrarLista(List<String> listaOriginal, int deslocamento) {
        List<String> listaCifrada = new ArrayList<>();
        for (String linha : listaOriginal) {
            listaCifrada.add(cifraCesar(linha, deslocamento));
        }
        return listaCifrada;
    }

    public static void main(String[] args) {
        List<String> linhasInvertidas = inverterTexto();
        int deslocamento = 3; // Configuração para escolha
        List<String> linhasCifradas = cifrarLista(linhasInvertidas, deslocamento);

        System.out.println("--- Exibindo lista cifrada através do método único ---");
        for (String linha : linhasCifradas) {
            System.out.println(linha);
        }
    }
}
