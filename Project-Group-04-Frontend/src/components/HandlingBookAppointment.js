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

    created: function () {
    const b = new BookableServiceDto(30, 'oil')
    const t = new TimeSlotDto('2021-02-12', '09:00:00','2021-02-12', '12:00:00','1')
    const r = new ReceiptDto(b.price)
    const a = new AppointmentDto(t,b,r)

    this.appointments = [a]

}
// methods: {
//     createAppointment: function (selectedService, date,time,garageSpot) {
//     //   AXIOS.post('/appointment/'.concat(appointmentId), {}, {})
//     //     .then(response => {
//         // JSON responses are automatically parsed.
//         var timeSlot = new TimeSlotDto(date, time, date, time+ selectedService.duration, garageSpot)
//         var receipt = new ReceiptDto(selectedService.price)
//         var appointment = new AppointmentDto(timeSlot, selectedService,receipt)
//         this.appointments.push(appointment)
//         //   this.errorPerson = ''
        
//         }
//         // .catch(e => {
//         //   var errorMsg = e.response.data.message
//         //   console.log(errorMsg)
//         //   this.errorPerson = errorMsg
//         // })
//     }
}


