
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'services',
    data() {
        return{
            bookableServices: [],
            newBookableService: '',
            errorBookableService: '',
            bookableResponse: [],
        }
    },
    created: function(){
        AXIOS.get('/bookableServices')
        .then(response => {this.bookableServices = response.data})
        .catch(e => {this.errorBookableService = e});
    },
    
    methods: {
        logout: function () {
            AXIOS.post('/logout', {}, {})
            .then(response => {
                this.$router.push('/')
      
              })
              .catch(e => {
                var errMsg = e.response.data.message
                window.alert(e)
              })
          }
    }
}