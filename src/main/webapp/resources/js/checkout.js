var data = {
  cart: [],
  orderMode: "delivery",
  time: "ASAP",
  name: '',
  addresses: [],
  address: null,
  fillAddress: {
    address1: '',
    address2: '',
    city: '',
    state: '',
    postcode: '',
    country: ''
  }
};

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

/* --> Vue Components <-- */

var dropdown = {
  data: function () {
    return {
      isShown: false
    }
  },
  template: `
    <div class="dropdown">
      <button class="dropdown-trigger button" @click="isShown=!isShown">
        <slot name="trigger"></slot>
        <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
      </button>
      <div v-show="isShown" class="dropdown-item wide">
        <slot></slot>
      </div>
    </div>
  `
};

var dropdownInline = {
  data: function () {
    return {
      isShown: true
    }
  },
  template: `
    <div class="dropdown-inline">
      <div class="dropdown-inline-trigger" @click="isShown=!isShown">
        <slot name="trigger"></slot>
        <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
      </div>
      <div v-show="isShown">
        <slot></slot>
      </div>
    </div>
  `
};

var cartItem = {
  data: function () {
    return {
      isShown: false
    }
  },
  props: {
    item: {
      type: Object,
      required: true
    },
    index: {
      type: Number,
      required: true
    }
  },
  computed: {
    totalPrice: function () {
      var price = this.item.dish.price;
      for([key, e] of Object.entries(this.item.categories)) {
        if (Array.isArray(e)) {
          for([key2, e2] of Object.entries(e)) {
            price += e2.price;
          }
        } else {
          if (e != null) {
            price += e.price;
          }
        }
      }
      return price * this.item.quantity;
    },
    hasCategories: function () {
      var conditions = [];
      for([key, e] of Object.entries(this.item.categories)) {
        if (Array.isArray(e)) {
          conditions.push(e.length > 0);
        } else {
          conditions.push(e != null);
        }
      }
      return Object.keys(this.item.categories).length !== 0 && conditions.every((e) => e);
    }
  },
  template: `
  <div class="cart-item">
    <div>
      <button class="button minus-button" @click="$emit('remove', index)">
        <i class="fas fa-minus"></i>
      </button>
      &nbsp;
      <div class="cart-item-trigger" @click="isShown=!isShown">
        {{ item.dish.name }} × {{ item.quantity }}
        <div>
        ₡{{ totalPrice }}&nbsp;
        <template v-if="hasCategories">
          <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
        </template>
        </div>
      </div>
    </div>
    <div class="cart-item-item" v-if="hasCategories" v-show="isShown">
      <div class="divisor"></div>
      <div v-for="selection in item.categories">
        <template v-if="Array.isArray(selection) && selection.length > 0">
          <b> {{ selection[0].additionalCategory.description }} </b>
          <div class="separate" v-for="sel in selection">
            <span>{{ sel.description }}</span>
            <span>₡{{ sel.price }}</span>
          </div>
        </template>
        <template v-else-if="!Array.isArray(selection) && selection !== null">
          <b> {{ selection.additionalCategory.description }} </b>
          <div class="separate">
            <span>{{ selection.description }}</span>
            <span>₡{{ selection.price }}</span>
          </div>
        </template>
      </div>
    </div>
  </div>
  `
}

/* --> Instances <-- */

var vmTime = new Vue({
  el: '#time',
  data: data,
  components: {
    'dropdown-menu': dropdown
  },
  methods: {
    update() {
      this.time = $('#date-picker').val();
    }
  }
});

