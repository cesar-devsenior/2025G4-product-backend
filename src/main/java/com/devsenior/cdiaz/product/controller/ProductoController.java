package com.devsenior.cdiaz.product.controller;

import com.devsenior.cdiaz.product.entity.Producto;
import com.devsenior.cdiaz.product.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * GET /api/productos
     * Obtiene todos los productos
     */
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    /**
     * GET /api/productos/{id}
     * Obtiene un producto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /api/productos
     * Crea un nuevo producto
     */
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/productos/{id}
     * Actualiza un producto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        producto.setId(id); // Asegurar que el ID coincida
        Producto productoActualizado = productoService.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    /**
     * DELETE /api/productos/{id}
     * Elimina un producto por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!productoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/productos/count
     * Obtiene el número total de productos
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProductosCount() {
        long count = productoService.count();
        return ResponseEntity.ok(count);
    }
}
