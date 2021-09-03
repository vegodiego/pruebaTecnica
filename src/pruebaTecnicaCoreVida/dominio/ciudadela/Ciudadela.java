package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ciudadela {
    private Integer id;
    private List<Construccion> construcciones;
    private List<Material> materiales;
    private List<Orden> ordenes;
    private FechaTerminacion fechaTerminacion;

    private static Integer ultimoId = -1;

    public Ciudadela(List<Construccion> construcciones, List<Material> materiales) {
        ultimoId++;
        this.id = ultimoId;
        this.construcciones = construcciones;
        this.materiales = materiales;
        this.ordenes = new ArrayList<>();
        this.fechaTerminacion = new FechaTerminacion(LocalDateTime.now());
    }

    public void agregarOrden(int construccionId, int coordenadaX, int coordenadaY){
        Construccion construccion = this.obtenerConstruccionPorId(construccionId);
        boolean validacionDeMaterial = this.validarCantidadDeMaterial(construccion);
        boolean validacionDeCoordenadas = this.validarCoordenadas(coordenadaX, coordenadaY);

        if(validacionDeCoordenadas && validacionDeMaterial){
            System.out.println("Solicitud de Construccion creada");
            LocalDateTime fechaInicialDeLaOrden = this.crearFechaDeInicioDeLaOrden();
            LocalDateTime fechaDeTerminacionDeLaOrden = fechaInicialDeLaOrden.plusDays(construccion.getDias().getValue()+1);
            Orden nuevaOrden = new Orden(construccionId, new CoordenadaX(coordenadaX), new CoordenadaY(coordenadaY), new FechaInicio(fechaInicialDeLaOrden), new FechaTerminacion(fechaDeTerminacionDeLaOrden));
            this.ordenes.add(nuevaOrden);
            this.notificarCreacionDeOrden(nuevaOrden);
            this.actualizarInformacion(construccion, fechaDeTerminacionDeLaOrden);
        }else {
            System.out.println("Solicitud de Construccion no creada (falta de material o coordenadas no validas)");
        }
    }

    private Construccion obtenerConstruccionPorId(int construccionId){
        Construccion construccion = this.construcciones.stream().filter(i -> i.getId() == construccionId).collect(Collectors.toList()).get(0);
        return  construccion;
    }

    private Boolean validarCantidadDeMaterial(Construccion construccion){
        if(construccion.getCantidadCemento().getValue() <= this.materiales.get(0).getCantidad().getValue() && construccion.getCantidadGrava().getValue() <= this.materiales.get(1).getCantidad().getValue() && construccion.getCantidadArena().getValue() <= this.materiales.get(2).getCantidad().getValue() && construccion.getCantidadMadera().getValue() <= this.materiales.get(3).getCantidad().getValue() && construccion.getCantidadAdobe().getValue() <= this.materiales.get(4).getCantidad().getValue()){
            return true;
        }
        return  false;
    }

    private Boolean validarCoordenadas(int coordenadasX, int coordenadasY){
        List<Orden> orden = this.ordenes.stream().filter(i -> i.getCoordenadaX().getValue() == coordenadasX || i.getCoordenadaY().getValue() == coordenadasY ).collect(Collectors.toList());
        if(orden.isEmpty()){
            return true;
        }
        return false;
    }

    private LocalDateTime crearFechaDeInicioDeLaOrden(){
        if(this.ordenes.isEmpty()){
            return (LocalDateTime.now().plusDays(1));
        }
        Orden ultimaOrden = this.ordenes.get(this.ordenes.size()-1);
        return ultimaOrden.getFechaTerminacion().getValue().plusDays(1);
    }

    private  void notificarCreacionDeOrden(Orden nuevaOrden){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Orden de Construccion creada");
        System.out.println("Orden: ");
        System.out.println("Id de la orden: " + nuevaOrden.getId());
        System.out.println("Id de la construccion: " + nuevaOrden.getIdConstrucion());
        System.out.println("Coordenadas (X,Y): " + "(" + nuevaOrden.getCoordenadaX().getValue()+ ", " + nuevaOrden.getCoordenadaY().getValue() + ")");
        System.out.println("Fecha de inicio: " + nuevaOrden.getFechaInicio().getValue().format(formato));
        System.out.println("Fecha de terminacion: " + nuevaOrden.getFechaTerminacion().getValue().format(formato));
        System.out.println("Estado de la orden: " + nuevaOrden.getEstado().getValue());
    }

    private void actualizarInformacion(Construccion construccion, LocalDateTime fechaTerminacion){
        this.materiales.get(0).setCantidad(new Cantidad(this.materiales.get(0).getCantidad().getValue() - construccion.getCantidadCemento().getValue()));
        this.materiales.get(1).setCantidad(new Cantidad(this.materiales.get(1).getCantidad().getValue() - construccion.getCantidadGrava().getValue()));
        this.materiales.get(2).setCantidad(new Cantidad(this.materiales.get(2).getCantidad().getValue() - construccion.getCantidadArena().getValue()));
        this.materiales.get(3).setCantidad(new Cantidad(this.materiales.get(3).getCantidad().getValue() - construccion.getCantidadMadera().getValue()));
        this.materiales.get(4).setCantidad(new Cantidad(this.materiales.get(4).getCantidad().getValue() - construccion.getCantidadAdobe().getValue()));

        this.fechaTerminacion = new FechaTerminacion(fechaTerminacion);
    }









    public Integer getId() {
        return id;
    }

    public List<Construccion> getConstrucciones() {
        return construcciones;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public FechaTerminacion getFechaTerminacion() {
        return fechaTerminacion;
    }
}
