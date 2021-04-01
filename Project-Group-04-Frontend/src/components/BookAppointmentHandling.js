import axios from 'axios'
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
  name: 'BookAppointmentHandling',
  data() {
    return {
      userID: '',
      errorUser: '',
      appointments: [],
      errorBookAppointment: '',
      // bookableServices: [],

      response: [],
      datePickerIdMin: new Date().toISOString().split("T")[0]
    }
  },

  created: function () {

    AXIOS.get('/login/currentCustomer')
      .then(response => {
        this.userID = response.data.userID;
      })
      .catch(e => { this.errorUser = e })
      .finally(() => {
        AXIOS.get('/appointments/next/' + this.userID)
          .then(response => {
            this.appointments = response.data
          })
          .catch(e => {
            this.errorBookAppointment = e
          })
      })

  },
  methods: {
    cancelAppointment: function (appointmentId) {
      // Create a new person and add it to the list of people

      AXIOS.delete('/cancel/appointment/' + appointmentId,
        {}, {}
      )
        .then(response => {
          // Update appropriate DTO collections

          this.errorProfile = ""
          this.appointments.pop(response.data)
          // this.appointment = response.data

        })
        .catch(e => {
          var errorMsg = e
          this.errorProfile = errorMsg
          window.alert(e);
        })
      // Reset the name field for new people
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

