package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;

import java.io.Serializable;

public class Orden implements Serializable {
    private static Integer ultimoId = -1;

    private Integer id;
    private Integer idConstrucion;
    private CoordenadaX coordenadaX;
    private CoordenadaY coordenadaY;
    private Estado estado;
    private FechaInicio fechaInicio;
    private FechaTerminacion fechaTerminacion;

    public Orden(Integer idConstrucion, CoordenadaX coordenadaX, CoordenadaY coordenadaY, FechaInicio fechaInicio, FechaTerminacion fechaTerminacion) {
        ultimoId++;
        this.id = ultimoId ;
        this.idConstrucion = idConstrucion;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.estado = new Estado("pendiente");
        this.fechaInicio = fechaInicio;
        this.fechaTerminacion = fechaTerminacion;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdConstrucion() {
        return idConstrucion;
    }

    public CoordenadaX getCoordenadaX() {
        return coordenadaX;
    }

    public CoordenadaY getCoordenadaY() {
        return coordenadaY;
    }

    public Estado getEstado() {
        return estado;
    }

    public FechaInicio getFechaInicio() {
        return fechaInicio;
    }

    public FechaTerminacion getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
