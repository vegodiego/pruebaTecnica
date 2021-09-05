package pruebaTecnicaCoreVida;

import pruebaTecnicaCoreVida.dominio.ciudadela.Ciudadela;
import pruebaTecnicaCoreVida.dominio.ciudadela.Construccion;
import pruebaTecnicaCoreVida.dominio.ciudadela.Material;
import pruebaTecnicaCoreVida.dominio.ciudadela.Orden;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        //trayendo las construcciones de datos
        List<Construccion> construcciones = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/construcciones.ddr"))){
            while (true){
                List<Construccion> construccionViejas = ( List<Construccion>) ois.readObject();
                construcciones = construccionViejas;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }

        //trayendo los materiales de datos
        List<Material> materiales = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/materiales.ddr"))){
            while (true){
                List<Material> materialViejo = (List<Material>) ois.readObject();
                materiales = materialViejo;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }

        //trayendo las ordenes antiguas de datos
        List<Orden> ordenesAntiguas = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/ordenes.ddr"))){
            while (true){
                List<Orden> ordenes = (List<Orden>) ois.readObject();
                ordenesAntiguas = ordenes;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }

        //creacion de la ciudadela
        Ciudadela ciudadela = new Ciudadela(construcciones, materiales);
        ciudadela.setOrdenes(ordenesAntiguas);


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
                    System.out.print("Ingrese el numero del tipo de construccion (0-casa, 1-lago, 2-cancha de futbol, 3-edificio, 4-gimnasio): ");
                    String construccionId = in.nextLine();
                    System.out.print("Ingrese la coordenada X: ");
                    String coordenadaX = in.nextLine();
                    System.out.print("Ingrese la coordenada y: ");
                    String coordenadaY = in.nextLine();

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
                    break;
                default:
                    System.out.println("\nOPCION INCORRECTA");
            }

        }
    }
}
