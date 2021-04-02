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
  name: 'BookAppointmentHandling',
  data() {
    return {
      userID: '',
      errorUser: '',
      appointments: [],
      errorBookAppointment: '',
      // bookableServices: [],
      bookableServices: [],
      garageTechnicians:[],
      chosenTechnicianId:'',
      chosenGarageTech:'',
      errorGarageTechnician:'',
      errorChosenTechnicianId:'',

      response: [],
      datePickerIdMin: new Date().toISOString().split("T")[0]
    }
  },

  created: function () {
    AXIOS.get('/bookableServices')
    .then(response => {this.bookableServices = response.data})
    .catch(e => {this.errorBookAppointment=e})
    AXIOS.get('/garageTechnicians')
    .then(response => {this.garageTechnicians = response.data})
      .catch(e => { this.errorBookAppointment=e })
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
    bookAppointment: function (selectedService, date, time, garageSpot, selectedGarageTechnician) {
      AXIOS.get('/garageTechnicians/' + selectedGarageTechnician)
        .then(response => {
          this.chosenGarageTech = response.data
          this.chosenTechnicianId = response.data.technicianId
        })
        .catch(e => { this.errorChosenTechnicianId })
        .finally(() => {
            console.log(this.userID)
          AXIOS.post('/book/appointment/' +"mohamad1"+ "?serviceName="+ selectedService + '&date=' + date + '&garageSpot='
            + garageSpot + '&startTime=' + time + '&garageTechnicianId=' + this.chosenTechnicianId, {}, {})
            .then(response => {
              console.log(selectedService)
              this.appointments = response.data
              swal("Success", "Your appointment has successfuly been booked", "success").then(okay => {
                if (okay) {
                  this.$router.go('/Home')
                }
              })
  
            })
            .catch(e => {
              var errMsg = e
              swal("ERROR", e.response.data, "error");
            });
  
        })
    },



    cancelAppointment: function (appointmentId) {
      // Create a new person and add it to the list of people

      AXIOS.delete('/cancel/appointment/' + appointmentId,
        {}, {}
      )
        .then(response => {
          // Update appropriate DTO collections

          this.errorProfile = ""
          this.appointments.pop(response.data)
          swal("Success", "Your appointment has successfuly been canceled", "success").then(okay => {
            if (okay) {
              this.$router.go('/Home')
            }
          })

        })
        .catch(e => {
          console.log(e.response)
          swal("Error",e.response.data,"error");
        })
      // Reset the name field for new people
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

