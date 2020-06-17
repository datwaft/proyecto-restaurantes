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
      if (!this.session.user) {
        return "Invalid User";
      } else {
        return `${this.session.user.firstName} ${this.session.user.lastName}`;
      }
    },
    isFirstNameValid: function () {
      var conditions = [
        this.data.firstname.length > 0,
        this.data.firstname.length <= 45,
        this.data.firstname != (this.session.user ? this.session.user.firstName : '')
      ];
      return conditions.every((e) => e);
    },
    isLastNameValid: function () {
      var conditions = [
        this.data.lastname.length > 0,
        this.data.lastname.length <= 45,
        this.data.lastname != (this.session.user ? this.session.user.lastName : '')
      ];
      return conditions.every((e) => e);
    },
    isCellphoneValid: function () {
      var conditions = [
        this.data.cellphone.length > 0,
        this.data.cellphone.length <= 8,
        !isNaN(this.data.cellphone),
        this.data.cellphone != (this.session.user ? this.session.user.cellphone : '')
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
      return conditions.some((e) => e);
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
    reset() {
      this.data.email = this.session.user.email;
      this.data.firstname = this.session.user.firstName;
      this.data.lastname = this.session.user.lastName;
      this.data.cellphone = this.session.user.cellphone;
      this.data.oldpassword = '';
      this.data.password = '';
      this.data.repassword = '';
    },
    async submit () {
      var future_data = {
        email: this.session.user.email,
        password: this.isPasswordValid ? this.data.password : this.session.user.password,
        firstname: this.isFirstNameValid ? this.data.firstname : this.session.user.firstName,
        lastname: this.isLastNameValid ? this.data.lastname : this.session.user.lastName,
        cellphone: this.isCellphoneValid ? this.data.cellphone : this.session.user.cellphone
      }
      try {
        var user = await updateUser(future_data.email,
          future_data.password,
          future_data.firstname,
          future_data.lastname,
          future_data.cellphone);
        this.session.user = user;
        this.reset();
        vmNotification.showNotification(
          "Transaction processed",
          "Your information has been updated",
          "information")
      } catch (ex) {
        vmNotification.showNotification(
          "An error ocurred while trying to update user information",
          "Your user information is invalid, try again please",
          "error")
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
  vmApp.reset();
});
