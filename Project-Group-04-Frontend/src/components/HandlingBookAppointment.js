import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TimeSlotDto(startDate, startTime,endDate, endTime, garageSpot){
    this.startDate = startDate
    this.startTime = startTime
    this.endDate = endDate
    this.endTime = endTime
    this.garageSpot = garageSpot
}

function BookableServiceDto(price, name){
    this.price = price
    this.name = name
}

function ReceiptDto (totalPrice){
    this.totalPrice = totalPrice
}

function AppointmentDto(timeSlot, bookableService, receipt){
    this.timeSlot = timeSlot
    this.bookableService = bookableService
    this.receipt = receipt
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
    name: 'receiptHandling',
    data() {
      return {
        appointments: [],
        errorBookAppointment: '',
        response: []
      }
    },

    // created: function () {
    // const b = new BookableServiceDto(30, 'oil')
    // const t = new TimeSlotDto('2021-02-12', '09:00:00','2021-02-12', '12:00:00','1')
    // const r = new ReceiptDto(b.price)
    // const a = new AppointmentDto(t,b,r)

    // this.appointments = [a]

    


methods: {
    bookAppointment: function (selectedService, date,time,garageSpot) {
     
        AXIOS.post('/book/appointment/'+yasmina +selectedService +'?date='+date+ '&garageSpot=' 
        + garageSpot+ '&startTime='+time+ '&garageTechnicianId=' + 74 ,{},{})
        .then(response => {
          this.$router.go('Home')
            // if(userId.localeCompare("owner")){
            //     this.$router.push('Home')    // need to change to owner homepage
            // }

            // else if(userId.localeCompare("admin")){
            //   this.$router.push('Home')      // need to change to owner homepage
            // }

            // else{
            //   this.$router.push('Home')
            // }
        })
        .catch(e => {
            var errMsg = e.response.data.message
            window.alert("Please register an account")
        });
    },
    }
}


