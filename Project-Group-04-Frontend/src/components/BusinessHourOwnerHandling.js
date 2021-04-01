
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
            const businessHourId = businessHour.id
            AXIOS.patch('/edit/businessHour/' + businessHourId + '?dayOfWeek=' + dayOfWeek + '&startTime=' + startTime + '&endTime=' + endTime, {}, {})
            .then(response=> {
                this.businessHour = response.data
            })
            .catch(e =>{
                var errorMSg = e
                console.log(errorMSg)
                this.errorBusinessHour = errorMSg
                window.alert(e)
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

