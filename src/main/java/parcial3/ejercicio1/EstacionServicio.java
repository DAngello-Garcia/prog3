package parcial3.ejercicio1;

public class EstacionServicio {
	
	int capacidad=200;
	int contenidoTanque=20;
	int ciclosAbastecimiento=0;
	int ciclosConsumo=0;
	private boolean cerrarEstacion=false;
	private boolean estaLlena = false;
    private boolean estaVacia = false;
	
	public EstacionServicio() {
		
	}
	
	public synchronized void recibirCamion(CamionSisterna camion) {
		while (estaLlena == true) {
            try {
                wait(); // Se sale cuando estaLlena cambia a false
            } catch (InterruptedException e) {
                ;
            }
		}
		System.out.println("Camion recibido");
		contenidoTanque+=20;
		System.out.println("Gasolina disponible "+contenidoTanque+"\n");
		camion.setCarga(camion.getCarga()-20);;
		ciclosAbastecimiento++;
		System.out.println("Ciclos abastecimiento "+ciclosAbastecimiento+"\n");
		if(contenidoTanque>=capacidad-19) {
			estaLlena=true;
		}
		estaVacia=false;
		notify();
	}
	
	public boolean cerrarEstacion() {
		if(ciclosAbastecimiento==22 || ciclosConsumo==33) {
			cerrarEstacion=true;
		}
		return cerrarEstacion;
	}
	
	public synchronized void recibirCliente(Vehiculo vehiculo) {
		while (estaVacia == true) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                ;
            }
		}
		System.out.println("Cliente recibido");
		contenidoTanque-=vehiculo.getConsumo();
		System.out.println("Gasolina disponible"+contenidoTanque);
		ciclosConsumo++;
		System.out.println("Ciclos consumo "+ciclosConsumo+"\n");
		if(contenidoTanque<=10) {
			estaVacia=true;
		}
		estaLlena=false;
		notify();
        }
		
	}

