package com.devsenior.cdiaz.product.service;

import com.devsenior.cdiaz.product.entity.Producto;
import com.devsenior.cdiaz.product.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos
     * @return Lista de todos los productos
     */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su ID
     * @param id ID del producto a buscar
     * @return Optional que contiene el producto si existe, vacío si no
     */
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    /**
     * Guarda un producto (crear o actualizar)
     * @param producto Producto a guardar
     * @return Producto guardado
     */
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Elimina un producto por su ID
     * @param id ID del producto a eliminar
     */
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    /**
     * Verifica si existe un producto con el ID dado
     * @param id ID del producto a verificar
     * @return true si existe, false si no
     */
    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }

    /**
     * Cuenta el total de productos
     * @return Número total de productos
     */
    public long count() {
        return productoRepository.count();
    }
}
