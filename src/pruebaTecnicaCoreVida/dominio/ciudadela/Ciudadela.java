package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;


import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void actualizarEstados(){
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int horaActual = fechaActual.getHour();

        if(horaActual < 12){
            this.actualizacionEnLaMañana(fechaActual, formato);
        }

        if(horaActual > 15){
            this.actualizacionEnLaNoche(fechaActual, formato);
        }
        System.out.println("Actualizacion de estados completada");
    }

    private void actualizacionEnLaMañana(LocalDateTime fechaActual, DateTimeFormatter formato){
        for (int i = 0; i < this.ordenes.size() ; i++) {
            if(this.ordenes.get(i).getFechaInicio().getValue().format(formato).equals(fechaActual.format(formato)) && this.ordenes.get(i).getEstado().getValue().equals("pendiente")){
                this.ordenes.get(i).setEstado(new Estado("en progreso"));
                System.out.println("Estado de la orden con Id " + this.ordenes.get(i).getId() + " actualizado (en progreso)");
            }
        }
    }

    private void actualizacionEnLaNoche(LocalDateTime fechaActual, DateTimeFormatter formato){
        for (int i = 0; i < this.ordenes.size() ; i++) {
            if(this.ordenes.get(i).getFechaTerminacion().getValue().format(formato).equals(fechaActual.format(formato)) && this.ordenes.get(i).getEstado().getValue().equals("en progreso")){
                this.ordenes.get(i).setEstado(new Estado("finalizado"));
                System.out.println("Estado de la orden con Id " + this.ordenes.get(i).getId() + "actualizado (finalizado)");
            }
        }
    }

    public void generarInforme(String tipoDeInforme){

        String estado;

        switch (tipoDeInforme) {
            case "4":
                estado = "pendiente";
                break;
            case "5":
                estado = "en progreso";
                break;
            default:
                estado = "finalizado";
        }

        List<Integer> listaNumeroDeConstrucciones = this.calcularCantidesParaElInforme(estado);
        List<String> construcciones  = Arrays.asList("casas", "lagos", "canchas de futbol", "edificios", "gimnasios");
        this.crearInforme(estado, listaNumeroDeConstrucciones, construcciones);
    }

    private List<Integer> calcularCantidesParaElInforme(String estado){
        List<Integer> listaNumeroDeConstrucciones = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            int contador = 0;
            for (int j = 0; j < this.ordenes.size() ; j++) {
                if(this.ordenes.get(j).getEstado().getValue().equals(estado) && this.ordenes.get(j).getIdConstrucion() == i){
                    contador++;
                }
            }
            listaNumeroDeConstrucciones.add(contador);
        }
        return listaNumeroDeConstrucciones;
    }

    private void crearInforme(String estado, List<Integer> listaNumeroDeConstrucciones, List<String> construcciones) {
        try {
            PrintWriter writer = new PrintWriter("src/informes/informe.txt", "UTF-8");
            writer.println("Construcciones con estado " + estado + ":");
            for (int i = 0; i < construcciones.size(); i++) {
                writer.println(construcciones.get(i) + ": " + listaNumeroDeConstrucciones.get(i));
            }
            writer.close();
            System.out.println("Informe generado exitosamente en el directorio informes");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
