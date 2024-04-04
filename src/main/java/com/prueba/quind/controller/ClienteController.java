/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.controller;

import com.prueba.quind.entity.Cliente;
import com.prueba.quind.model.RespuestaTransaccion;
import com.prueba.quind.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ander
 */
@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }
    
    @PostMapping("create")
    public ResponseEntity<RespuestaTransaccion> createCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }
    
    @PutMapping("update")
    public ResponseEntity<RespuestaTransaccion> updateCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.updateCliente(cliente));
    }
    
    @DeleteMapping("delete/{numeroIdentificacion}")
    public ResponseEntity<RespuestaTransaccion> deleteCliente(@PathVariable String numeroIdentificacion){
        return ResponseEntity.ok(clienteService.deleteCliente(numeroIdentificacion));
    }
    
}
