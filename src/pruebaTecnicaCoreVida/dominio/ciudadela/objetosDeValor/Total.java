package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class Total implements Serializable {
    private final Integer value;

    public Total(Integer value) {
        Objects.requireNonNull(value, "Total no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("Total no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}