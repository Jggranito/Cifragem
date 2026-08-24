package trabalhoSegurancaDaInforamcao;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class teste {
	public static void main(String[] args) {
		try {
			File nomeArquivo = new File("src/trabalhoSegurancaDaInforamcao/siaudhiudash.txt");
			int arquivoExt = (nomeArquivo.getName()).lastIndexOf('.');
			
			String fraseParaCriptografar = Files.readString(Path.of("src/trabalhoSegurancaDaInforamcao/siaudhiudash.txt"));
			System.out.println(fraseParaCriptografar);
			
			String fraseInvertida = new StringBuilder(fraseParaCriptografar).reverse().toString();
			System.out.println("\n" + fraseInvertida);
			FileWriter arquivoCriptografadoFraseInvertida = new FileWriter((nomeArquivo.getName()).substring(0, arquivoExt) + "-frase_invertida" + ".txt");
			arquivoCriptografadoFraseInvertida.write(fraseInvertida);
			arquivoCriptografadoFraseInvertida.close();
			
			System.out.println("\n" + cifraCesar(fraseParaCriptografar, 4));
			FileWriter arquivoCriptografadoCesar = new FileWriter((nomeArquivo.getName()).substring(0, arquivoExt) + "-cifra_cesar" + ".txt");
			arquivoCriptografadoCesar.write(fraseInvertida);
			arquivoCriptografadoCesar.close();
			
			System.out.println("\n" + mascaraAleatoria(fraseParaCriptografar));
			FileWriter arquivoCriptografadoMascara = new FileWriter((nomeArquivo.getName()).substring(0, arquivoExt) + "-mascara_aleatoria" + ".txt");
			arquivoCriptografadoMascara.write(fraseInvertida);
			arquivoCriptografadoMascara.close();
		} catch (Exception e) {
			System.out.println("erro " + e);
		}
	}
	
	public static String cifraCesar(String frase, int deslocamento) {
		
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < frase.length(); i++) {
			char c = frase.charAt(i);
			
			if (Character.isLowerCase(c)) {
				char deslocado = (char) (((c - 'a' + deslocamento) % 26) + 'a');
				s.append(deslocado);
			}else if (Character.isUpperCase(c)) {
				char deslocado = (char) (((c - 'A' + deslocamento) % 26) + 'A');
				s.append(deslocado);
			} else {
				s.append(c);
			}
		}
		
		return s.toString();
	}
	
	public static String mascaraAleatoria(String frase) {
		int mascara = 354654648;
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < frase.length(); i++) {
			char c = frase.charAt(i);
			
			if (Character.isLowerCase(c)) {
				char deslocado = (char) (((c - 'a' + mascara) % 26) + 'a');
				s.append(deslocado);
			}else if (Character.isUpperCase(c)) {
				char deslocado = (char) (((c - 'A' + mascara) % 26) + 'A');
				s.append(deslocado);
			} else {
				s.append(c);
			}
		}
		
		return s.toString();
	}
}
