import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Mensagem Invertida: Rafael Alves
    public static String inverterTexto(String frase) {
        if (frase == null) return "";
        return new StringBuilder(frase).reverse().toString();
    }

    // Cifra de César: João Gabriel
    public static String cifraCesar(String frase, int deslocamento) {
        try {
            deslocamento = ((deslocamento % 26) + 26) % 26;

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
            return frase;
        }
    }

    public static String mascaraAleatoria(String frase) {
        Random rand = new Random();
        int mascara = rand.nextInt(25) + 1;
        return cifraCesar(frase, mascara);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Recebe o caminho do arquivo interativamente
        System.out.println("Digite o caminho completo do arquivo .txt (ex: C:\\Users\\rafael.pferreira\\cifra\\Cifragem\\Main\\src\\teste.txt):");
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
            resultado = inverterTexto(fraseOriginal);
            sufixo = "-frase_invertida.txt";
        } else if (choice == 2) {
            System.out.println("Digite em inteiro a configuração para a Cifra (0 a 25): ");
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

        // Salvando o arquivo no mesmo diretório do arquivo original lido
        try {
            // Pega o caminho da pasta onde o arquivo original está
            String diretorio = nomeArquivo.getParent();

            // Monta o caminho completo do novo arquivo
            File arquivoDestino = new File(diretorio, nomeBase + sufixo);

            // Usando a boa prática do try-with-resources
            try (FileWriter arquivoCriptografado = new FileWriter(arquivoDestino)) {
                arquivoCriptografado.write(resultado);
                System.out.println("\nArquivo salvo com sucesso em: " + arquivoDestino.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }

        sc.close();
    }
}