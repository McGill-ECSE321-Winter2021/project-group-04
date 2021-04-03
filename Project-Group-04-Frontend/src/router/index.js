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
import BookEmergency from '@/components/BookEmergency'
import FieldTechnicianOwner from '@/components/FieldTechnicianOwner'
import TeamOwner from '@/components/TeamOwner'
import HomeOwner from '@/components/HomeOwner'
import BusinessHoursOwner from '@/components/BusinessHoursOwner'
import AboutOwner from '@/components/AboutOwner'
import HomeAdmin from '@/components/HomeAdmin'
import Reminders from '@/components/Reminders'
import AdminEmergency from'@/components/AdminEmergency'


 

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
      path: '/book/emergency',
      name: 'BookEmergency',
      component: BookEmergency
    },
    {

      path: '/homeOwner',
      name: 'HomeOwner',
      component: HomeOwner
    },
  
    {
      path: '/teamOwner',
      name: 'TeamOwner',
      component: TeamOwner
    },
    {
      path: '/fieldTechnicianOwner',
      name: 'FieldTechnicianOwner',
      component: FieldTechnicianOwner
    },
    {
      path: '/aboutOwner',
      name: 'AboutOwner',
      component: AboutOwner
    },
    {
      path: '/businessHoursOwner',
      name: 'BusinessHoursOwner',
      component: BusinessHoursOwner
    },
    {
      path: '/reminders',
      name: 'Reminders',
      component: Reminders
    },
    {
      path: '/homeAdmin',
      name: 'HomeAdmin',
      component: HomeAdmin
    },
    {
      path: '/admin/emergencyServices',
      name: 'AdminEmergency',
      component: AdminEmergency
    }    
  ]
})
