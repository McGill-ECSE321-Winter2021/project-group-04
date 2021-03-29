import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import AutoRepairShop from '@/components/AutoRepairShop'
import Home from '@/components/Home'
import Car from '@/components/Car'
import AboutUs from '@/components/AboutUs'
import BusinessHours from '@/components/BusinessHours'
import Profile from '@/components/Profile'
import Team from '@/components/Team'
import BookableServices from '@/components/BookableServices'
import EmergencyServices from '@/components/EmergencyServices'
import Receipts from '@/components/Receipts'
import LoginPage from '@/components/LoginPage'
import UserSelectPage from '@/components/UserSelectPage'
import EmployeeRegistrationPage from '@/components/EmployeeRegistrationPage'
import CustomerRegistrationPage from '@/components/CustomerRegistrationPage'
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
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/register',
      name: 'UserSelectPage',
      component: UserSelectPage
    },
    {
      path: '/register/employee',
      name: 'EmployeeRegistrationPage',
      component: EmployeeRegistrationPage
    },
    {
      path: '/register/customer',
      name: 'CustomerRegistrationPage',
      component: CustomerRegistrationPage
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
      path: '/businessHours',
      name: 'BusinessHours',
      component: BusinessHours
    },
    {
      path: '/team',
      name: 'Team',
      component: Team
    },
    {
      path: '/bookableServices',
      name: 'BookableServices',
      component: BookableServices
    }
    ,

    {
      path: '/emergencyServices',
      name: 'EmergencyServices',
      component: EmergencyServices
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
