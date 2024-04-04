/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.controller;

import com.prueba.quind.entity.Producto;
import com.prueba.quind.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ander
 */
@RestController
@RequestMapping(value = "/api/clientes")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("{idCliente}")
    public ResponseEntity<List<Producto>> getAllProductosByCliente(@PathVariable int idCliente) {
        return ResponseEntity.ok(productoService.getAllProductosByCliente(idCliente));
    }
    
    
}
