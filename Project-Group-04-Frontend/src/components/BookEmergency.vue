<template>
  <div>
       <div class="logout-href">
      <button @click="logout()" type="homeLogout" class="btn btn-primary">Logout</button>
    </div>
    <div class="topnav">
      <a class="active">
        <router-link to="/home">
          Home
        </router-link>
      </a>
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
    <div >
      <hr>
      <br /> 
      <h2>Booking</h2>
      <br />
    <table class="tableau">
        <tr class="table">
            <th> Service </th>
           
            <th> Location </th>
            <th> Technician </th>

          </tr>
          <tr>
             <td>
              ​<select v-model="selectedEmergencyService">
                ​<option disabled value="">Please select one</option>
                ​<option v-for="emergencyService in emergencyServices" :key="emergencyService.name" >
                  ​{{ emergencyService.name }}
                ​</option>
              ​</select>
           ​</td>

          
                    <td>
                    <input type="text" v-model="location"  required>
                    </td>
                      <td> <select v-model="selectedFieldTechnician">
                        ​<option disabled value="">Please select one</option>
                        ​<option v-for="fieldTechnician in fieldTechnicians" :key="fieldTechnician.name" >
                            ​{{fieldTechnician.name}}
                         </option>
                ​</select>
            </td>
           
           <td>  <button
        class="btn btn-primary"
        @click="bookEmergency(selectedEmergencyService,location,selectedFieldTechnician)">
        Book
      </button>
       </td>
   <td>   
      <router-link to="/home">
        <button type="backEmergencyButton" class="btn btn-primary" color="red">
          Back
        </button>
      </router-link>
   </td>
           
        </tr>
    </table>
     

    </div>
  </div>
 </template>



 <script src = './BookEmergencyHandling.js' >
 </script>


 <style>

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
    color: #696969;
    text-align: center;
    padding-left: 60px;
    padding-right: 60px;
  }


.bookButton {
       display: flex;
    align-items: center;
    justify-content: center;
    height: 10vh;
}

.backButton {

  display: flex;
    align-items: flex-end;
    justify-content: space-around;
    height: 10vh;

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
  .logout-href {
  padding: 20px 1px;
  font-size: 25px;
  position: absolute;
  top: 0;
  right: 0;
  width: 120px;
  text-align: left;
}


</style>
 
 

