package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.time.LocalDateTime;
import java.util.Objects;

public class FechaInicio {
    private final LocalDateTime value;

    public FechaInicio(LocalDateTime value) {
        Objects.requireNonNull(value, "FechaInicio no puede ser nulo");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }
}
