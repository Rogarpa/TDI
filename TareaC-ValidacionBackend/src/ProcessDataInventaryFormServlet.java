import java.util.Collections;
import java.util.Arrays;
//falta descripcion
//falta comentarios
//falta expiracion
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessDataInventaryFormServlet")
public class ProcessDataInventaryFormServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");

        //Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String productType = request.getParameter("productType");
        String adquisicion = request.getParameter("adquisicion");
        String expiracion = request.getParameter("expiracion");
        String cantidad = request.getParameter("cantidad");
        String medida = request.getParameter("medida");
        String precio = request.getParameter("precio");
        String descripcion = request.getParameter("descripcion");
        String comentarios = request.getParameter("comentarios");
        String Provedor = request.getParameter("Pnombre");

        //validacion de datos
        boolean validacion = true;
        



        /**
         * Validación de nombre
         * No presencia de caracteres especiales
         */
        if(stringInvalida(nombre)){
            validacion = false;
            request.setAttribute("nombreError", "El nombre es obligatorio");
        }else{// Verificamos presencia de caracteres especiales
            String patronSinCaracteresEspeciales = "^[\\p{L}\\p{N} ]+$";
            
            if(!(coincideConPatron(patronSinCaracteresEspeciales, nombre))){
                validacion = false;
                request.setAttribute("nombreError", "El nombre no debe llevar caracteres especiales");
            }
        }

        /**
         * Validación de productType:
         * Sea elemento de  productTypeRange
         */

        String[] productTypeRange = {"Perecedero", "Empacar"};
        boolean belongs = Arrays.stream(productTypeRange).anyMatch(productType::equals);
        
        if (productType == null || !(productType.equals("Perecedero") || belongs)) {
            validacion = false;
            request.setAttribute("errorproductType", "Error en el tipo de producto");
        } 
        
        /**
         * Validación de adquisicion:
         * adquisicion<hoy+10 anos
         * adquisicion>hoy
         */
        if(stringInvalida(adquisicion)){
            validacion = false;
            request.setAttribute("erroradquisicion", "La fecha de adquisicion es obligatoria");
        }else{
            try {
                // Verificamos formato de fecha y comparamos con la actual
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaAdquisicion = sdf.parse(adquisicion);
                //Obtenemos fecha actual
                LocalDate fechaActual = LocalDate.now();
                // Verificamos si producto es perecedero
                if ("Perecedero".equals(productType)) {
                
                    // Restamos 10 anos a fecha actual
                    LocalDate fechaHace10Anios = fechaActual.minusYears(10);
                
                    // Comparamos hoy hace diez anos con la fecha recibida
                    if (fechaAdquisicion.before(Date.from(fechaHace10Anios.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                        validacion = false;
                        request.setAttribute("erroradquisicion", "La fecha debe ser de no más a hace 10 años");
                    }
                }
                // Verificamos que la fecha recibida no sea posterior a fecha actual
                if (fechaAdquisicion.after(Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    validacion = false;
                    request.setAttribute("erroradquisicion", "La fecha de adquisición no puede ser posterior a la fecha actual");
                }


            } catch (Exception e) { //Reporte de fallo en el casteo a formato de fecha yyyy-MM-dd
                validacion = false;
                request.setAttribute("erroradquisicion", "Formato de fecha inválido");
            }
        }

        /**
         * Validación de condición:
         * productType="perecedero" => (expiracion>hoy)
         */
        if ("Perecedero".equals(productType)) {
            // El producto es perecedero, por lo que la fecha de caducidad debe ser obligatoria
            if (expiracion == null || expiracion.isEmpty()) {
                validacion = false;
                request.setAttribute("errorexpiracion", "La fecha de expiracion es obligatoria");
            } else {
                // Verificamos formato de fecha y comparamos con la actual
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {//
                    LocalDate expirationDate = LocalDate.parse(expiracion, formatter);
                    LocalDate fechaActual = LocalDate.now();
        
                    if (expirationDate.isBefore(fechaActual)) {
                        validacion = false;
                        request.setAttribute("errorexpiracion", "La fecha d     e expiracion no puede ser anterior a la actual");
                    }
                } catch (DateTimeParseException e) {//Reporte de fallo en el casteo a formato de fecha yyyy-MM-dd
                    validacion = false;
                    request.setAttribute("errorexpiracion", "Formato de fecha inválido");
                }
            }
        }

        /**
         * Validación de cantidad:
         * cantidad>0 y entera
         */
        if (stringInvalida(cantidad)) {
            validacion = false;
            request.setAttribute("errorcantidad", "La cantidad es obligatoria");
        } else {
            try {
                //pasar string a int
                int cant = Integer.parseInt(cantidad);
                if(cant<0){
                    validacion = false;
                    request.setAttribute("errorcantidad", "La cantidad debe ser mayor a 0");
                }
                
            } catch (NumberFormatException e) {
                validacion = false;
                request.setAttribute("errorcantidad", "La cantidad debe ser entera");
            }

        }

        /**
         * Validación de medida
         */

        String[] medidaPerecederosRange = {"g","kg","ml","l"};
        String[] medidaEmpacarRange = {"p"};
        if (stringInvalida(medida)) {
            validacion = false;
            request.setAttribute("errormedida", "Error en el tipo de medida");
        }else{
            boolean belongsPerecederos = Arrays.stream(productTypeRange).anyMatch(productType::equals);
            if("Perecedero".equals(productType)){
                boolean pertenecePerecederos = Arrays.stream(medidaPerecederosRange).anyMatch(medida::equals);
                if(!(pertenecePerecederos)){
                    validacion = false;
                    request.setAttribute("errormedida", "La medida debe ser para tipos perecederos");
                }
            }
            if("Empacar".equals(productType)){
                boolean perteneceEmpacar = Arrays.stream(medidaEmpacarRange).anyMatch(medida::equals);
                if(!(perteneceEmpacar)){
                    validacion = false;
                    request.setAttribute("errormedida", "La medida debe ser para tipo empacar");
                }
            }
        }

        /**
         * Validación de precio:
         * precio>0 y decimal
         */
        if (stringInvalida(precio)) {
            validacion = false;
            request.setAttribute("errorprecio", "El precio es obligatorio");
        } else {
            try {
                //Verificación de condicion de ser precio positivo
                double prec = Double.parseDouble(cantidad);
                if(prec<0){
                    validacion = false;
                    request.setAttribute("errorprecio", "El precio debe ser mayor a 0");
                }
                
            } catch (NumberFormatException e) { //Reporte de fallo en el casteo a double
                validacion = false;
                request.setAttribute("errorcantidad", "Error en el precio");
            }

        }
        
        /**
         * Validación de Proveedor:
         * No presencia de caracteres especiales
         */
        if(stringInvalida(Provedor)){
            validacion = false;
            request.setAttribute("provedorError", "El nombre del provedor es obligatorio");
        }else{ //Verificamos presencia de caracteres especiales
            String patronSinCaracteresEspeciales = "^[\\p{L}\\p{N} ]+$"; 
            
            if(!(coincideConPatron(patronSinCaracteresEspeciales, Provedor))){
                validacion = false;
                request.setAttribute("provedorError", "El nombre no debe llevar caracteres especiales");
            }
        }

        // Redirigimos:
        if(validacion){
            request.getRequestDispatcher("success.jsp").forward(request, response);//Vista confirmación adición a inventario
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response); //Vista inicial de formulario
        }

    }

    public boolean stringInvalida(String inputString){
        return (inputString==null || inputString.trim().isEmpty());
    }


    public boolean coincideConPatron(String patron, String aCoincidir){
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(aCoincidir);
        return matcher.matches();
    }
    
}
=======
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ProcessDataInventaryFormServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String productType = request.getParameter("productType");
        String adquisicion = request.getParameter("adquisicion");
        String expiracion = request.getParameter("expiracion");
        String cantidad = request.getParameter("cantidad");
        String medida = request.getParameter("medida");
        String precio = request.getParameter("precio");
        String descripcion = request.getParameter("descripcion");
        String comentarios = request.getParameter("comentarios");
        String Provedor = request.getParameter("Pnombre");

        //validacion de datos
        boolean validacion = true;

        // Si todo es correcto enviamos a:
        String action = "success.jsp";
        
        //Validacion para nombre
        if(nombre==null || nombre.trim().isEmpty()){
            validacion = false;
            request.setAttribute("nombreError", "El nombre es obligatorio");
        }else{
            String patron = "^[\\p{L}\\p{N} ]+$";
            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(nombre);
            validacion = matcher.matches();
            if(validacion=false){
                request.setAttribute("nombreError", "El nombre no debe llevar caracteres especiales");
            }
        }

        //Valida el tipo de producto
        if (productType == null || !(productType.equals("Perecedero") || productType.equals("Empacar"))) {
            validacion = false;
            request.setAttribute("errorproductType", "Error en el tipo de producto");
        } 
        
        //Valida Adquisicion
        if(adquisicion=="" || adquisicion.isEmpty()){
            validacion = false;
            request.setAttribute("erroradquisicion", "La fecha de adquisicion es obligatoria");
        }else{
            try {
                //objeto de la fecha a este tipo de formato
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaAdquisicion = sdf.parse(adquisicion);
                //fecha actual
                LocalDate fechaActual = LocalDate.now();
                // Verificar si el producto tiene fecha de caducidad
                if ("Perecedero".equals(productType)) {
                
                    // Restar 10 años a la fecha actual
                    LocalDate fechaHace10Anios = fechaActual.minusYears(10);
                
                    // Comparar la fecha de adquisición con la fecha actual menos 10 años
                    if (fechaAdquisicion.before(Date.from(fechaHace10Anios.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                        validacion = false;
                        request.setAttribute("erroradquisicion", "La fecha debe ser de no más a hace 10 años");
                    }
                }
                if (fechaAdquisicion.after(Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    validacion = false;
                    request.setAttribute("erroradquisicion", "La fecha de adquisición no puede ser posterior a la fecha actual");
                }


            } catch (Exception e) {
                validacion = false;
                request.setAttribute("erroradquisicion", "Formato de fecha inválido");
            }
        }

        //Valida expiracion
        if ("Perecedero".equals(productType)) {
            // El producto es perecedero, por lo que la fecha de caducidad debe ser obligatoria
            if (expiracion == null || expiracion.isEmpty()) {
                validacion = false;
                request.setAttribute("errorexpiracion", "La fecha de expiracion es obligatoria");
            } else {
                // Verificar si la fecha de caducidad cumple con los requisitos
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate expirationDate = LocalDate.parse(expiracion, formatter);
                    LocalDate fechaActual = LocalDate.now();
        
                    if (expirationDate.isBefore(fechaActual)) {
                        validacion = false;
                        request.setAttribute("errorexpiracion", "La fecha de expiracion no puede ser anterior a la actual");
                    }
                } catch (DateTimeParseException e) {
                    validacion = false;
                    request.setAttribute("errorexpiracion", "Formato de fecha inválido");
                }
            }
        }

        //Valida cantidad
        if (cantidad == null || cantidad.trim().isEmpty()) {
            validacion = false;
            request.setAttribute("errorcantidad", "La cantidad es obligatoria");
        } else {
            try {
                //pasar string a int
                int cant = Integer.parseInt(cantidad);
                if(cant<0){
                    validacion = false;
                    request.setAttribute("errorcantidad", "La cantidad debe ser mayor a 0");
                }
                
            } catch (NumberFormatException e) {
                validacion = false;
                request.setAttribute("errorcantidad", "La cantidad debe ser entera");
            }

        }

        //Valida medida
        if (medida == null || !(medida.equals("gramos") || medida.equals("kilogramos") || medida.equals("pieza"))) {
            validacion = false;
            request.setAttribute("errormedida", "Error en el tipo de medida");
        }

        //Valida precio
        if (precio == null || precio.trim().isEmpty()) {
            validacion = false;
            request.setAttribute("errorprecio", "El precio es obligatorio");
        } else {
            try {
                //pasar string a double
                double prec = Double.parseDouble(cantidad);
                if(prec<0){
                    validacion = false;
                    request.setAttribute("errorprecio", "El precio debe ser mayor a 0");
                }
                
            } catch (NumberFormatException e) {
                validacion = false;
                request.setAttribute("errorcantidad", "Error en el precio");
            }

        }

         //Validacion para provesor
        if(Provedor==null || Provedor.trim().isEmpty()){
            validacion = false;
            request.setAttribute("provedorError", "El nombre del provedor es obligatorio");
        }else{
            String patron = "^[\\p{L}\\p{N} ]+$";
            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(Provedor);
            validacion = matcher.matches();
            if(validacion=false){
                request.setAttribute("provedorError", "El nombre no debe llevar caracteres especiales");
            }
        }

        if(validacion){
            request.getRequestDispatcher("sucess.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<title>Probando Servlets ... </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("Hola mundo con un servlet by ntory... ");
        out.println("<br />");
        out.println("Fecha del Servidor: " + LocalDate.now());
        out.println("<br />");
        out.println("Hora del Servidor: " + LocalTime.now());
        out.println("</body>");
        out.println("</html>");
    }
}
>>>>>>> 7557340 (UPDATE index.jsp falta poner que solo muestre la fecha si tenemos un producto p)
