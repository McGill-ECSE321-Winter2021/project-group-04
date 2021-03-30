import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ReceiptDto(totalPrice){
  this.totalPrice = totalPrice
}

function AppointmentDto(date, service, receipt){
  this.date = date
  this.service = service
  this.receipt = receipt
}

function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  }

export default {
    name: 'receiptHandling',
    data() {
      return {
        userID: '',
        errorUsr:'',
        appointments: [],
        errorReceipt: '',
        response: []
      }
    },
    created: function () {
            // Test data
      AXIOS.get('/login/currentCustomer')
        .then(response => { this.userID = response.data.userID })
        .catch(e => { this.errorUser = e })
        .finally(() => {
          AXIOS.get('/appointments/' + this.userID)
          .then(response => {
            // JSON responses are automatically parsed.
            this.appointments = response.data
          })
          .catch(e => {
            this.errorReceipt = e
          })
        })
      
          }
      }
