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

/*  ¡¡NOTA IMPORTANTE!!
 *  
 *  A la hora de hacer login se puede hacer usando data.email y data.password, esos siempre
 *  poseen los datos del usuario.
 */

var data = {
  /*  Esta variable guarda la dirección de email del usuario.
   *  Se actualiza de manera automática.
   */
  email: '',
  /*  Esta variable guarda la contraseña del usuario.
   *  Se actualiza de manera automática.
   */
  password: ''
};

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */

var vmLogIn = new Vue({
  el: '#login',
  data: data,
  computed: {
    isEmailValid: function () {
      var conditions = [
        this.email.length > 0,
        this.email.length <= 35
      ];
      return conditions.every((e) => e);
    },
    isPasswordValid: function () {
      var conditions = [
        this.password.length > 0,
        this.password.length <= 64
      ];
      return conditions.every((e) => e);
    },
    isValid: function() {
      var conditions = [
        this.isEmailValid,
        this.isPasswordValid
      ];
      return conditions.every((e) => e);
    }
  },
  methods: {
    async submit() {
      /*  NOTA IMPORTANTE
       *  
       *  Aquí se coloca el envío a la base de datos y la redirección si la petición es correcta.
       */
      try {
        console.log(await createSelectedAdditionalCategory(null,null));
        var user = await verifyLogin(this.email, this.password);
        sessionData.user = user;
        window.location.href = `${ctx}/`;
      } catch(ex) {
        vmNotification.showNotification(
          "An error ocurred while trying to log in",
          "Your logging information is invalid, try again please",
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
 */

$(window).on('load', async () => {
});
