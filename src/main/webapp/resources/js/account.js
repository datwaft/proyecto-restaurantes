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
  email: '',
  firstname: '',
  lastname: '',
  cellphone: '',
  oldpassword: '',
  password: '',
  repassword: ''
};

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

var vmApp = new Vue({
  el: '#app',
  data: {
    data: data,
    session: sessionData
  },
  computed: {
    fullName: function () {
      if (this.session.user === null) {
        return "Invalid User";
      } else {
        return `${this.session.user.firstName} ${this.session.user.lastName}`;
      }
    },
    isFirstNameValid: function () {
      var conditions = [
        this.data.firstname.length > 0,
        this.data.firstname.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isLastNameValid: function () {
      var conditions = [
        this.data.lastname.length > 0,
        this.data.lastname.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isCellphoneValid: function () {
      var conditions = [
        this.data.cellphone.length > 0,
        this.data.cellphone.length <= 8,
        !isNaN(this.data.cellphone)
      ];
      return conditions.every((e) => e);
    },
    isOldPasswordValid: function () {
      var conditions = [
        this.data.oldpassword.length > 0,
        this.data.oldpassword.length <= 64,
        this.data.oldpassword == (this.session.user ? this.session.user.password : '')
      ];
      return conditions.every((e) => e);
    },
    isNewPasswordValid: function () {
      var conditions = [
        this.data.password.length > 0,
        this.data.password.length <= 64,
        this.data.password != this.data.oldpassword
      ];
      return conditions.every((e) => e);
    },
    isRePasswordValid: function () {
      var conditions = [
        this.data.repassword.length > 0,
        this.data.repassword.length <= 64,
        this.data.repassword == this.data.password
      ];
      return conditions.every((e) => e);
    },
    isDataValid: function () {
      var conditions = [
        this.isFirstNameValid,
        this.isLastNameValid,
        this.isCellphoneValid
      ];
      return conditions.every((e) => e);
    },
    isPasswordValid: function () {
      var conditions = [
        this.isOldPasswordValid,
        this.isNewPasswordValid,
        this.isRePasswordValid
      ];
      return conditions.every((e) => e);
    },
    isValid: function () {
      var conditions = [
        this.isDataValid,
        this.isPasswordValid
      ];
      return conditions.some((e) => e);
    }
  },
  methods: {
    async submit () {
      if (this.isDataValid && this.isPasswordValid) {

      } else if (this.isDataValid) {

      } else if (this.isPasswordValid) {

      }
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
  data.email = sessionData.user.email;
  data.firstname = sessionData.user.firstName;
  data.lastname = sessionData.user.lastName;
  data.cellphone = sessionData.user.cellphone;
});
