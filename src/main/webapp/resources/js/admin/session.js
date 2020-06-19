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
  admin: null,
  currentPage: ''
};

var notificationData = {
  type: '',
  title: '',
  description: '',
  isVisible: false
}

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

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
      await new Promise((res) => setTimeout(res, 3000));
      this.isVisible = false;
    }
  }
});

var vmHeader = new Vue({
  el: '#header',
  data: sessionData
});

var vmNav = new Vue({
  el: '#nav',
  data: sessionData,
  computed: {
    isAdminsSelected: function () {
      return this.currentPage === "Administrators Menu";
    },
    isUsersSelected: function () {
      return this.currentPage === "Users Menu";
    },
    isCategoriesSelected: function () {
      return this.currentPage === "Categories Menu";
    },
    isDishesSelected: function () {
      return this.currentPage === "Dishes Menu";
    },
    isOrdersSelected: function () {
      return this.currentPage === "Orders Menu";
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
  if (sessionStorage.getItem("admin")) {
    sessionData.admin = JSON.parse(sessionStorage.getItem('admin'));
  }
});

/*  Esta función guarda en la sesión el usuario (si este está disponible) extraído de sessionData.
 *  También borra el usuario de la sesión si sessionData.user pasa a ser null.
 */
$(window).on('unload', () => {
  if (sessionData.admin !== null) {
    sessionStorage.setItem('admin', JSON.stringify(sessionData.admin));
  } else {
    if (sessionStorage.getItem("admin")) {
      sessionData.admin = sessionStorage.removeItem('admin');
    }
  }
});