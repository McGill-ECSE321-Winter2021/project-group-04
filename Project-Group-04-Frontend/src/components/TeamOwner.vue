<template>
  <div>
    <div class="topnav">
      <a > <router-link to="/homeOwner">Home</router-link></a>


      <a>
        <router-link to="/owner/bookableServices">
          Our Services
        </router-link>

      </a>
      <a class="active">
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
         <div class="secondnav">
    <hr>
     <h2> Our Team </h2>
  
      <table>
       
      <tr class="table">
       <th > Name</th>
  
        <!-- <th>Total Price</th> -->
      </tr>
      
        <tr  v-for="team in teams" :key="team.service">
        <td> {{ team.name }}</td> 
                   <td class = "button">
              <button v-on:click="deleteGarageTechnician(garageTechnician)">Delete Garage Technician</button>
              <button v-on:click="deleteFieldTechnician(fieldTechnician)">Delete Field Technician</button>
            </td>
        </tr>
          <tr class = "addGarageTechnician">
            <td><input type="text" v-model="name" placeholder="Name"></td>
            <td class = "button">
              ​<button v-on:click="createGarageTechnician(name)">Add Garage Technician</button>
            </td>
            </tr>

            <tr class = "addFieldTechnician">
            <td><input type="text" v-model="name" placeholder="Name"></td>
            <td class = "button">
              ​<button v-on:click="createFieldTechnician(name)">Add Field Technician</button>
            </td>
            </tr>
      </table>
    ​<span v-if="errorTeam" style="color:red">Error: {{errorTeam}} </span>
    <hr>
    </div>
  </div>
</template>

<script  src = './TeamOwnerHandling.js'>
 
</script>


<style>

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