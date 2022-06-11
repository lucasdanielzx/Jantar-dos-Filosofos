package jantarDosFilosofos;

public class Garfo {
	
	private int id;
	private Thread filosofo;
	
	public Garfo (int id) {
		this.id = id;
		this.filosofo = null;   //garfo na mesa		
	}
	
	//synchronized: garante que n�o haver� conflito de thread
	
	public synchronized void pegar() throws InterruptedException {
		if (filosofo != null) {
			wait();   //espera at� que o garfo seja solto
		}
		filosofo = Thread.currentThread();   //botou o garfo na m�o
	}

	public synchronized void soltar() {
		if (filosofo == Thread.currentThread()) {   //verifica se o fil�sofo est� segurando esse garfo
			filosofo = null;
		}
		notify();   //notifica que a thread pausada pode continuar
	}		
}