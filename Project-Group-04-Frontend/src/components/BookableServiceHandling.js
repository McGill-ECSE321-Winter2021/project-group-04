import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function BookableServiceDto(duration, price, name, bookableServiceId){
    this.duration = duration;
    this.price = price;
    this.name = name;
    this.bookableServiceId = bookableServiceId;
}

export default {
  name: 'bookableServiceHandling',
  data () {
    return {
      bookableService: '',
      duration: '',
      price: '',
      name: '',
      bookableServiceId: '',
      errorBookableService:'',
      response: []
    }
  },
  created: function () {
    AXIOS.get('/bookableService/'.concat(bookableServiceId))
    .then(response => {
      this.bookableService = response.data
      this.duration = bookableService.duration
      this.price = bookableService.price
      this.name = bookableService.name
    })
    .catch(e => {
      this.errorBookableService = e
    })
  },
  methods: {
    editBookableService: function (duration, price, name){
      bookableService.duration = duration
      bookableService.price = price
      bookableService.name = name
      AXIOS.patch('/edit/bookableService/'.concat(bookableServiceId), {},
      {
        params: {
          "Duration" : duration,
          "Price": price,
          "name": name
        }
      })
      .then(response => {
        
      })
      .catch(e => {
        var errorMsg = e
        console.log(errorMsg)
        this.errorBookableService = errorMsg
      })
    }
  }
}