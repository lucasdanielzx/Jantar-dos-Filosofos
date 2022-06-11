package jantarDosFilosofos;

public class Garfo {
	
	private int id;
	private Thread filosofo;
	
	public Garfo (int id) {
		this.id = id;
		this.filosofo = null;   //garfo na mesa		
	}
	
	//synchronized: garante que não haverá conflito de thread
	
	public synchronized void pegar() throws InterruptedException {
		if (filosofo != null) {
			wait();   //espera até que o garfo seja solto
		}
		filosofo = Thread.currentThread();   //botou o garfo na mão
	}

	public synchronized void soltar() {
		if (filosofo == Thread.currentThread()) {   //verifica se o filósofo está segurando esse garfo
			filosofo = null;
		}
		notify();   //notifica que a thread pausada pode continuar
	}		
}