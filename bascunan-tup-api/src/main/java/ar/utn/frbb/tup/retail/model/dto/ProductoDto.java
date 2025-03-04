package ar.utn.frbb.tup.retail.model.dto;

import java.util.List;

public class ProductoDto {
    private String identificador;
    private String nombre;
    private String descripcion;
    private String marca;
    private double precioLista;
    private String tipoProducto;
    private String categoriaId;
    private List<String> especificaciones;

    public ProductoDto() {
    }

    public ProductoDto(String identificador, String nombre, String descripcion, 
                      String marca, double precioLista, String tipoProducto, 
                      String categoriaId, List<String> especificaciones) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precioLista = precioLista;
        this.tipoProducto = tipoProducto;
        this.categoriaId = categoriaId;
        this.especificaciones = especificaciones;
    }

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

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<String> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(List<String> especificaciones) {
        this.especificaciones = especificaciones;
    }
} 