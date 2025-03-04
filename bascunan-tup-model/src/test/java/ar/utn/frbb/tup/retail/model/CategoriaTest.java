package ar.utn.frbb.tup.retail.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Pruebas para la clase Categoria
public class CategoriaTest {
    private static final Logger logger = LoggerFactory.getLogger(CategoriaTest.class);
    private Categoria categoria;
    private Producto producto1;
    private Producto producto2;

    // Prepara el ambiente de pruebas
    @Before
    public void setUp() {
        categoria = new Categoria(CategoriaEnum.INFORMATICA_ELECTRONICA, "Electrónica");
        producto1 = new Producto("P001", "Laptop", "ThinkPad", "Computadora portátil",
                "Lenovo", 5000.0, categoria);
        producto2 = new Producto("P002", "Monitor", "Dell UltraSharp",
                "Monitor 27 pulgadas", "Dell", 3000.0, categoria);
    }

    // Prueba agregar productos a una categoría
    @Test
    public void testAgregarProducto() {
        categoria.getProductos().clear();
        categoria.agregarProducto(producto1);
        categoria.agregarProducto(producto2);
        assertEquals(2, categoria.obtenerProductosOrdenadosPorPrecio(true).size());
        logger.info("Test de agregar producto exitoso.");
    }
} 