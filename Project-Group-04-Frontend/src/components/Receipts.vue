<template>
<div>
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
      <router-link to="/services">
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
    
    <div class="secondnav">
    <hr>
     <h2> My Receipts </h2>
  
      <table>
        <thead>
      <tr class="table">
       <th>Service </th>
        <th> Date</th>
        <th>Total Price</th>
      </tr>
        </thead>
        <tbody>
         <tr v-for="receipt in receipts" :key="receipt.totalPrice">
        <td>{{ receipt.totalPrice }}</td> 
        </tr>
          </tbody>

     
   
      </table>
    ​<span v-if="errorReceipt" style="color:red">Error: {{errorRecepit}} </span>
    <hr>
  </div>
</div>
</template>
 
<script  src = './ReceiptHandling.js'>
 
</script>

<style>

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
