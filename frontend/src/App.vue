<script setup>
import { RouterView, RouterLink } from 'vue-router'
</script>

<template>
  <div class="min-h-screen flex flex-col">
    <header class="bg-white/85 backdrop-blur border-b sticky top-0 z-10">
      <div class="max-w-6xl mx-auto px-4 py-4 flex items-center justify-between">
        <div class="flex items-center gap-3">
          <span class="inline-flex h-10 w-10 items-center justify-center rounded-lg bg-rose-100 text-rose-600 font-bold">CB</span>
          <h1 class="text-xl font-semibold">Colmena Belleza</h1>
        </div>
        <nav class="flex items-center gap-4 text-sm">
          <RouterLink to="/" class="hover:text-rose-600">Inicio</RouterLink>
          <RouterLink to="/ventas" class="hover:text-rose-600">Ventas</RouterLink>
          <RouterLink to="/crud/productos" class="hover:text-rose-600">Productos</RouterLink>
          <RouterLink to="/crud/clientes" class="hover:text-rose-600">Clientes</RouterLink>
          <RouterLink to="/crud/categorias" class="hover:text-rose-600">Categorías</RouterLink>
          <RouterLink to="/crud/proveedores" class="hover:text-rose-600">Proveedores</RouterLink>
          <RouterLink to="/crud/gastos" class="hover:text-rose-600">Gastos</RouterLink>
          <RouterLink to="/crud/inventario_movimientos" class="hover:text-rose-600">Inventario</RouterLink>
        </nav>
      </div>
    </header>

    <main class="max-w-6xl w-full mx-auto flex-1 p-4">
      <RouterView />
    </main>
  </div>
</template>
