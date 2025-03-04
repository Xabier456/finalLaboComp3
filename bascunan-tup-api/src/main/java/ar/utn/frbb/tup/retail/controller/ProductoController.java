package ar.utn.frbb.tup.retail.controller;

import ar.utn.frbb.tup.retail.exception.ProductoNotFoundException;
import ar.utn.frbb.tup.retail.exception.ProductoExistsException;
import ar.utn.frbb.tup.retail.model.Producto;
import ar.utn.frbb.tup.retail.model.dto.ProductoDto;
import ar.utn.frbb.tup.retail.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok(productoService.crearProducto(producto));
        } catch (ProductoExistsException e) {
            return handleError(e.getMessage(), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException e) {
            return handleError(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{identificador}")
    public ResponseEntity<?> modificarProducto(
            @PathVariable String identificador,
            @RequestBody Producto producto) {
        try {
            return ResponseEntity.ok(productoService.modificarProducto(identificador, producto));
        } catch (ProductoNotFoundException e) {
            return handleError(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return handleError(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{identificador}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String identificador) {
        try {
            productoService.eliminarProducto(identificador);
            return ResponseEntity.ok(Map.of("mensaje", "Producto eliminado exitosamente"));
        } catch (ProductoNotFoundException e) {
            return handleError(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable String identificador) {
        try {
            return ResponseEntity.ok(productoService.obtenerProductoPorId(identificador));
        } catch (ProductoNotFoundException e) {
            return handleError(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProductoDto>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> buscarProductos(
            @RequestParam(required = false) String tipo_producto,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String categoria) {
        return ResponseEntity.ok(productoService.buscarProductos(tipo_producto, marca, categoria));
    }

    private ResponseEntity<?> handleError(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
} 