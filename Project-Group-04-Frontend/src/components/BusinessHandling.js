import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function BusinessDto(name, adress, phoneNumber, emailAddress, businessHours) {
  this.name = name
  this.adress = adress
  this.phoneNumber = phoneNumber
  this.emailAddress = emailAddress
  this.addressLine = addressLine
  this.businessHours = businessHours
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
  name: 'businessHandling',
  data() {
    return {
      business: [],
      name: 'AutoRepair',
      adress: '123 McGill street',
      emailAdress: 'business@cizo.com',
      phoneNumber: '123-456-9874',
      businessHours: [],
      response: []
    }
  },
  created: function () {

    const business1 = new BusinessDto('Auto', '123 McGill street', '123-456-9874', 'business@cizo.com', [])
    this.business = [business1]
    // Initializing persons from backend
    // AXIOS.get('/business/'.concat(businessId))
    //   .then(response => {
    //     // JSON responses are automatically parsed.
    //     this.business = response.data
    //     this.name = business.name
    //     this.adress = business.adress
    //     this.phoneNumber = business.phoneNumber
    //     this.emailAddress = business.emailAddress
    //     this.businessHours = business.businessHours
    //   })
    //   .catch(e => {
    //     this.errorProfile = e
    //   })
  },
  methods: {
    editBusiness: function (business, name, adress, phoneNumber, emailAddress, businessHours) {
      // Create a new person and add it to the list of people
      business.name = name
      business.adress = adress
      business.phoneNumber = phoneNumber
      business.emailAddress = emailAddress
      business.businessHours = businessHours
      AXIOS.post('/edit/profile/'.concat(businessId), {},
        {
          params: {
            "Name": name,
            "Phone Number": phoneNumber,
            "Address": address,
            "Email Adress": emailAddress,
            "Business Hours" : businessHours,
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
