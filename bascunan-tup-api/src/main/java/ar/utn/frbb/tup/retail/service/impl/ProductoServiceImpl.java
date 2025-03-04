package ar.utn.frbb.tup.retail.service.impl;

import ar.utn.frbb.tup.retail.exception.ProductoNotFoundException;
import ar.utn.frbb.tup.retail.exception.ProductoExistsException;
import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.model.dto.ProductoDto;
import ar.utn.frbb.tup.retail.repository.ProductoRepository;
import ar.utn.frbb.tup.retail.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ProductoDto crearProducto(Producto producto) {
        validarProducto(producto);
        if (productoRepository.findById(producto.getIdentificador()) != null) {
            throw new ProductoExistsException("Ya existe un producto con ese identificador");
        }
        Producto productoGuardado = productoRepository.save(producto);
        return convertToDto(productoGuardado);
    }

    @Override
    public ProductoDto modificarProducto(String identificador, Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        Producto productoExistente = productoRepository.findById(identificador);
        if (productoExistente == null) {
            throw new ProductoNotFoundException("Producto no encontrado");
        }
        
        // Validamos los campos del producto pero ignoramos el identificador
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es requerido");
        }
        if (producto.getPrecioLista() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (producto.getCategoria() == null || producto.getCategoria().getIdentificador() == null) {
            throw new IllegalArgumentException("La categoría del producto es requerida");
        }
        if (producto.getTipoProducto() == null || producto.getTipoProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de producto es requerido");
        }
        if (producto.getMarca() == null || producto.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("La marca del producto es requerida");
        }
        if (producto.getEspecificaciones() == null || producto.getEspecificaciones().isEmpty()) {
            throw new IllegalArgumentException("El producto debe tener al menos una especificación");
        }

        // Establecemos el identificador desde la URL
        producto.setIdentificador(identificador);
        
        Producto productoActualizado = productoRepository.save(producto);
        return convertToDto(productoActualizado);
    }

    @Override
    public void eliminarProducto(String identificador) {
        Producto producto = productoRepository.findById(identificador);
        if (producto == null) {
            throw new ProductoNotFoundException("Producto no encontrado con ID: " + identificador);
        }
        productoRepository.delete(identificador);
    }

    @Override
    public ProductoDto obtenerProductoPorId(String identificador) {
        Producto producto = productoRepository.findById(identificador);
        if (producto == null) {
            throw new ProductoNotFoundException("Producto no encontrado");
        }
        return convertToDto(producto);
    }

    @Override
    public List<ProductoDto> buscarProductos(String tipoProducto, String marca, String categoria) {
        List<Producto> productos = productoRepository.findByAttributes(tipoProducto, marca, categoria);
        return productos.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDto> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
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

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (producto.getIdentificador() == null || producto.getIdentificador().trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador del producto es requerido");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es requerido");
        }
        if (producto.getPrecioLista() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (producto.getCategoria() == null || producto.getCategoria().getIdentificador() == null) {
            throw new IllegalArgumentException("La categoría del producto es requerida");
        }
        if (producto.getTipoProducto() == null || producto.getTipoProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de producto es requerido");
        }
        if (producto.getMarca() == null || producto.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("La marca del producto es requerida");
        }
        if (producto.getEspecificaciones() == null || producto.getEspecificaciones().isEmpty()) {
            throw new IllegalArgumentException("El producto debe tener al menos una especificación");
        }
    }
} 