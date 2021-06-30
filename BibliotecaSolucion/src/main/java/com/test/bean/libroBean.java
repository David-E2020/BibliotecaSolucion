/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author david
 */
public class libroBean {
    private Connection connection;
    private PreparedStatement insertLibro;
    private VariablesConexion variable;
    //constructores
    public libroBean()throws SQLException{
        //instanciando
        variable=new VariablesConexion();
        variable.inicioConexion();
        //obteniendo la conexion
        connection=variable.getConnection();
        System.out.println("Iniciando la conexion");
    }
   
    //metodos
     @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    public String registrarLibro(HttpServletRequest request){
        String mensaje="";
        if(request==null){
            return"";
        }
        if(connection!=null){
            try {
                //definiendo la consulta
                StringBuilder query=new StringBuilder();
                query.append(" insert into libro ");
                query.append(" values (nextval('sec_libro'),?,?,?,?)");
                //enviando la consulta
                if(insertLibro==null){
                    insertLibro=connection.prepareStatement(query.toString());
                }
                //rescatando los parametros del formulario jsp registrarCategoria
                String codAutor=request.getParameter("codAutor");
                String titulo=request.getParameter("titulo");
                String edicion=request.getParameter("edicion");
                String nroejemplar=request.getParameter("nroejemplar");
                //pasando los datos a los parametros de la consulta
                insertLibro.setInt(1,Integer.parseInt(codAutor));
                insertLibro.setString(2, titulo);
                insertLibro.setInt(3,Integer.parseInt(edicion));
                insertLibro.setInt(4, Integer.parseInt(nroejemplar));
                //ejecutando la consulta
                int registro=insertLibro.executeUpdate();
                if(registro==1){
                    mensaje="Registro realizado con exito";
                }else{
                    mensaje="Error al insertar el registro";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mensaje;
    }
    //Realizando el listado de todas las categorias que se tienen registrados
    
    public String listarLibroAutor(String codAutor){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select concat(au.nombre || ' ' || au.paterno || ' ' || au.materno) as nombre, au.nacionalidad, li.cod_libro,li.titulo,li.edicion,li.nroejemplar ");
        query.append(" from autor au ");
        query.append(" join libro as li on ");
        query.append(" au.cod_autor=li.cod_autor ");
        query.append(" where au.cod_autor=? ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            pst.setInt(1,Integer.parseInt(codAutor));
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<tr>");
                    salidaTabla.append("<td>");
                    salidaTabla.append(resultado.getString(1));
                    salidaTabla.append("</td>");
                    salidaTabla.append("<td>");
                    salidaTabla.append(resultado.getString(2));
                    salidaTabla.append("</td>");
                    salidaTabla.append("<td>");
                    salidaTabla.append(resultado.getInt(3));
                    salidaTabla.append("</td>");
                    salidaTabla.append("<td>");
                    salidaTabla.append(resultado.getString(4));
                    salidaTabla.append("</td>");
                    salidaTabla.append("<td>");
                    salidaTabla.append(resultado.getInt(5));
                    salidaTabla.append("</td>");
                    salidaTabla.append("<td>");
                    salidaTabla.append(resultado.getInt(6));
                    salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    
    //getter y setter


    
}
