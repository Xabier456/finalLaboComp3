package ar.utn.frbb.tup.retail.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Pruebas para la clase ProductoOnline
public class ProductoOnlineTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductoOnlineTest.class);
    private ProductoOnline productoOnline;
    private Categoria categoria;

    // Prepara el ambiente de pruebas
    @Before
    public void setUp() {
        categoria = new Categoria(CategoriaEnum.INFORMATICA_ELECTRONICA, "Electrónica");
        productoOnline = new ProductoOnline("P002", "Monitor", "Dell UltraSharp",
                "Monitor 27 pulgadas", "Dell", 3000.0, categoria);
    }

    // Prueba el cálculo del precio con descuento online
    @Test
    public void testSetDescuentoOnline() {
        productoOnline.setDisponibleOnline(true);
        productoOnline.setDescuentoOnline(10);
        assertEquals(2700.0, productoOnline.getPrecioOnline(), 0.01);
        logger.info("Test de descuento online exitoso.");
    }

    // Prueba que no se puede aplicar descuento si no está disponible online
    @Test(expected = IllegalArgumentException.class)
    public void testSetDescuentoOnlineSinDisponibilidad() {
        productoOnline.setDescuentoOnline(15);
    }
} 