package pruebaTecnicaCoreVida;

import pruebaTecnicaCoreVida.datos.InformacionPersistida.InformacionPersistida;
import pruebaTecnicaCoreVida.dominio.ciudadela.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Se trae la informacion persistida
        InformacionPersistida informacionPersistida = new InformacionPersistida();
        List<Construccion> construcciones = informacionPersistida.getConstrucciones();
        List<Material> materiales = informacionPersistida.obtenerMateriales();
        List<Orden> ordenes = informacionPersistida.getOrdenes();
        List<Informe> informes = informacionPersistida.getInformes();

        //creacion de la ciudadela con la informacion persistida
        Ciudadela ciudadela = new Ciudadela(construcciones, materiales);
        ciudadela.setOrdenes(ordenes);
        ciudadela.setInformes(informes);

        //menu
        Scanner in = new Scanner(System.in);




        boolean salir = false;
        String opcion;

        while(!salir){

            System.out.println("\n****** CIUDADELA DEL FUTURO ********\n\n1-CREAR SOLICITUD DE CONSTRUCCION\n" +
                    "2-ACTUALIZAR ESTADO DE LAS ORDENES\n3-AVERIGUAR FECHA DE TERMINACION DE LA OBRA\n4-GENERAR INFOMRE DE CONSTRUCCIONES PENDIENTES\n5-GENERAR INFOMRE DE CONSTRUCCIONES EN PROGRESO\n" +
                    "6-GENERAR INFOMRE DE CONSTRUCCIONES FINALIZADAS\n7-SALIR\n");

            System.out.print("Ingrese una de las opciones del menu: ");
            opcion = in.nextLine();

            switch (opcion) {
                case "1":

                    boolean construccionIdValido = false;
                    String construccionId = "";
                    while (!construccionIdValido){
                        System.out.print("Ingrese el numero del tipo de construccion (0-casa, 1-lago, 2-cancha de futbol, 3-edificio, 4-gimnasio): ");
                        construccionId = in.nextLine();
                        if(construccionId.matches("[0|1|2|3|4]")){
                            construccionIdValido = true;
                        }else{
                            System.out.println("Ingreso invalido");
                        }
                    }

                    boolean coordenadaXValida = false;
                    String coordenadaX = "";
                    while (!coordenadaXValida){
                        System.out.print("Ingrese la coordenada X: ");
                        coordenadaX = in.nextLine();
                        if(coordenadaX.matches("^[\\d]*$")){
                            coordenadaXValida = true;
                        }else{
                            System.out.println("Ingreso invalido");
                        }
                    }

                    boolean coordenadaYValida = false;
                    String coordenadaY = "";
                    while (!coordenadaYValida){
                        System.out.print("Ingrese la coordenada Y: ");
                        coordenadaY = in.nextLine();
                        if(coordenadaY.matches("^[\\d]*$")){
                            coordenadaYValida = true;
                        }else{
                            System.out.println("Ingreso invalido");
                        }
                    }
                    ciudadela.agregarOrden( Integer.parseInt(construccionId), Integer.parseInt(coordenadaX),Integer.parseInt(coordenadaY));
                    break;
                case "2":
                    ciudadela.actualizarEstados();
                    break;
                case "3":
                    ciudadela.consultarFechaDeTerminacion();
                    break;
                case "4":
                    ciudadela.agregarInforme("4");
                    break;
                case "5":
                    ciudadela.agregarInforme("5");
                    break;
                case "6":
                    ciudadela.agregarInforme("6");
                    break;
                case "7":
                    salir=true;
                    System.out.println("\n¡HASTA PRONTO!");
                    ciudadela.persistirMateriales();
                    ciudadela.persistirOrdenes();
                    ciudadela.persistirInformes();
                    break;
                default:
                    System.out.println("\nOPCION INCORRECTA");
            }

        }
    }
}
