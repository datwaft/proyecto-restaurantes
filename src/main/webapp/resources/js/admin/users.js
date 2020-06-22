var data = {
  users: [],
  filter: '',
  filterMode: '',
  filters: [
    'email',
    'password',
    'full name',
    'cellphone'
  ]
}

var vmUsers = new Vue({
  el: '#users',
  data: data,
  computed: {
    filtered: function () {
      try {
        var list = this.users;
        var regex = RegExp(this.filter);
        return list.filter((object) => {
          if (this.filter.length === 0) return true;
          switch (this.filterMode) {
            case 'email':
              if (regex.test(object.email)) {
                return true;
              }
              break;
            case 'password':
              if (regex.test(object.password)) {
                return true;
              }
              break;
            case 'full name':
              if (regex.test(`${object.firstName} ${object.lastName}`)) {
                return true;
              }
              break;
            case 'cellphone':
              if (regex.test(object.cellphone)) {
                return true;
              }
              break;
            default:
              return true;
          }
        });
      } catch(ex) {
        console.error(ex);
        return this.users;
      }
    }
  },
  methods: {
    async update() {
      this.users = await loadUsers();
    }
  }
})

$(window).on('load', () => {
  vmUsers.update();
})
