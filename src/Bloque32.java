
public class Bloque32 implements Runnable {

	private int id;
	private static Object cerrojo = new Object();
	private static int cont = 0;
	
	public Bloque32(int id) {
		this.id = id;
	}
	
	public void run() {
		synchronized(cerrojo){
			//debe hacerse con un while porque si lo hacemos con un if cuando el notifyAll lo despierte
			//coninuará a partir del wait 
			while(id != cont) {
				try {
					cerrojo.wait(); //duerme a los hilos en la cola del cerrojo
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Soy el hilo: " + id);
			cont++;
			cerrojo.notifyAll(); //despierta a los hilos que duermen en la cola del cerrojo
		}
		
	}
	
	public static void main(String[] args) {
		
		double tiempo_inicio, tiempo_final;
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		tiempo_inicio = System.nanoTime(); //Hora en nanosegundos
		Thread[] hilos = new Thread[nNucleos];
		
		for(int i = 0; i < hilos.length; i++) {
			Runnable runnable = new Bloque32(i);
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		
		
		for(int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			}catch(Exception EX) {}
		}
		tiempo_final = System.nanoTime() - tiempo_inicio; 
		System.out.println("Soy el hilo principal");
		System.out.println((tiempo_final/1000000) + " milisegundos");
		
	}

}
