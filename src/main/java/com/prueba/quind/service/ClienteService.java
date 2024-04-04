/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.service;

import com.prueba.quind.entity.Cliente;
import com.prueba.quind.model.RespuestaTransaccion;
import com.prueba.quind.repository.ClienteRepository;
import com.prueba.quind.repository.ProductoRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ander
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente findByNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);
    }

    public RespuestaTransaccion createCliente(Cliente cliente) {
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();

        if (cliente.getNombres().length() < 2 || cliente.getApellido().length() < 2) {
            respuestaTransaccion.setMensaje("Error: el nombre y el apellido deben tener mas de 2 caracteres");
            return respuestaTransaccion;
        }

        if (!clienteMayorEdad(cliente.getFechaNacimiento())) {
            respuestaTransaccion.setMensaje("Error: el cliente debe ser mayor de edad");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

        if (!validarCorreoElectronico(cliente.getCorreoElectronico())) {
            respuestaTransaccion.setMensaje("Error: el cliente debe tener un correo valido");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

        try {
            clienteRepository.save(cliente);
            respuestaTransaccion.setMensaje("Se ha registrado correctamente.");
            respuestaTransaccion.setTransaccionCompletada(true);
            return respuestaTransaccion;
        } catch (Exception e) {
            respuestaTransaccion.setMensaje("Ocurrio un error registrando al cliente: " + e.toString());
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

    }

    public RespuestaTransaccion updateCliente(Cliente cliente) {
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
        Cliente clienteExiste = findByNumeroIdentificacion(cliente.getNumeroIdentificacion());

        if (clienteExiste == null) {
            respuestaTransaccion.setMensaje("Error: el numero de identificacion no existe, por lo cual no se puede actualizar");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

        cliente.setIdCliente(clienteExiste.getIdCliente());

        try {
            clienteRepository.save(cliente);
            respuestaTransaccion.setMensaje("Se ha actualizado correctamente.");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        } catch (Exception e) {
            respuestaTransaccion.setMensaje("Ocurrio un error actualizando al cliente: " + e.toString());
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

    }

    public RespuestaTransaccion deleteCliente(String numeroIdentificacion) {
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
        Cliente cliente = findByNumeroIdentificacion(numeroIdentificacion);

        if (cliente == null) {
            respuestaTransaccion.setMensaje("Error: el numero de identificacion no existe");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

        if (!productoRepository.findAllProductsByIdCliente(cliente.getIdCliente()).isEmpty()) {
            respuestaTransaccion.setMensaje("Error: el cliente tiene productos asociados");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }
        try {
            clienteRepository.delete(cliente);
            respuestaTransaccion.setMensaje("Se ha eliminado correctamente");
            respuestaTransaccion.setTransaccionCompletada(true);
            return respuestaTransaccion;
        } catch (Exception e) {
            respuestaTransaccion.setMensaje("Ocurrio un error eliminando al cliente: " + e.toString());
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

    }

    public boolean validarCorreoElectronico(String correoElectronico) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correoElectronico);
        return matcher.matches();
    }

    public boolean clienteMayorEdad(LocalDate fecha) {
        LocalDate fechaNacimiento = fecha;
        LocalDate fechaActual = LocalDate.now();

        return Period.between(fechaNacimiento, fechaActual).getYears() >= 18;
    }
}
