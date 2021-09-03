package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.util.Objects;

public class Dias {
    private final Integer value;

    public Dias(Integer value) {
        Objects.requireNonNull(value, "Dias no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("Dias no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
