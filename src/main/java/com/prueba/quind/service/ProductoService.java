/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.service;

import com.prueba.quind.entity.Producto;
import com.prueba.quind.model.RespuestaTransaccion;
import com.prueba.quind.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ander
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductosByCliente(int idCliente) {
        return productoRepository.findAllProductsByIdCliente(idCliente);
    }

    public RespuestaTransaccion createProducto(Producto producto) {
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();

        if (producto.getSaldo() < 0) {
            respuestaTransaccion.setMensaje("Error: no se puede crear una cuenta con valores menores a 0.");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }
        //activamos la cuenta con el valor 1 que corresponde a activa en la base de datos
        producto.setIdEstadoCuenta(1);
        try {
            productoRepository.save(producto);
            respuestaTransaccion.setMensaje("Se ha registrado correctamente.");
            respuestaTransaccion.setTransaccionCompletada(true);
            return respuestaTransaccion;
        } catch (Exception e) {
            respuestaTransaccion.setMensaje("Ocurrio un error resgistrando el producto: ." + e.toString());
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }
    }

    public RespuestaTransaccion updateProducto(Producto producto) {
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
        
        Producto oldProducto = productoRepository.findByIdProducto(producto.getIdProducto());
        
        if(!(producto.getIdEstadoCuenta() == 3 && oldProducto.getSaldo() == 0)){
            respuestaTransaccion.setMensaje("Error el saldo del producto es mayor a 0 no se puede cancelar.");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

        try {
            productoRepository.save(producto);
            respuestaTransaccion.setMensaje("Se ha actualizado correctamente.");
            respuestaTransaccion.setTransaccionCompletada(true);
            return respuestaTransaccion;
        } catch (Exception e) {
            respuestaTransaccion.setMensaje("Ocurrio un error actualizando el producto: ." + e.toString());
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }
    }

}
