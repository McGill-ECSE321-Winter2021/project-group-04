import axios from 'axios'
import swal from 'sweetalert'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TimeSlotDto(startDate, startTime, endDate, endTime, garageSpot) {
  this.startDate = startDate
  this.startTime = startTime
  this.endDate = endDate
  this.endTime = endTime
  this.garageSpot = garageSpot
}

function BookableServiceDto(duration, price, name) {
  this.duration = duration
  this.price = price
  this.name = name
}

function ReceiptDto(totalPrice) {
  this.totalPrice = totalPrice
}

function AppointmentDto(timeSlot, bookableService, receipt) {
  this.timeSlot = timeSlot
  this.bookableService = bookableService
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
  name: 'AdminEmergencyHandling',
  data() {
    return {
      userID: '',
      errorUser: '',
      bookedEmergency: [],
      errorBookedEmergency: '',
     
      chosenTechnicianId:'',
      chosenGarageTech:'',
      errorGarageTechnician:'',
      errorChosenTechnicianId:'',

      response: [],
      datePickerIdMin: new Date().toISOString().split("T")[0]
    }
  },

  created: function () {

        AXIOS.get('/onGoingEmergencies/')
          .then(response => {
            this.bookedEmergency = response.data
          })
          .catch(e => {
            this.errorBookedEmergency = e
          })
  

  },
  methods: {
   endEmergency:function(serviceId){
       AXIOS.post('/end/emergencyService/' + serviceId)
       .then(response => {
        this.$router.go('/Home')
       })
         .catch(e => {
           this.errorBookedEmergency = e;
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

