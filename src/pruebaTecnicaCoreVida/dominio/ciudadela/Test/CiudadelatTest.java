package pruebaTecnicaCoreVida.dominio.ciudadela.Test;

import org.junit.Test;
import static org.junit.Assert.*;

import pruebaTecnicaCoreVida.dominio.ciudadela.*;
import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class CiudadelatTest {
    Construccion construccion1 = new Construccion(new Dias(3), new Nombre("casa"), new CantidadAdobe(100),new CantidadArena(90), new CantidadCemento(100), new CantidadGrava(50), new CantidadMadera(20));
    Construccion construccion2 = new Construccion(new Dias(2), new Nombre("lago"), new CantidadAdobe(20),new CantidadArena(80), new CantidadCemento(50), new CantidadGrava(60), new CantidadMadera(10));
    Construccion construccion3 = new Construccion(new Dias(1), new Nombre("cancha de futbol"), new CantidadAdobe(20),new CantidadArena(20), new CantidadCemento(20), new CantidadGrava(20), new CantidadMadera(20));
    Construccion construccion4 = new Construccion(new Dias(6), new Nombre("edificio"), new CantidadAdobe(200),new CantidadArena(180), new CantidadCemento(200), new CantidadGrava(100), new CantidadMadera(40));
    Construccion construccion5 = new Construccion(new Dias(2), new Nombre("gimnasio"), new CantidadAdobe(50),new CantidadArena(45), new CantidadCemento(50), new CantidadGrava(25), new CantidadMadera(10));
    List<Construccion> construcciones = Arrays.asList(construccion1, construccion2, construccion3, construccion4, construccion5);

    Material material1 = new Material(new Nombre("cemento"), new Cantidad(800));
    Material material2 = new Material(new Nombre("grava"), new Cantidad(800));
    Material material3 = new Material(new Nombre("arena"), new Cantidad(800));
    Material material4 = new Material(new Nombre("madera"), new Cantidad(800));
    Material material5 = new Material(new Nombre("adobe"), new Cantidad(800));
    List<Material> materiales = Arrays.asList(material1, material2, material3, material4, material5);

    Ciudadela ciudadela = new Ciudadela(construcciones, materiales);

    @Test
    public void testAgregarOrdenCasoFeliz(){
        ciudadela.agregarOrden(0,100,200);

        int resultado = ciudadela.getOrdenes().size();
        int esperado = 1;

        assertEquals(esperado,resultado);
    }

    @Test
    public void testAgregarOrdenCasoTristePorCantidad(){
        ciudadela.getMateriales().get(0).setCantidad(new Cantidad(90));
        ciudadela.agregarOrden(0,101,201);

        int resultado = ciudadela.getOrdenes().size();
        int esperado = 0;

        assertEquals(esperado,resultado);
    }

    @Test
    public void testAgregarOrdenCasoTristePorCoordenadas(){
        List<Orden> ordenes = Arrays.asList(new Orden(0, new CoordenadaX(100), new CoordenadaY(200), new FechaInicio(LocalDateTime.now()), new FechaTerminacion(LocalDateTime.now())));
        ciudadela.setOrdenes(ordenes);
        ciudadela.agregarOrden(0,100,200);

        int resultado = ciudadela.getOrdenes().size();
        int esperado = 1;

        assertEquals(esperado,resultado);
    }

    //Probar antes de medio dia
    @Test
    public void testActualizarEstadosEnLaMañana(){
        List<Orden> ordenes = Arrays.asList(new Orden(0, new CoordenadaX(100), new CoordenadaY(200), new FechaInicio(LocalDateTime.now()), new FechaTerminacion(LocalDateTime.now())));
        ciudadela.setOrdenes(ordenes);
        ciudadela.actualizarEstados();

        String resultado = ciudadela.getOrdenes().get(0).getEstado().getValue();
        String esperado = "en progreso";

        assertEquals(esperado,resultado);
    }

    //Probar despues las 6 de la tarde
    @Test
    public void testActualizarEstadosEnLaNoche(){
        List<Orden> ordenes = Arrays.asList(new Orden(0, new CoordenadaX(100), new CoordenadaY(200), new FechaInicio(LocalDateTime.now()), new FechaTerminacion(LocalDateTime.now())));
        ciudadela.setOrdenes(ordenes);
        ciudadela.getOrdenes().get(0).setEstado(new Estado("en progreso"));
        ciudadela.actualizarEstados();

        String resultado = ciudadela.getOrdenes().get(0).getEstado().getValue();
        String esperado = "finalizado";

        assertEquals(esperado,resultado);
    }

    @Test
    public void testCrearInforme(){
        List<Orden> ordenes = Arrays.asList(new Orden(0, new CoordenadaX(100), new CoordenadaY(200), new FechaInicio(LocalDateTime.now()), new FechaTerminacion(LocalDateTime.now())));
        ciudadela.setOrdenes(ordenes);

        ciudadela.agregarInforme("4");

        int resultado = ciudadela.getInformes().size();
        int esperado = 1;

        assertEquals(esperado,resultado);
    }

    @Test
    public void testConsultarFechaDeTerminacion(){
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        ciudadela.agregarOrden(0,100,200);
        ciudadela.consultarFechaDeTerminacion();

        String resultado = ciudadela.getFechaTerminacion().getValue().format(formato);
        String esperado = LocalDateTime.now().plusDays(5).format(formato);

        assertEquals(esperado,resultado);
    }
}
