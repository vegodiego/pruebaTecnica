package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class CoordenadaX implements Serializable {
    private final Integer value;

    public CoordenadaX(Integer value) {
        Objects.requireNonNull(value, "CoordenadaX no puede ser nulo");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
