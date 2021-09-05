package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class CantidadAdobe implements Serializable {
    private final Integer value;

    public CantidadAdobe(Integer value) {
        Objects.requireNonNull(value, "CantidadAdobe no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("CantidadAdobe no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
