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

var sessionData = {
  user: null
};

var notificationData = {
  type: '',
  title: '',
  description: '',
  isVisible: false
}

/* --> Vue Components <-- */

var dropdown = {
  data: function () {
    return {
      isShown: false
    }
  },
  template: `
    <div class="dropdown">
      <div class="dropdown-trigger" @click="isShown=!isShown">
        <slot name="trigger"></slot>
        <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
      </div>
      <div v-show="isShown" class="dropdown-item vertical-nav">
        <slot></slot>
      </div>
    </div>
  `
};

/* --> Vue Instances <-- */

var vmHeader = new Vue({
  el: '#header',
  data: sessionData,
  components: {
    'dropdown-menu': dropdown
  },
  methods: {
    logOut() {
      this.user = null;
      window.location.href = `${ctx}/`;
    }
  },
  computed: {
    isUserLoggedIn: function () {
      return this.user !== null;
    }
  }
});

var vmNotification = new Vue({
  el: '#notification',
  data: notificationData,
  computed: {
    isValid: function () {
      var conditions = [
        this.type.length > 0,
        this.title.length > 0,
        this.description.length > 0,
        ['error', 'warning', 'information'].includes(this.type),
        this.isVisible
      ];
      return conditions.every((e) => e);
    },
    classObject: function () {
      return {
        error: this.type === 'error',
        warning: this.type === 'warning',
        information: this.type === 'information'
      }
    }
  },
  methods: {
    async showNotification(title, description, type) {
      this.title = title;
      this.description = description;
      this.type = type;
      this.isVisible = true;
      await new Promise((res) => setTimeout(res, 5000));
      this.isVisible = false;
    }
  }
});

/* ============================================================================================ 
 * FIN DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

/*  NOTA IMPORTANTE
 *
 *  Aquí se hacen las llamadas a la BD al inicio y al final del programa.
 *  Se usan para guardar el usuario en la sesión.
 */

/*  Esta función carga de la sesión el usuario (si este está disponible) y lo guarda en una 
 *  estructura de datos especializada llamada sessionData.
 */
$(window).on('load', () => {
  if (sessionStorage.getItem("user")) {
    sessionData.user = JSON.parse(sessionStorage.getItem('user'));
  }
});

/*  Esta función guarda en la sesión el usuario (si este está disponible) extraído de sessionData.
 *  También borra el usuario de la sesión si sessionData.user pasa a ser null.
 */
$(window).on('unload', () => {
  if (sessionData.user !== null) {
    sessionStorage.setItem('user', JSON.stringify(sessionData.user));
  } else {
    if (sessionStorage.getItem("user")) {
      sessionData.user = sessionStorage.removeItem('user');
    }
  }
});