var vmInformation = new Vue({
  el: '#information',
  data: {
    data: data,
    session: sessionData
  },
  watch: {
    'data.address': function(noveau, old) {
        this.data.fillAddress.address1 = noveau.address1;
        this.data.fillAddress.address2 = noveau.address2;
        this.data.fillAddress.city = noveau.city;
        this.data.fillAddress.state = noveau.state;
        this.data.fillAddress.postcode = noveau.postcode;
        this.data.fillAddress.country = noveau.country;
    }
  },
  computed: {
    hasUser: function () {
      return this.session.user !== null;
    },
    isNameValid: function () {
      var conditions = [
        this.data.name.length > 0,
        this.data.name.length < 45
      ];
      return conditions.every((e) => e);
    },
    isAddressValid: function () {
      var conditions = [
        this.data.address != null
      ];
      return conditions.every((e) => e);
    },
    isAddress1Valid: function () {
      var conditions = [
        this.data.fillAddress.address1.length > 0,
        this.data.fillAddress.address1.length <= 100
      ];
      return conditions.every((e) => e);
    },
    isAddress2Valid: function () {
      var conditions = [
        this.data.fillAddress.address2.length > 0,
        this.data.fillAddress.address2.length <= 100
      ];
      return conditions.every((e) => e);
    },
    isCityValid: function () {
      var conditions = [
        this.data.fillAddress.city.length > 0,
        this.data.fillAddress.city.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isStateValid: function () {
      var conditions = [
        this.data.fillAddress.state.length > 0,
        this.data.fillAddress.state.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isPostcodeValid: function () {
      var conditions = [
        this.data.fillAddress.postcode.length > 0,
        this.data.fillAddress.postcode.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isCountryValid: function () {
      var conditions = [
        this.data.fillAddress.country.length > 0,
        this.data.fillAddress.country.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isFillAddressValid: function () {
      var conditions = [
        this.isAddress1Valid,
        this.isAddress2Valid,
        this.isCityValid,
        this.isStateValid,
        this.isPostcodeValid,
        this.isCountryValid
      ];
      return conditions.every((e) => e);
    },
    canBuy: function () {
      return this.data.cart.length > 0;
    },
    isValid: function () {
      var conditions = [
        this.canBuy,
        this.isFillAddressValid || this.isAddressValid
      ];
      return conditions.every((e) => e);
    },
    realTime: function () {
      if (this.data.time !== 'ASAP')
        return this.data.time;
      var today = new Date().toISOString();
      return today.replace(/[TZ]/g, ' ').slice(0,16);
    }
  },
  methods: {
    async buy() {
      var bill = null;
      try {
        if (this.hasUser) {
          bill = {
            user: this.session.user,
            address: this.data.address,
            name: null,
            orderType: this.data.orderMode,
            orderTime: this.realTime,
            status: 'received'
          }
        } else {
          bill = {
            user: null,
            address: null,
            name: this.data.name,
            orderType: this.data.orderMode,
            orderTime: this.realTime,
            status: 'received'
          }
          bill.address = await createAddress(this.session.user,
            this.data.fillAddress.address1,
            this.data.fillAddress.address2,
            this.data.fillAddress.city,
            this.data.fillAddress.postcode,
            this.data.fillAddress.country,
            this.data.fillAddress.state);
        }
        console.log(bill);
        var createdBill = await createBill(
          bill.user,
          bill.address,
          bill.name,
          bill.orderType,
          bill.orderTime+":00",
          bill.status);
        console.log(createdBill);
        for (item of this.data.cart) {
          for([key, selectedDish] in item) {

          }
        }
        // this.cart = [];
        // window.location.href = `${ctx}/`;
      } catch (ex) {
        console.error(ex);
        vmNotification.showNotification(
          "An error ocurred while trying to buy",
          "Please try again please",
          "error")
      }
    }
  }
});

var vmCart = new Vue({
  el: '#cart',
  data: data,
  components: {
    'cart-item': cartItem
  },
  computed: {
    isDelivery: function () {
      return this.orderMode == "delivery";
    },
    isPickUp: function () {
      return this.orderMode == "pick-up";
    },
    orderTotal: function () {
      var total = 0;
      for(item of this.cart) {
        var price = item.dish.price;
        for([key, e] of Object.entries(item.categories)) {
          if (Array.isArray(e)) {
            for([key2, e2] of Object.entries(e)) {
              price += e2.price;
            }
          } else {
            if (e != null) {
              price += e.price;
            }
          }
        }
        total += price * item.quantity;
      }
      return total;
    }
  },
  methods: {
    remove(key) {
      this.cart.splice(key, 1);
    }
  }
});

/* ============================================================================================ 
 * FIN DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

$(window).on('load', async () => {
  if (sessionData.user) {
    data.addresses = await getDirections(sessionData.user.email);
  }
});

$(window).on('load', () => {
  if (sessionStorage.getItem("cart")) {
    data.cart = JSON.parse(sessionStorage.getItem('cart'));
  }
  if (sessionStorage.getItem("orderMode")) {
    data.orderMode = JSON.parse(sessionStorage.getItem('orderMode'));
  }
  if (sessionStorage.getItem("time")) {
    data.time = JSON.parse(sessionStorage.getItem('time'));
  }
});

$(window).on('unload', () => {
  if (data.cart !== null) {
    sessionStorage.setItem('cart', JSON.stringify(data.cart));
  } else {
    if (sessionStorage.getItem("cart")) {
      data.cart = sessionStorage.removeItem('cart');
    }
  }
  if (data.orderMode !== null) {
    sessionStorage.setItem('orderMode', JSON.stringify(data.orderMode));
  } else {
    if (sessionStorage.getItem("orderMode")) {
      data.orderMode = sessionStorage.removeItem('orderMode');
    }
  }
  if (data.time !== null) {
    sessionStorage.setItem('time', JSON.stringify(data.time));
  } else {
    if (sessionStorage.getItem("time")) {
      data.time = sessionStorage.removeItem('time');
    }
  }
});
