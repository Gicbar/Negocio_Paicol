<script setup>
import { ref, computed } from 'vue'
import Swal from 'sweetalert2'
import { api } from '../api'

const barcode = ref('')
const cart = ref([]) // { producto, cantidad }
const idCliente = ref(-1)
const metodoPago = ref('EFECTIVO')

async function addByBarcode() {
  if (!barcode.value) return
  try {
    const { data: p } = await api.get(`/productos/barcode/${encodeURIComponent(barcode.value)}`)
    const idx = cart.value.findIndex(i => i.producto.id === p.id)
    if (idx >= 0) cart.value[idx].cantidad += 1
    else cart.value.push({ producto: p, cantidad: 1 })
    barcode.value = ''
  } catch (e) {
    Swal.fire('Producto no encontrado', 'Verifica el código de barras', 'error')
  }
}

function changeQty(item, delta) {
  item.cantidad = Math.max(1, item.cantidad + delta)
}

const total = computed(() => cart.value.reduce((s, i) => s + Number(i.producto.precio) * i.cantidad, 0))

async function procesarVenta() {
  if (!cart.value.length) return
  const payload = {
    idCliente: idCliente.value >= 0 ? idCliente.value : null,
    metodoPago: metodoPago.value,
    items: cart.value.map(i => ({ idProducto: i.producto.id, cantidad: i.cantidad })),
  }
  const { data } = await api.post('/ventas', payload)
  await Swal.fire('Venta registrada', `Venta #${data.id} - Total: $${data.total}`, 'success')
  cart.value = []
}
</script>

<template>
  <section class="space-y-4">
    <h2 class="text-2xl font-bold">Ventas</h2>

    <div class="bg-white border rounded-xl p-4 grid gap-3">
      <div class="grid sm:grid-cols-3 gap-3">
        <div>
          <label class="text-sm text-gray-600">Cliente (ID o -1 anónimo)</label>
          <input v-model.number="idCliente" type="number" class="w-full mt-1 px-3 py-2 border rounded" />
        </div>
        <div>
          <label class="text-sm text-gray-600">Método de pago</label>
          <select v-model="metodoPago" class="w-full mt-1 px-3 py-2 border rounded">
            <option>EFECTIVO</option>
            <option>TRANSFERENCIA</option>
            <option>TARJETA</option>
          </select>
        </div>
        <div>
          <label class="text-sm text-gray-600">Código de barras</label>
          <input v-model="barcode" @keyup.enter="addByBarcode" placeholder="Escanea o escribe y Enter" class="w-full mt-1 px-3 py-2 border rounded" />
        </div>
      </div>

      <div class="overflow-x-auto">
        <table class="min-w-full text-sm">
          <thead>
            <tr class="text-left text-gray-600">
              <th class="py-2">Producto</th>
              <th class="py-2">Precio</th>
              <th class="py-2">Cantidad</th>
              <th class="py-2">Subtotal</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in cart" :key="item.producto.id" class="border-t">
              <td class="py-2">{{ item.producto.nombre }}</td>
              <td class="py-2">${{ Number(item.producto.precio).toFixed(2) }}</td>
              <td class="py-2">
                <div class="inline-flex items-center gap-2">
                  <button class="px-2 border rounded" @click="changeQty(item, -1)">-</button>
                  <span class="w-8 text-center">{{ item.cantidad }}</span>
                  <button class="px-2 border rounded" @click="changeQty(item, 1)">+</button>
                </div>
              </td>
              <td class="py-2">${{ (Number(item.producto.precio) * item.cantidad).toFixed(2) }}</td>
              <td class="py-2 text-right">
                <button class="px-2 text-red-600" @click="cart = cart.filter(i => i !== item)">Quitar</button>
              </td>
            </tr>
            <tr v-if="!cart.length">
              <td colspan="5" class="text-center text-gray-500 py-6">Sin productos</td>
            </tr>
          </tbody>
          <tfoot>
            <tr class="border-t font-semibold">
              <td colspan="3" class="py-3 text-right">Total</td>
              <td class="py-3">${{ total.toFixed(2) }}</td>
              <td></td>
            </tr>
          </tfoot>
        </table>
      </div>

      <div class="flex justify-end">
        <button :disabled="!cart.length" class="px-4 py-2 rounded bg-brand-accent text-white disabled:opacity-50" @click="procesarVenta">Procesar venta</button>
      </div>
    </div>
  </section>
</template>
