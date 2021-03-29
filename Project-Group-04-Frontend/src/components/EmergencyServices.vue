<template>
  <div>
      <div class="topnav">
      <a><router-link to="/home">Home</router-link></a>
      <a>
        <router-link to="/profile">
          My Profile
        </router-link>
      </a>
      <a>
        <router-link to="/car">
          My Car
        </router-link>
      </a>
      <a>
        <router-link to="/receipts">
          My Receipts
        </router-link>
      </a>
      <a class="active">
        <router-link to="/emergencyServices">
          Our Services
        </router-link>

      </a>
      <a>
        <router-link to="/team">
          Our Team
        </router-link>
      </a>
      <a>
        <router-link to="/about">
          About Us
        </router-link>

      </a>
    </div>
    
    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
      <i class="fa fa-bars"></i>
    </a>

      <div class = "serviceTable">  
        <hr>
          <h2>
            <div class="dropdown">
              <button class="dropbtn">Our Services</button>
              <div class="dropdown-content">
                <a>
                  <router-link to="/bookableServices">
                    Bookable Services
                  </router-link>
                </a>
                <a>
                  <router-link to="/emergencyServices">
                    Emergency Services
                  </router-link>
                </a>
              </div>
            </div>
          </h2>

        <table>
          <tr class="table">
            <th>Emergency Service</th>
            <th>Price ($)</th>
          </tr>
            <tr v-for="emergencyService in emergencyServices" :key="emergencyService.name">
              <td><span contenteditable="true" class="newName">{{ emergencyService.name }}</span></td>
              <td><span contenteditable="true" class="newPrice">{{ emergencyService.price }}</span></td>
            <td class = "button">
              ​<button v-on:click="updateEmergencyService(emergencyService)">Edit</button>
            </td>
            <td class = "button">
              <button v-on:click="deleteEmergencyService(emergencyService)">Delete</button>
            </td>
          </tr>
          <tr class = "addService">
            <td><input type="text" v-model="serviceName" placeholder="Service Name"></td>
            <td><input type="text" v-model="servicePrice" placeholder="Service Price"></td>
            <td class = "button">
              ​<button v-on:click="createEmergencyService(servicePrice, serviceName)">Add</button>
            </td>
            <!-- <td class = "button">
              <button v-bind:disabled="!newBookableService" @click="createBookableService(serviceDuration, servicePrice, serviceName)">Add</button>
            </td> -->
          </tr>
        </table>
        <!-- ​<span v-if="errorEvent" style="color:red">Error: {{errorEvent}} </span> -->
        <hr>
    </div>
  </div>
</template>

<script src="./EmergencyServiceHandling.js">
function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}
</script>

<style>

  .button {
    padding: 10px;
  }

  .template {
    background-color: #ffffff;
  }

  .serviceTable table {
    margin-left: auto;
    margin-right: auto;
  }

  .table {
    background-color: #ffffff;
    margin: 0px;
    align-content: center;
  }
  
  .table th {
    color: #696969;
    text-align: center;
    padding-left: 100px;
    padding-right: 100px;
  }

  /* Add a black background color to the top navigation */
  .topnav {
    background-color: #696969;
    overflow: hidden;
  }

    /* Style the links inside the navigation bar */
    .topnav a {
      float: left;
      color: #f2f2f2;
      text-align: center;
      padding: 8px;
      text-decoration: none;
      font-size: 17px;
    }
      

      /* Change the color of links on hover */
      .topnav a:not(active):hover {
        background-color: #ddd;
        color: black;
      }

      /* Add a color to the active/current link */
      .topnav a.active {
        background-color: #FF4500;
        color: white;
      }
    /* Hide the link that should open and close the topnav on small screens */
    .topnav .icon {
      display: none;
    }
  /* When the screen is less than 600 pixels wide, hide all links, except for the first one ("Home"). Show the link that contains should open and close the topnav (.icon) */
  @media screen and (max-width: 600px) {
    .topnav a:not(:first-child) {
      display: none;
    }

    .topnav a.icon {
      float: right;
      display: block;
    }
  }

  /* The "responsive" class is added to the topnav with JavaScript when the user clicks on the icon. This class makes the topnav look good on small screens (display the links vertically instead of horizontally) */
  @media screen and (max-width: 600px) {
    .topnav.responsive {
      position: relative;
    }

      .topnav.responsive a.icon {
        position: absolute;
        right: 0;
        top: 0;
      }

      .topnav.responsive a {
        float: none;
        display: block;
        text-align: left;
      }
  }

  /* Dropdown Button */
.dropbtn {
  background-color: #696969;
  color: white;
  padding: 16px;
  font-size: 30px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #FF4500;}


/* pop up CSS*/

/* Popup box BEGIN */
.hover_bkgr_fricc{
    background:rgba(0,0,0,.4);
    cursor:pointer;
    display:none;
    height:100%;
    position:fixed;
    text-align:center;
    top:0;
    width:100%;
    z-index:10000;
}
.hover_bkgr_fricc .helper{
    display:inline-block;
    height:100%;
    vertical-align:middle;
}
.hover_bkgr_fricc > div {
    background-color: #fff;
    box-shadow: 10px 10px 60px #555;
    display: inline-block;
    height: auto;
    max-width: 551px;
    min-height: 100px;
    vertical-align: middle;
    width: 60%;
    position: relative;
    border-radius: 8px;
    padding: 15px 5%;
}
.popupCloseButton {
    background-color: #fff;
    border: 3px solid #999;
    border-radius: 50px;
    cursor: pointer;
    display: inline-block;
    font-family: arial;
    font-weight: bold;
    position: absolute;
    top: -20px;
    right: -20px;
    font-size: 25px;
    line-height: 30px;
    width: 30px;
    height: 30px;
    text-align: center;
}
.popupCloseButton:hover {
    background-color: #ccc;
}
.trigger_popup_fricc {
    cursor: pointer;
    font-size: 20px;
    margin: 20px;
    display: inline-block;
    font-weight: bold;
}
/* Popup box BEGIN */



</style>
