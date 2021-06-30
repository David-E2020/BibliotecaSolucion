<%-- 
    Document   : registrarProveedor
    Created on : 26-05-2021, 07:30:10 PM
    Author     : Melissa Ibañez Lopez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>registrar Proveedor</title>
    </head>
    <body>
        <jsp:useBean id="libro" scope="session" class="com.test.bean.libroBean"/>
        <jsp:useBean id="autorBean" scope="session" class="com.test.bean.autorBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String mensaje=libro.registrarLibro(request);
                out.print(mensaje);
            }
        %>
        <h1>PROVEEDOR</h1>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2"> REGISTRAR LIBRO</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>AUTOR: </td>
                        <td>
                            <select name="codAutor">
                                <%=autorBean.listarCategoria()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>TITULO: </td>
                        <td><input type="text" name="titulo" value="" /></td>
                    </tr>
                    <tr>
                        <td>EDICION: </td>
                        <td><input type="number" name="edicion" value="" /></td>
                    </tr>
                    <tr>
                        <td>NRO EJEMPLAR: </td>
                        <td><input type="number" name="nroejemplar" value="" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="REGISTRAR" name="guardar" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
        <a href="index.jsp">INICIO</a>
        
    </body>
</html>
