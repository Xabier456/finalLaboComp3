package ar.utn.frbb.tup.retail.model;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    
    private String identificador; // código
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private String marca;
    private double precioLista;
    private String tipoProducto;
    private List<String> especificaciones;
    private boolean disponibleOnline;
    private double descuentoOnline;

    public Producto() {
 
    }

    public Producto(String identificador, String nombre, String descripcion, 
                   String marca, double precioLista, String tipoProducto) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precioLista = precioLista;
        this.tipoProducto = tipoProducto;
        this.especificaciones = new ArrayList<>();
        this.disponibleOnline = false;
        this.descuentoOnline = 0.0;
    }

    public double getPrecioContado() {
        return precioLista * 0.85;
    }

    public void agregarEspecificacion(String especificacion) {
        especificaciones.add(especificacion);
    }

    // Getters y Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public List<String> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(List<String> especificaciones) {
        this.especificaciones = especificaciones;
    }

    public boolean isDisponibleOnline() {
        return disponibleOnline;
    }

    public void setDisponibleOnline(boolean disponibleOnline) {
        this.disponibleOnline = disponibleOnline;
    }

    public double getDescuentoOnline() {
        return descuentoOnline;
    }

    public void setDescuentoOnline(double descuentoOnline) {
        if (!disponibleOnline) {
            throw new IllegalArgumentException("El producto no está disponible para venta online");
        }
        this.descuentoOnline = descuentoOnline;
    }

    public double getPrecioOnline() {
        return disponibleOnline ? precioLista * (1 - descuentoOnline / 100) : getPrecioContado();
    }
} 