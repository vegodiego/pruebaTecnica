package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.ConstruccionEnInforme;
import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.Estado;
import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.Total;

import java.io.Serializable;
import java.util.List;

public class Informe implements Serializable {
    private Integer id;
    private Estado estado;
    private List<ConstruccionEnInforme> construccionesEnInforme;
    private Total total;

    private static Integer ultimoId = -1;

    public Informe(Estado estado, List<ConstruccionEnInforme> construccionesEnInforme, Total total) {
        ultimoId++;
        this.id = ultimoId;
        this.estado = estado;
        this.construccionesEnInforme = construccionesEnInforme;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }

    public List<ConstruccionEnInforme> getConstruccionesEnInforme() {
        return construccionesEnInforme;
    }

    public Total getTotal() {
        return total;
    }

    public static Integer getUltimoId() {
        return ultimoId;
    }
}
