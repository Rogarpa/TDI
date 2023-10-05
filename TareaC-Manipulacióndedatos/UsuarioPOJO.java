/**
 * POJO for Usuario
 */
public class UsuarioPOJO{
    private int id;
    private String nombre;

    public UsuarioPOJO(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return 
            "Id:"+ id
            +" Nombre:"+ nombre;
    }
}