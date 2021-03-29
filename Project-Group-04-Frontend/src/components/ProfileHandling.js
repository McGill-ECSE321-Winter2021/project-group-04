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
      newProfile :'',
      profileId: '',
      errorProfile: '',
      response: []
    }
  },
  
  methods: {
    editProfile: function (firstName, lastName, addressLine, emailAddress, zipCode, phoneNumber) {
      // Create a new person and add it to the list of people
      
      AXIOS.patch('/edit/profile/' + this.profileId + '?Email Address=' + emailAddress + '&Phone Number=' + phoneNumber
        + '&Address Line=' + addressLine + '&Zip Code=' + zipCode + '&First Name=' + firstName + '&Last Name=' + lastName,
        {}, {}
        )
        .then(response => {
          // Update appropriate DTO collections
          this.profile = response.data.profile
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorProfile = errorMsg
          window.alert(e);
        })
      // Reset the name field for new people
    },
    getProfile: function () {
      // Initializing persons from backend
      AXIOS.get('/profile/' + "abrarfahad7")
        .then(response => {
          // JSON responses are automatically parsed.
          this.profile = response.data
          this.profileId = profile.profileId
        })
        .catch(e => {
          this.errorProfile = e
        })
    },
  }
  //...

}
