package ar.utn.frbb.tup.retail.service.impl;

import ar.utn.frbb.tup.retail.exception.CategoriaNotFoundException;
import ar.utn.frbb.tup.retail.exception.CategoriaExistsException;
import ar.utn.frbb.tup.retail.exception.CategoriaConProductosException;
import ar.utn.frbb.tup.retail.model.Categoria;
import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.model.dto.ProductoDto;
import ar.utn.frbb.tup.retail.model.dto.CategoriaDto;
import ar.utn.frbb.tup.retail.repository.CategoriaRepository;
import ar.utn.frbb.tup.retail.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    private static final Logger logger = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDto crearCategoria(Categoria categoria) {
        logger.info("Intentando crear categoría: {}", categoria.getIdentificador());
        
        if (categoria == null || categoria.getIdentificador() == null) {
            logger.error("Categoría o identificador nulos");
            throw new IllegalArgumentException("La categoría y su identificador no pueden ser nulos");
        }
        
        if (categoriaRepository.existsById(categoria.getIdentificador())) {
            logger.error("Categoría ya existe: {}", categoria.getIdentificador());
            throw new CategoriaExistsException("Ya existe una categoría con ese identificador");
        }
        
        Categoria categoriaSaved = categoriaRepository.save(categoria);
        logger.info("Categoría creada exitosamente: {}", categoria.getIdentificador());
        return convertToDto(categoriaSaved);
    }

    @Override
    public CategoriaDto modificarCategoria(String identificador, Categoria categoria) {
        logger.info("Intentando modificar categoría: {}", identificador);
        
        if (identificador == null || categoria == null) {
            logger.error("Identificador o categoría nulos");
            throw new IllegalArgumentException("El identificador y la categoría no pueden ser nulos");
        }
        
        Categoria categoriaExistente = categoriaRepository.findById(identificador);
        if (categoriaExistente == null) {
            logger.error("Categoría no encontrada: {}", identificador);
            throw new CategoriaNotFoundException("Categoría no encontrada");
        }
        
        List<Producto> productosExistentes = categoriaExistente.getProductos();
        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setDescripcion(categoria.getDescripcion());
        categoriaExistente.setTipo(categoria.getTipo());
        categoriaExistente.setProductos(productosExistentes);
        
        Categoria categoriaUpdated = categoriaRepository.save(categoriaExistente);
        logger.info("Categoría modificada exitosamente: {}", identificador);
        return convertToDto(categoriaUpdated);
    }

    @Override
    public void eliminarCategoria(String identificador) {
        logger.info("Intentando eliminar categoría: {}", identificador);
        
        if (identificador == null) {
            logger.error("Identificador nulo");
            throw new IllegalArgumentException("El identificador no puede ser nulo");
        }
        
        Categoria categoria = categoriaRepository.findById(identificador);
        if (categoria == null) {
            logger.error("Categoría no encontrada: {}", identificador);
            throw new CategoriaNotFoundException("Categoría no encontrada");
        }

        if (!categoria.getProductos().isEmpty()) {
            logger.error("No se puede eliminar la categoría {} porque tiene {} productos asociados", 
                identificador, categoria.getProductos().size());
            throw new CategoriaConProductosException(
                "No se puede eliminar la categoría porque tiene productos asociados. " +
                "Primero debe eliminar o reasignar los productos.");
        }
        
        categoriaRepository.delete(identificador);
        logger.info("Categoría eliminada exitosamente: {}", identificador);
    }

   
    @Override
    public List<ProductoDto> obtenerProductosOrdenados(String orderPrice) {
        logger.info("Obteniendo productos ordenados por precio: {}", orderPrice);
        
        if (orderPrice != null && !orderPrice.equalsIgnoreCase("asc") && !orderPrice.equalsIgnoreCase("desc")) {
            logger.error("Parámetro de ordenamiento inválido: {}", orderPrice);
            throw new IllegalArgumentException("El parámetro order_price debe ser 'asc' o 'desc'");
        }
        
        List<Producto> todosLosProductos = new ArrayList<>();
        List<Categoria> categorias = categoriaRepository.findAll();
        
        if (categorias.isEmpty()) {
            logger.info("No se encontraron categorías");
            return new ArrayList<>();
        }
        
        for (Categoria categoria : categorias) {
            todosLosProductos.addAll(categoria.getProductos());
        }
        
        if (todosLosProductos.isEmpty()) {
            logger.info("No se encontraron productos");
            return new ArrayList<>();
        }
        
        if ("desc".equalsIgnoreCase(orderPrice)) {
            todosLosProductos.sort((p1, p2) -> Double.compare(p2.getPrecioLista(), p1.getPrecioLista()));
        } else {
            todosLosProductos.sort((p1, p2) -> Double.compare(p1.getPrecioLista(), p2.getPrecioLista()));
        }
        
        List<ProductoDto> productosDto = todosLosProductos.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        
        logger.info("Se encontraron {} productos", productosDto.size());
        return productosDto;
    }

    private ProductoDto convertToDto(Producto producto) {
        return new ProductoDto(
            producto.getIdentificador(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getMarca(),
            producto.getPrecioLista(),
            producto.getTipoProducto(),
            producto.getCategoria() != null ? producto.getCategoria().getIdentificador() : null,
            producto.getEspecificaciones()
        );
    }

    @Override
    public List<ProductoDto> obtenerProductosPorMarca(String marca) {
        logger.info("Buscando productos por marca: {}", marca);
        
        if (marca == null || marca.trim().isEmpty()) {
            logger.error("Marca nula o vacía");
            throw new IllegalArgumentException("La marca no puede ser nula o vacía");
        }
        
        List<Producto> productosFiltrados = new ArrayList<>();
        List<Categoria> categorias = categoriaRepository.findAll();
        
        for (Categoria categoria : categorias) {
            for (Producto producto : categoria.getProductos()) {
                if (producto.getMarca().equalsIgnoreCase(marca)) {
                    productosFiltrados.add(producto);
                }
            }
        }
        
        List<ProductoDto> productosDto = productosFiltrados.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        
        logger.info("Se encontraron {} productos de la marca {}", productosDto.size(), marca);
        return productosDto;
    }

    @Override
    public List<ProductoDto> obtenerProductosPorPrecio(Double precioMin, Double precioMax) {
        logger.info("Buscando productos por rango de precio: {} - {}", precioMin, precioMax);
        
        if (precioMin == null || precioMax == null) {
            logger.error("Precio mínimo o máximo nulo");
            throw new IllegalArgumentException("Los precios no pueden ser nulos");
        }
        
        if (precioMin > precioMax) {
            logger.error("Rango de precios inválido");
            throw new IllegalArgumentException("El precio mínimo no puede ser mayor al máximo");
        }
        
        List<Producto> productosFiltrados = new ArrayList<>();
        List<Categoria> categorias = categoriaRepository.findAll();
        
        for (Categoria categoria : categorias) {
            for (Producto producto : categoria.getProductos()) {
                if (producto.getPrecioLista() >= precioMin && producto.getPrecioLista() <= precioMax) {
                    productosFiltrados.add(producto);
                }
            }
        }
        
        List<ProductoDto> productosDto = productosFiltrados.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        
        logger.info("Se encontraron {} productos en el rango de precio", productosDto.size());
        return productosDto;
    }

    @Override
    public List<CategoriaDto> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    private CategoriaDto convertToDto(Categoria categoria) {
        return new CategoriaDto(
            categoria.getIdentificador(),
            categoria.getNombre(),
            categoria.getDescripcion(),
            categoria.getTipo().toString()
        );
    }
} 