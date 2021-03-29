
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
        AXIOS.get('/bookableServices')
        .then(response => {this.bookableServices = response.data})
        .catch(e => {this.errorBookableService = e});
    }, 

    methods: {
        createBookableService: function(price, name, duration){
            AXIOS.post('/create/bookableService/' + name + '?duration=' + duration + '&price=' + price, {}, {})
            .then(response => {
              //   this.bookableService = response.data.bookableService
                this.bookableServices.push(response.data)
                this.errorBookableService = ''
                this.newBookableService = ''
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorBookableService = errorMSg
                window.alert(e)
            })
          },
  
          deleteBookableService: function(bookableService){
              const id = bookableService.id
              AXIOS.delete('/delete/bookableService/' + id , {}, {})
              .then(response => {
                  this.bookableServices.pop(response.data)
              })
              .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  this.errorBookableService = errorMSg
                  window.alert(e)
              })
          },
  
          updateBookableService: function(bookableService){
              const id = bookableService.id
              AXIOS.patch('/edit/bookableService/' + id + '?name=' + newName + '&duration=' + newDuration +'&price=' + newPrice, {}, {})
              .then(response => {
                  this.bookableService = response.data
              })
              .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  this.errorbookableService = errorMSg
                  window.alert(e)
              })
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