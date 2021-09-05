package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class CantidadGrava implements Serializable {
    private final Integer value;

    public CantidadGrava(Integer value) {
        Objects.requireNonNull(value, "CantidadGrava no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("CantidadGrava no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
