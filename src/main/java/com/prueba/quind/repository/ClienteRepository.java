/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.quind.repository;

import com.prueba.quind.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ander
 */
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    public Cliente findByNumeroIdentificacion(String numeroIdentificacion);
}
