package ar.utn.frbb.tup.retail.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Pruebas para la clase Producto
public class ProductoTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductoTest.class);
    private Producto producto;
    private Categoria categoria;

    // Prepara el ambiente de pruebas
    @Before
    public void setUp() {
        categoria = new Categoria(CategoriaEnum.INFORMATICA_ELECTRONICA, "Electrónica");
        producto = new Producto("P001", "Laptop", "ThinkPad", "Computadora portátil",
                "Lenovo", 5000.0, categoria);
    }

    // Prueba el cálculo del precio de contado
    @Test
    public void testGetPrecioContado() {
        assertEquals(4250.0, producto.getPrecioContado(), 0.01);
        logger.info("Test de precio contado exitoso.");
    }

    // Prueba la asignación automática a categoría OTROS
    @Test
    public void testProductoSinCategoria() {
        Producto productoSinCategoria = new Producto("P004", "Teclado", "HyperX",
                "Teclado mecánico", "HyperX", 800.0, null);
        assertEquals(CategoriaEnum.OTROS, productoSinCategoria.getCategoria().getCategoriaEnum());
        logger.info("Test de producto sin categoría asignado correctamente a 'OTROS' exitoso.");
    }
} 