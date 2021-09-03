package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.util.Objects;

public class CoordenadaY {
    private final Integer value;

    public CoordenadaY(Integer value) {
        Objects.requireNonNull(value, "CoordenadaY no puede ser nulo");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
