package ar.utn.frbb.tup.retail.controller;

import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void obtenerProductoPorId_Existente() {
        // Preparar
        Producto producto = new Producto();
        producto.setIdentificador("1");
        producto.setNombre("TV Samsung");
        
        when(productoService.obtenerProductoPorId("1")).thenReturn(producto);

        // Ejecutar
        var resultado = productoController.obtenerProductoPorId("1");

        // Comprobar
        assertNotNull(resultado.getBody());
        assertEquals("TV Samsung", resultado.getBody().getNombre());
    }
} 