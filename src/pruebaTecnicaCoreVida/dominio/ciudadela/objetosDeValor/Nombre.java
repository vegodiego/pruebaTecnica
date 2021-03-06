package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class Nombre implements Serializable {
    private final String value;

    public Nombre(String value) {
        Objects.requireNonNull(value, "El nombre no puede ser nulo");
        if(!value.matches("^[a-zA-Z\\s]*$")){
            throw new IllegalArgumentException("El nombre no es valido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
