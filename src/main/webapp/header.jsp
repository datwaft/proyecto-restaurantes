<header>
  <div class="header">
    <div class="title">
      <a href="${pageContext.request.contextPath}">
        <i class="fas fa-utensils logo"></i>
      </a>
    </div>
    <!-- START Vue: vmHeader -->
    <div class="nav" id="header">
      <ul>
        <li><a id="a-menu" href="${pageContext.request.contextPath}/menu">View Menu</a></li>
        <template v-if="isUserLoggedIn">
          <li>
            <dropdown-menu>
              <template #trigger>
                <a>My Account</a>
              </template>
              <a id="a-orders" href="${pageContext.request.contextPath}/user/orders">Recent Orders</a>
              <a id="a-account" href="${pageContext.request.contextPath}/user/account">My Account</a>
              <a id="a-address" href="${pageContext.request.contextPath}/user/address">Address Book</a>
              <a @click="logOut">Log Out</a>
            </dropdown-menu>
          </li>
        </template>
        <template v-else>
          <li><a id="a-login" href="${pageContext.request.contextPath}/user/login">Login</a></li>
          <li><a id="a-register" href="${pageContext.request.contextPath}/user/register">Register</a></li>
        </template>
      </ul>
    </div>
    <!-- END Vue: vmHeader -->
  </div>
</header>
