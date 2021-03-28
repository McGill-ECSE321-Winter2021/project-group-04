<template>
  <div>
    <div class="topnav">
      <a class="active"> <router-link to="/home">Home</router-link></a>

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

      <a>
        <router-link to="/bookableServices">
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
      <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
      </a>
    </div>
    <div>
      <br />
      <router-link to="/book">
              <button type="bookingButton" class="btn btn-primary">
                Book a new Appointment
              </button>
            </router-link>
      <button type="cancelAppointment" class="btn btn-primary">
                Cancel your appointment
              </button>
    </div>
    <div>
      <br/>
      <h3>Your next appointment:</h3>
    </div>
    <div>
      <table class="paddingBetweenCols">
        <tr>
          <td>Date</td>
           <td>Start Time</td>
           <td>End Time</td> 
           <td>Service</td>
           <td> Spot </td>
         
        </tr>
         <tr  v-for="appointment in appointments" :key="appointment.service">           
        <td> {{ appointment.timeSlot.startDate }}</td> 
        <td>{{ appointment.timeSlot.startTime }}</td> 
        <td> {{appointment.timeSlot.endTime}}</td>
        <td> {{appointment.service.name}}</td>
        <td> {{appointment.timeSlot.garageSpot}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script src='./BookAppointmentHandling'>
  
</script>

<style>
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
  .paddingBetweenCols td {
    padding: 0 15px;
  }
</style>
