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
  addresses: [],
  isOverlayAddShown: false,
  id: null,
  address1: '',
  address2: '',
  city: '',
  state: '',
  postcode: '',
  country: ''
};

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

/* --> Componentes de Vue <-- */

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
    </transition>
  `
};

var listitem = {
  props: ['item'],
  methods: {
    update() {
      this.$emit('update', this.item);
    }
  },
  template: `
    <div class="list-item">
      <div>
        <slot></slot>
      </div>
      <div>
        <button class="button" @click="update">
          <i class="fas fa-edit"></i>
        </button>
      </div>
    </div>
  `
}

/* --> Instancias de Vue <-- */

var vmApp = new Vue({
  el: '#app',
  data: data,
  components: {
    'overlay-box': overlay,
    'list-item': listitem
  },
  computed: {
    isAddress1Valid: function () {
      var conditions = [
        this.address1.length > 0,
        this.address1.length <= 100
      ];
      return conditions.every((e) => e);
    },
    isAddress2Valid: function () {
      var conditions = [
        this.address2.length > 0,
        this.address2.length <= 100
      ];
      return conditions.every((e) => e);
    },
    isCityValid: function () {
      var conditions = [
        this.city.length > 0,
        this.city.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isStateValid: function () {
      var conditions = [
        this.state.length > 0,
        this.state.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isPostcodeValid: function () {
      var conditions = [
        this.postcode.length > 0,
        this.postcode.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isCountryValid: function () {
      var conditions = [
        this.country.length > 0,
        this.country.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isValid: function () {
      var conditions = [
        this.isAddress1Valid,
        this.isAddress2Valid,
        this.isCityValid,
        this.isStateValid,
        this.isPostcodeValid,
        this.isCountryValid
      ];
      return conditions.every((e) => e);
    }
  },
  methods: {
    addmodify(address = null) {
      if (address !== null) {
        this.id = address.id;
        this.address1 = address.address1;
        this.address2 = address.address2;
        this.city = address.city;
        this.state = address.state;
        this.postcode = address.postcode;
        this.country = address.country;
      } else {
        this.id = null;
        this.address1 = '';
        this.address2 = '';
        this.city = '';
        this.state = '';
        this.postcode = '';
        this.country = '';
      }
      this.isOverlayAddShown = true;
    },
    async submit() {
      try {
        if (this.id === null) {
          await createAddress(sessionData.user,
            this.address1,
            this.address2,
            this.city,
            this.postcode,
            this.country,
            this.state);
        } else {
          await updateAddress(this.id,
            this.address1,
            this.address2,
            this.city,
            this.postcode,
            this.country,
            this.state);
        }
        this.addresses = await getDirections(sessionData.user.email);
        vmNotification.showNotification(
          "Transaction processed",
          "Your information has been updated",
          "information")
      } catch (ex) {
        console.log(ex);
        vmNotification.showNotification(
          "An error ocurred while trying to update user information",
          "Your address is invalid, try again please",
          "error")
      }
      this.isOverlayAddShown = false;
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
  data.addresses = await getDirections(sessionData.user.email);
});

