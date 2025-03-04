package ar.utn.frbb.tup.retail.model;

import java.util.ArrayList;
import java.util.List;

// Clase para manejar las categorías de productos
public class Categoria {
    private CategoriaEnum categoriaEnum;
    private String descripcion;
    private List<Producto> productos;

    // Constructor
    public Categoria(CategoriaEnum categoriaEnum, String descripcion) {
        this.categoriaEnum = categoriaEnum;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    // Agrega un producto a la categoría
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Ordena los productos por precio
    public List<Producto> obtenerProductosOrdenadosPorPrecio(boolean ascendente) {
        List<Producto> productosOrdenados = new ArrayList<>(productos);
        productosOrdenados.sort((p1, p2) -> {
            return ascendente ? Double.compare(p1.getPrecioContado(), p2.getPrecioContado()) 
                            : Double.compare(p2.getPrecioContado(), p1.getPrecioContado());
        });
        return productosOrdenados;
    }

    // Filtra productos por marca
    public List<Producto> filtrarPorMarca(String marca) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getMarca().equalsIgnoreCase(marca)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Filtra productos por rango de precio
    public List<Producto> filtrarPorPrecio(double min, double max) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getPrecioContado() >= min && p.getPrecioContado() <= max) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Getters
    public CategoriaEnum getCategoriaEnum() {
        return categoriaEnum;
    }

    public List<Producto> getProductos() {
        return productos;
    }
} 