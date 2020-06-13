<header>
  <div class="header">
    <div class="title">
      <a href="${pageContext.request.contextPath}">
        <i class="fas fa-utensils"></i>
      </a>
    </div>
    <!-- START Vue: vmHeader -->
    <div class="nav" id="header">
      <ul>
        <li><a id="a-menu" href="${pageContext.request.contextPath}/menu">View Menu</a></li>
        <template v-if="isUserLoggedIn">
          <li><a id="a-user">My Account</a></li>
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
<script>
  var vmHeader = new Vue({
    el: '#header',
    data: sessionData,
    computed: {
      isUserLoggedIn: function () {
        return this.user !== null;
      }
    }
  });
</script>
