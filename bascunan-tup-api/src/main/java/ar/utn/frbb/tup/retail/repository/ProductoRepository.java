package ar.utn.frbb.tup.retail.repository;

import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.model.Categoria;
import ar.utn.frbb.tup.retail.model.CategoriaEnum;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
;

@Repository
public class ProductoRepository {
    
    private List<Producto> productos = new ArrayList<>();
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProductoRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        
        // Creamos todas las categorías primero
        Categoria catATV = new Categoria();
        catATV.setIdentificador("ATV");
        catATV.setNombre("Audio, TV y Video");
        catATV.setDescripcion("Productos de audio y video");
        catATV.setTipo(CategoriaEnum.TV_AUDIO_VIDEO);
        categoriaRepository.save(catATV);

        Categoria catElectro = new Categoria();
        catElectro.setIdentificador("ELE");
        catElectro.setNombre("Electrodomésticos");
        catElectro.setDescripcion("Electrodomésticos para el hogar");
        catElectro.setTipo(CategoriaEnum.ELECTRODOMESTICOS_CLIMATIZACION);
        categoriaRepository.save(catElectro);

        Categoria catInfo = new Categoria();
        catInfo.setIdentificador("INF");
        catInfo.setNombre("Informática");
        catInfo.setDescripcion("Productos de informática");
        catInfo.setTipo(CategoriaEnum.INFORMATICA_ELECTRONICA);
        categoriaRepository.save(catInfo);

        Categoria catSalud = new Categoria();
        catSalud.setIdentificador("SAL");
        catSalud.setNombre("Salud y Aire Libre");
        catSalud.setDescripcion("Productos para deporte y aire libre");
        catSalud.setTipo(CategoriaEnum.SALUD_AIRE_LIBRE);
        categoriaRepository.save(catSalud);

        Categoria catHogar = new Categoria();
        catHogar.setIdentificador("HOG");
        catHogar.setNombre("Hogar y Muebles");
        catHogar.setDescripcion("Productos para el hogar");
        catHogar.setTipo(CategoriaEnum.HOGAR_MUEBLES);
        categoriaRepository.save(catHogar);

        // Notebook HP
        Producto notebook = new Producto();
        notebook.setIdentificador("NOTE001");
        notebook.setNombre("Notebook HP 15");
        notebook.setDescripcion("Notebook HP 15.6 pulgadas");
        notebook.setMarca("HP");
        notebook.setPrecioLista(580000.0);
        notebook.setTipoProducto("notebook");
        List<String> especificacionesNote = new ArrayList<>();
        especificacionesNote.add("Procesador Intel i5");
        especificacionesNote.add("8GB RAM");
        especificacionesNote.add("256GB SSD");
        especificacionesNote.add("Windows 11");
        especificacionesNote.add("15.6 pulgadas Full HD");
        notebook.setEspecificaciones(especificacionesNote);
        notebook.setCategoria(catInfo);
        productos.add(notebook);
        catInfo.getProductos().add(notebook);

        // Bicicleta
        Producto bicicleta = new Producto();
        bicicleta.setIdentificador("BIC001");
        bicicleta.setNombre("Bicicleta Mountain Bike Rodado 29");
        bicicleta.setDescripcion("Bicicleta Mountain Bike Aluminio Rod 29");
        bicicleta.setMarca("Venzo");
        bicicleta.setPrecioLista(280000.0);
        bicicleta.setTipoProducto("bicicleta");
        List<String> especificacionesBici = new ArrayList<>();
        especificacionesBici.add("Rodado 29");
        especificacionesBici.add("21 velocidades");
        especificacionesBici.add("Cuadro de aluminio");
        especificacionesBici.add("Color Negro/Rojo");
        especificacionesBici.add("Frenos a disco hidráulicos");
        bicicleta.setEspecificaciones(especificacionesBici);
        bicicleta.setCategoria(catSalud);
        productos.add(bicicleta);
        catSalud.getProductos().add(bicicleta);

