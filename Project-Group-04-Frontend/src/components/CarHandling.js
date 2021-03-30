import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function CarDto(model, year, color) {
  this.model = model
  this.year = year
  this.color = color
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
  name: 'CarHandling',
  data() {
    return {
      car: '',
      carId: '',
      errorCar: '',
      response: []
    }
  },
  created: function () {
    // Initializing persons from backend
    AXIOS.get('/car/'+"abrarfahad7")
      .then(response => {
        // JSON responses are automatically parsed.
        this.car = response.data
        this.carId=this.car.carId
      })
      .catch(e => {
        this.errorCar = e
      })
  },
  methods: {
    editCar: function (model, year, color) {
      console.log(this.carId);
      console.log(model);
      console.log(year);
      console.log(color);
      // Create a new person and add it to the list of people
      AXIOS.patch('/edit/car/' + this.carId+'?model='+model+'&year='+year+'&color='+color, {}, {})
        .then(response => {
          this.errorCar = '';
          // Update appropriate DTO collections
          this.car = response.data
        })
        .catch(e => {
          this.errorCar=e
          console.log(errorCar)
          window.alert(errorCar)
        })
      // Reset the name field for new people

    }
  }
  //...
}
