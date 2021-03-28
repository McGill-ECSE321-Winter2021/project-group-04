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
import Receipts from '@/components/Receipts'
import Login from '@/components/Login'
import Register from '@/components/Register'
import WelcomePage from '@/components/WelcomePage'
import BookAppointment from '@/components/BookAppointment'





Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: WelcomePage
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
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
    ,
    {
      path: '/receipts',
      name: 'Receipts',
      component: Receipts
    },
    {
      path: '/book',
      name: 'Booking',
      component: BookAppointment
    }
  ]
})
