<template>
<div>
  <div class="logout-href">
      <button @click="logout()" type="homeLogout" class="btn btn-primary">Logout</button>
    </div>
  <div class="topnav">
    <a> <router-link to="/home">Home</router-link></a>

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

    <a class="active">
      <router-link to="/receipts">
        My Receipts
      </router-link>
    </a>
    <a>
      <router-link to="/reminders">
        My Reminders
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
  </div>

    
    <div class="secondnav">
    <hr>
     <h2> My Receipts </h2>
 <div>
      <table>
       
      <tr class="table">
       <th > Service</th>
        <th> Date</th>
        <th>Total Price ($)</th>
      </tr>
      
        <tr  v-for="appointment in appointments" :key="appointment.bookableService.name">
          <td>{{ appointment.bookableService.name }}</td> 
          <td>{{ appointment.timeSlot.startDate }}</td> 
          <td> {{appointment.receipt.totalPrice}}</td>
        </tr>
      
      

     
   
      </table>
 </div>
      <div>
      <table>
       
      <tr class="table">
       <th > Service</th>
        <th> Location </th>
        <th>Total Price ($)</th>
      </tr>
        <tr  v-for="emergencyService in emergencyServices" :key="emergencyService.location">
          <td>{{ cutName(emergencyService.name) }}</td> 
          <td>{{ emergencyService.location}}</td> 
          <td> {{emergencyService.receipt.totalPrice}}</td>
        </tr>
     
      </table>
      </div>
    ​<span v-if="errorReceipt" style="color:red">Error: {{errorReceipt}} </span>
    <hr>
    </div>
</div>
</template>
 
<script src = './ReceiptHandling.js'>
//   function cutName(str) {
//   str = str.split('for')[0];
// }
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

  .secondnav table {
    margin-left: auto;
    margin-right: auto;
  }

  .template {
    background-color: #696969;
  }
 
  .table {
    background-color: #f2f2f2;
    margin: 0px;
  }
    .table th { 
      color: #290e36;
      
      padding-left: 100px;
      padding-right: 100px;
      text-align: center;

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
</style>
