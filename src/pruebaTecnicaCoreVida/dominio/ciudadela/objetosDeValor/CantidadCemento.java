package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class CantidadCemento implements Serializable {
    private final Integer value;

    public CantidadCemento(Integer value) {
        Objects.requireNonNull(value, "CantidadCemento no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("CantidadCemento no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
