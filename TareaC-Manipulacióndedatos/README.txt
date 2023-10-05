
    El proyecto consiste en ejercer las operaciones basicas sobre una base de datos Mysql, esto sobre las tablas usuario e inventario usando un POJO y un DAO para cada una, por lo que hace falta crear el schema y las tablas ejecutando el archivo db.sql.
    El archivo db.sql crea la bd (tablas usuario e inventario) y además crea un usuario=algo con contrasena=algo.
    
    Para correr el proyecto hay que compilar y correr (con el classpath modificado para incluir mysql-connector.jar):
        Compilar usando jdk8:
        $javac -cp mysql-connector.jar DatabaseConnection.java InventarioPOJO.java UsuarioPOJO.java InventarioDAO.java UsuarioDAO.java Main.java 
        Correr nuestro archivo Main.java
        $java -cp .:mysql-connector.jar Main
    
    