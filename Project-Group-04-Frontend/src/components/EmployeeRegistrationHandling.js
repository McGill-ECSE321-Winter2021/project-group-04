import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })

  export default {
      name: 'EmployeeRegistrationPage',

      data() {
          return {
              userId: '',
            password: ''
          }
        },
      

      methods: {
          registerEmployee: function(userId, password){
              
              if (userId.localeCompare("owner") == 0){
                
                AXIOS.post('/register/owner/'+userId+'?password='+password,{},{})
                .then(response => {
                    window.alert(userId)
                    this.$router.push('Home') // needs to be changed
                })          
                .catch(e => {
                    var errMsg = e.response.data.message
                    window.alert(errMsg)
                });    
              }

              else if (userId.localeCompare("admin") == 0){
                AXIOS.post('/register/administrativeAssistant/'+userId+'?password='+password,{},{})
                .then(response => {
                    this.$router.push('Home')  // needs to be changed
                })
                .catch(e => {
                    var errMsg = e.response.data.message
                    window.alert(errMsg)
                });
              }

              else {
                  window.alert("You are not an employee!")
              }
              
          }
    
      }
  }
