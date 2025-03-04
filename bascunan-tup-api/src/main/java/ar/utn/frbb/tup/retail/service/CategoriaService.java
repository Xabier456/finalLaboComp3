package ar.utn.frbb.tup.retail.service;

import ar.utn.frbb.tup.retail.model.Categoria;
import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.model.dto.ProductoDto;
import ar.utn.frbb.tup.retail.model.dto.CategoriaDto;
import java.util.List;

public interface CategoriaService {

    CategoriaDto crearCategoria(Categoria categoria);
    CategoriaDto modificarCategoria(String identificador, Categoria categoria);
    void eliminarCategoria(String identificador);
    List<ProductoDto> obtenerProductosOrdenados(String orderPrice);
    List<ProductoDto> obtenerProductosPorMarca(String marca);
    List<ProductoDto> obtenerProductosPorPrecio(Double precioMin, Double precioMax);
    List<CategoriaDto> obtenerTodasLasCategorias();
} 