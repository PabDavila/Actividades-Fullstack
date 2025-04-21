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
