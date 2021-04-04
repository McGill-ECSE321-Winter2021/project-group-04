<template>
  <div>
    <div class="logout-href">
      <button @click="logout()" type="homeLogout" class="btn btn-primary">Logout</button>
    </div>
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
       <div class = "serviceTable">  
        <hr>
          <h2>
            <div class="dropdown">
              <button class="dropbtn">Our Team</button>
              <div class="dropdown-content">
                <a>
                  <router-link to="/teamOwner">
                    Garage Technician
                  </router-link>
                </a>
                <a>
                  <router-link to="/fieldTechnicianOwner">
                    Field Technician
                  </router-link>
                </a>
              </div>
            </div>
          </h2>

        ​<table class="tableau">
          <tr class="table">
            <th>Name</th>
          </tr>
       <tr  v-for="fieldTechnician in teams" :key="fieldTechnician.name">
        <td> {{ fieldTechnician.name }}</td> 
         
          <td class="button">
            ​<button v-on:click="deleteFieldTechnician(fieldTechnician)">Delete</button>
          </td>
          </tr>
          <tr class = "addService">
            <td><input id="myInput" size="10" type="text" v-model="name" placeholder="Name"></td>
            <td class = "button">
              ​<button v-on:click="createFieldTechnician(name)" >Add</button>
            </td>
        </tr>
        </table>
        <hr>
    </div>
    
  </div>
</template>

<script  src = './FieldTechnicianOwnerHandling.js'>
 
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

  .tableau {
    margin-left: auto;
    margin-right: auto;
    /* padding-left: 2px;
    padding-right: 2px;
    align-content: center; */
  }

  .tableau table {
    background-color: #ffffff;
    margin: 0px;
    align-content: center;
  }

  .tableau th {
    font-size: 17px;
    color: #696969;
    text-align: center;
    padding-left: 50px;
    padding-right: 50px;
  }

  /* .tableau input {
    size="10";
  } */

  /* .input {
    width: 10px;
  } */

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
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #FF4500;}
</style>
