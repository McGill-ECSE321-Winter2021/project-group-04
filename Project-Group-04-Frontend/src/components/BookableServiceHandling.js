
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function BookableServiceDto(duration, price, name){
    this.duration = duration;
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
                this.$router.go('/Home')
                // location.reload()
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorBookableService = errorMSg
                window.alert(errorMSg)
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
  
          updateBookableService: function(bookableService, newName, newDuration, newPrice){
              const id = bookableService.id
              AXIOS.patch('/edit/bookableService/' + id + '?name=' + newName + '&duration=' + newDuration +'&price=' + newPrice, {}, {})
              .then(response => {
                  this.bookableService = response.data
                  location.reload()
              })
              .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  this.errorbookableService = errorMSg
                  window.alert(errorMSg)
              })
          },

          logout: function () {
            AXIOS.post('/logout', {}, {})
            .then(response => {
                this.$router.push('/')
      
              })
              .catch(e => {
                var errMsg = e.response.data.message
                window.alert(e)
              })
          }
  
      }
}