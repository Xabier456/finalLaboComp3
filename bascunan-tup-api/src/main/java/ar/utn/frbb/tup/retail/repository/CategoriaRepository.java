package ar.utn.frbb.tup.retail.repository;

import ar.utn.frbb.tup.retail.model.Categoria;
import ar.utn.frbb.tup.retail.model.CategoriaEnum;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepository {
    
    private List<Categoria> categorias = new ArrayList<>();

    public CategoriaRepository() {
        // Inicializamos las categorías según el documento
        Categoria catTV = new Categoria();
        catTV.setIdentificador("ATV");
        catTV.setNombre("Audio, TV y Video");
        catTV.setDescripcion("Productos de audio, televisores y video");
        catTV.setTipo(CategoriaEnum.TV_AUDIO_VIDEO);
        categorias.add(catTV);

        Categoria catElectro = new Categoria();
        catElectro.setIdentificador("ELE");
        catElectro.setNombre("Electrodomésticos y Climatización");
        catElectro.setDescripcion("Electrodomésticos para el hogar y equipos de climatización");
        catElectro.setTipo(CategoriaEnum.ELECTRODOMESTICOS_CLIMATIZACION);
        categorias.add(catElectro);

        Categoria catHogar = new Categoria();
        catHogar.setIdentificador("HOG");
        catHogar.setNombre("Hogar y Muebles");
        catHogar.setDescripcion("Muebles y artículos para el hogar");
        catHogar.setTipo(CategoriaEnum.HOGAR_MUEBLES);
        categorias.add(catHogar);

        Categoria catInfo = new Categoria();
        catInfo.setIdentificador("INF");
        catInfo.setNombre("Informática y Electrónica");
        catInfo.setDescripcion("Productos de computación y electrónica");
        catInfo.setTipo(CategoriaEnum.INFORMATICA_ELECTRONICA);
        categorias.add(catInfo);

        Categoria catSalud = new Categoria();
        catSalud.setIdentificador("SAL");
        catSalud.setNombre("Salud y Aire Libre");
        catSalud.setDescripcion("Productos para la salud y actividades al aire libre");
        catSalud.setTipo(CategoriaEnum.SALUD_AIRE_LIBRE);
        categorias.add(catSalud);
    }

    public Categoria save(Categoria categoria) {
        if (existsById(categoria.getIdentificador())) {
            // Si existe, actualizar
            categorias.removeIf(c -> c.getIdentificador().equals(categoria.getIdentificador()));
        }
        categorias.add(categoria);
        return categoria;
    }

    public Categoria findById(String identificador) {
        for(Categoria categoria : categorias) {
            if(categoria.getIdentificador().equals(identificador)) {
                return categoria;
            }
        }
        return null;
    }

    public List<Categoria> findAll() {
        return new ArrayList<>(categorias);
    }

    public void delete(String identificador) {
        Categoria categoriaAEliminar = null;
        for(Categoria categoria : categorias) {
            if(categoria.getIdentificador().equals(identificador)) {
                categoriaAEliminar = categoria;
                break;
            }
        }
        if(categoriaAEliminar != null) {
            categorias.remove(categoriaAEliminar);
        }
    }

    public boolean existsById(String identificador) {
        for(Categoria categoria : categorias) {
            if(categoria.getIdentificador().equals(identificador)) {
                return true;
            }
        }
        return false;
    }
} 