
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
            this.errorProfile = e
          })
      },

    methods: {
        updateBusinessHour: function(dayOfWeek, startTime, endTime){
            // if (dayOfWeek == 'Monday'){
            //     var businessHour = this.businessHours[0]
            // }
            // if (dayOfWeek == 'Tuesday'){
            //     var businessHour = this.businessHours[1]
            // }
            // if (dayOfWeek == 'Wednesday'){
            //     var businessHour = this.businessHours[2]
            // }
            // if (dayOfWeek == 'Thursday'){
            //     var businessHour = this.businessHours[3]
            // }
            // if (dayOfWeek == 'Friday'){
            //     var businessHour = this.businessHours[4]
            // }
            // if (dayOfWeek == 'Saturday'){
            //     var businessHour = this.businessHours[5]
            // }
            // if (dayOfWeek == 'Sunday'){
            //     var businessHour = this.businessHours[6]
            // }
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
        }

        },

}

