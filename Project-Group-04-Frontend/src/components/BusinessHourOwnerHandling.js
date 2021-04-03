
import axios from 'axios'
import swal from 'sweetalert'
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
            errorBusinessHour: '',
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
            this.errorBusinessHour = e
          })
      },

    methods: {
        updateBusinessHour: function(dayOfWeek, startTime, endTime){
            var i
            for (i =0; i<this.businessHours.length; i++){
                if(dayOfWeek == this.businessHours[i].dayOfWeek){
                    var businessHour = this.businessHours[i]
                }
            }
        if (businessHour === undefined) {
          AXIOS.post('/add/businessHour/' + dayOfWeek + '?startTime=' + startTime + '&endTime=' + endTime, {}, {})
                .then(response => {
                  this.businessHour = response.data
                  swal("Success", "The business hours of " + dayOfWeek + " have been added", "success").then(okay => {
                    if (okay) {
                      location.reload()
                    }
                  })
                })
                .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  swal("Error", e.response.data, "error");
                })
            }
            else {
              const businessHourId = businessHour.id
              AXIOS.patch('/edit/businessHour/' + businessHourId + '?dayOfWeek=' + dayOfWeek + '&startTime=' + startTime + '&endTime=' + endTime, {}, {})
                .then(response => {
                  this.businessHour = response.data
                  swal("Success", "The business Hours of " + dayOfWeek + " have been changed", "success").then(okay => {
                    if (okay) {
                      location.reload()
                    }
                  })
                })
                .catch(e => {
                  var errorMSg = e
                  console.log(errorMSg)
                  swal("Error", e.response.data, "error");
                })
            }
            
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

