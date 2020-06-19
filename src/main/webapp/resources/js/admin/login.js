var data = {
  username: '',
  password: ''
}

var vmLogIn = new Vue({
  el: '#login',
  data: data,
  computed: {
    isUsernameValid: function () {
      var conditions = [
        this.username.length > 0,
        this.username.length <= 100
      ];
      return conditions.every((e) => e);
    },
    isPasswordValid: function () {
      var conditions = [
        this.password.length > 0,
        this.password.length <= 100
      ];
      return conditions.every((e) => e);
    },
    isValid: function () {
      var conditions = [
        this.isUsernameValid,
        this.isPasswordValid
      ];
      return conditions.every((e) => e);
    }
  },
  methods: {
    async submit() {
      try {
        // var admin = await verifyLoginAdmin(this.username, this.password);
        // sessionData.admin = admin;
        window.location.href = `${ctx}/admin/`;
      } catch (ex) {
        console.log(ex);
          vmNotification.showNotification(
          "An error ocurred while trying to log in",
          "Your logging information is invalid, try again please",
          "error")
      }
      this.isOverlayShown = false;
    }
  }
})

$(window).on('load', () => {
  if (sessionData.admin) {
    data.admin.username = sessionData.admin.username;
    data.admin.password = sessionData.admin.password;
  }
})
