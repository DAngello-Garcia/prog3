package parcial3.ejercicio1;

import java.util.ArrayList;


public class Productor extends Thread{
	
	private EstacionServicio estacionServicio;
	ArrayList<CamionSisterna>camionesSisterna;
	int indiceCamion=0;
	
	public Productor(EstacionServicio estacionServicio) {
		this.estacionServicio=estacionServicio;
		camionesSisterna= new ArrayList<CamionSisterna>();
		for(int i=0; i<=3; i++) {
			CamionSisterna camion = new CamionSisterna();
			camionesSisterna.add(camion);
		}
	}
	
	public void run() {
		
		CamionSisterna camion;
		while(!estacionServicio.cerrarEstacion()) {
			camion=camionesSisterna.get(indiceCamion);
			if(camion.getCarga()>0) {
				if(indiceCamion==3) {
					indiceCamion=0;
				}else {
					indiceCamion++;
				}
				System.out.println("Camion enviado");
				estacionServicio.recibirCamion(camion);
				camion.setCarga(camion.getCarga()-4);
				
			}else {
				System.out.println("Camion sin gasolina\n");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(e);
				;
			}
		}
	}
}

