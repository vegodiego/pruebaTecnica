package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class ConstruccionEnInforme implements Serializable {
    private final String construccion;
    private final Integer cantidad;


    public ConstruccionEnInforme(String construccion ,Integer cantidad ) {
        Objects.requireNonNull(construccion, "Construccion no puede ser nulo");
        if(!construccion.matches("^[a-zA-Z\\s]*$")){
            throw new IllegalArgumentException("La construccion no es valida");
        }
        Objects.requireNonNull(cantidad, "Cantidad no puede ser nulo");
        if(cantidad < 0){
            throw new IllegalArgumentException("Cantidad no puede ser un numero negativo");
        }
        this.construccion = construccion;
        this.cantidad = cantidad;
    }

    public String getConstruccion() {
        return construccion;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
