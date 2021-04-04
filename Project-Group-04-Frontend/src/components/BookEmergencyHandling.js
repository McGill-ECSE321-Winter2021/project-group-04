import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



export default {  
    name: 'BookEmergencyHandling',
    data() {
      return {
        userID: '',
        errorUser:'',
        bookedEmergency: '',
        errorBookEmergency: '',
        emergencyServices: [],
        fieldTechnicians:[],
        chosenTechnicianId:'',
        chosenFieldTech:'',
        errorFieldTechnician:'',
        errorChosenTechnicianId:'',

        response: [],
        datePickerIdMin : new Date().toISOString().split("T")[0]
      }
    },

    created: function(){
      AXIOS.get('/emergencyServices')
      .then(response => {this.emergencyServices = response.data})
      .catch(e => {this.errorBookEmergency=e})
      AXIOS.get('/fieldTechnician')
      .then(response => {this.fieldTechnicians = response.data})
        .catch(e => { this.errorBookEmergency=e })
      AXIOS.get('/login/currentCustomer')
        .then(response => {
          this.userID = response.data.userID
          console.log(this.userID)
        }) 
        .catch(e => {this.errorUser=e})
    
    },

methods: {
   

  bookEmergency: function (selectedEmergencyService,  location, selectedFieldTechnician) {
    console.log(location)
    AXIOS.get('/fieldTechnicians/' + selectedFieldTechnician)
      .then(response => {
        this.chosenFieldTech = response.data
        this.chosenTechnicianId = response.data.technicianId
      })
      .catch(e => { this.errorChosenTechnicianId })
      .finally(() => {
          console.log(this.chosenTechnicianId)
        AXIOS.post('/book/emergencyService/' + this.userID +'?serviceName=' +selectedEmergencyService + '&Location=' + location + '&fieldTechnicianId='
          + this.chosenTechnicianId , {}, {})
          .then(response => {
            console.log(selectedEmergencyService)
            this.bookedEmergency = response.data
            swal("Success", "We have received your request and " + selectedFieldTechnician+" is on his way.", "success").then(okay => {
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
