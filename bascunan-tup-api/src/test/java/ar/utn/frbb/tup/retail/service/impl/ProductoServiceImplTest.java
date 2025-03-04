package ar.utn.frbb.tup.retail.service.impl;

import ar.utn.frbb.tup.retail.exception.ProductoNotFoundException;
import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void buscarProducto_Existente() {
        // Preparar
        Producto producto = new Producto();
        producto.setIdentificador("1");
        producto.setNombre("TV Samsung");
        
        when(productoRepository.findById("1")).thenReturn(producto);

        // Ejecutar
        Producto resultado = productoService.obtenerProductoPorId("1");

        // Comprobar
        assertEquals("TV Samsung", resultado.getNombre());
    }

    @Test
    void obtenerProductoPorId_CuandoNoExisteProducto_DeberiaLanzarExcepcion() {
        // Arrange
        String id = "NOEXISTE";
        when(productoRepository.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(ProductoNotFoundException.class, () -> {
            productoService.obtenerProductoPorId(id);
        });
    }
} 