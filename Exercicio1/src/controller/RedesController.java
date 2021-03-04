package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public String os() {
		return System.getProperty("os.name");
	}

	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);

			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String nomeAdaptador = "";
			String linha = buffer.readLine();

			while (linha != null) {
				linha = buffer.readLine();
				if (linha.contains("Adaptador") || linha.contains("flags")) {
					nomeAdaptador = linha;
				}
				if (linha.contains("IPv4") || linha.contains("inet ")) {
					System.out.println(nomeAdaptador);
					System.out.println(linha);
				}
			}

			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void ip() {
		if (os().contains("Windows")) {
			readProcess("ipconfig");
		} else if (os().contains("Linux")) {
			readProcess("ifconfig");
		}
	}

}
