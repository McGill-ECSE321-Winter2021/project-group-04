<template>
  <div>
    <div class="logout-href">
      <button @click="logout()" type="homeLogout" class="btn btn-primary">
        Logout
      </button>
    </div>
    <div class="topnav">
      <a> <router-link to="/home">Home</router-link></a>

      <a class="active">
        <router-link to="/profile"> My Profile </router-link>
      </a>

      <a>
        <router-link to="/car"> My Car </router-link>
      </a>

      <a>
        <router-link to="/receipts"> My Receipts </router-link>
      </a>
      <a>
        <router-link to="/reminders"> My Reminders </router-link>
      </a>
      <a>
        <router-link to="/bookableServices"> Our Services </router-link>
      </a>
      <a>
        <router-link to="/team"> Our Team </router-link>
      </a>
      <a>
        <router-link to="/about"> About Us </router-link>
      </a>
      <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
      </a>
    </div>
    <div class="myProfile">
      <br />
      <h2>My Profile</h2>

      <br />
      <div>
        <table class="paddingBetweenCols">
          <!-- <button class = "btn btn-primary"  @click="editProfile(newFirstName,newLastName,newAddressLine,newEmailAddress,newZipCode,newPhoneNumber)">
       Show Profile
      </button> -->
          <tr>
            <td>First Name</td>
            <td>{{ profile.firstName }}</td>
          </tr>
          <tr>
            <td>Last Name</td>
            <td>{{ profile.lastName }}</td>
          </tr>
          <tr>
            <td>Address Line</td>
            <!-- <td v-if="profile" :key="profile.addressLine"> -->

            <td>{{ profile.addressLine }}</td>
          </tr>
          <td>Email Address</td>

          <td>{{ profile.emailAddress }}</td>
          <tr>
            <td>Zip Code</td>

            <td>{{ profile.zipCode }}</td>
          </tr>
          <tr>
            <td>Phone Number</td>

            <td>{{ profile.phoneNumber }}</td>
          </tr>
        </table>
      </div>
      <br />
      <!-- <span v-if="errorProfile" style="color:red"> {{errorProfile}} </span> -->
    </div>

    <!-- <h3 class ="updateMessage"> Update </h3> -->
    <div>
      <table class="paddingBetweenCols">
        <tr>
          <td>New First Name</td>
          <td>
            <input
              type="text"
              v-model="newFirstName"
              placeholder="First Name"
              required
            />
          </td>
        </tr>
        <tr>
          <td>New Last Name</td>
          <td>
            <input
              type="text"
              v-model="newLastName"
              placeholder="Last name"
              required
            />
          </td>
        </tr>
        <tr>
          <td>New Address Line</td>
          <td>
            <input
              type="text"
              v-model="newAddressLine"
              placeholder="Address Line"
              required
            />
          </td>
        </tr>
        <tr>
          <td>New Email Address</td>
          <td>
            <input
              type="text"
              v-model="newEmailAddress"
              placeholder="Email Address"
              required
            />
          </td>
        </tr>
        <tr>
          <td>New Zip Code</td>
          <td>
            <input
              type="text"
              v-model="newZipCode"
              placeholder="Zip Code"
              required
            />
          </td>
        </tr>
        <tr>
          <td>New Phone Number</td>
          <td>
            <input
              type="text"
              v-model="newPhoneNumber"
              placeholder="Phone Number"
              required
            />
          </td>
        </tr>
      </table>
      <button
        class="btn btn-primary"
        @click="
          editProfile(
            newFirstName,
            newLastName,
            newAddressLine,
            newEmailAddress,
            newZipCode,
            newPhoneNumber
          )
        "
      >
        Save Changes
      </button>
    </div>
  </div>
</template>
<script  src = "./ProfileHandling.js">
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

/* Add a black background color to the top navigation */
.topnav {
  background-color: #696969;
  overflow: hidden;
}
/* 
 .btn btn-primary {
   right: 50px;
 } */

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
  background-color: #ff2600;
  color: white;
}
/* Hide the link that should open and close the topnav on small screens */
.topnav .icon {
  display: none;
}
/* When the screen is less than 600 pixels wide, hide all links, except for the first one ("Home"). Show the link that contains should open and close the topnav (.icon) */
@media screen and (max-width: 600px) {
  .topnav a:not(first-child) {
    display: none;
  }

  .topnav a.icon {
    float: right;
    display: block;
  }

  .myProfile h2 {
    padding-top: 10px;
    justify-content: space-around;
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
}

.paddingBetweenCols {
  margin-left: auto;
  margin-right: auto;
  padding: 100px;
}
</style>
