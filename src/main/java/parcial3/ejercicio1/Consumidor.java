package parcial3.ejercicio1;

import java.util.ArrayList;


public class Consumidor extends Thread{
	private EstacionServicio estacionServicio;
	private ArrayList<Vehiculo> clientes = new ArrayList<>();
	
	public Consumidor(EstacionServicio estacionServicio) {
		this.estacionServicio=estacionServicio;
		for (int i=0; i<5; i++) {
			Vehiculo vehiculo= new Vehiculo();
			vehiculo.setTipoVehiculo(Tipo_Vehiculo.Auto);
			vehiculo.setConsumo(10);
			clientes.add(vehiculo);
		}
		for (int i=0; i<5; i++) {
			Vehiculo vehiculo= new Vehiculo();
			vehiculo.setTipoVehiculo(Tipo_Vehiculo.Moto);
			vehiculo.setConsumo(4);
			clientes.add(vehiculo);
		}
	}
	
	public void run() {
        Vehiculo vehiculo;
        while(!estacionServicio.cerrarEstacion()) {
        	vehiculo = clientes.get((int) (Math.random()*9));
        	System.out.println("Enviado cliente");
            estacionServicio.recibirCliente(vehiculo);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ;
            }
        }
    }

}

