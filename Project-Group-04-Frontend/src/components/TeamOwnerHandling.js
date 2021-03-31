import axios from 'axios'

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
        AXIOS.get('/fieldTechnician')
        .then(response => {
          // JSON responses are automatically parsed.
          this.teams += response.data
        })
        .catch(e => {
          this.errorProfile = e
        })
    },

    methods: {
        createGarageTechnician: function (name){
            AXIOS.patch('/register/garageTechnician/' + name, {}, {})
            .then(response => {
                this.errorTeam=""
                this.teams += response.data

            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorTeam = errorMsg
                window.alert(e);
              })
      
        },
        
        createFieldTechnician: function (name){
            AXIOS.patch('/register/fieldTechnician/' + name, {}, {})
            .then(response => {
                this.errorTeam=""
                this.teams += response.data

            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorTeam = errorMsg
                window.alert(e);
              })
        },

        deleteGarageTechnician: function (garageTechnician){
            const technicianId = garageTechnician.technicianId
            AXIOS.patch('/delete/garageTechnician/' + technicianId, {}, {})
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
        deleteFieldTechnician: function (fieldTechnician){
            const technicianId = fieldTechnician.technicianId
            AXIOS.patch('/delete/fieldTechnician/' + technicianId, {}, {})
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
        }
    }
      }
