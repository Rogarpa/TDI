/**
 * Data Access Object for UsuarioPOJO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO{


    public List<UsuarioPOJO> listarUsuario() throws ClassNotFoundException{
        List<UsuarioPOJO> usuarios = new ArrayList<>();
        String sql = 
                    "SELECT id,nombre "
                    +"FROM usuario";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                
                UsuarioPOJO usuario = new UsuarioPOJO(id);
                usuario.setNombre(nombre);
                usuarios.add(usuario);
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return usuarios;
        
    }
    
    public boolean agregarUsuario(UsuarioPOJO usuario) throws ClassNotFoundException{
        String sql = "INSERT INTO usuario(id, nombre) VALUES (?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,usuario.getId());
            preparedStatement.setString(2,usuario.getNombre());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas>0;
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
        
    }

    public boolean editarUsuario(UsuarioPOJO usuario) throws ClassNotFoundException{
        String sql = "UPDATE usuario SET id=?, nombre=? WHERE id=?";
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,usuario.getId());
            preparedStatement.setString(2,usuario.getNombre());
            preparedStatement.setInt(3,usuario.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas>0;
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
        
    }

    public boolean eliminarUsuario(int id) throws ClassNotFoundException{
        String sql = "DELETE FROM usuario WHERE id=?";
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