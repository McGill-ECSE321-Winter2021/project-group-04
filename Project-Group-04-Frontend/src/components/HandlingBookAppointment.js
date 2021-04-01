import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



export default {  
    name: 'HandlingBookAppointment',
    data() {
      return {
        userID: '',
        errorUser:'',
        appointments: '',
        errorBookAppointment: '',
        bookableServices: [],
        garageTechnicians:[],
        chosenTechnicianId:'',
        chosenGarageTech:'',
        errorGarageTechnician:'',
        errorChosenTechnicianId:'',

        response: [],
        datePickerIdMin : new Date().toISOString().split("T")[0]
      }
    },

    created: function(){
      AXIOS.get('/bookableServices')
      .then(response => {this.bookableServices = response.data})
      .catch(e => {this.errorBookAppointment=e})
      AXIOS.get('/garageTechnicians')
      .then(response => {this.garageTechnicians = response.data})
        .catch(e => { this.errorBookAppointment=e })
      AXIOS.get('/login/currentCustomer')
        .then(response => { this.userID = response.data.userID })
        .catch(e => {this.errorUser=e})
    
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

        AXIOS.post('/book/appointment/' + this.userID + selectedService + '?date=' + date + '&garageSpot='
          + garageSpot + '&startTime=' + time + '&Garage Technician Id=' + this.chosenTechnicianId, {}, {})
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
