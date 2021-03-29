
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function EmergencyServiceDto(price, name){
    this.price = price;
    this.name = name;
}

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


// window.onload = function() {
//     var spanName = document.getElementsByClassName("newName")

//     if (spanName.contentEditable) {
//         spanName.onblur = function() {
//             var text = this.innerHTML;
//             newName = text.replace(/&/g, "&amp").replace(/</g, "&lt;");
//         };
//     }


//     var spanPrice = document.getElementsByClassName("newPrice")

//     if (spanPrice.contentEditable) {
//         spanPrice.onblur = function() {
//             var text = this.innerHTML;
//             newPrice = text.replace(/&/g, "&amp").replace(/</g, "&lt;");
//         };
//     }
// }

// window.onload = function() {
// 	if(localStorage.getItem('newName')) {
// 		document.querySelector('.newName').innerHTML = localStorage.getItem('newName');
//   }
// }

// let editBtn = document.querySelector('#edit_content');
// let content = document.querySelector('.newName');

// editBtn.addEventListener('click', () => {
//   // Toggle contentEditable on button click
// 	content.contentEditable = !content.isContentEditable;
  
//   // If disabled, save text
//   if(content.contentEditable === 'false') {
//   	localStorage.setItem('newName', content.innerHTML);
//   }
// });

// $(window).load(function () {
//     $(".trigger_popup_fricc").click(function(){
//        $('.hover_bkgr_fricc').show();
//     });
//     $('.hover_bkgr_fricc').click(function(){
//         $('.hover_bkgr_fricc').hide();
//     });
//     $('.popupCloseButton').click(function(){
//         $('.hover_bkgr_fricc').hide();
//     });
// });

export default {
    name: 'services',
    data() {
        return{
            emergencyServices: [],
            newEmergencyService: '',
            errorEmergencyService: '',
            EmergencyResponse: [],

        }
    },

    created: function(){
        AXIOS.get('/emergencyServices')
        .then(response => {this.emergencyServices = response.data})
        .catch(e => {this.errorEmergencyService = e});
    }, 

    methods: {
        createEmergencyService: function(price, name){
          AXIOS.post('/create/emergencyService/' + name + '?price=' + price, {}, {})
          .then(response => {
            //   this.emergencyService = response.data.emergencyService
              this.emergencyServices.push(response.data)
              this.errorEmergencyService = ''
              this.newEmergencyService = ''
          })
          .catch(e => {
              var errorMSg = e
              console.log(errorMSg)
              this.errorEmergencyService = errorMSg
              window.alert(e)
          })
        },

        deleteEmergencyService: function(emergencyService){
            const id = emergencyService.id
            AXIOS.delete('/delete/emergencyService/' + id , {}, {})
            .then(response => {
                this.emergencyServices.pop(response.data)
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorEmergencyService = errorMSg
                window.alert(e)
            })
        },

        updateEmergencyService: function(emergencyService){
            const id = emergencyService.id
            AXIOS.patch('/edit/emergencyService/' + id + '?name=' + newName + '&price=' + newPrice, {}, {})
            .then(response => {
              //   this.emergencyService = response.data.emergencyService
                // this.emergencyServices.push(response.data)
                // this.errorEmergencyService = ''
                // this.newEmergencyService = ''
                this.emergencyService = response.data
            })
            .catch(e => {
                var errorMSg = e
                console.log(errorMSg)
                this.errorEmergencyService = errorMSg
                window.alert(e)
            })
        }

    }
}


// import axios from 'axios'
// var config = require('../../config')

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

// var AXIOS = axios.create({
//   baseURL: backendUrl,
//   headers: { 'Access-Control-Allow-Origin': frontendUrl }
// })

// function EmergencyServiceDto(price, name, location){
//     this.price = price;
//     this.name = name;
//     this.location = location;
// }

// export default {
//     name: 'emergencyService',
//     data () {
//       return {
//         emergencyService: '',
//         price: '50$',
//         name: 'Towing',
//         location: '',
//         emergencyServiceId: '',
//         erroremergencyService:'',
//         response: []
//       }
//     },

//     created: function () {
//       AXIOS.get('/emergencyService/'.concat(emergencyServiceId))
//       .then(response => {
//         this.emergencyService = response.data
//         this.location = emergencyService.location
//         this.price = emergencyService.price
//         this.name = emergencyService.name
//       })
//       .catch(e => {
//         this.erroremergencyService = e
//       })
//     },

//     methods: {
//       editemergencyService: function (price, name, location){
//         emergencyService.location = location
//         emergencyService.price = price
//         emergencyService.name = name
//         AXIOS.patch('/edit/emergencyService/'.concat(emergencyServiceId), {},
//         {
//           params: {
//             "location" : location,
//             "Price": price,
//             "name": name
//           }
//         })
//         .then(response => {
          
//         })
//         .catch(e => {
//           var errorMsg = e
//           console.log(errorMsg)
//           this.erroremergencyService = errorMsg
//         })
//       }
//     }
//   }