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
                    this.errorProfile = ""
                    this.profile = response.data
                    swal("Success", "Registration Successful", "success").then(okay =>{
                        if(okay){
                            this.$router.push('/homeOwner')
                        }
                    })
                })          
                .catch(e => {
                
                    var errMsg = e
                    console.log(errMsg)
                    this.errorProfile = errMsg
                    swal("ERROR", e.response.data, "error")
                });    
              }

              else if (userId.localeCompare("admin") == 0){
                AXIOS.post('/register/administrativeAssistant/'+userId+'?password='+password,{},{})
                .then(response => {
                    this.errorProfile = ""
                    this.profile = response.data
                    swal("Success", "Registration Successful", "success").then(okay =>{
                        if(okay){
                            this.$router.push('/homeAdmin')
                        }
                    })
                    
                })
                .catch(e => {
                    var errMsg = e
                    console.log(errMsg)
                    this.errorProfile = errMsg
                    swal("ERROR", e.response.data, "error")
                });
              }

              else {
                  swal("ERROR", "You are not an employee!", "error")
              }
              
          }
    
      }
  }
