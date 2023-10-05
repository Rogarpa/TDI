/**
 * Data Access Object for InventarioPOJO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class InventarioDAO{


    public List<InventarioPOJO> listarInventario() throws ClassNotFoundException{
        List<InventarioPOJO> inventarios = new ArrayList<>();
        String sql = 
                    "SELECT id,nombre,stock,costo,fechaAdquisicion "
                    +"FROM inventario";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int stock = resultSet.getInt("stock");
                int costo = resultSet.getInt("costo");
                String fechaAdquisicion = resultSet.getString("fechaAdquisicion");

                InventarioPOJO inventario = new InventarioPOJO(id);
                inventario.setNombre(nombre);
                inventario.setStock(stock);
                inventario.setCosto(costo);
                inventario.setFechaAdquisicion(fechaAdquisicion);

                inventarios.add(inventario);
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return inventarios;
        
    }
    
    public boolean agregarInventario(InventarioPOJO inventario) throws ClassNotFoundException{
        String sql = "INSERT INTO inventario(id,nombre,stock,costo,fechaAdquisicion) VALUES (?,?,?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,inventario.getId());
            preparedStatement.setString(2,inventario.getNombre());
            preparedStatement.setInt(3,inventario.getStock());
            preparedStatement.setInt(4,inventario.getCosto());
            preparedStatement.setString(5,inventario.getFechaAdquisicion());


            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas>0;
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
        
    }

    public boolean editarInventario(InventarioPOJO inventario) throws ClassNotFoundException{
        String sql = "UPDATE inventario SET id=?,nombre=?,stock=?,costo=?,fechaAdquisicion=? WHERE id=?";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,inventario.getId());
            preparedStatement.setString(2,inventario.getNombre());
            preparedStatement.setInt(3,inventario.getStock());
            preparedStatement.setInt(4,inventario.getCosto());
            preparedStatement.setString(5,inventario.getFechaAdquisicion());
            preparedStatement.setInt(6,inventario.getId());


            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas>0;
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
        
    }
    public boolean eliminarInventario(int id) throws ClassNotFoundException{
        String sql = "DELETE FROM inventario WHERE id=?";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas>0;
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
        
    }
}