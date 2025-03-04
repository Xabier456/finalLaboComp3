package ar.utn.frbb.tup;

import ar.utn.frbb.tup.retail.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        // Lista general de productos
        List<Producto> catalogoGeneral = new ArrayList<>();

        // Creación de categorías
        Categoria tvAudioVideo = new Categoria(CategoriaEnum.TV_AUDIO_VIDEO, "TV, Audio y Video");
        Categoria electrodomesticos = new Categoria(CategoriaEnum.ELECTRODOMESTICOS_CLIMATIZACION, "Electrodomésticos y Climatización");
        Categoria hogar = new Categoria(CategoriaEnum.HOGAR_MUEBLES, "Hogar y Muebles");
        Categoria informatica = new Categoria(CategoriaEnum.INFORMATICA_ELECTRONICA, "Informática y Electrónica");
        Categoria saludAireLibre = new Categoria(CategoriaEnum.SALUD_AIRE_LIBRE, "Salud y Aire Libre");

        // Creación de productos con categoría
        Producto tv = new Producto("TV001", "Smart TV 55''", "UN55AU7000", 
            "Smart TV LED 55 pulgadas 4K", "Samsung", 450000.0, tvAudioVideo);
        catalogoGeneral.add(tv);

        Producto homeTheater = new Producto("TV002", "Home Theater", "HT-S20R", 
            "Home Theater 5.1 400W", "Sony", 280000.0, tvAudioVideo);
        catalogoGeneral.add(homeTheater);

        Producto lavarropas = new Producto("ELE001", "Lavarropas", "WLTP1486D", 
            "Lavarropas automático 8kg", "LG", 380000.0, electrodomesticos);
        catalogoGeneral.add(lavarropas);

        Producto heladera = new Producto("ELE002", "Heladera", "RT38K5932SL", 
            "Heladera No Frost 382L", "Samsung", 620000.0, electrodomesticos);
        catalogoGeneral.add(heladera);

        Producto silla = new Producto("HOG001", "Silla Oficina", "ErgoComfort", 
            "Silla ergonómica con ruedas", "Erasmo", 95000.0, hogar);
        catalogoGeneral.add(silla);

        Producto mesa = new Producto("HOG002", "Mesa Comedor", "Nordic", 
            "Mesa extensible 140/180cm", "Nordika", 180000.0, hogar);
        catalogoGeneral.add(mesa);

        Producto notebook = new Producto("INF001", "Notebook", "IdeaPad 3", 
            "Notebook 15.6'' AMD Ryzen 5", "Lenovo", 580000.0, informatica);
        catalogoGeneral.add(notebook);

        Producto tablet = new Producto("INF002", "Tablet", "Galaxy Tab S7", 
            "Tablet 11'' 128GB", "Samsung", 340000.0, informatica);
        catalogoGeneral.add(tablet);

        Producto bicicleta = new Producto("SAL001", "Bicicleta Mountain Bike", "TopMega R29", 
            "Bicicleta MTB 21 velocidades", "TopMega", 250000.0, saludAireLibre);
        catalogoGeneral.add(bicicleta);

        // Productos sin categoría (se asignarán automáticamente a OTROS)
        Producto neumatico = new Producto("NEU001", "Neumático", "Energy XM2+", 
            "Neumático 195/65 R15", "Michelin", 85000.0, null);
        catalogoGeneral.add(neumatico);

        // === EJEMPLOS DE PRODUCTOS ESPECÍFICOS DEL ENUNCIADO ===

        // Ejemplo de TV con especificaciones
        Producto smartTv = new Producto("TV003", "Smart TV 50''", "UN50AU7000", 
            "Smart TV LED 50 pulgadas 4K", "Samsung", 400000.0, tvAudioVideo);
        smartTv.agregarEspecificacion("Tipo de Pantalla: LED");
        smartTv.agregarEspecificacion("Pulgadas: 50");
        smartTv.agregarEspecificacion("Resolución: 4K");
        smartTv.agregarEspecificacion("Entradas HDMI: 3");
        smartTv.agregarEspecificacion("Entradas USB: 2");
        smartTv.agregarEspecificacion("Smart TV: Sí");
        catalogoGeneral.add(smartTv);

        // Ejemplo de Bicicleta con especificaciones
        Producto bicicletaVenzo = new Producto("SAL002", "Bicicleta Mountain Bike", "Venzo R29", 
            "Bicicleta MTB Venzo Skyline", "Venzo", 280000.0, saludAireLibre);
        bicicletaVenzo.agregarEspecificacion("Rodado: 29");
        bicicletaVenzo.agregarEspecificacion("Cambios: 21");
        bicicletaVenzo.agregarEspecificacion("Tamaño Cuadro: L");
        bicicletaVenzo.agregarEspecificacion("Color Cuadro: Negro");
        catalogoGeneral.add(bicicletaVenzo);

        // Ejemplo de Notebook personalizable
        ProductoPersonalizable notebookLenovo = new ProductoPersonalizable("INF003", "Notebook", "ThinkPad T14",
            "Notebook ThinkPad empresarial", "Lenovo", 750000.0, informatica);
        notebookLenovo.agregarEspecificacion("RAM");
        notebookLenovo.agregarEspecificacion("SSD");
        notebookLenovo.agregarEspecificacion("Procesador: Intel i7");
        notebookLenovo.agregarEspecificacion("Pantalla: 14 pulgadas");
        notebookLenovo.agregarOpcionConfiguracion("RAM", Arrays.asList("8GB", "16GB", "32GB"));
        notebookLenovo.agregarOpcionConfiguracion("SSD", Arrays.asList("512GB", "1TB", "2TB"));
        catalogoGeneral.add(notebookLenovo);

        // Ejemplo de Bicicleta personalizable
        ProductoPersonalizable bicicletaVenzoConfig = new ProductoPersonalizable("SAL003", "Bicicleta Mountain Bike", 
            "Venzo Skyline R29", "Bicicleta MTB personalizable", "Venzo", 300000.0, saludAireLibre);
        bicicletaVenzoConfig.agregarEspecificacion("Color del Cuadro");
        bicicletaVenzoConfig.agregarEspecificacion("Rodado: 29");
        bicicletaVenzoConfig.agregarEspecificacion("Cambios: 21");
        bicicletaVenzoConfig.agregarOpcionConfiguracion("Color del Cuadro", Arrays.asList("Verde", "Rojo", "Amarillo"));
        catalogoGeneral.add(bicicletaVenzoConfig);

        // Ejemplo de Producto Online con productos relacionados
        ProductoOnline consolaPS5 = new ProductoOnline("INF004", "Consola PS5", "PlayStation 5",
            "Consola de videojuegos última generación", "Sony", 800000.0, informatica);
        consolaPS5.setDisponibleOnline(true);
        consolaPS5.setDescuentoOnline(5.0); // 5% de descuento online
        catalogoGeneral.add(consolaPS5);

        ProductoOnline auricularesGamer = new ProductoOnline("INF005", "Auriculares Gamer", "Cloud Alpha",
            "Auriculares gaming profesionales", "HyperX", 120000.0, informatica);
        auricularesGamer.setDisponibleOnline(true);
        auricularesGamer.setDescuentoOnline(10.0); // 10% de descuento online
        catalogoGeneral.add(auricularesGamer);

        // Agregar productos relacionados
        consolaPS5.agregarProductoRelacionado(auricularesGamer);

        // === PRUEBAS CON EL CATÁLOGO GENERAL ===
        
        System.out.println("=== Estadísticas del Catálogo ===");
        System.out.println("Total de productos: " + catalogoGeneral.size());
        
        // Productos por categoría
        Map<CategoriaEnum, Long> productosPorCategoria = catalogoGeneral.stream()
            .collect(Collectors.groupingBy(p -> p.getCategoria().getCategoriaEnum(), Collectors.counting()));
        
        System.out.println("\n=== Productos por Categoría ===");
        productosPorCategoria.forEach((categoria, cantidad) -> 
            System.out.println(categoria + ": " + cantidad + " productos"));

        // Productos por marca
        Map<String, Long> productosPorMarca = catalogoGeneral.stream()
            .collect(Collectors.groupingBy(Producto::getMarca, Collectors.counting()));
        
        System.out.println("\n=== Productos por Marca ===");
        productosPorMarca.forEach((marca, cantidad) -> 
            System.out.println(marca + ": " + cantidad + " productos"));

        // Productos ordenados por precio (más caros)
        System.out.println("\n=== Top 3 Productos más caros ===");
        catalogoGeneral.stream()
            .sorted((p1, p2) -> Double.compare(p2.getPrecioContado(), p1.getPrecioContado()))
            .limit(3)
            .forEach(p -> System.out.println(p.getMarca() + " " + p.getModelo() + 
                " - Precio: $" + p.getPrecioContado()));

        // Productos ordenados por precio (más baratos)
        System.out.println("\n=== Top 3 Productos más baratos ===");
        catalogoGeneral.stream()
            .sorted((p1, p2) -> Double.compare(p1.getPrecioContado(), p2.getPrecioContado()))
            .limit(3)
            .forEach(p -> System.out.println(p.getMarca() + " " + p.getModelo() + 
                " - Precio: $" + p.getPrecioContado()));

        // Precio promedio por categoría
        System.out.println("\n=== Precio Promedio por Categoría ===");
        catalogoGeneral.stream()
            .collect(Collectors.groupingBy(
                p -> p.getCategoria().getCategoriaEnum(),
                Collectors.averagingDouble(Producto::getPrecioContado)))
            .forEach((categoria, promedio) -> 
                System.out.println(categoria + ": $" + String.format("%.2f", promedio)));

        // Pruebas específicas por categoría
        System.out.println("\n=== Productos Samsung ordenados por precio ===");
        List<Producto> productosSamsung = catalogoGeneral.stream()
            .filter(p -> p.getMarca().equals("Samsung"))
            .sorted((p1, p2) -> Double.compare(p1.getPrecioContado(), p2.getPrecioContado()))
            .collect(Collectors.toList());
        
        for (Producto p : productosSamsung) {
            System.out.println(p.getMarca() + " " + p.getModelo() + 
                " - Categoría: " + p.getCategoria().getCategoriaEnum() + 
                " - Precio: $" + p.getPrecioContado());
        }

        // Productos en rango de precio
        double minPrecio = 200000;
        double maxPrecio = 400000;
        System.out.println("\n=== Productos entre $" + minPrecio + " y $" + maxPrecio + " ===");
        catalogoGeneral.stream()
            .filter(p -> p.getPrecioContado() >= minPrecio && p.getPrecioContado() <= maxPrecio)
            .forEach(p -> System.out.println(p.getMarca() + " " + p.getModelo() + 
                " - Categoría: " + p.getCategoria().getCategoriaEnum() + 
                " - Precio: $" + p.getPrecioContado()));

        // === PRUEBAS ADICIONALES SEGÚN ENUNCIADO ===

        // Prueba de especificaciones de productos
        System.out.println("\n=== Especificaciones de Smart TV ===");
        System.out.println("Producto: " + smartTv.getMarca() + " " + smartTv.getModelo());
        smartTv.getEspecificaciones().forEach(spec -> System.out.println("- " + spec));

        // Prueba de productos personalizables
        System.out.println("\n=== Opciones de configuración de Notebook ===");
        System.out.println("Producto: " + notebookLenovo.getMarca() + " " + notebookLenovo.getModelo());
        notebookLenovo.getOpcionesConfigurables().forEach((caracteristica, opciones) -> {
            System.out.println("- " + caracteristica + ": " + String.join(", ", opciones));
        });

        // Prueba de productos online y sus descuentos
        System.out.println("\n=== Productos disponibles online y sus precios ===");
        catalogoGeneral.stream()
            .filter(p -> p instanceof ProductoOnline && ((ProductoOnline) p).isDisponibleOnline())
            .forEach(p -> {
                System.out.println(p.getMarca() + " " + p.getModelo());
                System.out.println("- Precio Lista: $" + p.getPrecioLista());
                System.out.println("- Precio Contado: $" + p.getPrecioContado());
                System.out.println("- Precio Online: $" + p.getPrecioOnline());
            });

        // Prueba de productos relacionados
        System.out.println("\n=== Productos relacionados de PS5 ===");
        System.out.println("Producto principal: " + consolaPS5.getMarca() + " " + consolaPS5.getModelo());
        System.out.println("Productos relacionados:");
        consolaPS5.getProductosRelacionados().forEach(p -> 
            System.out.println("- " + p.getMarca() + " " + p.getModelo()));

        // Prueba de filtrado por rango de precios específico para una categoría
        System.out.println("\n=== Productos de Informática entre $100000 y $500000 ===");
        informatica.filtrarPorPrecio(100000, 500000).forEach(p ->
            System.out.println(p.getMarca() + " " + p.getModelo() + " - $" + p.getPrecioContado()));

        // Prueba de filtrado por marca en una categoría específica
        System.out.println("\n=== Productos Lenovo en Informática ===");
        informatica.filtrarPorMarca("Lenovo").forEach(p ->
            System.out.println(p.getMarca() + " " + p.getModelo() + " - $" + p.getPrecioContado()));
    }
}