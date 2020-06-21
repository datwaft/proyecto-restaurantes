var data = {
  admins: [],
  username: '',
  password: '',
  isOverlayShown: false
}

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

var vmAdmins = new Vue({
  el: '#admins',
  data: data,
  components: {
    'overlay-box': overlay
  },
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
    add() {
      this.username = '';
      this.password = '';
      this.isOverlayShown = true;
    },
    async update() {
      this.admins = await loadAdmins();
    },
    async submit() {
      try {
        await registerAdmin(this.username, this.password);
        this.update();
        vmNotification.showNotification(
          "Transaction processed",
          "An administrator has been created",
          "information")
      } catch (ex) {
        console.log(ex);
        vmNotification.showNotification(
          "An error ocurred while trying to create administrator",
          "Please try again later",
          "error")
      }
      this.isOverlayShown = false;
    }
  }
})

$(window).on('load', () => {
  vmAdmins.update();
})
