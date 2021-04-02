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
        this.$router.push('/')

      })
      .catch(e => {
        var errMsg = e.response.data.message
        window.alert(e)
      })
  }
}
 
  }
  //...


