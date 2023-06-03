package co.edu.uniquindio.proyectofinal.controllers;

public class BoundedSemaphore {
	private int senal = 0;
	private int bound   = 0;

	public BoundedSemaphore(int upperBound){
		this.bound = upperBound;
	}

	public synchronized void ocupar() throws InterruptedException{
		while(this.senal == bound){
			wait();
		}
		this.senal++;
		this.notify();
	}

	public synchronized void liberar() throws InterruptedException{
		while(this.senal == 0){
			wait();
		}
		this.senal--;
		this.notify();
	}
}

