package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.Cantidad;
import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.Nombre;

import java.io.Serializable;

public class Material implements Serializable {
    private static Integer ultimoId = -1;

    private Integer id;
    private Nombre nombre;
    private Cantidad cantidad;

    public Material(Nombre nombre, Cantidad cantidad) {
        ultimoId++;
        this.id = ultimoId ;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Cantidad getCantidad() {
        return cantidad;
    }

    public void setCantidad(Cantidad cantidad) {
        this.cantidad = cantidad;
    }
}
