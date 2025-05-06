-- Tabla EVENTO
CREATE TABLE evento (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    fecha VARCHAR2(50),
    ubicacion VARCHAR2(100)
);

-- Tabla PARTICIPANTES de cada evento
CREATE TABLE evento_participantes (
    evento_id NUMBER,
    participante VARCHAR2(100),
    CONSTRAINT fk_evento FOREIGN KEY (evento_id) REFERENCES evento(id)
);

-- Tabla FACTURA
CREATE TABLE factura (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente VARCHAR2(100),
    total NUMBER(10, 2),
    pagada NUMBER(1)
);

-- Tabla SERVICIO relacionada con la factura
CREATE TABLE servicio (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR2(255),
    costo NUMBER(10, 2),
    factura_id NUMBER,
    CONSTRAINT fk_factura FOREIGN KEY (factura_id) REFERENCES factura(id)
);

-- Insertar eventos
INSERT INTO evento (id, nombre, fecha, ubicacion)
VALUES (1, 'Conferencia de Tecnología', '2025-05-10', 'Auditorio 1');
INSERT INTO evento (id, nombre, fecha, ubicacion)
VALUES (2, 'Taller de Inteligencia Artificial', '2025-05-15', 'Sala 3');
INSERT INTO evento (id, nombre, fecha, ubicacion)
VALUES (3, 'Expo Innovación', '2025-05-20', 'Centro de Eventos');

-- Participantes no se insertan si son lista embebida, se gestionan con controladores.

-- Insertar facturas
INSERT INTO factura (id, cliente, total, pagada)
VALUES (1, 'Juan Pérez', 200.0, 0);
INSERT INTO factura (id, cliente, total, pagada)
VALUES (2, 'María López', 150.0, 0);
INSERT INTO factura (id, cliente, total, pagada)
VALUES (3, 'Pedro Sánchez', 300.0, 0);

-- Insertar servicios asociados (si tienes tabla de servicios relacionada)
-- Requiere relación FK si está modelado como entidad separada
INSERT INTO servicio (id, descripcion, costo, factura_id)
VALUES (1, 'Asesoría técnica', 120.0, 1);
INSERT INTO servicio (id, descripcion, costo, factura_id)
VALUES (2, 'Instalación de software', 80.0, 1);
INSERT INTO servicio (id, descripcion, costo, factura_id)
VALUES (3, 'Diseño gráfico', 150.0, 2);
INSERT INTO servicio (id, descripcion, costo, factura_id)
VALUES (4, 'Mantenimiento de red', 200.0, 3);
INSERT INTO servicio (id, descripcion, costo, factura_id)
VALUES (5, 'Revisión de servidores', 100.0, 3);
