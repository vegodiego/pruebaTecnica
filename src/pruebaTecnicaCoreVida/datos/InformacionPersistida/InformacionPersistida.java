package pruebaTecnicaCoreVida.datos.InformacionPersistida;

import pruebaTecnicaCoreVida.dominio.ciudadela.Construccion;
import pruebaTecnicaCoreVida.dominio.ciudadela.Informe;
import pruebaTecnicaCoreVida.dominio.ciudadela.Material;
import pruebaTecnicaCoreVida.dominio.ciudadela.Orden;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class InformacionPersistida {
    private Integer id;
    private List<Construccion> construcciones;
    private List<Material> materiales;
    private List<Orden> ordenes;
    private List<Informe> informes;

    private static Integer ultimoId = -1;

    public InformacionPersistida() {
        ultimoId++;
        this.id = ultimoId;
        this.construcciones = this.obtenerConstrucciones();
        this.materiales = this.obtenerMateriales();
        this.ordenes = this.obtenerOrdenes();
        this.informes = this.obtenerInformes();
    }

    public List<Construccion> obtenerConstrucciones(){
        List<Construccion> construccionesAnt = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/ficheros/construcciones.ddr"))){
            while (true){
                List<Construccion> construccionViejas = ( List<Construccion>) ois.readObject();
                construccionesAnt = construccionViejas;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }
        return construccionesAnt;
    }

    public List<Material> obtenerMateriales(){
        List<Material> materialesAnt = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/ficheros/materiales.ddr"))){
            while (true){
                List<Material> materialViejo = (List<Material>) ois.readObject();
                materialesAnt = materialViejo;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }
        return materialesAnt;
    }

    public List<Orden> obtenerOrdenes(){
        List<Orden> ordenesAnt = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/ficheros/ordenes.ddr"))){
            while (true){
                List<Orden> ordenesAntiguas = (List<Orden>) ois.readObject();
                ordenesAnt = ordenesAntiguas;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }
        return ordenesAnt;
    }

    public List<Informe> obtenerInformes(){
        List<Informe> informesAnt = new ArrayList<>();
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/pruebaTecnicaCoreVida/datos/ficheros/informes.ddr"))){
            while (true){
                List<Informe> informesAntiguos = (List<Informe>) ois.readObject();
                informesAnt = informesAntiguos;
            }
        }catch (ClassNotFoundException e){
        }catch (EOFException e){
        }catch (IOException e){
        }
        return informesAnt;
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
}