        // Sillón
        Producto sillon = new Producto();
        sillon.setIdentificador("SILL001");
        sillon.setNombre("Sillón 3 Cuerpos");
        sillon.setDescripcion("Sillón 3 cuerpos tapizado en tela");
        sillon.setMarca("Suavestar");
        sillon.setPrecioLista(320000.0);
        sillon.setTipoProducto("sillon");
        List<String> especificacionesSillon = new ArrayList<>();
        especificacionesSillon.add("3 cuerpos");
        especificacionesSillon.add("Tapizado en tela");
        especificacionesSillon.add("Alto: 90cm, Ancho: 220cm");
        especificacionesSillon.add("Color: Gris");
        especificacionesSillon.add("Estructura de madera");
        sillon.setEspecificaciones(especificacionesSillon);
        sillon.setCategoria(catHogar);
        productos.add(sillon);
        catHogar.getProductos().add(sillon);

        // TV Samsung
        Producto tv = new Producto();
        tv.setIdentificador("TV001");
        tv.setNombre("Smart TV Samsung 55");
        tv.setDescripcion("Smart TV Samsung 55 pulgadas 4K");
        tv.setMarca("Samsung");
        tv.setPrecioLista(450000.0);
        tv.setTipoProducto("televisor");
        List<String> especificacionesTV = new ArrayList<>();
        especificacionesTV.add("55 pulgadas");
        especificacionesTV.add("4K UHD");
        especificacionesTV.add("Smart TV");
        especificacionesTV.add("HDR");
        especificacionesTV.add("Tizen OS");
        tv.setEspecificaciones(especificacionesTV);
        tv.setCategoria(catATV);
        productos.add(tv);
        catATV.getProductos().add(tv);

        // Heladera Samsung
        Producto heladera = new Producto();
        heladera.setIdentificador("HEL001");
        heladera.setNombre("Heladera Samsung No Frost");
        heladera.setDescripcion("Heladera Samsung No Frost 400L");
        heladera.setMarca("Samsung");
        heladera.setPrecioLista(350000.0);
        heladera.setTipoProducto("heladera");
        List<String> especificacionesHeladera = new ArrayList<>();
        especificacionesHeladera.add("400L");
        especificacionesHeladera.add("No Frost");
        especificacionesHeladera.add("Doble puerta");
        especificacionesHeladera.add("Freezer superior");
        especificacionesHeladera.add("Color Inox");
        heladera.setEspecificaciones(especificacionesHeladera);
        heladera.setCategoria(catElectro);
        productos.add(heladera);
        catElectro.getProductos().add(heladera);
    }

    public Producto save(Producto producto) {
        if (existsById(producto.getIdentificador())) {
            // Si existe, actualizar
            productos.removeIf(p -> p.getIdentificador().equals(producto.getIdentificador()));
        }
        productos.add(producto);
        if (producto.getCategoria() != null) {
            producto.getCategoria().getProductos().add(producto);
        }
        return producto;
    }

    public boolean existsById(String identificador) {
        for(Producto producto : productos) {
            if(producto.getIdentificador().equals(identificador)) {
                return true;
            }
        }
        return false;
    }

    public Producto findById(String identificador) {
        for (Producto producto : productos) {
            if (producto.getIdentificador().equals(identificador)) {
                return producto;
            }
        }
        return null;
    }

    public void delete(String identificador) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdentificador().equals(identificador)) {
                Producto producto = productos.get(i);
                if (producto.getCategoria() != null) {
                    producto.getCategoria().getProductos().remove(producto);
                }
                productos.remove(i);
                break;
            }
        }
    }

    public List<Producto> findAll() {
        return productos;
    }

    public List<Producto> findByAttributes(String tipoProducto, String marca, String categoria) {
        List<Producto> resultados = new ArrayList<>();
        
        for (Producto producto : productos) {
            boolean coincide = true;
            
            if (tipoProducto != null && !producto.getTipoProducto().equals(tipoProducto)) {
                coincide = false;
            }
            if (marca != null && !producto.getMarca().equals(marca)) {
                coincide = false;
            }
            if (categoria != null) {
                if (producto.getCategoria() == null || 
                    !producto.getCategoria().getIdentificador().equals(categoria)) {
                    coincide = false;
                }
            }
            
            if (coincide) {
                resultados.add(producto);
            }
        }
        
        return resultados;
    }
} 