
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
        }
    },

    created: function(){
        //test data
        const businessHourMonday = new BusinessHourDto('Monday', '08:00 a.m. - 06:00 p.m.')
        const businessHourTuesday = new BusinessHourDto('Tuesday', '08:00 a.m. - 06:00 p.m.')
        const businessHourWednesday = new BusinessHourDto('Wednesday', '08:00 a.m. - 06:00 p.m.')
        const businessHourThursday = new BusinessHourDto('Thursday', '08:00 a.m. - 06:00 p.m.')
        const businessHourFriday = new BusinessHourDto('Friday', '08:00 a.m. - 08:00 p.m.')
        const businessHourSatruday = new BusinessHourDto('Saturday', '09:00 a.m. - 08:00 p.m.')
        const businessHourSunday = new BusinessHourDto('Sunday', 'Closed')
        //sample initial content
        this.businessHours = [businessHourMonday, 
            businessHourTuesday,
            businessHourWednesday,
            businessHourThursday,
            businessHourFriday,
            businessHourSatruday,
            businessHourSunday]
    }, 

    methods: {
        createBusinessHour: function(days, openningHours){
            var e = new BusinessHourDto(days, openningHours)
            this.businessHourServices.push(e)
        },

}
}
