import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function BusinessDto(name, address, phoneNumber, emailAddress, businessHours) {
  this.name = name
  this.address = address
  this.phoneNumber = phoneNumber
  this.emailAddress = emailAddress
  this.businessHours = businessHours
}

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'business',
  data() {
    return {
      businesses: [],
      adress: '',
      phoneNumber: '',
      emailAdress: '',
      errorBusiness: '',
      response: []
    }
  },
  created: function () {
    AXIOS.get('/business/')
      .then(response => {
        // JSON responses are automatically parsed.
        this.businesses = response.data
        this.businessId = this.business.businessId
      })
      .catch(e => {
        this.errorProfile = e
      })
  },
  methods: {
    editBusiness: function (business) {
    const businessId = business.businessId
      AXIOS.post('/edit/business/' + businessId + '?name=' + newName + '&address=' + newAddress + '&phoneNumber=' + newPhoneNumber + '&emailAddress=' + newEmailAdress, {},{})
        .then(response => {
          // Update appropriate DTO collections
            this.business = response.data
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorBusiness = errorMsg
        })
      // Reset the name field for new people
    }
  }
  //...

}