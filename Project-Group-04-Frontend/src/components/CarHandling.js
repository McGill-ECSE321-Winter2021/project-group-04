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
  name: 'profileHandling',
  data() {
    return {
      car: '',
      model: 'Mercedes',
      year: '2010',
      color: 'Black',
      carId: '4242',
      errorCar: '',
      response: []
    }
  },
  created: function () {
    // Initializing persons from backend
    AXIOS.get('/car/'.concat(carId))
      .then(response => {
        // JSON responses are automatically parsed.
        this.car = response.data
        this.model = car.model
        this.year = car.year
        this.color = car.color
      })
      .catch(e => {
        this.errorCar = e
      })
  },
  methods: {
    editCar: function (model, year, color) {
      // Create a new person and add it to the list of people
      car.model = model
      car.year = year
      car.color = color
      AXIOS.patch('/edit/profile/'.concat(profileId), {},
        {
          params: {
            "Email Address": emailAddress,
            "Phone Number": phoneNumber,
            "Address Line": addressLine,
            "Zip Code": zipCode,
            "First Name": firstName,
            "Last Name": lastName
          }
        })
        .then(response => {
          // Update appropriate DTO collections
          this.car = response.data
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorCar = errorMsg
        })
      // Reset the name field for new people

    }
  }
  //...
}
