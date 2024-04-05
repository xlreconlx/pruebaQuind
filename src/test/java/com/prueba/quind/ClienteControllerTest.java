/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind;

import com.prueba.quind.controller.ClienteController;
import com.prueba.quind.entity.Cliente;
import com.prueba.quind.model.RespuestaTransaccion;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ander
 */
@SpringBootTest
class ClienteControllerTest {

    public static final String NOMBRE_CLIENTE = "Anderson";
    public static final String DOCUMENTO_CLIENTE = "12345876";
    public static final String TIPO_DOCUMENTO_CLIENTE = "CC";
    public static final String APELLIDO_CLIENTE = "Urrego";
    public static final String CORREO_CLIENTE = "andersonur@hotmail.com";
    public static final String FECHA_NACIMIENTO_CLIENTE = "1995-05-02";
    

    @Autowired
    private ClienteController clienteController;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        LocalDate fechaNacimiento = LocalDate.parse(FECHA_NACIMIENTO_CLIENTE);
        cliente = new Cliente(TIPO_DOCUMENTO_CLIENTE, DOCUMENTO_CLIENTE, NOMBRE_CLIENTE, APELLIDO_CLIENTE, CORREO_CLIENTE, fechaNacimiento);
        System.out.println("@BeforeEach -> setUp()");
    }

    @Test
    void createClienteTest() {
        ResponseEntity<RespuestaTransaccion> respuestaTransaccion = clienteController.createCliente(cliente);

        if (respuestaTransaccion.getStatusCode() == HttpStatus.OK) {
            RespuestaTransaccion respuesta = respuestaTransaccion.getBody();

            assertEquals(respuesta.getMensaje(), "Se ha registrado correctamente.");
            assertEquals(respuesta.isTransaccionCompletada(), true);
        } else {
            System.err.println(respuestaTransaccion.getStatusCode());
        }
    }

    @Test
    void updateCliente() {
        cliente.setNombres("Prueba");
        ResponseEntity<RespuestaTransaccion> respuestaTransaccion = clienteController.updateCliente(cliente);
        if (respuestaTransaccion.getStatusCode() == HttpStatus.OK) {
            RespuestaTransaccion respuesta = respuestaTransaccion.getBody();

            assertEquals(respuesta.getMensaje(), "Se ha actualizado correctamente.");
            assertEquals(respuesta.isTransaccionCompletada(), true);
        } else {
            System.err.println(respuestaTransaccion.getStatusCode());
        }
    }

    @Test
    void deleteCliente() {
        ResponseEntity<RespuestaTransaccion> respuestaTransaccion = clienteController.deleteCliente(cliente.getNumeroIdentificacion());
        if (respuestaTransaccion.getStatusCode() == HttpStatus.OK) {
            RespuestaTransaccion respuesta = respuestaTransaccion.getBody();

            assertEquals(respuesta.getMensaje(), "Se ha eliminado correctamente.");
            assertEquals(respuesta.isTransaccionCompletada(), true);
        } else {
            System.err.println(respuestaTransaccion.getStatusCode());
        }
    }

}
