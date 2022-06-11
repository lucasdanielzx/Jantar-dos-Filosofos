package jantarDosFilosofos;

import java.util.Random;

public class Filosofo implements Runnable {

	private static final int TEMPO_MAXIMO_PENSANDO = 1000;
	
	private int id;
	private int tempoComendo;
	private Garfo meuGarfo, garfoVizinho;
	
	private Random rand = new Random();
	
	public Filosofo (int id, int tempoComendo, Garfo esquerda, Garfo direita) {
		this.id = id;
		this.tempoComendo = tempoComendo;
		this.meuGarfo = esquerda;
		this.garfoVizinho = direita;
	}
	
	private void pensar () {
		System.out.println("\n\nFil�sofo " + id + " est� PENSANDO");
		
		try {
			int tempoPensando = rand.nextInt(TEMPO_MAXIMO_PENSANDO);
			
			Thread.sleep(tempoPensando);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void pegarGarfo() {
		try {
			if (rand.nextInt(2) == 1) {
				meuGarfo.pegar();
				System.out.println("Fil�sofo " + id + " PEGOU o 1� garfo (esquerdo)");
				garfoVizinho.pegar();
				System.out.println("Fil�sofo " + id + " PEGOU o 2� garfo (direito)");
			} 
			else {
				garfoVizinho.pegar();
				System.out.println("Fil�sofo " + id + " PEGOU o 1� garfo (direito)");
				meuGarfo.pegar();
				System.out.println("Fil�sofo " + id + " PEGOU o 2� garfo (esquerdo)");				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void soltarGarfo () {
		if (rand.nextInt(2) == 1) {
			meuGarfo.soltar();
			System.out.println("Fil�sofo " + id + " SOLTOU o 1� garfo (esquerdo)");
			garfoVizinho.soltar();
			System.out.println("Fil�sofo " + id + " SOLTOU o 2� garfo (direito)");
		} 
		else {
			garfoVizinho.soltar();
			System.out.println("Fil�sofo " + id + " SOLTOU o 1� garfo (direito)");
			meuGarfo.soltar();
			System.out.println("Fil�sofo " + id + " SOLTOU o 2� garfo (esquerdo)");				
		}
	}
	
	private void comer() throws InterruptedException {
		System.out.println("\n\nFil�sofo " + id + " est� COMENDO");
		
		Thread.sleep(tempoComendo);
	}
		
	@Override
	public void run() {
		try {
			while (true) {
				pensar();
				pegarGarfo();
				comer();
				soltarGarfo();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}