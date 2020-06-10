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
        <ul id="categories"></ul>
      </div>
      <div class="center">
        <div class="box">
          <div class="dropdown">
            <button class="dropdown-trigger button">
              <i class="fas fa-clock"></i>
              <b id="time">&nbsp;ASAP&nbsp;</b>
              <i class="fas fa-arrow-up"></i>
            </button>
            <div class="dropdown-item inactive">
              <button class="button" onclick="time.value='ASAP'">
                <i class="fas fa-clock"></i>&nbsp;ASAP
              </button>
              <div class="dropdown">
                <button class="dropdown-trigger button">
                  <i class="fas fa-calendar-times"></i>
                  &nbsp;Select Date&nbsp;
                  <i class="fas fa-arrow-up"></i>
                </button>
                <div class="dropdown-item inactive">
                  <input type="text" id="date" placeholder="Select Date..." readonly>
                  <button class="button" onclick="processDate()">Set delivery time</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="menu" id="dishes"></div>
      </div>
      <div class="right box">
        <div id="mode">
          <span id="delivery" onclick="mode.mode = 'delivery'">
            <b>Delivery</b><br>
            in 15 min
          </span>
          <span id="pick-up" onclick="mode.mode = 'pick-up'">
            <b>Pick-up</b><br>
            starts 06:00am
          </span>
        </div>
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
    $('#date').flatpickr({
      enableTime: true,
      dateFormat: "Y-m-d H:i",
      defaultDate: 'today',
      minDate: 'today',
      onChange: (_1, result, _2) => {
        $('#date').val(result)
      }
    })
  })
</script>
<script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
</html>
