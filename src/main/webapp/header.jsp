<header>
  <!-- START Vue: vmNotification -->
  <div id="notification">
    <transition name="fade">
      <div class="banner" v-show="isValid" :class="classObject">
        <div class="title">{{ title }}</div>
        <div class="data">
          {{ description }}
        </div>
      </div>
    </transition>
  </div>
  <!-- END Vue: vmNotification -->
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
        <li v-show="isUserLoggedIn">
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
        <li v-show="!isUserLoggedIn"><a id="a-login" href="${pageContext.request.contextPath}/user/login">Login</a></li>
        <li v-show="!isUserLoggedIn"><a id="a-register" href="${pageContext.request.contextPath}/user/register">Register</a></li>
      </ul>
    </div>
    <!-- END Vue: vmHeader -->
  </div>
</header>
