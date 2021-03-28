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
        receipts: [],
        errorReceipt: '',
        response: []
      }
    },
    created: function () {
            // Test data
            const p1 = new ReceiptDto(30)
            const p2 = new ReceiptDto(70)
            // Sample initial content
            this.receipts = [p1, p2]
          }
      }
