package view;

import java.util.concurrent.Semaphore;

import controller.Ex01Threads;

public class Main {

	public static void main(String[] args) {
		
		
		Semaphore semaforoFinal = new Semaphore(1);
		Semaphore semaforoTocha = new Semaphore(1);
		Semaphore semaforoPedra = new Semaphore(1);
		
		for(int i =1; i <=4; i++) {
			Thread thread = new Ex01Threads(semaforoFinal, i, semaforoTocha, semaforoTocha);
			thread.start();
		}
		

		
		for(int i =0 ; i<7 ; i++) {
			int passada = (int) ((Math.random() * 4) +1);
			System.out.println(passada);
		}
	}
}
