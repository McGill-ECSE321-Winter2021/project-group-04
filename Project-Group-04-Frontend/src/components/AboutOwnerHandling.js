import axios from 'axios'
import swal from 'sweetalert'
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
    editBusiness: function (newAddress, newPhoneNumber, newEmailAddress) {
      var business = this.businesses[0]
    const businessId = business.id
    const name = business.name
      AXIOS.patch('/edit/businessInformation/' + businessId + '?name=' + name + '&address=' + newAddress + '&phoneNumber=' + newPhoneNumber + '&emailAddress=' + newEmailAddress, {},{})
        .then(response => {
          // Update appropriate DTO collections
          this.errorBusiness=""
            this.business = response.data
            swal("Success", "The business infos have been updated successfully", "success")
            location.reload()
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          swal("Error",e.response.data,"error");
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
