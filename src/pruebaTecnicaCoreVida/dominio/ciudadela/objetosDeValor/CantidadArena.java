package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class CantidadArena implements Serializable {
    private final Integer value;

    public CantidadArena(Integer value) {
        Objects.requireNonNull(value, "CantidadArena no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("CantidadArena no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
