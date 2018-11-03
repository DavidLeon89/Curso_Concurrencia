import java.util.Random;

public class Bloque33 implements Runnable{

	private int id;
	private static Random cerrojoA = new Random();
	private static Thread cerrojoB = new Thread();
	
	public Bloque33 (int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		if (id % 2 == 0) {
			synchronized(cerrojoA) {
				mostrarA();
			}
		}else {
			synchronized(cerrojoB) {
				mostrarB();
			}
		}
	}
	
	private void mostrarA() {
		synchronized(cerrojoB) {
			System.out.println("Soy el hilo: "+id);
		}
	}
	
	private void mostrarB() {
		synchronized(cerrojoA) {
			System.out.println("Soy el hilo: "+id);
		}
	}
	
public static void main(String[] args) {
		
		double tiempo_inicio, tiempo_final;
		Runtime runtime = Runtime.getRuntime();
		int nNucleos = runtime.availableProcessors();
		tiempo_inicio = System.nanoTime(); //Hora en nanosegundos
		Thread[] hilos = new Thread[nNucleos];
		
		for(int i = 0; i < hilos.length; i++) {
			Runnable runnable = new Bloque33(i);
			hilos[i] = new Thread(runnable);
			hilos[i].start();
		}
		
		
		for(int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			}catch(Exception EX) {}
		}
		System.out.println("Soy el hilo principal");
		tiempo_final = System.nanoTime() - tiempo_inicio; 		
		System.out.println((tiempo_final/1000000) + " milisegundos");
		
	}
	
	
}
