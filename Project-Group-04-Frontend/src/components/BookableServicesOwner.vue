<template>
  <div>
    <div class="topnav">
      <a > <router-link to="/homeOwner">Home</router-link></a>

      

      <a class="active">
        <router-link to="/bookableServicesOwner">
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
      <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
      </a>
    </div>

   
  </div>
</template>
<script >
  
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
