package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class Cantidad implements Serializable {
    private final Integer value;

    public Cantidad(Integer value) {
        Objects.requireNonNull(value, "Cantidad no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("Cantidad no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
