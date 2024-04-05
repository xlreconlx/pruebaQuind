CREATE DATABASE PRUEBA;
USE PRUEBA;

CREATE TABLE Clientes (
    id_cliente INTEGER AUTO_INCREMENT PRIMARY KEY,
    tipo_identificacion VARCHAR(50),
    numero_identificacion VARCHAR(50),
    nombres VARCHAR(100),
    apellido VARCHAR(100),
    correo_electronico VARCHAR(100),
    fecha_nacimiento DATE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE TipoCuenta (
    id_tipo_cuenta INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE,
    valor_numero_cuenta INT,
    CHECK (nombre IN ('Cuenta Corriente', 'Cuenta de Ahorros'))
);

CREATE TABLE EstadoCuenta (
    id_estado_cuenta INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE,
    CHECK (nombre IN ('Activa', 'Inactiva', 'Cancelada'))
);

CREATE TABLE TipoTransaccion (
    id_tipo_transaccion INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE,
    CHECK (nombre IN ('Consignacion', 'Retiro', 'Transferencia'))
);

CREATE TABLE Productos (
    id_producto INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_cliente INTEGER,
    id_tipo_cuenta INTEGER,
    numero_cuenta VARCHAR(10) UNIQUE,
    id_estado_cuenta INTEGER,
    saldo DECIMAL(10, 2),
    exenta_gmf BOOLEAN,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_tipo_cuenta) REFERENCES TipoCuenta(id_tipo_cuenta),
    FOREIGN KEY (id_estado_cuenta) REFERENCES EstadoCuenta(id_estado_cuenta)
);

CREATE TABLE Transacciones (
    id_transaccion INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_tipo_transaccion INTEGER,
    id_cuenta_origen INTEGER,
    id_cuenta_destino INTEGER,
    monto DECIMAL(10, 2),
    fecha_transaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tipo_transaccion) REFERENCES TipoTransaccion(id_tipo_transaccion),
    FOREIGN KEY (id_cuenta_origen) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_cuenta_destino) REFERENCES Productos(id_producto)
);

INSERT INTO `tipotransaccion` VALUES (1,'Consignacion'),(2,'Retiro'),(3,'Transferencia');
INSERT INTO `tipocuenta` VALUES (1,'Cuenta Corriente',2,33),(2,'Cuenta de Ahorros',3,53);
INSERT INTO `estadocuenta` VALUES (1,'Activa'),(3,'Cancelada'),(2,'Inactiva');
