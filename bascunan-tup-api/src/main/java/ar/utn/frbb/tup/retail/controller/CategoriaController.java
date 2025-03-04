package ar.utn.frbb.tup.retail.controller;

import ar.utn.frbb.tup.retail.model.Categoria;
import ar.utn.frbb.tup.retail.model.dto.ProductoDto;
import ar.utn.frbb.tup.retail.model.dto.CategoriaDto;
import ar.utn.frbb.tup.retail.service.CategoriaService;
import ar.utn.frbb.tup.retail.exception.CategoriaNotFoundException;
import ar.utn.frbb.tup.retail.exception.CategoriaConProductosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDto> crearCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.crearCategoria(categoria));
    }

    @PutMapping("/{identificador}")
    public ResponseEntity<CategoriaDto> modificarCategoria(
            @PathVariable String identificador,
            @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.modificarCategoria(identificador, categoria));
    }

    @DeleteMapping("/{identificador}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable String identificador) {
        try {
            categoriaService.eliminarCategoria(identificador);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Categoría eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (CategoriaNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (CategoriaConProductosException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerProductos(
            @RequestParam(required = false) String order_price,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Double precio_min,
            @RequestParam(required = false) Double precio_max) {
        
        if (order_price != null) {
            return ResponseEntity.ok(categoriaService.obtenerProductosOrdenados(order_price));
        }
        
        if (marca != null) {
            return ResponseEntity.ok(categoriaService.obtenerProductosPorMarca(marca));
        }
        
        if (precio_min != null && precio_max != null) {
            return ResponseEntity.ok(categoriaService.obtenerProductosPorPrecio(precio_min, precio_max));
        }
        
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/todas")
    public ResponseEntity<List<CategoriaDto>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerTodasLasCategorias());
    }
} 