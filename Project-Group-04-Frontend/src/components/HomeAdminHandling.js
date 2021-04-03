import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}

export default {
  name: 'homeOwnerHandling',
  data() {
    return {
      appointments: [],
      errorAppointments:'',
      response: []
    }
  },
  
  methods:{
   showAppointment: function(date){
    
   AXIOS.get('/appointments/date/' + date)
    .then(response => { 
      console.log(date)
      this.appointments = response.data
      
    })
    .catch(e => { this.errorAppointments = e })
   
   
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
  //...


