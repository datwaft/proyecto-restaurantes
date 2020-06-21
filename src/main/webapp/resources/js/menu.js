/*  NOTA IMPORTANTE 
 *  
 *  En la variable global llamada 'data' es donde se guardan los datos de esta página web,
 *  con solo modificarla ya se modifican sus datos.
 *
 *  Ejemplo: 
 *    Para actualizar las categorias basta con hacer `data.categories = [...]` y con eso ya
 *    se renderizan las nuevas categorías.
 *
 *  Adicionalmente, voy a poner una descripción a cada atributo del data para saber qué
 *  hace cada uno y qué utilidad posee.
 *
 *  Pd: Abajo del todo está la función donde se hacen las llamadas a la BD al inicio del
 *  programa.
 */ 

var data = {
  /*  Aquí van las categorias para los platillos.
   *
   *  ¿Cómo deben ser los objetos internos?
   *    Deben tener mínimo un atributo 'id' y un atributo 'description'.
   *    Se le puede cambiar el nombre a los atributos internos, nada más avisar (ejemplo: si se
   *    quiere 'name' en lugar de 'description').
   */
  categories: [],
  /*  Aquí van los platillos.
   *
   *  ¿Cómo deben ser los objetos internos?
   *    Deben tener mínimo los siguientes atributos:
   *    - id
   *    - name
   *    - description
   *    - price
   *    - category (el id de la categoría a la que pertenecen)
   *
   *  La categoría se puede cambiar cuando se vaya a usar con la base de datos.
   */
  dishes: [],
  /*  Aquí van los platillos que están en el carrito.
   *
   *  Todavía NO está planeado cómo seran, o cómo implementarlo, ahí lo vemos :D
   */
  cart: [],
  /*  Es el id de la categoría que está seleccionada actualmente.
   *  Si es null entonces se muestran todas las categorias.
   */
  selected: [],
  /*  Es el modo de entrega, o sea, si es por envío o la persona viene a recogerlo.
   *  Es un string siempre.
   *  Puede ser "delivery" o "pick-up".
   */
  orderMode: "delivery",
  /*  Es la fecha de entrega que el usuario eligió.
   *  Puede ser "ASAP", en cuyo caso equivaldría a decir la fecha y hora actual.
   *  También puede ser una fecha en formato: yyyy-MM-dd HH:mm e.g. 2020-06-10 22:53.
   */
  time: "ASAP",
  /*
  */
  isOverlayShown: false,
  dish: {
    name: '',
    description: '',
    price: '',
    additionalCategories: [],
    additionals: []
  },
  selectedDish: {
    quantity: 1
  }
};

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

/* --> Vue Components <-- */

var overlay = {
  model: {
    prop: 'isShown',
    event: 'hide'
  },
  props: {
    isShown: Boolean
  },
  methods: {
    hide() {
      this.$emit('hide', false);
    }
  },
  template: `
    <transition name="fade">
      <div class="overlay" v-if="isShown">
        <div>
          <div class="box">
            <h3>
              <slot name="title"></slot>
            </h3>
            <i class="fas fa-times-circle" @click="hide"></i>
          </div>
          <div class="box">
            <slot></slot>
          </div>
        </div>
      </div>
    </transition>
  `
};

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

/* --> Vue Instances <-- */

var vmCategories = new Vue({
  el: '#categories',
  data: data,
  methods: {
    select(id) {
      if (this.selected.includes(id)) {
        this.selected = this.selected.filter((e) => e !== id);
      } else {
        this.selected.push(id);
      }
    }
  }
});

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
})

var vmDishes = new Vue({
  el: '#dishes',
  data: data,
  components: {
    'dropdown-menu': dropdownInline,
    'overlay-box': overlay
  },
  methods: {
    getCategoryById(id) {
      var object = this.categories.find((e) => e.id == id);
      return (object ? object.description : "Invalid Category");
    },
    async showOverlay(dish) {
      this.dish.additionalCategories = [];
      this.dish.category = dish.category;
      this.dish.description = dish.description;
      this.dish.id = dish.id;
      this.dish.name = dish.name;
      this.dish.price = dish.price;

      var petition = await getDishComplements(dish.id);
      this.dish.additionalCategories = petition.AdditionalCategory;
      for (category of this.dish.additionalCategories) {
        var additionals = petition.Additionals.filter((e) => e.additionalCategory.id === category.id);
        if (additionals.length === 0) {
          this.dish.additionalCategories.filter((e) => e.id !== category.id);
        } else {
          category.additionals = additionals;
        }
      }
      this.selectedDish = {
        quantity: 1
      };
      for (category of this.dish.additionalCategories) {
        if (category.multiple) {
          Vue.set(this.selectedDish, category.id, [])
        } else {
          Vue.set(this.selectedDish, category.id, null)
        }
      }
      this.isOverlayShown = true;
    },
    addDishToCart() {
      var object = {
        dish: Object.assign({}, this.dish),
        quantity: this.selectedDish.quantity,
        categories: {}
      };
      for([key, value] of Object.entries(this.selectedDish)) {
        if (key !== 'quantity')
          object.categories[key] = value;
      }
      this.cart.push(object);
      this.isOverlayShown = false;
    }
  },
  computed: {
    isSelectionValid: function() {
      var conditions = [];
      for (category of this.dish.additionalCategories) {
        if (category.required) {
          if (category.multiple) {
            conditions.push(this.selectedDish[category.id].length > 0);
          } else {
            conditions.push(this.selectedDish[category.id] !== null);
          }
        }
      }
      return conditions.every((e) => e);
    },
    isQuantityValid: function() {
      var conditions = [
        this.selectedDish.quantity > 0,
        !isNaN(this.selectedDish.quantity)
      ];
      return conditions.every((e) => e);
    },
    isValid: function() {
      var conditions = [
        this.isSelectionValid,
        this.isQuantityValid
      ];
      return conditions.every((e) => e);
    },
    dishesByCategory: function() {
      var result = {};
      this.dishes.forEach((e) => {
        if (this.selected.length == 0 || this.selected.includes(e.category.id)) {
          if (!result[e.category.id]) {
            result[e.category.id] = [];
          }
          result[e.category.id].push(e);
        }
      });
      return result;
    }
  }
})

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
    canCheckout: function () {
      return this.cart.length > 0;
    }
  },
  methods: {
    remove(key) {
      this.cart.splice(key, 1);
    },
    checkout() {
      window.location.href = `${ctx}/checkout/`;
    }
  }
});

/* ============================================================================================ 
 * FIN DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

/*  NOTA IMPORTANTE
 *
 *  Aquí se hacen las llamadas a la BD al inicio del programa.
 *  Actualmente tienen datos de prueba.
 */

$(window).on('load', async () => {
  data.categories = await loadCategory();
  data.dishes = await loadDishes();
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

/*  Esta función guarda en la sesión el usuario (si este está disponible) extraído de sessionData.
 *  También borra el usuario de la sesión si sessionData.user pasa a ser null.
 */
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
