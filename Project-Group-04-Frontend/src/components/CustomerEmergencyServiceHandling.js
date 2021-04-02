
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
    name: 'services',
    data() {
        return{
            emergencyServices: [],
            newEmergencyService: '',
            errorEmergencyService: '',
            EmergencyResponse: [],

        }
    },

    created: function(){
        // const es = new EmergencyServiceDto('70', 'towing')
        // this.emergencyServices = [es]
        AXIOS.get('/emergencyServices')
        .then(response => {this.emergencyServices = response.data})
        .catch(e => {this.errorEmergencyService = e});
    },
    
    methods: {
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
