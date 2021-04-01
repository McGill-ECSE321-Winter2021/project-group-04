<template>
  <div>
    <div class="logout-href">
      <button @click="logout()" type="homeLogout" class="btn btn-primary">Logout</button>
    </div>
    <div class="topnav">
      <a><router-link to="/homeOwner">Home</router-link></a>
      
      <a class="active">
        <router-link to="/owner/emergencyServices">
          Our Services
        </router-link>
      </a>
      <a>
        <router-link to="/teamOwner">
          Our Team
        </router-link>
      </a>
      <a>
        <router-link to="/aboutOwner">
          About Us
        </router-link>
      </a>
    </div>

    <a href="javascript:void(0);" class="icon" onclick="myFunction()">
      <i class="fa fa-bars"></i>
    </a>

    <div class="serviceTable">
      <hr />
      <h2>
        <div class="dropdown">
          <button class="dropbtn">Our Services</button>
          <div class="dropdown-content">
            <a>
              <router-link to="/owner/bookableServices">
                Bookable Services
              </router-link>
            </a>
            <a>
              <router-link to="/owner/emergencyServices">
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
       
          <th>New Price</th>
        </tr>
        <tr
          v-for="emergencyService in emergencyServices"
          :key="emergencyService.name"
        >
          <td>
            <span class="newName">{{ emergencyService.name }}</span>
          </td>
          <td>
            <span class="newPrice">{{ emergencyService.price }}</span>
          </td>
          
          <td>
            <input
              type="text"
              v-model="newServicePrice"
              placeholder="New Price"
            />
          </td>
          <td class="button">
            <!-- v-on:click="updateEmergencyService(emergencyService) -->
            ​<button v-on:click="updateEmergencyService(emergencyService, newServiceName, newServicePrice)">Edit</button>
          </td>
          <td class="button">
            <button v-on:click="deleteEmergencyService(emergencyService)">
              Delete
            </button>
          </td>
        </tr>
        <tr class="addService">
          <td>
            <input
              type="text"
              v-model="serviceName"
              placeholder="Name"
            />
          </td>
          <td>
            <input
              type="text"
              v-model="servicePrice"
              placeholder="Price"
            />
          </td>
          <td class="button">
            ​<button v-on:click="createEmergencyService(servicePrice, serviceName)">
              Add
            </button>
          </td>
        </tr>
      </table>
      <hr />
    </div>
  </div> 
</template> 

<script src="./EmergencyServiceHandling.js">
</script>

<style>
.logout-href {
  padding: 20px 1px;
  font-size: 25px;
  position: absolute;
  top: 0;
  right: 0;
  width: 120px;
  text-align: left;
}
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
  font-size: 17px;
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
  background-color: #ff4500;
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
  font-size: 17px;
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
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
  font-size: 17px;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
  background-color: #ddd;
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
  background-color: #ff4500;
}

/* Popup box BEGIN */

.bg-modal {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  position: absolute;
  top: 0;
  justify-content: center;
  align-items: center;
  display: none;
}

.modal-content {
  width: 500px;
  height: 300px;
  background-color: white;
  border-radius: 4px;
  justify-content: center;
  /* align-items: center; */
  position: relative;
}

.newInfo {
  width: 50%;
  display: block;
  margin: 25px auto;
}

.close {
  position: absolute;
  top: 0;
  right: 14px;
  font-size: 17px;
  transform: rotate(45deg);
  cursor: pointer;
}
</style>
