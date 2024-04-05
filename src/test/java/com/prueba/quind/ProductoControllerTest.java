/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind;

import com.prueba.quind.controller.ProductoController;
import com.prueba.quind.entity.Producto;
import com.prueba.quind.model.RespuestaTransaccion;
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
public class ProductoControllerTest {

    public static final int CUENTA_CORRIENTE = 1;
    public static final int CUENTA_AHORROS = 2;

    public static final int CUENTA_ACTIVA = 1;
    public static final int CUENTA_CANCELADA = 2;
    public static final int CUENTA_INACTIVA = 3;

    public static final boolean CUENTA_EXENTA = true;
    public static final boolean CUENTA_NO_EXENTA = true;
    
    public static final int ID_CLIENTE = 1;

    @Autowired
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto(ID_CLIENTE, CUENTA_CORRIENTE, CUENTA_ACTIVA, 10000d, true);
        System.out.println("@BeforeEach -> setUp()");
    }

    @Test
    void createProducto() {
        ResponseEntity<RespuestaTransaccion> respuestaTransaccion = productoController.createProducto(producto);

        if (respuestaTransaccion.getStatusCode() == HttpStatus.OK) {
            RespuestaTransaccion respuesta = respuestaTransaccion.getBody();

            assertEquals(respuesta.getMensaje(), "Se ha registrado correctamente.");
            assertEquals(respuesta.isTransaccionCompletada(), true);
        } else {
            System.err.println(respuestaTransaccion.getStatusCode());
        }
    }

}
