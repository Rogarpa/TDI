/**
 * POJO for Inventario
 */
public class InventarioPOJO{
    private int id;
    private String nombre;
    private int stock;
    private int costo;
    private String fechaAdquisicion;

    public InventarioPOJO(int id){
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

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getCosto(){
        return costo;
    }

    public void setCosto(int costo){
        this.costo = costo;
    }

    public String getFechaAdquisicion(){
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(String fechaAdquisicion){
        this.fechaAdquisicion = fechaAdquisicion;
    }
    
    @Override
    public String toString(){
        return 
            "Id:"+ id
            +" Nombre:"+ nombre
            +" Stock:"+ stock
            +" Costo:"+ costo
            +" FechaAdquisicion:"+ fechaAdquisicion;
    }
}