
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function EmergencyServiceDto(price, name){
    this.price = price;
    this.name = name;
}

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'services',
    data() {
        return{
            emergencyServices: [],
            newEmergencyService: '',
            errorEmergencyService: '',
            EmergencyResponse: [],

        }
    },

    created: function(){
        //test data
        const emergencyService1 = new EmergencyServiceDto('$50', 'Towing')
        //sample initial content
        this.emergencyServices = [emergencyService1]
    }, 

    methods: {
        createEmergencyService: function(price, name){
            var e = new emergencyServiceDto(price, name)
            this.emergencyServicesServices.push(e)
            this.newEmergencyService = ''
        },

        deleteEmergencyService: function(emergencyService){
            this.emergencyServices.pop(emergencyService)
        },

        updateEmergencyService: function(emergencyService, price, name){
          emergencyService.price = price
          emergencyService.name = name
        }

    }
}


// import axios from 'axios'
// var config = require('../../config')

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

// var AXIOS = axios.create({
//   baseURL: backendUrl,
//   headers: { 'Access-Control-Allow-Origin': frontendUrl }
// })

// function EmergencyServiceDto(price, name, location){
//     this.price = price;
//     this.name = name;
//     this.location = location;
// }

// export default {
//     name: 'emergencyService',
//     data () {
//       return {
//         emergencyService: '',
//         price: '50$',
//         name: 'Towing',
//         location: '',
//         emergencyServiceId: '',
//         erroremergencyService:'',
//         response: []
//       }
//     },

//     created: function () {
//       AXIOS.get('/emergencyService/'.concat(emergencyServiceId))
//       .then(response => {
//         this.emergencyService = response.data
//         this.location = emergencyService.location
//         this.price = emergencyService.price
//         this.name = emergencyService.name
//       })
//       .catch(e => {
//         this.erroremergencyService = e
//       })
//     },

//     methods: {
//       editemergencyService: function (price, name, location){
//         emergencyService.location = location
//         emergencyService.price = price
//         emergencyService.name = name
//         AXIOS.patch('/edit/emergencyService/'.concat(emergencyServiceId), {},
//         {
//           params: {
//             "location" : location,
//             "Price": price,
//             "name": name
//           }
//         })
//         .then(response => {
          
//         })
//         .catch(e => {
//           var errorMsg = e
//           console.log(errorMsg)
//           this.erroremergencyService = errorMsg
//         })
//       }
//     }
//   }