-- =====================================================
-- SCHEMA: COLMENA BELLEZA
-- =====================================================

-- 🔹 1. Categorías
CREATE TABLE IF NOT EXISTS categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- 🔹 2. Proveedores
CREATE TABLE IF NOT EXISTS proveedores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion TEXT
);

-- 🔹 3. Productos (modificado)
CREATE TABLE IF NOT EXISTS productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    codigo_barras VARCHAR(50) UNIQUE,
    id_categoria INT REFERENCES categorias(id) ON DELETE SET NULL,
    id_proveedor INT REFERENCES proveedores(id) ON DELETE SET NULL
);


-- 🔹 4. Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion TEXT
);

-- 🔹 5. Ventas
CREATE TABLE IF NOT EXISTS ventas (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT NOW(),
    id_cliente INT REFERENCES clientes(id) ON DELETE SET NULL,
    total DECIMAL(12,2) DEFAULT 0,
    metodo_pago VARCHAR(50)
);

-- 🔹 6. Detalle de ventas
CREATE TABLE IF NOT EXISTS detalle_ventas (
    id SERIAL PRIMARY KEY,
    id_venta INT REFERENCES ventas(id) ON DELETE CASCADE,
    id_producto INT REFERENCES productos(id) ON DELETE SET NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(12,2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED
);

-- 🔹 7. Gastos
CREATE TABLE IF NOT EXISTS gastos (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT NOW(),
    descripcion TEXT NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    categoria VARCHAR(100)
);

-- 🔹 8. Movimientos de inventario
CREATE TABLE IF NOT EXISTS inventario_movimientos (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT NOW(),
    tipo VARCHAR(20) CHECK (tipo IN ('ENTRADA', 'SALIDA')),
    id_producto INT REFERENCES productos(id) ON DELETE SET NULL,
    cantidad INT NOT NULL,
    descripcion TEXT
);
