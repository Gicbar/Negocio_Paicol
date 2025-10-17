<script setup>
import { ref, watch, computed } from 'vue'
import { api } from '../api'
import { useRoute } from 'vue-router'

const route = useRoute()
const entity = computed(() => route.params.entity)
const items = ref([])
const loading = ref(false)
const editing = ref(null)

const endpoints = {
  productos: '/productos',
  clientes: '/clientes',
  categorias: '/categorias',
  proveedores: '/proveedores',
  gastos: '/gastos',
  inventario_movimientos: '/inventario_movimientos',
}

async function load() {
  loading.value = true
  try {
    const { data } = await api.get(endpoints[entity.value])
    items.value = data
  } finally { loading.value = false }
}

watch(entity, load, { immediate: true })

function startNew() { editing.value = {} }
function startEdit(row) { editing.value = { ...row } }
async function remove(id) {
  if (!confirm('¿Eliminar?')) return
  await api.delete(`${endpoints[entity.value]}/${id}`)
  await load()
}
async function save() {
  const body = editing.value
  if (body.id) {
    await api.put(`${endpoints[entity.value]}/${body.id}`, body)
  } else {
    await api.post(endpoints[entity.value], body)
  }
  editing.value = null
  await load()
}
</script>

<template>
  <section>
    <div class="flex items-center justify-between mb-4">
      <h2 class="text-2xl font-bold capitalize">{{ entity }}</h2>
      <button class="px-3 py-2 bg-brand-accent text-white rounded" @click="startNew">Nuevo</button>
    </div>

    <div v-if="loading">Cargando…</div>

    <div v-else class="grid gap-2">
      <div v-for="row in items" :key="row.id" class="bg-white border rounded-lg p-3 flex items-center justify-between">
        <pre class="text-xs text-gray-700 overflow-x-auto">{{ row }}</pre>
        <div class="flex gap-2">
          <button class="px-3 py-1 border rounded" @click="startEdit(row)">Editar</button>
          <button class="px-3 py-1 border rounded text-red-600" @click="remove(row.id)">Eliminar</button>
        </div>
      </div>
    </div>

    <div v-if="editing" class="fixed inset-0 bg-black/40 grid place-items-center p-4">
      <div class="bg-white rounded-xl p-4 w-full max-w-xl space-y-3">
        <h3 class="font-semibold">{{ editing.id ? 'Editar' : 'Nuevo' }} {{ entity }}</h3>
        <textarea v-model="editingJson" class="hidden"></textarea>
        <div class="text-sm text-gray-500">Edición rápida: modifica los campos en JSON.</div>
        <pre class="text-xs bg-gray-50 border rounded p-3" contenteditable @input="editing.value = JSON.parse($event.target.innerText)">{{ JSON.stringify(editing, null, 2) }}</pre>
        <div class="flex justify-end gap-2">
          <button class="px-3 py-1 border rounded" @click="editing=null">Cancelar</button>
          <button class="px-3 py-1 bg-brand-accent text-white rounded" @click="save">Guardar</button>
        </div>
      </div>
    </div>
  </section>
</template>
