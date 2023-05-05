package view;

import java.io.IOException;

import controller.SteamController;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		SteamController read = new SteamController();
		String path = "C:\\TEMP";
		String nome = "SteamCharts.csv";
		
		read.readFile(path, nome);

	}

}
