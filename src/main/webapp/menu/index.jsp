<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
  <title>Menu - TastyTasty</title>
  <script>$(window).on('load', () => $('#a-menu').addClass('active'))</script>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <div class="main">
      <div class="left">
        <h2>Categories</h2>
        <!-- START Vue: vmCategories -->
        <ul id="categories">
          <li v-if="selected.length != 0" class="clear-selection-button">
            <a @click="selected = []" class="selectable">
              <i class="fas fa-times"></i>&nbsp;Clear selection
						</a>
          </li>
          <li v-for="category of categories" :key="category.id">
            <a @click="select(category.id)" class="selectable" :class="{selected: selected.includes(category.id)}">
              {{ category.description }}
            </a>
          </li>
        </ul>
        <!-- END Vue: vmCategories -->
      </div>
      <div class="center">
        <div class="box">
          <!-- START Vue: vmTime -->
          <div id="time">
            <dropdown-menu>
              <template #trigger>
                <i class="fas fa-clock"></i>
                <b>&nbsp;{{time}}&nbsp;</b>
              </template>
              <button class="button" @click="time='ASAP'">
                <i class="fas fa-clock"></i>&nbsp;ASAP
              </button>
              <dropdown-menu>
                <template #trigger>
                  <i class="fas fa-calendar-times"></i>
                  &nbsp;Select Date&nbsp;
                </template>
                <input type="text" id="date-picker" placeholder="Select Date..." readonly>
                <button class="button" @click="update">Set delivery time</button>
              </dropdown-menu>
            </dropdown-menu>
          </div>
          <!-- END Vue: vmTime -->
        </div>
        <!-- START Vue: vmDishes -->
        <div class="menu" id="dishes">
          <div class="box" v-for="(dishes, id) in dishesByCategory" :key="id">
            <dropdown-menu>
              <template #trigger>
                <h3>{{ getCategoryById(id) }}</h3>
              </template>
              <div class="dropdown-inline-item" v-for="dish in dishes" :key="dish.id">
                <div>
									<div class="title">{{ dish.name }}</div>
									<div class="description">{{ dish.description }}</div>
								</div>
								<div class="content-left">
									<b>\${{ dish.price }}</b>
									<button class="button">
										<i class="fas fa-plus"></i>
									</button>
								</div>
              </div>
            </dropdown-menu>
          </div>
        </div>
        <!-- END Vue: vmDishes -->
      </div>
      <div class="right box">
        <!-- START Vue: vmOrderMode -->
        <div id="orderMode">
          <span class="selectable" :class="{active: isDelivery}" @click="orderMode = 'delivery'">
            <b>Delivery</b><br>
            in 15 min
          </span>
          <span class="selectable" :class="{active: isPickUp}" @click="orderMode = 'pick-up'">
            <b>Pick-up</b><br>
            starts 06:00am
          </span>
        </div>
        <!-- END Vue: vmOrderMode -->
        <div class="cart">
          Add menu items to your cart.
        </div>
        <button class="button" id="checkout">Checkout</button>
      </div>
    </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
<script>
  $(window).on('load', () => {
    $('#date-picker').flatpickr({
      enableTime: true,
      dateFormat: "Y-m-d H:i",
      defaultDate: 'today',
      minDate: 'today',
      onChange: (_1, result, _2) => {
        $('#date-picker').val(result);
      }
    })
  })
</script>
<script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
</html>
