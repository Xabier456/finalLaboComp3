package ar.utn.frbb.tup.retail.model;

import java.util.ArrayList;
import java.util.List;

// Producto con características específicas para venta online
public class ProductoOnline extends Producto {
    private List<Producto> productosRelacionados;

    // Constructor para producto online
    public ProductoOnline(String codigo, String nombre, String modelo, String descripcion, String marca,
                          double precioLista, Categoria categoria) {
        super(codigo, nombre, modelo, descripcion, marca, precioLista, categoria);
        this.productosRelacionados = new ArrayList<>();
    }

    // Agrega un producto relacionado, evitando duplicados y auto-referencias
    public void agregarProductoRelacionado(Producto producto) {
        if (!productosRelacionados.contains(producto) && !producto.equals(this)) {
            productosRelacionados.add(producto);
        }
    }

    // Devuelve la lista de productos relacionados
    public List<Producto> getProductosRelacionados() {
        return productosRelacionados;
    }
} 