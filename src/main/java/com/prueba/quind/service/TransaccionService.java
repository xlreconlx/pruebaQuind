/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.quind.service;

import com.prueba.quind.entity.Producto;
import com.prueba.quind.entity.Transaccion;
import com.prueba.quind.model.RespuestaTransaccion;
import com.prueba.quind.repository.ProductoRepository;
import com.prueba.quind.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ander
 */
@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public RespuestaTransaccion createTransaccion(Transaccion transaccion) {
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
        if (transaccion.getMonto() < 0) {
            respuestaTransaccion.setMensaje("Error debe ingresar un monto mayor a 0.");
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }
        Producto productoOrigen;
        Producto productoDestino;

        try {
            switch (transaccion.getIdTipoTransaccion()) {
                //consignacion segun el id de la base de datos
                case 1:
                    if (transaccion.getIdCuentaDestino() == null) {
                        respuestaTransaccion.setMensaje("Error: creando la transaccion debe ingresar la cuenta de destino.");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }
                    productoDestino = productoRepository.findByIdProducto(transaccion.getIdCuentaDestino());
                    if (productoDestino == null) {
                        respuestaTransaccion.setMensaje("Error: creando la transaccion la cuenta de destino no existe");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }

                    productoDestino.setSaldo(productoDestino.getSaldo() + transaccion.getMonto());

                    productoRepository.save(productoDestino);
                    break;
                //Retiro segun el id de la base de datos
                case 2:
                    if (transaccion.getIdCuentaOrigen() == null) {
                        respuestaTransaccion.setMensaje("Error: creando la transaccion debe ingresar la cuenta de origen.");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }
                    productoOrigen = productoRepository.findByIdProducto(transaccion.getIdCuentaOrigen());

                    if (productoOrigen.getIdTipoCuenta() == 2 && productoOrigen.getSaldo() - transaccion.getMonto() < 0) {
                        respuestaTransaccion.setMensaje("Error: el retiro sobrepasa los fondos disponibles");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }

                    productoOrigen.setSaldo(productoOrigen.getSaldo() - transaccion.getMonto());

                    productoRepository.save(productoOrigen);
                    break;
                //Transferencia segun el id de la base de datos    
                case 3:
                    if (transaccion.getIdCuentaOrigen() == null) {
                        respuestaTransaccion.setMensaje("Error: creando la transaccion debe ingresar la cuenta de origen.");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }

                    if (transaccion.getIdCuentaDestino() == null) {
                        respuestaTransaccion.setMensaje("Error: creando la transaccion debe ingresar la cuenta de destino.");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }

                    productoOrigen = productoRepository.findByIdProducto(transaccion.getIdCuentaOrigen());
                    productoDestino = productoRepository.findByIdProducto(transaccion.getIdCuentaDestino());

                    if (productoOrigen == null || productoDestino == null) {
                        respuestaTransaccion.setMensaje("Error: no se encontro una de las cuentas");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }

                    if (productoOrigen.getIdTipoCuenta() == 2 && productoOrigen.getSaldo() - transaccion.getMonto() < 0) {
                        respuestaTransaccion.setMensaje("Error: la transferencia sobrepasa los fondos disponibles");
                        respuestaTransaccion.setTransaccionCompletada(false);
                        return respuestaTransaccion;
                    }

                    productoOrigen.setSaldo(productoOrigen.getSaldo() - transaccion.getMonto());
                    productoDestino.setSaldo(productoDestino.getSaldo() + transaccion.getMonto());

                    productoRepository.save(productoOrigen);
                    productoRepository.save(productoDestino);

                    break;
            }

            transaccionRepository.save(transaccion);
            respuestaTransaccion.setMensaje("Operacion realizada correctamente");
            respuestaTransaccion.setTransaccionCompletada(true);
            return respuestaTransaccion;
        } catch (Exception e) {
            respuestaTransaccion.setMensaje("Ocurrio un error realizando la operacion: " + e.toString());
            respuestaTransaccion.setTransaccionCompletada(false);
            return respuestaTransaccion;
        }

    }
}
