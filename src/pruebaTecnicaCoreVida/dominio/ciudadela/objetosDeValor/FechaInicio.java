package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FechaInicio implements Serializable {
    private final LocalDateTime value;

    public FechaInicio(LocalDateTime value) {
        Objects.requireNonNull(value, "FechaInicio no puede ser nulo");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }
}
