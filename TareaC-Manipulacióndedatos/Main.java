import java.sql.*;  

import java.util.ArrayList;
import java.util.List;
public class Main{  
    /**
     * Makes aggregation, edition and deleting of UsuarioPOJO through UsuarioDAO and InventarioPOJO through InventarioDAO
     */
    public static void main(String args[]){  
        Main m = new Main();
        UsuarioDAO ud = new UsuarioDAO();
        InventarioDAO ui = new InventarioDAO();
        try{
            System.out.println("Base inicial usuario:");
            System.out.println(ud.listarUsuario());
            
            
            // Agregamos un usuario
            System.out.println("Insertamos usuarios: (1, Raul),(2, Roberto),(3,Rodrigo)");
            UsuarioPOJO u1 = new UsuarioPOJO(1);
            u1.setNombre("Raul");
            ud.agregarUsuario(u1);
            UsuarioPOJO u2 = new UsuarioPOJO(2);
            u2.setNombre("Roberto");
            ud.agregarUsuario(u2);
            UsuarioPOJO u3 = new UsuarioPOJO(3);
            u3.setNombre("Rodrigo");
            ud.agregarUsuario(u3);
            System.out.println("Base de datos tras agregar:");
            System.out.println(ud.listarUsuario());
            
            // Editamos un usuario
            System.out.println("Editamos usuario: (3,Rodrigo) -> (3, Ricardo)");
            u3.setNombre("Ricardo");
            ud.editarUsuario(u3);
            System.out.println("Base de datos tras editar:");
            System.out.println(ud.listarUsuario());

            // Eliminamos un usuario
            System.out.println("Eliminamos el usuario con id = 3: (3, Ricardo)");
            ud.eliminarUsuario(3);
            System.out.println("Base de datos tras eliminar:");
            System.out.println(ud.listarUsuario());
            System.out.println("=============================================================================================================");






            System.out.println("Base inicial inventario:");
            System.out.println(ui.listarInventario());
            
            // Agregamos un inventario
            System.out.println("Insertamos inventario: (1,Manzana,...,05-10-2023),(3,Fresa,...,05-10-2023),(3,Cajeta,...,05-10-2023)");
            InventarioPOJO i1 = new InventarioPOJO(1);
            i1.setNombre("Manzana");
            i1.setStock(25);
            i1.setCosto(23);
            i1.setFechaAdquisicion("05-10-2023");
            ui.agregarInventario(i1);
            InventarioPOJO i2 = new InventarioPOJO(2);
            i2.setNombre("Fresa");
            i2.setStock(5);
            i2.setCosto(23);
            i2.setFechaAdquisicion("05-10-2023");
            ui.agregarInventario(i2);
            InventarioPOJO i3 = new InventarioPOJO(3);
            i3.setNombre("Cajeta");
            i3.setStock(16);
            i3.setCosto(75);
            i3.setFechaAdquisicion("05-10-2023");
            ui.agregarInventario(i3);
            System.out.println("Base de datos tras agregar:");
            System.out.println(ui.listarInventario());

            // Editamos un inventario
            System.out.println("Editamos inventario: (3,Cajeta,...,05-10-2023) -> (3,Cajeta,...,06-10-2023)");
            i3.setFechaAdquisicion("06-10-2023");
            ui.editarInventario(i3);
            System.out.println("Base de datos tras editar:");
            System.out.println(ui.listarInventario());

            // Eliminamos un usuario
            System.out.println("Eliminamos el inventario con id = 3: (3,Cajeta,...,05-10-2023)");
            ui.eliminarInventario(3);
            System.out.println("Base de datos tras eliminar:");
            System.out.println(ui.listarInventario());


            
        }catch(ClassNotFoundException e){
            throw new RuntimeException("Error al cargar el controlador JDBC",e);
        }
    }  

}  