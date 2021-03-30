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

function BookableServiceDto(duration,price, name){
  this.duration = duration
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

function getMinDate() {
    var todaysDate = new Date(); // Gets today's date
    // Max date attribute is in "YYYY-MM-DD".  Need to format today's date accordingly
    var year = todaysDate.getFullYear();                        // YYYY
    var month = ("0" + (todaysDate.getMonth() + 1)).slice(-2);  // MM
    var day = ("0" + todaysDate.getDate()).slice(-2);           // DD
  var maxDate = (day + "/" + month + "/" + year); // Results in "YYYY-MM-DD" for today's date 
  // Now to set the max date value for the calendar to be today's date
  return maxDate;
}




export default {
    name: 'HandlingBookAppointment',
    data() {
      return {
        appointments: [],
        errorBookAppointment: '',
        bookableServices: [],

        response: [],
        datePickerIdMin : new Date().toISOString().split("T")[0]
      }
    },

    created: function(){
      AXIOS.get('/bookableServices')
      .then(response => {this.bookableServices = response.data})
      .catch(e => {this.errorBookAppointment})
    },

methods: {
    bookAppointment: function (selectedService, date,time,garageSpot) {
     console.log(selectedService)
     console.log(date)
     console.log(garageSpot)
     console.log(time)

        AXIOS.post('/book/appointment/'+ "abrarfahad7" + selectedService +'?date='+date+ '&garageSpot=' 
        + garageSpot+ '&startTime='+time+ '&Garage Technician Id=' + 74)
        .then(response => {
          this.appointments = response.data
          // this.$router.go('Home')
        
        })
        .catch(e => {

            var errMsg = e
            window.alert(errMsg)
        });
    },
    }
}


