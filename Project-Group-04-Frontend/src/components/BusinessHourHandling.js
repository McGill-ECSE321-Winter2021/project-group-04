
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function BusinessHourDto(day, openningHour){
    this.day = day;
    this.openningHour = openningHour;
}

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'services',
    data() {
        return{
            businessHours: [],
            days: '',
            startTime: '',
            endTime: '',
            response: []
        }
    },

    created: function () {
        AXIOS.get('/businessHours/')
          .then(response => {
            // JSON responses are automatically parsed.
            this.businessHours = response.data
            this.businessHourId = this.business.businessHourId
          })
          .catch(e => {
            this.errorProfile = e
          })
      },

    methods: {
        createBusinessHour: function(days, openningHours){
            var e = new BusinessHourDto(days, openningHours)
            this.businessHourServices.push(e)
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
