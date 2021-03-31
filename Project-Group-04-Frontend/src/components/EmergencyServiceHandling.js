
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function EmergencyServiceDto(price, name){
    this.price = price;
    this.name = name;
}

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
        createEmergencyService: function(price, name){
          AXIOS.post('/create/emergencyService/' + name + '?price=' + price, {}, {})
          .then(response => {
            //   this.emergencyService = response.data.emergencyService
              this.emergencyServices.push(response.data)
              this.errorEmergencyService = ''
              this.newEmergencyService = ''
          })
          .catch(e => {
              var errorMSg = e
              console.log(errorMSg)
              this.errorEmergencyService = errorMSg
              window.alert(e)
          })
        },

        deleteEmergencyService: function(emergencyService){
            const id = emergencyService.id
            AXIOS.delete('/delete/emergencyService/' + id , {}, {})
            .then(response => {
                this.emergencyServices.pop(response.data)
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorEmergencyService = errorMSg
                window.alert(e)
            })
        },

        updateEmergencyService: function(emergencyService, newName, newPrice){
            const id = emergencyService.id
            AXIOS.patch('/edit/emergencyService/' + id + '?name=' + newName + '&price=' + newPrice, {}, {})
            .then(response => {
                this.emergencyService = response.data
                location.reload()
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorEmergencyService = errorMSg
                window.alert(e)
            })
        },

    }
}
