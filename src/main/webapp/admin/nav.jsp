<nav>
  <div id="nav" class="nav">
    <ul>
      <li :class="{selected: isAdminsSelected}">
        <a href="${pageContext.request.contextPath}/admin/admins">Admins</a>
      </li>
      <li :class="{selected: isUsersSelected}">
        <a href="${pageContext.request.contextPath}/admin/users">Users</a>
      </li>
      <li :class="{selected: isCategoriesSelected}">
        <a href="${pageContext.request.contextPath}/admin/categories">Categories</a>
      </li>
      <li :class="{selected: isDishesSelected}">
        <a href="${pageContext.request.contextPath}/admin/dishes">Dishes</a>
      </li>
      <li :class="{selected: isOrdersSelected}">
        <a href="${pageContext.request.contextPath}/admin/orders/">Orders</a>
      </li>
    </ul>
  </div>
</nav>
