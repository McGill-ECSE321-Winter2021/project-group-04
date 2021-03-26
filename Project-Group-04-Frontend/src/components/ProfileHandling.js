import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ProfileDto(firstName, lastName, addressLine, emailAddress, zipCode, phoneNumber, profileId) {
  this.firstName = firstName
  this.lastName = lastName
  this.emailAddress = emailAddress
  this.addressLine = addressLine
  this.zipCode = zipCode
  this.phoneNumber = phoneNumber
  this.profileId = profileId
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
      profile: '',
      firstName: 'Mohamad',
      lastName: 'Dimassi',
      addressLine: 'Beirut, Lebanon',
      emailAddress: 'mohamad@dimassi',
      zipCode: 'H2X',
      phoneNumber: '4389786824',
      profileId:'4242',
      errorProfile: '',
      response: []
    }
  },
  created: function () {
    // Initializing persons from backend
    AXIOS.get('/profile/'.concat(profileId))
      .then(response => {
        // JSON responses are automatically parsed.
        this.profile = response.data
        this.firstName = profile.firstName
        this.lastName = profile.lastName
        this.addressLine = profile.addressLine
        this.emailAddress = profile.emailAddress
        this.zipCode = profile.zipCode
        this.phoneNumber = profile.phoneNumber
      })
      .catch(e => {
        this.errorProfile = e
      })
  },
  methods: {
    editProfile: function (firstName,lastName,addressLine,emailAddress,zipCode,phoneNumber) {
      // Create a new person and add it to the list of people
      profile.firstName = firstName
      profile.lastName = lastName
      profile.addressLine = addressLine
      profile.emailAddress = emailAddress
      profile.zipCode = zipCode
      profile.phoneNumber = phoneNumber
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
          person.events.push(event)
          this.profile = response.data
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorProfile = errorMsg
        })
      // Reset the name field for new people
      
    }
  }
  //...
}
