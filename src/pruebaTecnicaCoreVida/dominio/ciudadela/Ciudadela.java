package pruebaTecnicaCoreVida.dominio.ciudadela;

import pruebaTecnicaCoreVida.dominio.ciudadela.objetosDeValor.*;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
    private List<Informe> informes;
    private FechaTerminacion fechaTerminacion;

    private static Integer ultimoId = -1;

    public Ciudadela(List<Construccion> construcciones, List<Material> materiales) {
        ultimoId++;
        this.id = ultimoId;
        this.construcciones = construcciones;
        this.materiales = materiales;
        this.ordenes = new ArrayList<>();
        this.informes = new ArrayList<>();
        this.fechaTerminacion = new FechaTerminacion(LocalDateTime.now());
    }

    public void agregarOrden(int construccionId, int coordenadaX, int coordenadaY){
        var construccion = this.obtenerConstruccionPorId(construccionId);
        boolean validacionDeMaterial = this.validarCantidadDeMaterial(construccion);
        boolean validacionDeCoordenadas = this.validarCoordenadas(coordenadaX, coordenadaY);

        if(validacionDeCoordenadas && validacionDeMaterial){
            System.out.println("\nSolicitud de Construccion creada");
            LocalDateTime fechaInicialDeLaOrden = this.crearFechaDeInicioDeLaOrden();
            LocalDateTime fechaDeTerminacionDeLaOrden = fechaInicialDeLaOrden.plusDays(construccion.getDias().getValue()+1);
            var nuevaOrden = new Orden(construccionId, new CoordenadaX(coordenadaX), new CoordenadaY(coordenadaY), new FechaInicio(fechaInicialDeLaOrden), new FechaTerminacion(fechaDeTerminacionDeLaOrden));
            this.ordenes.add(nuevaOrden);
            this.notificarCreacionDeOrden(nuevaOrden);
            this.actualizarInformacion(construccion, fechaDeTerminacionDeLaOrden);
        }else {
            System.out.println("\nSolicitud de Construccion no creada (falta de material o coordenadas no validas)");
        }
    }

    private Construccion obtenerConstruccionPorId(int construccionId){ ;
        return this.construcciones.stream().filter(i -> i.getId() == construccionId).collect(Collectors.toList()).get(0);
    }

    private Boolean validarCantidadDeMaterial(Construccion construccion){
        return construccion.getCantidadCemento().getValue() <= this.materiales.get(0).getCantidad().getValue() && construccion.getCantidadGrava().getValue() <= this.materiales.get(1).getCantidad().getValue() && construccion.getCantidadArena().getValue() <= this.materiales.get(2).getCantidad().getValue() && construccion.getCantidadMadera().getValue() <= this.materiales.get(3).getCantidad().getValue() && construccion.getCantidadAdobe().getValue() <= this.materiales.get(4).getCantidad().getValue();
    }

    private Boolean validarCoordenadas(int coordenadasX, int coordenadasY){
        List<Orden> orden = this.ordenes.stream().filter(i -> i.getCoordenadaX().getValue() == coordenadasX || i.getCoordenadaY().getValue() == coordenadasY ).collect(Collectors.toList());
        return orden.isEmpty();
    }

    private LocalDateTime crearFechaDeInicioDeLaOrden(){
        if(this.ordenes.isEmpty()){
            return (LocalDateTime.now().plusDays(1));
        }
        var ultimaOrden = this.ordenes.get(this.ordenes.size()-1);
        return ultimaOrden.getFechaTerminacion().getValue().plusDays(1);
    }

    private  void notificarCreacionDeOrden(Orden nuevaOrden){
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("\nOrden de Construccion creada");
        System.out.println("\nOrden: ");
        System.out.println("\nId de la orden: " + nuevaOrden.getId());
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
        var fechaActual = LocalDateTime.now();
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int horaActual = fechaActual.getHour();

        if(horaActual < 12){
            this.actualizacionEnLaMañana(fechaActual, formato);
        }

        if(horaActual >= 18){
            this.actualizacionEnLaNoche(fechaActual, formato);
        }
        System.out.println("\nActualizacion de estados completada");
    }

    private void actualizacionEnLaMañana(LocalDateTime fechaActual, DateTimeFormatter formato){
        for (Orden orden: this.ordenes) {
            if(orden.getFechaInicio().getValue().format(formato).equals(fechaActual.format(formato)) && orden.getEstado().getValue().equals("pendiente")){
                orden.setEstado(new Estado("en progreso"));
                System.out.println("Estado de la orden con Id " + orden.getId() + " actualizado (en progreso)");
            }
        }
    }

    private void actualizacionEnLaNoche(LocalDateTime fechaActual, DateTimeFormatter formato){
        for (Orden orden: this.ordenes) {
            if(orden.getFechaTerminacion().getValue().format(formato).equals(fechaActual.format(formato)) && orden.getEstado().getValue().equals("en progreso")){
                orden.setEstado(new Estado("finalizado"));
                System.out.println("Estado de la orden con Id " + orden.getId() + "actualizado (finalizado)");
            }
        }
    }

    public void agregarInforme(String tipoDeInforme){

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
        this.crearInforme(estado, listaNumeroDeConstrucciones);
    }

    private List<Integer> calcularCantidesParaElInforme(String estado){
        List<Integer> listaNumeroDeConstrucciones = new ArrayList<>();
        for (Construccion construccion: this.construcciones) {
            var contador = 0;
            for (Orden orden: this.ordenes) {
                if(orden.getEstado().getValue().equals(estado) && orden.getIdConstrucion().equals(construccion.getId())){
                    contador++;
                }
            }
            listaNumeroDeConstrucciones.add(contador);
        }

        return listaNumeroDeConstrucciones;
    }

    private void crearInforme(String estado, List<Integer> listaNumeroDeConstrucciones) {
        List<ConstruccionEnInforme> construccionesEnInforme = new ArrayList<>();
        var total = 0;
        for (int i = 0; i < this.construcciones.size(); i++) {
             var construccionEnInforme = new ConstruccionEnInforme(this.construcciones.get(i).getNombre().getValue(), listaNumeroDeConstrucciones.get(i));
             construccionesEnInforme.add(construccionEnInforme);
             total += listaNumeroDeConstrucciones.get(i);
        }

        var nuevoInforme = new Informe(new Estado(estado), construccionesEnInforme, new Total(total));
        this.informes.add(nuevoInforme);

        this.generarInforme(estado, nuevoInforme);
    }

    private void generarInforme(String estado, Informe nuevoInforme) {
        try {
            var writer = new PrintWriter("src/pruebaTecnicaCoreVida/informes/informe"+nuevoInforme.getId()+".txt", "UTF-8");
            writer.println("Construcciones con estado " + estado + ":");
            for (ConstruccionEnInforme construccion : nuevoInforme.getConstruccionesEnInforme()) {
                writer.println(construccion.getConstruccion() + ": " + construccion.getCantidad());
            }
            writer.println("Total de construcciones: " + nuevoInforme.getTotal().getValue());
            writer.close();
            System.out.println("\nInforme generado exitosamente en el directorio informes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarFechaDeTerminacion(){
        var formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if(this.ordenes.isEmpty()){
            System.out.println("\nFecha de terminacion del proyecto: " + this.fechaTerminacion.getValue().format(formato));
        } else {
            var ultimaOrden = this.ordenes.get(this.ordenes.size()-1);
            System.out.println("\nFecha de terminacion del proyecto: " + ultimaOrden.getFechaTerminacion().getValue().format(formato));
        }
    }

    public void persistirMateriales(){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/pruebaTecnicaCoreVida/datos/ficheros/materiales.ddr"))){
            oos.writeObject(this.materiales);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void persistirOrdenes(){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/pruebaTecnicaCoreVida/datos/ficheros/ordenes.ddr"))){
            oos.writeObject(this.ordenes);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void persistirInformes(){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/pruebaTecnicaCoreVida/datos/ficheros/informes.ddr"))){
            oos.writeObject(this.informes);
        }catch (IOException e){
            System.out.println(e.getMessage());
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

    public List<Informe> getInformes() {
        return informes;
    }

    public FechaTerminacion getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;

        if(!this.ordenes.isEmpty()){
            this.ordenes.get(this.ordenes.size()-1).setUltimoId(this.ordenes.size()-1);
        }
    }

    public void setInformes(List<Informe> informes) {
        this.informes = informes;

        if(!this.informes.isEmpty()){
            this.informes.get(this.informes.size()-1).setUltimoId(this.informes.size()-1);
        }
    }
}
