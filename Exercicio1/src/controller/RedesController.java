package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public String os() {
		return System.getProperty("os.name");
	}

	private void ListIp(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);

			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String nomeAdaptador = "";
			String linha = buffer.readLine();

			while (linha != null) {
				
				if (linha.contains("Adaptador") || linha.contains("flags")) {
					nomeAdaptador = linha;
				}
				if (linha.contains("IPv4") || linha.contains("inet ")) {
					System.out.println(nomeAdaptador);
					System.out.println(linha);
				}
				linha = buffer.readLine();
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
			ListIp("ipconfig");
		} else if (os().contains("Linux")) {
			ListIp("ifconfig");
		}
	}

	public void ping() {
		if(os().contains("Windows")) {
			averagePing("ping -4 -n 10 www.google.com");
		}else if(os().contains("Linux")) {
			averagePing("ping -4 -c 10 www.google.com");	
		}
	}

	private void averagePing(String process) {
		Process p;
		
		try {
			p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String aux = "";
			while(linha != null) {
				aux = linha;
				linha = buffer.readLine();
			}
			
			System.out.println(aux);			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		
	}
}
