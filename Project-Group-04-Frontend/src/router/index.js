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
import CustomerBookableService from '@/components/CustomerBookableService'
import CustomerEmergencyService from '@/components/CustomerEmergencyService'
import OwnerBookableServices from '@/components/OwnerBookableServices'
import OwnerEmergencyServices from '@/components/OwnerEmergencyServices'
import Receipts from '@/components/Receipts'
import LoginPage from '@/components/LoginPage'
import UserSelectPage from '@/components/UserSelectPage'
import EmployeeRegistrationPage from '@/components/EmployeeRegistrationPage'
import CustomerRegistrationPage from '@/components/CustomerRegistrationPage'
import WelcomePage from '@/components/WelcomePage'
import BookAppointment from '@/components/BookAppointment'

import TeamOwner from '@/components/TeamOwner'
import HomeOwner from '@/components/HomeOwner'
import BookableServicesOwner from '@/components/BookableServicesOwner'
import AboutOwner from '@/components/AboutOwner'

import Reminders from '@/components/Reminders'


 

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
      name: 'CustomerBookableService',
      component: CustomerBookableService
    }
    ,
    {
      path: '/emergencyServices',
      name: 'CustomerEmergencyService',
      component: CustomerEmergencyService
    }
    ,

    {
      path: '/owner/bookableServices',
      name: 'OwnerBookableServices',
      component: OwnerBookableServices
    }
    ,
    {
      path: '/owner/emergencyServices',
      name: 'OwnerEmergencyServices',
      component: OwnerEmergencyServices
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
    },
    {

      path: '/homeOwner',
      name: 'HomeOwner',
      component: HomeOwner
    },
  
    {
      path: '/bookableServicesOwner',
      name: 'BookableServicesOwner',
      component: BookableServicesOwner
    },
    {
      path: '/teamOwner',
      name: 'TeamOwner',
      component: TeamOwner
    },
    {
      path: '/aboutOwner',
      name: 'AboutOwner',
      component: AboutOwner
    },
    // {
    //   path: '/businessHoursOwner',
    //   name: 'BusinessHoursOwner',
    //   component: BusinessHoursOwner
    // },
    {
      path: '/reminders',
      name: 'Reminders',
      component: Reminders
    } 
  ]
})
