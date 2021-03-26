<template>
  <body>
    <br />
    <br />
    <h1>Welcome to our Auto Repair Shop System!</h1>
    <h3>Log In or Register to get started</h3>
    <h3>
      <br />
      <router-link to="/login">
        <button type="LoginButton" class="btn btn-primary">Log In</button>
      </router-link>
    </h3>
    <h3>
      <br />
      <router-link to="/register">
      <button type="RegisterButton" class="btn btn-primary">Register</button>
      </router-link>
    </h3>
  </body>
</template>

<script>
export default {};
</script>

<style scoped>
body {
  padding: 0;
  margin: 0;
}
h1 {
  font-size: 50px;
  margin: 0;
  padding: 0;
  text-align: center;
  font-family: "arial";
  font-weight: bold;
}
h3 {
  font-size: 40px;
  margin: 0;
  padding: 0;
  text-align: center;
  font-family: "arial";
}
.btn-primary {
  padding: 30px 60px;
  font-size: 30px;
  border-radius: 10px;
}
</style>