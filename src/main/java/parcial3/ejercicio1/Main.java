package parcial3.ejercicio1;


public class Main {

	public static void main(String[] args) {
		EstacionServicio estacion= new EstacionServicio();
		Productor p= new Productor(estacion);
		Consumidor c= new Consumidor(estacion);
		
		c.start();
		p.start();
	}

}
