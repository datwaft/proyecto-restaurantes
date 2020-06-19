<header>
  <!-- START Vue: vmHeader -->
  <div class="header" id="header">
    <a href="${pageContext.request.contextPath}/admin/">
      <i class="fas fa-utensils logo"></i>
    </a>
    <h3>{{ currentPage }}</h3>
    <a href="${pageContext.request.contextPath}/">Exit</a>
  </div>
  <!-- END Vue: vmHeader -->
</header>
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
