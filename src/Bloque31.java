
public class Bloque31 implements Runnable {
	private static int cont = 0;
	private static Object cerrojo = new Object();
	@Override
	public void run() {
		synchronized(cerrojo){
		for (int i = 0; i < 20000; i++) {
			//secci�n cr�tica 
			cont++;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		double tiempo_inicio, tiempo_final;
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		tiempo_inicio = System.nanoTime(); //Hora en nanosegundos
		Thread[] hilos = new Thread[nNucleos];
		
		for(int i = 0; i < hilos.length; i++) {
			Runnable runnable = new Bloque31();
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		
		
		for(int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			}catch(Exception EX) {}
		}
		tiempo_final = System.nanoTime() - tiempo_inicio; 
		
		System.out.println((tiempo_final/1000000) + " milisegundos");
		System.out.println(cont);
	}

}
