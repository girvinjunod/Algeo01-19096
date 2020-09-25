/*
 * Author: Jauhar Wibisono 13519160
 * 23 September 2020
 * menu.java
 */

import java.util.Scanner;

public class menu{
	public static void main(String args[]){
		Scanner in=(new Scanner(System.in));
		boolean keluar=false;
		int choice;
		while (!keluar){
			System.out.println("MENU");
			System.out.printf("1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks Balikan\n4. interpolasi polinom\n5. Regresi Linear Berganda\n6. Keluar\n");
			choice=in.nextInt();
			while (choice<1 && choice>6){
				System.out.printf("masukan tidak valid, ulangi masukan\n");
				choice=in.nextInt();
			}
			if (choice==1){
				// SPL
				System.out.printf("1. Metode eliminasi Gauss\n2. Metode Eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer\n");
				choice=in.nextInt();
				while (choice<1 && choice>6){
					System.out.printf("masukan tidak valid, ulangi masukan\n");
					choice=in.nextInt();
				}
				if (choice==1){
					// Metode eliminasi Gauss
				}
				else if (choice==2){
					// Metode eliminasi Gauss-Jordan
				}
				else if (choice==3){
					// Metode matriks balikan
				}
				else{
					// Kaidah Cramer
				}
			}
			else if (choice==2){
				// Determinan
				System.out.printf("1. Metode reduksi baris\n2. Metode ekspansi kofaktor\n");
				choice=in.nextInt();
				while (choice<1 && choice>6){
					System.out.printf("masukan tidak valid, ulangi masukan\n");
					choice=in.nextInt();
				}
				if (choice==1){
					// Metode reduksi baris
				}
				else{
					// Metode ekspansi kofaktor
				}
			}
			else if (choice==3){
				// Matriks balikan

			}
			else if (choice==4){
				// Interpolasi polinom
				interpolasi tmp=(new interpolasi());
				tmp.driver_interpolasi();
			}
			else if (choice==5){
				// Regresi linier berganda

			}
			else{
				keluar=true;
			}
		}
	}
}