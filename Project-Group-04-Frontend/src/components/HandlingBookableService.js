
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function BookableServiceDto(duration, price, name){
    this.duration = duration;
    this.price = price;
    this.name = name;
    // this.bookableServiceId = bookableServiceId;
}

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'services',
    data() {
        return{
            bookableServices: [],
            newBookableService: '',
            errorBookableService: '',
            bookableResponse: [],
        }
    },

    created: function(){
        //test data
        const bookableService1 = new BookableServiceDto('0:10:00', '$20', 'Oil change')
        const bookableService2 = new BookableServiceDto('1:00:00', '$50', 'Car wash')
        //sample initial content
        this.bookableServices = [bookableService1, bookableService2]
    }, 

    methods: {
        createBookableService: function(duration, price, name){
            var b = new BookableServiceDto(duration, price, name)
            this.bookableServices.push(b)
            this.newBookableService = ''
        },

        deleteBookableService: function(bookableService){
            this.bookableServices.pop(bookableService)
        },

        updateBookableService: function(bookableService, duration, price, name){
          bookableService.duration = duration
          bookableService.price = price
          bookableService.name = name
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

// function BookableServiceDto(duration, price, name, bookableServiceId){
//     this.duration = duration;
//     this.price = price;
//     this.name = name;
//     this.bookableServiceId = bookableServiceId;
// }

// export default {
//   name: 'bookableServiceHandling',
//   data () {
//     return {
//       bookableService: '',
//       duration: '',
//       price: '',
//       name: '',
//       bookableServiceId: '',
//       errorBookableService:'',
//       response: []
//     }
//   },
//   created: function () {
//     AXIOS.get('/bookableService/'.concat(bookableServiceId))
//     .then(response => {
//       this.bookableService = response.data
//       this.duration = bookableService.duration
//       this.price = bookableService.price
//       this.name = bookableService.name
//     })
//     .catch(e => {
//       this.errorBookableService = e
//     })
//   },
//   methods: {
//     editBookableService: function (duration, price, name){
//       bookableService.duration = duration
//       bookableService.price = price
//       bookableService.name = name
//       AXIOS.patch('/edit/bookableService/'.concat(bookableServiceId), {},
//       {
//         params: {
//           "Duration" : duration,
//           "Price": price,
//           "name": name
//         }
//       })
//       .then(response => {
        
//       })
//       .catch(e => {
//         var errorMsg = e
//         console.log(errorMsg)
//         this.errorBookableService = errorMsg
//       })
//     }
//   }
// }