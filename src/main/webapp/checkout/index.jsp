<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
  <title>Menu - TastyTasty</title>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <div class="main">
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
        <!-- START Vue: vmInformation -->
        <div class="box" id="information">
          <div class="input-group" v-if="!hasUser">
            <div class="label">Name</div>
            <input
              type="text"
              v-model="data.name"
              placeholder="Name"
              :class="{invalid: !isNameValid}">
          </div>
          <template v-if="data.orderMode==='delivery'">
            <div class="input-group"v-if="hasUser && data.addresses.length">
              <div class="label">Address</div>
              <select v-model="data.address">
                <option disabled :value="null">Please select one</option>
                <option v-for="address in data.addresses" v-bind:value="address">
                  {{ address.address1 }}, {{ address.address2 }}
                </option>
              </select>
            </div>
            <div class="input-group">
              <input
                type="text"
                v-model.trim="data.fillAddress.address1"
                placeholder="First line of Address"
                :class="{invalid: !isAddress1Valid}"
                :readonly="hasUser">
            </div>
            <div class="input-group">
              <input
                type="text"
                v-model.trim="data.fillAddress.address2"
                placeholder="Second line of Address"
                :class="{invalid: !isAddress2Valid}"
                :readonly="hasUser">
            </div>
            <div class="input-group">
              <input
                type="text"
                v-model.trim="data.fillAddress.city"
                placeholder="City"
                :class="{invalid: !isCityValid}"
                :readonly="hasUser">
              <input
                type="text"
                v-model.trim="data.fillAddress.state"
                placeholder="State"
                :class="{invalid: !isStateValid}"
                :readonly="hasUser">
              <input
                type="text"
                v-model.trim="data.fillAddress.postcode"
                placeholder="Postcode"
                :class="{invalid: !isPostcodeValid}"
                :readonly="hasUser">
            </div>
            <div class="input-group">
              <input
                type="text"
                v-model.trim="data.fillAddress.country"
                placeholder="Country"
                :class="{invalid: !isCountryValid}"
                :readonly="hasUser">
            </div>
          </template>
          <template v-else>
            <div class="title">Continue, please</div>
          </template>
          <button
            class="button"
            id="checkout"
            :disabled="!isValid"
            @click="buy()">Confirm</button>
        </div>
        <!-- END Vue: vmInformation -->
      </div>
      <div id="cart" class="right box">
        <!-- START Vue: vmOrderMode -->
        <div id="orderMode">
          <span class="selectable" :class="{active: isDelivery}" @click="orderMode='delivery'">
            <b>Delivery</b><br>
            in 15 min
          </span>
          <span class="selectable" :class="{active: isPickUp}" @click="orderMode='pick-up'">
            <b>Pick-up</b><br>
            starts 06:00am
          </span>
        </div>
        <!-- END Vue: vmOrderMode -->
        <div class="cart">
          <template v-if="cart.length == 0">
            Add menu items to your cart.
          </template>
          <cart-item
            v-for="(item, key) in cart"
            :key="key"
            :item="item"
            :index="key"
            @remove="remove($event)">
          </cart-item>
        </div>
        <div>
          <b>Total: </b>₡{{ orderTotal }}
        </div>
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
<script src="${pageContext.request.contextPath}/resources/js/checkout.js" defer></script>
</html>
