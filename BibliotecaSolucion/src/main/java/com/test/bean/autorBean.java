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
 * @author Miriam Lopez
 */
public class autorBean {
    //atributos
    private Connection connection;
    private PreparedStatement insertLibro;
    private VariablesConexion variable;
    //constructores
    public autorBean()throws SQLException{
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
    //Realizando el listado de todas las categorias que se tienen registrados
    public String listarCategoria(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select au.cod_autor,concat(au.nombre || ' ' || au.paterno || ' ' || au.materno) as nombre ");
        query.append(" from autor au ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<option value='");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("'>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
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
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</tr>");
                salidaTabla.append("</td>");
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(2));
                salidaTabla.append("</tr>");
                salidaTabla.append("</td>");
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(3));
                salidaTabla.append("</tr>");
                salidaTabla.append("</td>");
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(4));
                salidaTabla.append("</tr>");
                salidaTabla.append("</td>");
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(5));
                salidaTabla.append("</tr>");
                salidaTabla.append("</td>");
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(6));
                salidaTabla.append("</tr>");
                salidaTabla.append("</td>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    
    //getter y setter

    
    
}
