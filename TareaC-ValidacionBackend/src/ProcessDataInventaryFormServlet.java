import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.time.*;

public class TestingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<title>Probando Servlets ... </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("Hola mundo con un servlet 3... ");
        out.println("<br />");
        out.println("Fecha del Servidor: " + LocalDate.now());
        out.println("<br />");
        out.println("Hora del Servidor: " + LocalTime.now());
        out.println("</body>");
        out.println("</html>");
    }

}