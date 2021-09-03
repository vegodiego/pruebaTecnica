package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.FechaTerminacion;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ciudadela {
    private Integer id;
    private List<Construccion> construcciones;
    private List<Material> materiales;
    private List<Orden> ordenes;
    private FechaTerminacion fechaTerminacion;

    private static Integer ultimoId = -1;

    public Ciudadela(List<Construccion> construcciones, List<Material> materiales) {
        ultimoId++;
        this.id = ultimoId;
        this.construcciones = construcciones;
        this.materiales = materiales;
        this.ordenes = new ArrayList<>();
        this.fechaTerminacion = new FechaTerminacion(LocalDateTime.now());
    }

    public Integer getId() {
        return id;
    }

    public List<Construccion> getConstrucciones() {
        return construcciones;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public FechaTerminacion getFechaTerminacion() {
        return fechaTerminacion;
    }
}
