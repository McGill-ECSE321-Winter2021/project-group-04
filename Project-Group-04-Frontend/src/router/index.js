import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import AutoRepairShop from '@/components/AutoRepairShop'
import Home from '@/components/Home'
import Car from '@/components/Car'
import AboutUs from '@/components/AboutUs'
import Profile from '@/components/Profile'
import Team from '@/components/Team'
import Services from '@/components/Services'




Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'AutoRepairShop',
      component: AutoRepairShop
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/car',
      name: 'Car',
      component: Car
    },
    {
      path: '/profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/about',
      name: 'AboutUs',
      component: AboutUs
    },
    {
      path: '/team',
      name: 'Team',
      component: Team
    },
    {
      path: '/services',
      name: 'Services',
      component: Services
    }
  ]
})
