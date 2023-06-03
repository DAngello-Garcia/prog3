package parcial3.ejercicio2.servidor.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String documento;
    private TipoVehiculo tipoVehiculo;
    private float cantidadGasolina;
    private LocalDateTime fecha;

    public Cliente() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public float getCantidadGasolina() {
        return cantidadGasolina;
    }

    public void setCantidadGasolina(float cantidadGasolina) {
        this.cantidadGasolina = cantidadGasolina;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setTipoVehiculo(parcial3.ejercicio2.cliente.model.TipoVehiculo automovil) {
    }
}
