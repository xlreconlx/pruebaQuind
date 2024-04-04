/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.controller;

import com.prueba.quind.entity.Transaccion;
import com.prueba.quind.model.RespuestaTransaccion;
import com.prueba.quind.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ander
 */
@RestController
@RequestMapping(value = "/api/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;
    
    @PostMapping("create")
    public ResponseEntity<RespuestaTransaccion> createCliente(@RequestBody Transaccion transaccion){
        return ResponseEntity.ok(transaccionService.createTransaccion(transaccion));
    }
}
