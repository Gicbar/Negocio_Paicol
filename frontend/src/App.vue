<script setup>
import { RouterView, RouterLink } from 'vue-router'
</script>

<template>
  <div class="min-h-screen flex flex-col">
    <header class="bg-white/90 backdrop-blur border-b sticky top-0 z-10">
      <div class="max-w-6xl mx-auto px-4 py-4 flex items-center justify-between">
        <div class="flex items-center gap-3">
          <span class="inline-flex h-10 w-10 items-center justify-center rounded-lg bg-brand-accent/20 text-brand-accent font-bold">CB</span>
          <h1 class="text-xl font-semibold">Colmena Belleza</h1>
        </div>
        <nav class="flex items-center gap-4 text-sm">
          <RouterLink to="/" class="hover:text-brand-accent">Inicio</RouterLink>
          <RouterLink to="/ventas" class="hover:text-brand-accent">Ventas</RouterLink>
          <RouterLink to="/crud/productos" class="hover:text-brand-accent">Productos</RouterLink>
          <RouterLink to="/crud/clientes" class="hover:text-brand-accent">Clientes</RouterLink>
          <RouterLink to="/crud/categorias" class="hover:text-brand-accent">Categorías</RouterLink>
          <RouterLink to="/crud/proveedores" class="hover:text-brand-accent">Proveedores</RouterLink>
          <RouterLink to="/crud/gastos" class="hover:text-brand-accent">Gastos</RouterLink>
          <RouterLink to="/crud/inventario_movimientos" class="hover:text-brand-accent">Inventario</RouterLink>
        </nav>
      </div>
    </header>

    <main class="max-w-6xl w-full mx-auto flex-1 p-4">
      <RouterView />
    </main>
  </div>
</template>
