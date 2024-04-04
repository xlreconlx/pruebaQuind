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
@Table(name = "TipoCuenta")
public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cuenta")
    private Integer idTipoCuenta;

    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "valor_numero_cuenta")
    private Integer valorNumeroCuenta;
    
    @Column(name = "numeroInicio")
    private Integer numeroInicio;

    public TipoCuenta() {
    }

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValorNumeroCuenta() {
        return valorNumeroCuenta;
    }

    public void setValorNumeroCuenta(Integer valorNumeroCuenta) {
        this.valorNumeroCuenta = valorNumeroCuenta;
    }

    public Integer getNumeroInicio() {
        return numeroInicio;
    }

    public void setNumeroInicio(Integer numeroInicio) {
        this.numeroInicio = numeroInicio;
    }
    
}
