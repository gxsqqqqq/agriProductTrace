import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: () => import('@/views/Dashboard.vue')
  },
  {
    path: '/products',
    name: 'Products',
    component: () => import('@/views/Products.vue')
  },
  {
    path: '/batch',
    name: 'Batch',
    component: () => import('@/views/Batch.vue')
  },
  {
    path: '/trace',
    name: 'Trace',
    component: () => import('@/views/Trace.vue')
  },
  {
    path: '/activity',
    name: 'Activity',
    component: () => import('@/views/Activity.vue')
  },
  {
    path: '/welfare',
    name: 'Welfare',
    component: () => import('@/views/Welfare.vue')
  },
  {
    path: '/evaluate',
    name: 'Evaluate',
    component: () => import('@/views/Evaluate.vue')
  },
  {
    path: '/alarm',
    name: 'Alarm',
    component: () => import('@/views/Alarm.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
