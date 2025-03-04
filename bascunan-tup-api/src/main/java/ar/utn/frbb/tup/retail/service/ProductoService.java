package ar.utn.frbb.tup.retail.service;

import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.model.dto.ProductoDto;
import java.util.List;

public interface ProductoService {
    
    ProductoDto crearProducto(Producto producto);
    ProductoDto modificarProducto(String identificador, Producto producto);
    void eliminarProducto(String identificador);
    ProductoDto obtenerProductoPorId(String identificador);
    List<ProductoDto> buscarProductos(String tipoProducto, String marca, String categoria);
    List<ProductoDto> obtenerTodosLosProductos();
} 