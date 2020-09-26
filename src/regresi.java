/*
 * Author: Jauhar Wibisono 13519160
 * 23 September 2020
 * regresi.java
 */

// pending, gak paham sama format masukannya

import java.util.Scanner;
import java.io.*;

public class regresi{
	// atribut
	// method
	regresi(){} // konstruktor
	static double[] tmp_GaussJordan(int n, double a[][]){
		// buat matriks eselon baris
		for (int i=0;i<n;i++){
			if (Math.abs(a[i][i])<1e-7){
				// diagonal sekarang nol, cari yang tidak nol
				boolean found=false;
				int j=i+1;
				while (j<n && !found){
					if (Math.abs(a[i][j])>1e-7){
						found=true;
						// tukar baris
						for (int k=i;k<=n;k++){
							double tmp=a[i][k];
							a[i][k]=a[j][k];
							a[j][k]=tmp;
						}
					}
				}
			}
			if (Math.abs(a[i][i])>1e-7){
				// eliminasi kolom i
				double tmp1=1/a[i][i];
				for (int j=i;j<=n;j++) a[i][j]*=tmp1;
				for (int j=i+1;j<n;j++){
					double tmp2=a[j][i]/a[i][i];
					for (int k=i;k<=n;k++) a[j][k]-=tmp2*a[i][k];
				}
			}
		}
		// buat matriks eselon baris tereduksi
		for (int i=n-1;i>=0;i--){
			// eliminasi kolom i
			for (int j=i-1;j>=0;j--){
				double tmp=a[j][i]/a[i][i];
				for (int k=i;k<=n;k++) a[j][k]-=tmp*a[i][k];
			}
		}
		// buat array solusi
		double ret[]=new double[101];
		for (int i=0;i<n;i++) ret[i]=a[i][n];
		return ret;
	}
	public static double[] regresi(int n, int m, double x[][], double y[]){
		// buat matriks tmp, yaitu matriks augmented [1|x|y]
		double tmp[][]=new double[101][101];
		for (int i=0;i<m;i++) tmp[i][0]=1;
		for (int j=1;j<=n;j++){
			for (int i=0;i<m;i++){
				tmp[i][j]=x[i][j-1];
			}
		}
		for (int i=0;i<m;i++) tmp[i][n+1]=y[i];
		// buat matriks SPL
		double a[][]=new double[101][101];
		for (int i=0;i<=n;i++){
			for (int j=0;j<=n+1;j++){
				a[i][j]=0;
				for (int k=0;k<m;k++){
					a[i][j]+=tmp[k][i]*tmp[k][j];
				}
			}
		}
		// selesaikan SPL dengan eliminasi Gauss-Jordan
		return tmp_GaussJordan(n+1,a);
	}
	public static void driver_regresi(){
		Scanner in=new Scanner(System.in);
		BufferedReader in2=new BufferedReader(new InputStreamReader(System.in));
		int n=0, m=0;
		double x[][]=new double[101][101], y[]=new double[101];
		double xk[]=new double[101];
		// baca masukan
		System.out.printf("1. masukan dari keyboard\n2. masukan dari file\n");
		int choice;
		choice=in.nextInt();
		while(choice<1 || choice>2){
			System.out.printf("masukan tidak valid, masukan diulang\n");
			choice=in.nextInt();
		}
		if (choice==1){
			n=in.nextInt();
			m=in.nextInt();
			for (int i=0;i<m;i++){
				for (int j=0;j<n;j++) x[i][j]=in.nextDouble();
				y[i]=in.nextDouble(); 
			}
		}
		else{ // choice == 2
			// diasumsikan file input berada di folder test
			String s="";
			System.out.printf("masukkan nama file: ");
			try{
				s=in2.readLine();
			}
			catch (IOException err){
				err.printStackTrace();
			}
			try{
				BufferedReader file=new BufferedReader(new FileReader(new File("../test/"+s))); 
				String line;
				n=0;
				m=0;
				try{
					while ((line=file.readLine())!=null){
						String parts[]=line.split(" ");
						n=parts.length-1;
						for (int i=0;i<n;i++) x[m][i]=Double.parseDouble(parts[i]);
						y[m]=Double.parseDouble(parts[n]);
						m++;
					}
				}
				catch(IOException err){
					err.printStackTrace();
				}
			}
			catch (FileNotFoundException err){
				err.printStackTrace();
			}
		}
		// imput nilai-nilai x yang akan ditaksir nilai fungsinya
		for (int i=0;i<n;i++) xk[i]=in.nextDouble();
		// dapatkan persamaan regresi
		double b[]=regresi(n,m,x,y);
		// hitung nilai taksiran
		double ans=b[0];
		for (int i=0;i<n;i++) ans+=b[i+1]*xk[i];
		// cetak jawaban
		System.out.printf("persamaan regresi:\n");
		for (int i=0;i<=n;i++){
			if (i>0){
				if (b[i]>0) System.out.printf("+");
			}
			if (i==0) System.out.printf("%f\n",b[i]);
			else System.out.printf("%fx%d\n",b[i],i);
		}
		System.out.printf("nilai taksiran: %f\n",ans);
	}
}