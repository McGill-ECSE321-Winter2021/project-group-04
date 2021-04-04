import axios from 'axios'
import swal from 'sweetalert'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TechnicianDto(name, isAvailable){
  this.name = name
  this.isAvailable = isAvailable
}

export default {
    name: 'fieldTechnicianOwnerHandling',
    data() {
      return {
        teams: [],
        name: '',
        technicianId: '',
        errorTeam: '',
        reponse: []
      }
    },
    created: function () {
      // Initializing persons from backend
        AXIOS.get('/fieldTechnician')
        .then(response => {
          // JSON responses are automatically parsed.
          this.teams = response.data
        })
        .catch(e => {
          this.errorProfile = e
        })
    },

    methods: {
        createFieldTechnician: function (name){
            AXIOS.post('/register/fieldTechnician/' + name, {}, {})
            .then(response => {
                this.errorTeam=""
                this.teams = response.data
                swal("Success", "The field technician " + name +  " has been added", "success")
                location.reload()
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                swal("Error",e.response.data,"error");
              })
        },
        deleteFieldTechnician: function (fieldTechnician){
            const technicianId = fieldTechnician.technicianId
            const name = fieldTechnician.name
            AXIOS.delete('/delete/fieldTechnician/' + technicianId, {}, {})
            .then(response => {
                this.teams.pop(response.data)
                this.errorTeam=""
                swal("Success", "The garage technician " + name +  " has been deleted", "success")
                location.reload()
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                swal("Error",e.response.data,"error");
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
