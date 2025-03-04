package ar.utn.frbb.tup.retail.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Pruebas para la clase ProductoPersonalizable
public class ProductoPersonalizableTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductoPersonalizableTest.class);
    private ProductoPersonalizable productoPersonalizable;
    private Categoria categoria;

    // Prepara el ambiente de pruebas
    @Before
    public void setUp() {
        categoria = new Categoria(CategoriaEnum.INFORMATICA_ELECTRONICA, "Electrónica");
        productoPersonalizable = new ProductoPersonalizable("P003", "Notebook Gamer",
                "Asus ROG", "Laptop con alto rendimiento", "Asus", 6000.0, categoria);
    }

    // Prueba agregar opciones de configuración válidas
    @Test
    public void testAgregarOpcionConfiguracion() {
        productoPersonalizable.agregarEspecificacion("RAM");
        productoPersonalizable.agregarOpcionConfiguracion("RAM", Arrays.asList("8GB", "16GB", "32GB"));
        Map<String, List<String>> opciones = productoPersonalizable.getOpcionesConfigurables();
        assertEquals(3, opciones.get("RAM").size());
        logger.info("Test de opciones configurables exitoso.");
    }

    // Prueba que falla al agregar opciones para una característica inexistente
    @Test(expected = IllegalArgumentException.class)
    public void testAgregarOpcionConfiguracionInvalida() {
        productoPersonalizable.agregarOpcionConfiguracion("Batería", Arrays.asList("5000mAh", "6000mAh"));
    }
} 