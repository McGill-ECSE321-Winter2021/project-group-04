<template>

  <div>
    <div class="logout-href">
      <button @click="logout()" type="homeLogout" class="btn btn-primary">
        Logout
      </button>
    </div>
    <div class="topnav">
      <a > <router-link to="/homeOwner">Home</router-link></a>

    
      <a>
        <router-link to="/owner/bookableServicesr">
          Our Services
        </router-link>

      </a>
      <a>
        <router-link to="/teamOwner">
          Our Team
        </router-link>

      </a>
      <a class="active">
        <router-link to="/aboutOwner">
          About Us
        </router-link>

      </a>
      </div>
      <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
      </a>
   
      <div class = "businessTable">  
        <hr>
          <h2>
            <div class="dropdown">
              <button class="dropbtn">Our Business</button>
              <div class="dropdown-content">
                <a>
                  <router-link to="/businessHoursOwner">
                    Business Hours
                  </router-link>
                </a>
                <a>
                  <router-link to="/aboutOwner">
                    Contact us
                  </router-link>
                </a>
              </div>
            </div>
          </h2>

        ​<table class="table">

          <tr >
            <th>Days</th>
            <th>Openning Hour</th>
            <th>Closing Hour</th>
          </tr>
            <tr v-for="businessHour in businessHours" :key="businessHour.openningHour">
              <td>{{ businessHour.dayOfWeek }}</td>
              <td>{{ businessHour.startTime }}</td>
              <td>{{ businessHour.endTime }}</td>
          </tr>
          <tr class = "editBusinessHour">
            <td><input type="text" v-model="dayOfWeek" placeholder="Day"></td>
            <td><input type="text" v-model="startTime" placeholder="Example: 08:00"></td>
            <td><input type="text" v-model="endTime" placeholder="Example: 18:00"></td>
            <td class = "button">
              ​<button v-on:click="updateBusinessHour(dayOfWeek, startTime, endTime)">Update</button>
            </td>
          <!-- <td class = "button">
            <button v-bind:disabled="!newBookableService" @click="createBookableService(serviceDuration, servicePrice, serviceName)">Add</button>
          </td> -->
        </tr>
        </table>
        <hr>
    </div>
  </div>
</template>
<script src="./BusinessHourOwnerHandling.js" >
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

 

  .table {
    background-color: #ffffff;
    margin: 0px;
    align-content: center;
    margin-right: auto;
    margin-left : auto;
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
  .paddingBetweenCols  {
      margin-left: auto;
    margin-right: auto;

  }
  .paddingBetweenCols table{
     background-color: #ffffff;
    margin: 0px;
    align-content: center;
  }
  .paddingBetweenCols th{
    color: #696969;
    text-align: center;
    padding-left: 70px;
    padding-right: 70px;
  }

</style>
