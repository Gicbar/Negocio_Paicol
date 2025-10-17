-- Align integer PK/FK columns to BIGINT and timestamps to TIMESTAMPTZ

-- 1) Drop foreign keys to allow type changes
ALTER TABLE IF EXISTS productos DROP CONSTRAINT IF EXISTS productos_id_categoria_fkey;
ALTER TABLE IF EXISTS productos DROP CONSTRAINT IF EXISTS productos_id_proveedor_fkey;
ALTER TABLE IF EXISTS ventas DROP CONSTRAINT IF EXISTS ventas_id_cliente_fkey;
ALTER TABLE IF EXISTS detalle_ventas DROP CONSTRAINT IF EXISTS detalle_ventas_id_venta_fkey;
ALTER TABLE IF EXISTS detalle_ventas DROP CONSTRAINT IF EXISTS detalle_ventas_id_producto_fkey;
ALTER TABLE IF EXISTS inventario_movimientos DROP CONSTRAINT IF EXISTS inventario_movimientos_id_producto_fkey;

-- 2) Promote primary key columns from INTEGER to BIGINT
ALTER TABLE IF EXISTS categorias ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS proveedores ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS productos ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS clientes ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS ventas ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS detalle_ventas ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS gastos ALTER COLUMN id TYPE BIGINT;
ALTER TABLE IF EXISTS inventario_movimientos ALTER COLUMN id TYPE BIGINT;

-- 3) Promote foreign key columns from INTEGER to BIGINT
ALTER TABLE IF EXISTS productos ALTER COLUMN id_categoria TYPE BIGINT USING id_categoria::BIGINT;
ALTER TABLE IF EXISTS productos ALTER COLUMN id_proveedor TYPE BIGINT USING id_proveedor::BIGINT;
ALTER TABLE IF EXISTS ventas ALTER COLUMN id_cliente TYPE BIGINT USING id_cliente::BIGINT;
ALTER TABLE IF EXISTS detalle_ventas ALTER COLUMN id_venta TYPE BIGINT USING id_venta::BIGINT;
ALTER TABLE IF EXISTS detalle_ventas ALTER COLUMN id_producto TYPE BIGINT USING id_producto::BIGINT;
ALTER TABLE IF EXISTS inventario_movimientos ALTER COLUMN id_producto TYPE BIGINT USING id_producto::BIGINT;

-- 4) Recreate foreign keys with original ON DELETE semantics
ALTER TABLE IF EXISTS productos
  ADD CONSTRAINT productos_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES categorias(id) ON DELETE SET NULL;
ALTER TABLE IF EXISTS productos
  ADD CONSTRAINT productos_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES proveedores(id) ON DELETE SET NULL;
ALTER TABLE IF EXISTS ventas
  ADD CONSTRAINT ventas_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE SET NULL;
ALTER TABLE IF EXISTS detalle_ventas
  ADD CONSTRAINT detalle_ventas_id_venta_fkey FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS detalle_ventas
  ADD CONSTRAINT detalle_ventas_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE SET NULL;
ALTER TABLE IF EXISTS inventario_movimientos
  ADD CONSTRAINT inventario_movimientos_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE SET NULL;

-- 5) Upgrade timestamp columns to TIMESTAMPTZ to match OffsetDateTime usage
ALTER TABLE IF EXISTS ventas ALTER COLUMN fecha TYPE TIMESTAMPTZ USING fecha::timestamptz;
ALTER TABLE IF EXISTS ventas ALTER COLUMN fecha SET DEFAULT now();

ALTER TABLE IF EXISTS gastos ALTER COLUMN fecha TYPE TIMESTAMPTZ USING fecha::timestamptz;
ALTER TABLE IF EXISTS gastos ALTER COLUMN fecha SET DEFAULT now();

ALTER TABLE IF EXISTS inventario_movimientos ALTER COLUMN fecha TYPE TIMESTAMPTZ USING fecha::timestamptz;
ALTER TABLE IF EXISTS inventario_movimientos ALTER COLUMN fecha SET DEFAULT now();
