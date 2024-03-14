package com.practica.Heladas.documentos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

//indicamos que esta clase representará la colección paletas
@Document(collection = "paletas")
public class Paleta {

    //atributos que representan los atributos en la base de datos
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private Date caducidad;
    private int cantidad;
    private int precio;
    private boolean disponibilidad;

    /**
     * Constructor parametrizado
     *
     * @param id             de la paleta
     * @param nombre         de la paleta
     * @param descripcion    de la paleta
     * @param caducidad      de la paleta
     * @param cantidad       de la paleta
     * @param precio         de la paleta
     * @param disponibilidad de la paleta
     */
    public Paleta(String id, String nombre, String descripcion, Date caducidad, int cantidad, int precio, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    //Métodos de acceso
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
