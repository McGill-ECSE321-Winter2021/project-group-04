import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })

  export default {
      name: 'LoginPage',

      data() {
          return {
              userId: '',
            password: ''
          }
        },
      

      methods: {
          loginUser: function(userId, password){
              AXIOS.post('/login/'+userId+'?password='+password,{},{})
              .then(response => {
                  if(userId.localeCompare("Owner") == 0){
                      this.$router.push('/homeOwner')    // need to change to owner homepage
                  }

                  else if(userId.localeCompare("Admin") == 0){
                    this.$router.push('/homeAdmin')      // need to change to owner homepage
                  }

                  else{
                    this.$router.push('/home')
                  }
              })
              .catch(e => {
                var errMsg = e.response.data.message
                window.alert("Please register an account")
              });
          },
      }
  }
