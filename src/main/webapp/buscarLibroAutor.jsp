<%-- 
    Document   : buscarLibroAutor
    Created on : 30-06-2021, 03:11:53 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%! String salidaTabla = "";%>
    </head>
    <body>
        <h1>LISTAR LIBROS DE UN DETERMINADO AUTOR</h1>
        <jsp:useBean id="autorBean" scope="session" class="com.test.bean.autorBean"/>
        <jsp:useBean id="libroBean" scope="session" class="com.test.bean.libroBean"/>
        <%
            if (request.getParameter("buscar") != null) {
                String codAutor = request.getParameter("codAutor");
                salidaTabla = libroBean.listarLibroAutor(codAutor);
            }
        %>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">AUTOR</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <select name="codAutor">
                                <%=autorBean.listarCategoria()%>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="BUSCAR" name="buscar">
                        </td>
                    </tr>
                </tbody>
            </table>
            <table border="1">
                <thead>
                    <tr>
                        <th>Nombre de Autor</th>
                        <th>Nacionalidad</th>
                        <th>Codigo de libro</th>
                        <th>Titulo</th>
                        <th>Edicion</th>
                        <th>Nro. ejemplar</th>
                    </tr>
                </thead>
                <tbody>
                    <%=salidaTabla%>
                </tbody>
            </table>

        </form>
        <a href="index.jsp">INICIO</a>
    </body>
</html>
