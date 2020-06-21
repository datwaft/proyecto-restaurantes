var data = {
  users: [],
}

var vmUsers = new Vue({
  el: '#users',
  data: data,
  methods: {
    async update() {
      this.users = await loadUsers();
    }
  }
})

$(window).on('load', () => {
  vmUsers.update();
})
