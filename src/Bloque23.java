import java.util.Random;
//Multiplicar por 10 todos los elementos de una matriz de forma concurrente y medir el tiempo

public class Bloque23 extends Thread{
	private static int tam = 4;
	private static int[][] matriz = new int[tam][tam];
	private int inicio, fin;
	
	
	
	public Bloque23(int inicio, int fin) {
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
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = rand.nextInt(10);
			}
		}
		
		tiempo_inicio = System.nanoTime(); //Hora en nanosegundos
		
		Bloque23 h1 = new Bloque23(0,tam/3);
		Bloque23 h2 = new Bloque23(tam/3,tam*2/3);
		Bloque23 h3 = new Bloque23(tam*2/3,tam);
		
		h1.start();
		h2.start();
		h3.start();
		
		try {
			h1.join();
			h2.join();
			h3.join();
		}catch(Exception e) {}
		
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
