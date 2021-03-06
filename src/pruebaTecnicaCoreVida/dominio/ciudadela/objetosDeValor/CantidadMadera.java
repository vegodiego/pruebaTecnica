package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class CantidadMadera implements Serializable {
    private final Integer value;

    public CantidadMadera(Integer value) {
        Objects.requireNonNull(value, "CantidadMadera no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("CantidadMadera no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
