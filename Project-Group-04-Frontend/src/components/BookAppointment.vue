<template>
  <div>
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
      <a href="javascript:void(0);" class="icon" onclick="myFunction()">
        <i class="fa fa-bars"></i>
      </a>
    </div> 
    <div >
      <br /> 
      <h2>Booking</h2>

      <br />
        <tr class="table">
            
          <th>Service
            <input type="text" name="serviceName" value="service name">
          </th>
          <th>Date
            <input type="text" name="date" placeholder="yyyy-mm-dd">
          </th>     
          <th>Time
            <input type="text" name="time" placeholder="hh:mm">
          </th>
        </tr>
       
      
   
    </div>
    <div class="bookButton">
      <button type="bookAppointmentButton" class="btn btn-primary">
               Book
              </button>

  </div>
  <span v-if="errorProfile" style="color:red">Error: {{errorProfile}} </span>
  </div>
 </template>
 
 <script>

 </script>

 <style>
 .table {
     display: flex;
    align-items: center;
    justify-content: center;
    height: 1vh;
}
.bookButton {
       display: flex;
    align-items: center;
    justify-content: center;
    height: 10vh;
}

 </style>
 
 
