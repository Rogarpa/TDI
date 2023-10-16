<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Receta Formulario S&L </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="styles.css"> <!-- Ruta relativa al archivo styles.css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
      /* Estilos personalizados para mejorar la apariencia */
      .container {
        margin-top: 20px;
      }
      .form-group {
        margin-bottom: 20px;
      }
      .form-control {
        border-radius: 10;
      }
      .btn-primary {
        border-radius: 1  0;
      }
      /* Estilos para centrar el texto del placeholder */
      .form-control::placeholder {
          text-align: center;
      }
    </style>
  </head>
<body>

<div class="container-fluid">
  <div class="row content">

    <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
      <div class="position-sticky sidenav">
        <h4>Salads & Life</h4>
          <ul class="nav flex-column nav-fill">
            <li class="nav-item">
              <a class="nav-link" href="../menu.html">MENU</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Inventarios/inventarioEnsaladas.html">INVENTARIO</a>
            </li>
          </ul>
          <div class="input-group mt-3">
            <span class="input-group-text">
              <img src="img/search.svg"  alt="Search icon" width="mx-auto"> 
            </span>
            <input type="text" class="form-control" placeholder="Search S&L ">
            <button class="btn btn-outline-secondary" type="button">
              
            </button>
          </div>
    </nav>

    <div class="col" >
      <div class="card text-center mb-3">
          <div class="card-header fw-bold bg-info "> INVENTARIO</div>
          <div class="border border-3 border-success">
            <div class="bg-info bg-opacity-50">
              <div class="col-sm-9  ms-sm-auto col-lg-10 px-md-4">
                <div class="row">
                  <div class="col-md-12 ">
                    <h2 class="text-center bg-info rounded-5 ">FORMULARIO PARA AGREGAR UN NUEVO INGREDIENTE </h2>
                  </div>
                </div>
                <article>
                    <div class="row">
                        <div class="col-md-6 mx-auto">
                            <form action="ProcessDataInventaryFormServlet" method="post">
                                <!--Nombre del producto-->
                                <div class="form-group">
                                    <label for="nombre">Nombre del producto</label>
                                    <input type="text" class="form-control"  aria-label="Small" placeholder="Nombre del producto" id="nombre" name="nombre" required>
                                </div>
                                <!--Tipo de producto-->
                                <div class="form-group">
                                    <label for="productType">Tipo de Producto:</label>
                                        <select id="productType" name="productType" required>
                                            <option value="Perecedero">Perecedero</option>
                                            <option value="noP">No Perecedero</option>
                                            <option value="Empacar">para Empacar</option>
                                        </select> 
                                        <br>
                                    <small id="productTypeHelp" class="form-text text-muted">Por favor selecciona 1.</small>
                                    <!-- <% String productType = (String) request.getParameter("productType"); %> -->
                                </div>

                                
                                <!-- Fecha de caducidad -->
                                  <div class="form-group">
                                    <label for="expiracion">Fecha de Caducidad:</label>
                                    <input type="date" id="expiracion" name="expiracion" min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" >
                                  </div>

                                <!-- Fecha -->
                                <div class="form-group">
                                  <label for="adquisicion">Fecha de Adquisicion:</label>
                                  <input type="date" id="adquisicion" name="adquisicion" required max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" />
                                </div>

                                <!--Cantidad en existencia-->
                                <div class="form-group">
                                    <label for="cantidad">Cantidad en existencia</label>
                                    <input type="number" class="form-control"  aria-label="Small" name="cantidad" id="cantidad"  placeholder="Stock Existente" required>
                                </div>
                                <!--Medida-->
                                <div class="form-group">
                                    <label for="medida">Medida:</label>
                                    <select id="medida" name="medida" required>
                                        <option value="g">Gramos</option>
                                        <option value="kg">Kilogramos</option>
                                        <option value="ml">Mililitros</option>
                                        <option value="l">Litros</option>
                                        <option value="p">Pieza(s)</option>
                                    </select>
                                </div>
                                <!--Precio-->
                                <div class="form-group">
                                    <label for="precio">Precio</label>
                                    <input type="number" id="precio" name="precio" class="form-control"  aria-label="Small" placeholder="Precio del Producto" required>
                                </div>
                                <!--Nombre del provedor-->
                                <div class="form-group">
                                    <label for="Pnombre">Nombre del Proveedor:</label>
                                    <input type="text" class="form-control"  aria-label="Small" id="Pnombre" name="Pnombre" placeholder="Proveedor del Producto"  required />
                                </div>
                                <!--Descripcion -->
                                <div class="form-group">
                                    <label for="descripcion">Descripci\u00f3n del Producto (breve y opcional):</label>
                                    <input class="form-control"  aria-label="Small" type="text" name="descripcion" id="descripcion"  placeholder="Descripci�n breve del Producto(opcional)" />
                                </div>
                                <!--Comentarios-->
                                <div class="form-group">
                                    <label for="comentarios">Comentarios Adicionales (opcional):</label>
                                    <textarea class="form-control"  aria-label="Small" id="comentarios" name="comentarios"  placeholder="Comentarios Adicionales del Producto"></textarea>
                                </div>
        
                                <p></p>
                                <input type="submit" value="Guardar" />
                            </form>
                        </div>
                        
                    </div>
            </div>
          </div>
      </div>
    </div>
  </div>
</div>
<footer class="container-fluid">
  <p> &copy; Salads & Life All rights reserved 2024. </p>
</footer>
</body>
</html>