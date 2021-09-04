package pruebaTecnicaCoreVida;

import pruebaTecnicaCoreVida.dominio.ciudadela.Ciudadela;
import pruebaTecnicaCoreVida.dominio.ciudadela.Construccion;
import pruebaTecnicaCoreVida.dominio.ciudadela.Material;
import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Creacion de la lista de construcciones
        List<Construccion> construcciones = new ArrayList<>();
        List<String> nombresDeLasConstrucciones = Arrays.asList("casa", "lago", "cancha de futbol", "edificio", "gimnasio");
        List<List<Integer>> cantidadDeMaterialPorConstruccion = Arrays.asList(Arrays.asList(100,90,100,50,20),Arrays.asList(20,80,50,60,10), Arrays.asList(20,20,20,20,20), Arrays.asList(200,180,200,100,40), Arrays.asList(50,45,50,25,10));
        List<Integer> diasPorConstruccion = Arrays.asList(3,2,1,6,2);

        for (int i = 0; i < cantidadDeMaterialPorConstruccion.size() ; i++) {
            Dias dias = new Dias(diasPorConstruccion.get(i));
            Nombre nombre = new Nombre(nombresDeLasConstrucciones.get(i));
            CantidadAdobe cantidadAdobe = new CantidadAdobe(cantidadDeMaterialPorConstruccion.get(i).get(0));
            CantidadArena cantidadArena = new CantidadArena(cantidadDeMaterialPorConstruccion.get(i).get(1));
            CantidadCemento cantidadCemento = new CantidadCemento(cantidadDeMaterialPorConstruccion.get(i).get(2));
            CantidadGrava cantidadGrava = new CantidadGrava(cantidadDeMaterialPorConstruccion.get(i).get(3));
            CantidadMadera cantidadMadera = new CantidadMadera(cantidadDeMaterialPorConstruccion.get(i).get(4));
            Construccion construccion = new Construccion(dias, nombre, cantidadAdobe, cantidadArena, cantidadCemento, cantidadGrava, cantidadMadera);
            construcciones.add(construccion);
        }

        //Creacion de la lista de materiales
        List<Material> materiales = new ArrayList<>();
        List<String> nombresDeLosMateriales = Arrays.asList("cemento", "grava", "arena", "madera", "adobe");
        List<Integer> cantidadTotalDeLosMateriales = Arrays.asList(500, 500, 500, 500 ,500);

        for (int i = 0; i < cantidadTotalDeLosMateriales.size() ; i++) {
            Nombre nombre = new Nombre(nombresDeLosMateriales.get(i));
            Cantidad cantidad = new Cantidad(cantidadTotalDeLosMateriales.get(i));
            Material material = new Material(nombre, cantidad);
            materiales.add(material);
        }

        //creacion de la ciudadela
        Ciudadela ciudadela = new Ciudadela(construcciones, materiales);

        ciudadela.agregarOrden(0, 100,200);

        System.out.println(ciudadela.getFechaTerminacion().getValue());
        System.out.println(ciudadela.getMateriales().get(0).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(0).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(1).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(1).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(2).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(2).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(3).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(3).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(4).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(4).getCantidad().getValue());


        ciudadela.agregarOrden(0, 101,201);

        System.out.println(ciudadela.getFechaTerminacion().getValue());
        System.out.println(ciudadela.getMateriales().get(0).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(0).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(1).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(1).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(2).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(2).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(3).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(3).getCantidad().getValue());
        System.out.println(ciudadela.getMateriales().get(4).getNombre().getValue());
        System.out.println(ciudadela.getMateriales().get(4).getCantidad().getValue());

        ciudadela.actualizarEstados();

        ciudadela.agregarInforme("4");
    }
}
