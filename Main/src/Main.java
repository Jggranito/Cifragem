import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {

    // Mensagem Invertida: Rafael Alves
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
    // Cifra de César: João Gabriel
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

    public static String mascaraAleatoria(String frase) {
        Random rand = new Random();
        int mascara = rand.nextInt(26) + 1; // Gera um número aleatório para a máscara
        return cifraCesar(frase, mascara);  // Reutiliza a lógica de substituição
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Recebe o caminho do arquivo interativamente
        System.out.println("Digite o caminho completo do arquivo .txt (ex: C:Cifragem\\Main\\src\\teste.txt):");
        String caminhoString = sc.nextLine();

        File nomeArquivo = new File(caminhoString);
        int arquivoExt = (nomeArquivo.getName()).lastIndexOf('.');
        String nomeBase = (arquivoExt == -1) ? nomeArquivo.getName() : nomeArquivo.getName().substring(0, arquivoExt);

        String fraseOriginal = "";
        try {
            fraseOriginal = Files.readString(Path.of(caminhoString));
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo. Verifique o caminho digitado.");
            sc.close();
            return;
        }

        System.out.println("""
                Escolha o tipo de criptografia:\s
                1. Mensagem Invertida\s
                2. Cifra de César\s
                3. Método de Substituição (Máscara Aleatória)""");
        int choice = sc.nextInt();

        String resultado = "";
        String sufixo = "";

        // Estrutura condicional para rodar apenas o método escolhido
        if (choice == 1) {
            resultado = new StringBuilder(fraseOriginal).reverse().toString();
            sufixo = "-frase_invertida.txt";
        } else if (choice == 2) {
            System.out.println("Digite em inteiro a configuração para a Cifra (0 a 27): ");
            int deslocamento = sc.nextInt();
            resultado = cifraCesar(fraseOriginal, deslocamento);
            sufixo = "-cifra_cesar.txt";
        } else if (choice == 3) {
            resultado = mascaraAleatoria(fraseOriginal);
            sufixo = "-mascara_aleatoria.txt";
        } else {
            System.out.println("Opção inválida.");
            sc.close();
            return;
        }

        // Impressão na tela
        System.out.println("\n--- Resultado da Cifragem ---");
        System.out.println(resultado);

        // Salvando um único arquivo com base na escolha do usuário
        try {
            FileWriter arquivoCriptografado = new FileWriter(nomeBase + sufixo);
            arquivoCriptografado.write(resultado);
            arquivoCriptografado.close();
            System.out.println("\nArquivo salvo com sucesso com o nome: " + nomeBase + sufixo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo.");
        }
        sc.close();
    }
}
