package ar.utn.frbb.tup.retail.model.dto;

public class CategoriaDto {
    private String identificador;
    private String nombre;
    private String descripcion;
    private String tipo;

    public CategoriaDto() {
    }

    public CategoriaDto(String identificador, String nombre, String descripcion, String tipo) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
} 