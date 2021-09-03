package pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor;

import java.util.Objects;

public class CantidadAdobe {
    private final Integer value;

    public CantidadAdobe(Integer value) {
        Objects.requireNonNull(value, "CantidadAdobe no puede ser nulo");
        if(value < 0){
            throw new IllegalArgumentException("CantidadAdobe no puede ser un numero negativo");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
