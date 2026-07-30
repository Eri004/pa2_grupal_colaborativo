
-- ============================================================
-- CLIENTES (3)
-- ============================================================
INSERT INTO cliente (clie_id, clie_cedula, clie_nombres, clie_apellidos, clie_telefono, clie_correo) VALUES
    (nextval('seq_cliente'), '1712345678', 'Juan',     'Pérez',    '0991234567', 'juan.perez@mail.com'),
    (nextval('seq_cliente'), '1723456789', 'Ana',      'Gómez',    '0982345678', 'ana.gomez@mail.com'),
    (nextval('seq_cliente'), '1734567890', 'Luis',     'Rodríguez','0973456789', 'luis.rodriguez@mail.com');

-- ============================================================
-- VENDEDORES (3)
-- ============================================================
INSERT INTO vendedor (vend_id, vend_cedula, vend_nombres, vend_apellidos) VALUES
    (nextval('seq_vendedor'), '0912345678', 'María',     'López'     ),
    (nextval('seq_vendedor'), '0923456789', 'Carlos',    'Mendoza'  ),
    (nextval('seq_vendedor'), '0934567890', 'Patricia',  'Vera'   );

-- ============================================================
-- VEHICULOS (3)
-- ============================================================
INSERT INTO vehiculo (vehi_id, vehi_placa, vehi_marca, vehi_modelo) VALUES
    (nextval('seq_vehiculo'), 'ABC-1234', 'Toyota',    'Corolla 2024'),
    (nextval('seq_vehiculo'), 'DEF-5678', 'Mazda',     'CX-5 2023'),
    (nextval('seq_vehiculo'), 'GHI-9012', 'Hyundai',   'Tucson 2022');

-- ============================================================
-- PAGOS (3)
-- ============================================================
INSERT INTO pago (pago_id, pago_monto, pago_metodo) VALUES
    (nextval('seq_pago'), 1500.50, 'TARJETA_CREDITO'),
    (nextval('seq_pago'), 2300.00, 'EFECTIVO'),
    (nextval('seq_pago'),  899.99, 'TRANSFERENCIA');
