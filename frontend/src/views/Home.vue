<script setup>
const cards = [
  { to: '/ventas', title: 'Ventas', desc: 'Registrar ventas con lector de código de barras' },
  { to: '/crud/productos', title: 'Productos', desc: 'Gestiona catálogo, inventario y precios' },
  { to: '/crud/clientes', title: 'Clientes', desc: 'Administra clientes frecuentes' },
  { to: '/crud/categorias', title: 'Categorías', desc: 'Organiza tu catálogo' },
  { to: '/crud/proveedores', title: 'Proveedores', desc: 'Relaciones de compra' },
  { to: '/crud/gastos', title: 'Gastos', desc: 'Control de gastos' },
  { to: '/crud/inventario_movimientos', title: 'Inventario', desc: 'Entradas y salidas' },
]
</script>

<template>
  <section>
    <div class="py-10">
      <h2 class="text-2xl font-bold mb-6">Inicio</h2>
      <div class="grid sm:grid-cols-2 lg:grid-cols-3 gap-4">
        <RouterLink v-for="c in cards" :key="c.to" :to="c.to"
          class="group bg-white rounded-2xl border p-6 card-hover">
          <div class="flex items-start justify-between mb-2">
            <h3 class="text-lg font-semibold">{{ c.title }}</h3>
            <span class="text-rose-500 group-hover:translate-x-1 transition">→</span>
          </div>
          <p class="text-sm text-gray-600">{{ c.desc }}</p>
        </RouterLink>
      </div>
    </div>
  </section>
</template>
