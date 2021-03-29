<template>
  <div class="container">
    <div class="register-href">
      <router-link to="/register"> Register </router-link>
    </div>
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <br />
        <br />
        <h2 class="heading">Log In!</h2>
        <h5 class="subheading">Using your User ID and Password</h5>
        <br style="margin-bottom: 240px" />
        <br style="margin-bottom: 240px" />
        <label for="floatingInput">User ID</label>
        <div class="form-floating mb-3">
          <input
            type="text"
            v-model="userId"
            class="form-control"
            id="floatingInput"
            placeholder="User ID"
            required
          />
        </div>
        <label for="floatingPassword">Password</label>
        <div class="form-floating">
          <input
            type="text"
            v-model="password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
            required
          />
        </div>
        <br style="margin-bottom: 240px" />
        <br style="margin-bottom: 240px" />
        <div class="button">
          <h3>
            <button @click="loginUser(userId, password)" type="LoginButton" class="btn btn-primary">Log In</button>
          </h3>
          <h3>
            <br />
            <router-link to="/">
              <button type="CancelButton" class="btn btn-primary">
                Cancel
              </button>
            </router-link>
          </h3>
        </div>
      </div>
      <div class="col-md-3"></div>
    </div>
    <br />
    <br />
  </div>
</template>

<script src = "./Login.js">
</script>

<style scoped>
.register-href {
  padding: 20px 1px;
  font-size: 25px;
  position: absolute;
  top: 0;
  right: 0;
  width: 120px;
  text-align: left;
}

.container {
  background-color: rgb(248, 247, 247);
}
.btn-primary {
  padding: 10px 20px;
  font-size: 25px;
  border-radius: 10px;
}
</style>