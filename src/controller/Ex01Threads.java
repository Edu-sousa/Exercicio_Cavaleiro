package controller;

import java.util.concurrent.Semaphore;

public class Ex01Threads extends Thread {

	private Semaphore semaforo;
	private Semaphore semaforoTocha;
	private Semaphore semaforoPedra;
	private int cavaleiro;
	private int trajeto;
	private static int velocidadeMax = 3;
	private static int velocidadeMin = 2;
	private int portaCerta = (int) ((Math.random() * 4) + 1);;

	public Ex01Threads(Semaphore semaforo, int cavaleiro, Semaphore semaforoTocha, Semaphore semaforoPedra) {
		this.semaforo = semaforo;
		this.cavaleiro = cavaleiro;
		this.semaforoTocha = semaforoTocha;
		this.semaforoPedra = semaforoPedra;
	}

	@Override
	public void run() {

		caminhar();

	}

	private void caminhar() {
		boolean pegarTocha = false, pegarPedra = false, saida = true;

		while (trajeto < 2000) {

			int passada = (int) ((Math.random() * velocidadeMax) + velocidadeMin);
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			trajeto += passada;
			System.out.println("Cavaleiro " + cavaleiro + " Andou " + passada + " Metros. Total percorrido " + trajeto
					+ " Metros");

			if (trajeto >= 500 && pegarTocha == false) {
				try {
					semaforoTocha.acquire();
					acharTocha();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforoTocha.release();
				}
				pegarTocha = true;
			}

			if (trajeto >= 1500 && pegarPedra == false) {
				try {
					semaforoPedra.acquire();
					acharPedra();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforoPedra.release();
				}
				pegarPedra = true;
			}

			if (trajeto > 2000 && saida == true) {
				try {
					semaforoPedra.acquire();
					portaFinal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforoPedra.release();
				}
				saida = false;
			}
		}
	}

	private void acharTocha() {
		System.out.println("Cavaleiro " + cavaleiro + " achou a Tocha");
		velocidadeMax = 4;
		velocidadeMin = 4;
	}

	private void acharPedra() {
		System.out.println("Cavaleiro " + cavaleiro + " achou a Pedra");
		velocidadeMax = 6;
		velocidadeMin = 6;
	}

	private void portaFinal() {

	}

}
