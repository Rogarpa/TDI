<!DOCTYPE html>
<html lang="en">
<head>
  <title>Inventario de Ensaladas</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="../styles.css"> <!-- Ruta relativa al archivo styles.css -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
              <ul class="nav flex-column">
                <li class="nav-item">
                  <a class="nav-link" href="../Recetas/recetaFormulario.html">CREAR RECETA</a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Inventarios/inventarioEnsaladas.html">INVENTARIO</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../Inventarios/inventarioFormulario.html">CREAR INGREDIENTE</a>
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

    <div class="col-sm-9  ms-sm-auto col-lg-10 px-md-4">
      <div class="row">
        <div class="col-md-12 center">
          <h2 class="text-center bg-success rounded-5 ">Guardado</h2>
        </div>
      </div>
      

      <div class="row">
        <div class="col-md-6 center">
          <h2 class="text-center bg-success rounded-5 "><small>Ingrediente guardado con exito</small></h2>
        </div>
      </div>
      <hr>
      <!-- <ul class="nav nav-pills nav-stacked">
        <div class = "bg-success">
            <li>Ingrediente 1</li>
            <p class = "badge badge-primary">
                Existencia
            </p>
            <p class = "badge badge-secondary">
                Costo
            </p>
            <p class = "badge badge-primary">
                Fecha adquisición
            </p>
        </div>
        <div class = "bg-success">
            <li>Ingrediente 2</li>
            <p class = "badge badge-primary">
                Existencia
            </p>
            <p class = "badge badge-secondary">
                Costo
            </p>
            <p class = "badge badge-primary">
                Fecha adquisición
            </p>
        </div>
      </ul><br> -->

      <div class="col-md-6">
        <div class="card-header fw-bold bg-info "></div>
        <div class="border border-3 border-success p-1">
          <div class="bg-info bg-opacity-50">
              <table class="table">
                <thead>
                    <tr>
                        <th>Nombre del producto</th>
                        <th>Tipo de Producto</th>
                        <%= (request.getParameter("expiracion") == "")? "":"<th>Fecha de Caducidad</th>" %>
                        <th>Fecha de Adquisicion</th>
                        <th>Cantidad en existencia</th>
                        <th>Medida</th>
                        <th>Precio</th>
                        <th>Nombre del Proveedor</th>
                        <%= (request.getParameter("descripcion") == "")? "":"<th>Descripci\u00f3n del Producto</th>" %>
                        <%= (request.getParameter("comentarios") == "")? "":"<th>Comentarios Adicionales</th>" %>

                        <!-- <th>Nombre</th>
                        <th>Existencia</th>
                        <th>Costo</th>
                        <th>Fecha de Adquisicion</th> -->
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${param.nombre}</td>
                        <td>${param.productType}</td>
                        <%= (request.getParameter("expiracion") == "")? "":("<td>"+request.getParameter("expiracion")+"</td>")%>
                        <td>${param.adquisicion}</td>
                        <td>${param.cantidad}</td>
                        <td>${param.medida}</td>
                        <td>${param.precio}</td>
                        <td>${param.Pnombre}</td>
                        <%= (request.getParameter("descripcion") == "")? "":("<td>"+request.getParameter("descripcion")+"</td>")%>
                        <%= (request.getParameter("comentarios") == "")? "":("<td>"+request.getParameter("comentarios")+"</td>")%>
                    </tr>
                    
                  
                    <!-- Agrega más ingredientes según sea necesario
                    
                    <tr>
                        <td>Limón</td>
                        <td>1 pieza</td>
                    </tr>
                   -->
                </tbody>
            </table>
          </div>
        </div>  <!-- Agrega más ingredientes según sea necesario -->
      </div>


    </div>
  </div>
</div>

<footer class="container-fluid">
  <p> &copy; Salads & Life All rights reserved. </p>
</footer>

</body>
</html>
