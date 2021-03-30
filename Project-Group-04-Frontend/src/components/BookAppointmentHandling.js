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
    name: 'BookAppointmentHandling',
    data() {
      return {
        appointments: [],
        errorBookAppointment: '',
        appointmentId:'',
     // bookableServices: [],

        response: [],
        datePickerIdMin : new Date().toISOString().split("T")[0]
      }
    },

    created: function () {
      // Test data
AXIOS.get('/appointments/' + "abrarfahad7")
  .then(response => {
    // JSON responses are automatically parsed.
    this.appointments = response.data
    this.appointmentId=  this.appointments[0].id
    // console.log(this.appointmentId)
  })
  .catch(e => {
    this.errorBookAppointment = e
  })
    },
    methods:{
    cancelAppointment: function (appointmentId) {
      // Create a new person and add it to the list of people
      
      AXIOS.delete('/cancel/appointment/' + appointmentId,
        {}, {}
        )
        .then(response => {
          // Update appropriate DTO collections

          this.errorProfile=""
          this.appointments.pop(response.data)
          // this.appointment = response.data
          
        })
        .catch(e => {
          var errorMsg = e
          this.errorProfile = errorMsg
          window.alert(e);
        })
      // Reset the name field for new people
    }
  
}

}

