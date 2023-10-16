import java.util.Collections;
import java.util.Arrays;
//falta descripcion
//falta comentarios
//falta expiracion
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.regex.Matcher;
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
        if (stringInvalida(medida)) {
            validacion = false;
            request.setAttribute("errormedida", "Error en el tipo de medida");
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
        System.out.println(Collections.list(request.getParameterNames()));
        System.out.println(Collections.list(request.getParameterNames()));
        System.out.println(Collections.list(request.getAttributeNames()));
        
        

        // Redirigimos:
        if(validacion){
            request.getRequestDispatcher("success.jsp").forward(request, response);//Vista confirmación adición a inventario
        }else{
            request.getRequestDispatcher("indexjosue.jsp").forward(request, response); //Vista inicial de formulario
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
