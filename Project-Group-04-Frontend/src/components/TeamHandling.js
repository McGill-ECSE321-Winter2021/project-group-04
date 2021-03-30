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
        errorTeam: '',
      }
    },
    created: function () {
            // Test data
          const tech1 = new TechnicianDto('Cesar', true)
          const tech2 = new TechnicianDto('Abrar', false)

            // Sample initial content
           
            this.teams = [tech1,tech2]
          }
      }
