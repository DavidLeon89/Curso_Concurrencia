import java.util.Random;
//Multiplicar por 10 todos los elementos de una matriz de forma concurrente y medir el tiempo

public class Bloque24 extends Thread{
	private static int tam = 5;
	private static int[][] matriz = new int[tam][tam];
	private int inicio, fin;
	
	
	
	public Bloque24(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void run() {
		for (int i = inicio; i < fin; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] *= 10;
			}
		}
	}
	
	
	public static void main(String[] args) {
		Random rand = new Random(System.nanoTime());
		double tiempo_inicio, tiempo_final;
		Runtime runtime = Runtime.getRuntime();	
		int rango, start, finish, nNucleos = runtime.availableProcessors();
		Thread[] hilos = new Thread[nNucleos];
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = rand.nextInt(10);
			}
		}
		
		tiempo_inicio = System.nanoTime(); //Hora en nanosegundos
		
		
		rango = tam/nNucleos;
		start = 0;
		finish = rango;
		
		
		for(int i = 0; i < nNucleos; i++) {
			if (i != nNucleos -1) {
				hilos[i] = new Bloque24(start, finish);
				hilos[i].start();
				start = finish;
				finish += rango;
			}else {
				hilos[i] = new Bloque24(start, tam);
				hilos[i].start();
			}
			
		}
		
		for(int i = 0; i < nNucleos; i++) {
			try {
				hilos[i].join();
			}catch(Exception ex) {}
		}
		
		
		
		tiempo_final = System.nanoTime() - tiempo_inicio;
		
		
		
		
		System.out.println((tiempo_final/1000000) + " milisegundos");
		
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j]+" ");
			}
			
			System.out.println();
		}
		
	}
	
	
}




