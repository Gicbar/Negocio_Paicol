import { createRouter, createWebHistory } from 'vue-router'
import Home from './views/Home.vue'
import Crud from './views/Crud.vue'
import Ventas from './views/Ventas.vue'

const routes = [
  { path: '/', name: 'home', component: Home },
  { path: '/crud/:entity', name: 'crud', component: Crud, props: true },
  { path: '/ventas', name: 'ventas', component: Ventas },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})
