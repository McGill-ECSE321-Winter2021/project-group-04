import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'CustomerRegistrationPage',

    data() {
        return {
            userId: '',
            password: '',
            firstName: '',
            lastName: '',
            address: '',
            phoneNumber: '',
            zipCode: '',
            emailAddress: '',
            modelNumber: '',
            year: '',
            color: ''
        }
    },


    methods: {
        registerCustomer: function (userId, password, firstName, lastName, address, phoneNumber, 
            zipCode, emailAddress, modelNumber, year, color) {
            AXIOS.post('/register/customer/'+userId+'?password='+password+'&firstName='+firstName+'&lastName='
            +lastName+'&address='+address+'&phoneNumber='+phoneNumber+'&zipCode='+zipCode+'&emailAddress='
            +emailAddress +'&modelNumber='+ modelNumber+'&year='+year+'&color='+color)
                .then(response => {
                    this.errorProfile = ""
                    this.profile = response.data
                    swal("Success", "Registration Successful", "success").then(okay =>{
                        this.$router.push('/home')
                    })
                    
                })
                .catch(e => {
                    var errorMsg = e
                    console.log(errorMsg)
                    this.errorProfile = errorMsg
                    swal("Error", e.response.data, "error")
                });
        }
    }
}
