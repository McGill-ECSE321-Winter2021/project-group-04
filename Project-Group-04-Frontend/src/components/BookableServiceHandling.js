
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
                // location.reload()
                swal("Success", "Bookable service was succesfully added", "success").then(okay => {
                    if (okay) {
                      this.$router.go('/Home')
                    }
                })
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorBookableService = errorMSg
                swal("ERROR", e.response.data, "error")
            })
          },
  
          deleteBookableService: function(bookableService){
              const id = bookableService.id
              AXIOS.delete('/delete/bookableService/' + id , {}, {})
              .then(response => {
                  this.bookableServices.pop(response.data)
                  swal("Success", "Bookable service was succesfully deleted", "success").then(okay => {
                    if (okay) {
                      this.$router.go('/Home')
                    }
                })
              })
              .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  this.errorBookableService = errorMSg
                  swal("ERROR", e.response.data, "error")
              })
          },
  
          updateBookableService: function(bookableService, newName, newDuration, newPrice){
              const id = bookableService.id
              AXIOS.patch('/edit/bookableService/' + id + '?name=' + newName + '&duration=' + newDuration +'&price=' + newPrice, {}, {})
              .then(response => {
                  this.bookableService = response.data
                  swal("Success", "Bookable service was succesfully updated", "success").then(okay => {
                    if (okay) {
                      this.$router.go('/Home')
                    }
                })
                  location.reload()
              })
              .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  this.errorbookableService = errorMSg
                  swal("ERROR", e.response.data, "error")
              })
          },

          logout: function () {
            AXIOS.post('/logout', {}, {})
              .then(response => {
                this.errorProfile = ""
                this.profile = response.data
                swal("Success", "You have been logged out successfully", "success").then(okay => {
                  this.$router.push('/')
                })
              })
              .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorProfile = errorMsg
                swal("ERROR", e.response.data, "error")
              })
          }
  
      }
}