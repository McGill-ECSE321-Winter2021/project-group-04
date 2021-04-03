
import axios from 'axios'
import swal from 'sweetalert';

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function EmergencyServiceDto(price, name) {
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
    return {
      emergencyServices: [],
      newEmergencyService: '',
      errorEmergencyService: '',
      EmergencyResponse: [],

    }
  },

  created: function () {
    // const es = new EmergencyServiceDto('70', 'towing')
    // this.emergencyServices = [es]
    AXIOS.get('/emergencyServices')
      .then(response => { this.emergencyServices = response.data })
      .catch(e => { this.errorEmergencyService = e });
  },

  methods: {
    createEmergencyService: function (price, name) {
      AXIOS.post('/create/emergencyService/' + name + '?price=' + price, {}, {})
        .then(response => {
          //   this.emergencyService = response.data.emergencyService
          
          this.emergencyServices.push(response.data)
          this.errorEmergencyService = ''
          this.newEmergencyService = ''
          swal("Success", "Emergency service was succesfully added", "success")

          //   location.reload()
        })
        .catch(e => {
          var errorMSg = e
          console.log(errorMSg)
          this.errorEmergencyService = errorMSg
          swal("ERROR", e.response.data, "error")
        })
    },

    deleteEmergencyService: function (emergencyService) {
      const id = emergencyService.id
      AXIOS.delete('/delete/emergencyService/' + id, {}, {})
        .then(response => {
          this.emergencyServices.pop(response.data)
          swal("Success", "Emergency service was succesfully deleted", "success").then(okay => {
            if (okay) {
              this.$router.go('/Home')
            }
          })
        })
        .catch(e => {
          var errorMSg = e
          console.log(errorMSg)
          this.errorEmergencyService = errorMSg
          swal("ERROR", e.response.data, "error")
        })
    },

    updateEmergencyService: function (emergencyService, newName, newPrice) {
      const id = emergencyService.id
      AXIOS.patch('/edit/emergencyService/' + id + '?name=' + newName + '&price=' + newPrice, {}, {})
        .then(response => {
          this.emergencyService = response.data
          swal("Success", "Emergency service was succesfully updated", "success").then(okay => {
            if (okay) {
              this.$router.go('/Home')
            }
          })
        })
        .catch(e => {
          var errorMSg = e
          console.log(errorMSg)
          this.errorEmergencyService = errorMSg
          swal("ERROR", e.response.data, "error")
        })
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
