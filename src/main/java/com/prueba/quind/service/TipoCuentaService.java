/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.service;

import com.prueba.quind.entity.TipoCuenta;
import com.prueba.quind.repository.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ander
 */
@Service
public class TipoCuentaService {
    @Autowired
    private TipoCuentaRepository tipoCuentaRepository;
    
    public String getNewNumeroCuenta(int idTipoCuenta){
        TipoCuenta tipoCuenta =  tipoCuentaRepository.findByIdTipoCuenta(idTipoCuenta);
        int valorNumeroCuenta = tipoCuenta.getValorNumeroCuenta();
        int numeroInicio = tipoCuenta.getNumeroInicio();

        return String.valueOf(numeroInicio) + String.format("%08d", valorNumeroCuenta);
    }
}
