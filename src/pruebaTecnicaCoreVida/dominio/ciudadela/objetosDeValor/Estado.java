package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.io.Serializable;
import java.util.Objects;

public class Estado implements Serializable {
    private final String value;

    public Estado(String value) {
        Objects.requireNonNull(value, "El estado no puede ser nulo");
        if(!value.matches("^[a-zA-Z\\s]*$")){
            throw new IllegalArgumentException("El estado no es valido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
