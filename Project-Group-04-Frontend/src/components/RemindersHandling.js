import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ReceiptDto(totalPrice) {
  this.totalPrice = totalPrice
}

function AppointmentDto(date, service, receipt) {
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
      errorUsr: '',
      appointments: [],
      errorReminder: '',
      response: []
    }
  },
  created: function () {
    // Test data
    AXIOS.get('/login/currentCustomer')
      .then(response => { this.userID = response.data.userID })
      .catch(e => { this.errorUser = e })
      .finally(() => {
        AXIOS.get('/appointments/next/24Hours/' + this.userID)
          .then(response => {
            // JSON responses are automatically parsed.
            this.appointments = response.data
          })
          .catch(e => {
            this.errorReminder = e
          })
      })

  },

  methods: {
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
