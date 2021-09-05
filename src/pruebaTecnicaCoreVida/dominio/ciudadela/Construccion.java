package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;

import java.io.Serializable;

public class Construccion implements Serializable {
    private static Integer ultimoId = -1;

    private Integer id;
    private Dias dias;
    private Nombre nombre;
    private CantidadAdobe cantidadAdobe;
    private CantidadArena cantidadArena;
    private CantidadCemento cantidadCemento;
    private CantidadGrava cantidadGrava;
    private CantidadMadera cantidadMadera;

    public Construccion(Dias dias, Nombre nombre, CantidadAdobe cantidadAdobe, CantidadArena cantidadArena, CantidadCemento cantidadCemento, CantidadGrava cantidadGrava, CantidadMadera cantidadMadera) {
        ultimoId++;
        this.id = ultimoId ;
        this.dias = dias;
        this.nombre = nombre;
        this.cantidadAdobe = cantidadAdobe;
        this.cantidadArena = cantidadArena;
        this.cantidadCemento = cantidadCemento;
        this.cantidadGrava = cantidadGrava;
        this.cantidadMadera = cantidadMadera;
    }

    public Integer getId() {
        return id;
    }

    public Dias getDias() {
        return dias;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public CantidadAdobe getCantidadAdobe() {
        return cantidadAdobe;
    }

    public CantidadArena getCantidadArena() {
        return cantidadArena;
    }

    public CantidadCemento getCantidadCemento() {
        return cantidadCemento;
    }

    public CantidadGrava getCantidadGrava() {
        return cantidadGrava;
    }

    public CantidadMadera getCantidadMadera() {
        return cantidadMadera;
    }
}
