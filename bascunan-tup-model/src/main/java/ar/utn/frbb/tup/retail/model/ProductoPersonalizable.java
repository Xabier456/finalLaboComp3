package ar.utn.frbb.tup.retail.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Producto que permite personalización de sus características
public class ProductoPersonalizable extends Producto {
    private Map<String, List<String>> opcionesConfigurables;

    // Constructor para producto personalizable
    public ProductoPersonalizable(String codigo, String nombre, String modelo, String descripcion, String marca,
                                  double precioLista, Categoria categoria) {
        super(codigo, nombre, modelo, descripcion, marca, precioLista, categoria);
        this.opcionesConfigurables = new HashMap<>();
    }

    // Agrega opciones de configuración para una característica existente
    public void agregarOpcionConfiguracion(String caracteristica, List<String> opciones) {
        if (getEspecificaciones().contains(caracteristica)) {
            opcionesConfigurables.put(caracteristica, opciones);
        } else {
            throw new IllegalArgumentException("La característica " + caracteristica +
                    " no está definida en las especificaciones del producto.");
        }
    }

    // Devuelve el mapa de opciones configurables
    public Map<String, List<String>> getOpcionesConfigurables() {
        return opcionesConfigurables;
    }
} 