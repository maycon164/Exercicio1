package view;

import java.util.Scanner;

import controller.RedesController;

public class Main {
	
	public static void main(String [] args) {
		RedesController rc = new RedesController();
		int opcao = 0;
		Scanner sc = new Scanner(System.in);
		while(opcao != 9) {
			System.out.println("Opções: \n	1 - Ip \n	2 - Ping \n	9 - finalizar");
			opcao = sc.nextInt();
			System.out.println("=================");
			switch (opcao) {
			case 1:
				rc.ip();
				break;
			case 2:
				rc.ping();
				break;
			case 9:
				System.out.println("Finalizado");
				break;
			default:
				System.out.println("Inválido");
				break;
			}
		}
		sc.close();
	}

}
