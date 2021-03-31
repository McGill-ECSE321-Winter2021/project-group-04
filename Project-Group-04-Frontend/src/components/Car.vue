<template>
  <div>
    <div class="topnav">
      <a> <router-link to="/home">Home</router-link></a>
      <a>
        <router-link to="/profile">
          My Profile
        </router-link>
      </a>
      <a class="active">
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
      <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
      </a>
    </div>
    <div id="myCar">
      <br />
      <h2>My Car</h2>
<br />
    <div>
      <table class="paddingBetweenCols">
        <tr>
          <td>Model</td>
          <td>
            {{car.model}}
          </td>
        </tr>
        <tr>
          <td>Year</td>
          <td>
            {{car.year}}
          </td>
        </tr>
        <tr>
          <td>Color</td>
          <td>
            {{car.color}}
          </td>
        </tr>
      </table>
    </div>
    <br/>
    <span v-if="errorCar" style="color:red">Error: {{errorCar}} </span>
  </div>
      <table class="paddingBetweenCols">
        <tr>
          <td>Model</td>
          <td>
            <input type="text" v-model="newModel" placeholder="Model">
          </td>
        </tr>
        <tr>
          <td>Year</td>
          <td>
            <input type="text" v-model="newYear" placeholder="Year">
          </td>
        </tr>
        <tr>
          <td>Color</td>
          <td>
            <input type="text" v-model="newColor" placeholder="Color">
          </td>
        </tr>
      </table>
      <button id="editButton" class="btn btn-primary" @click="editCar(newModel,newYear,newColor)">
        Save Changes
      </button>
    </div>
          
</template>
<script  src = "./CarHandling.js">
 
</script>

<style>

  .paddingBetweenCols {
    margin-right: auto;
    margin-left: auto;
  }

  .paddingBetweenCols td{
    padding-right: 10px;
    padding-bottom: 10px;
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
