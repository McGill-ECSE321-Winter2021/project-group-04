import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function EmergencyServiceDto(price, name, location){
    this.price = price;
    this.name = name;
    this.location = location;
}

export default {
    name: 'emergencyServiceHandling',
    data () {
      return {
        emergencyService: '',
        price: '',
        name: '',
        location: '',
        emergencyServiceId: '',
        erroremergencyService:'',
        response: []
      }
    },
    created: function () {
      AXIOS.get('/emergencyService/'.concat(emergencyServiceId))
      .then(response => {
        this.emergencyService = response.data
        this.location = emergencyService.location
        this.price = emergencyService.price
        this.name = emergencyService.name
      })
      .catch(e => {
        this.erroremergencyService = e
      })
    },
    methods: {
      editemergencyService: function (price, name, location){
        emergencyService.location = location
        emergencyService.price = price
        emergencyService.name = name
        AXIOS.patch('/edit/emergencyService/'.concat(emergencyServiceId), {},
        {
          params: {
            "location" : location,
            "Price": price,
            "name": name
          }
        })
        .then(response => {
          
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.erroremergencyService = errorMsg
        })
      }
    }
  }