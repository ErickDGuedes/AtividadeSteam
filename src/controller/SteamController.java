package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class SteamController {

	public SteamController() {
		super();
	}
	
	public void readFile(String path, String nome) throws IOException { //Leitura do arquivo para análise
		File dir = new File(path);
		File arq = new File(path, nome);
		if(dir.exists() && dir.isDirectory()) {
			if(arq.exists() && arq.isFile()) {
				FileInputStream fluxo = new FileInputStream(arq);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String mesPesquisa = JOptionPane.showInputDialog(null, "Digite o Mês que deseja procurar (Inglês): ",
						"Pesquisa", JOptionPane.INFORMATION_MESSAGE);
				String anoPesquisa = JOptionPane.showInputDialog(null, "Digite o ano que deseja procurar:",
						"Pesquisa", JOptionPane.INFORMATION_MESSAGE);
				double mediaPesquisa = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a média de jogadores: ",
						"Pesquisa", JOptionPane.INFORMATION_MESSAGE));
				while(linha != null) {
					if(linha.contains(mesPesquisa) && linha.contains(anoPesquisa)) {
						String[] filtro = linha.split(",");
						double mediaJogadores = Double.parseDouble(filtro[3]);
						if(mediaJogadores >= mediaPesquisa) {
							System.out.println(filtro[0] + " | " + filtro[3]);
							String linhaArquivo = filtro[0] + " ; " + filtro[3];
							createfile(path,"nome.csv", linhaArquivo);
						}
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			}else {
				throw new IOException("Arquivo Inválido");
			}

		}else {
			throw new IOException("Diretório Inválido");
		}

	}

	public void createfile(String path, String nome, String linhaArquivo)throws IOException { //Criação do arquivo com os dados coletados
		File dir = new File(path);
		File arq = new File(path, nome);
		
		if(dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(arq.exists()) {
			   existe = true;
			}
			String informacoes = createCsv(linhaArquivo);
			FileWriter escreveArquivo = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(escreveArquivo);
			print.write(informacoes);
			print.flush();
			print.close();
		}else {
			throw new IOException("Diretório Inválido");
		}
			
	}

	private String createCsv(String linhaArquivo) { //Estrutura do arquivo .csv
		StringBuffer buffer = new StringBuffer();
		buffer.append(linhaArquivo + ";");
		buffer.append("\n");
		return buffer.toString();
	}


}
