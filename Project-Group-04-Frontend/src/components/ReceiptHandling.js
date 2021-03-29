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
        appointments: [],
        errorReceipt: '',
        response: []
      }
    },
    created: function () {
            // Test data
          const r1 = new ReceiptDto('30')
          const r2 = new ReceiptDto('20')
            const a1 = new AppointmentDto('2021-02-21', 'oil',r1)
            const a2 = new AppointmentDto('2022-02-21', 'tire change',r2)
            // Sample initial content
           
            this.appointments = [a1,a2]
          }
      }
