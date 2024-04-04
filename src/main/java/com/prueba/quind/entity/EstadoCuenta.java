/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author ander
 */
@Entity
@Table(name = "EstadoCuenta")
public class EstadoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_cuenta")
    private Integer idEstadoCuenta;

    @Column(name = "nombre", unique = true)
    private String nombre;

    public EstadoCuenta() {
    }
   
    public Integer getIdEstadoCuenta() {
        return idEstadoCuenta;
    }

    public void setIdEstadoCuenta(Integer idEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
