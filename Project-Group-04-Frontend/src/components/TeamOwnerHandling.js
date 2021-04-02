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

function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  }

export default {
    name: 'teamHandling',
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
      AXIOS.get('/garageTechnicians')
        .then(response => {
          // JSON responses are automatically parsed.
          this.teams = response.data 
        })
        .catch(e => {
          this.errorProfile = e
        })
    },

    methods: {
        createGarageTechnician: function (name){
            AXIOS.post('/register/garageTechnician/' + name, {}, {})
            .then(response => {
                this.errorTeam=""
                this.teams = response.data
                swal("Success", "The garage technician " + name +  " has been added to the database", "success")
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                swal("Error",e.response.data,"error");
              })
      
        },

        deleteGarageTechnician: function (name){
          var i
          for(i=0; i<teams.length; i++){
            if(name == this.teams[i].name){
              var garageTechnician = this.teams[i]
            }
          }
            const technicianId = garageTechnician.id
            AXIOS.delete('/delete/garageTechnician/' + technicianId, {}, {})
            .then(response => {
                this.teams.pop(response.data)
                this.errorTeam=""
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorteams = errorMSg
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
