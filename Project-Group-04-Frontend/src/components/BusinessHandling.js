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
    editBusiness: function (business, name, adress, phoneNumber, emailAddress, businessHours) {
      // Create a new person and add it to the list of people
      business.name = name
      business.adress = adress
      business.phoneNumber = phoneNumber
      business.emailAddress = emailAddress
      business.businessHours = businessHours
      AXIOS.post('/edit/business/'.concat(businessId), {},
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
  //...

}
