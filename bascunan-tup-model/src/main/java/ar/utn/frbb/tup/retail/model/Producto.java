package ar.utn.frbb.tup.retail.model;

import java.util.ArrayList;
import java.util.List;

// Clase base para todos los productos del sistema
public class Producto {
    // Atributos del producto
    private String codigo;
    private String nombre;
    private String modelo;
    private String descripcion;
    private String marca;
    private double precioLista;
    private Categoria categoria;
    private List<String> especificaciones;
    private boolean disponibleOnline;
    private double descuentoOnline;

    // Inicializa un nuevo producto. Si la categoría es nula, se asigna a OTROS
    public Producto(String codigo, String nombre, String modelo, String descripcion, String marca, double precioLista, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precioLista = precioLista;
        this.categoria = (categoria != null) ? categoria : new Categoria(CategoriaEnum.OTROS, "Otros");
        this.categoria.agregarProducto(this);
        this.especificaciones = new ArrayList<>();
        this.disponibleOnline = false; // Por defecto, los productos no son vendibles online
        this.descuentoOnline = 0.0;
    }

    // Verifica si el producto está disponible para venta online
    public boolean isDisponibleOnline() {
        return disponibleOnline;
    }

    // Cambia la disponibilidad online del producto
    public void setDisponibleOnline(boolean disponibleOnline) {
        this.disponibleOnline = disponibleOnline;
    }

    // Obtiene el porcentaje de descuento para ventas online
    public double getDescuentoOnline() {
        return descuentoOnline;
    }

    // Establece el descuento para ventas online
    public void setDescuentoOnline(double descuentoOnline) {
        if (!disponibleOnline) {
            throw new IllegalArgumentException("El producto no está disponible para venta online, por lo que no puede tener un descuento online.");
        }
        this.descuentoOnline = descuentoOnline;
    }

    // Calcula el precio final para venta online con descuento si corresponde
    public double getPrecioOnline() {
        return disponibleOnline ? precioLista * (1 - descuentoOnline / 100) : getPrecioContado();
    }

    // Calcula el precio de contado (15% menos que el precio de lista)
    public double getPrecioContado() {
        return precioLista * 0.85;
    }

    // Agrega una especificación técnica al producto
    public void agregarEspecificacion(String especificacion) {
        especificaciones.add(especificacion);
    }

    // Devuelve la lista de especificaciones del producto
    public List<String> getEspecificaciones() {
        return especificaciones;
    }

    // Getters y setters
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // Obtiene el precio de lista del producto
    public double getPrecioLista() {
        return precioLista;
    }
} 