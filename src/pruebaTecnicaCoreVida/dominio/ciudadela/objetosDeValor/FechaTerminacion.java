package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FechaTerminacion implements Serializable {
    private final LocalDateTime value;

    public FechaTerminacion(LocalDateTime value) {
        Objects.requireNonNull(value, "FechaTerminacion no puede ser nulo");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }
}
