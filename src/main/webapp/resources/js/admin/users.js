var data = {
  users: [],
}

var vmUsers = new Vue({
  el: '#users',
  data: data,
  methods: {
    async update() {
      this.users = await getUsers();
    }
  }
})

$(window).on('load', () => {
  // vmUsers.update();
  data.users = [
    {
      email: 'cosito@mail.com',
      password: 'losiram01',
      firstName: 'Cosito',
      lastName: 'Lindo',
      cellphone: '87232504'
    },
    {
      email: 'user@mail.com',
      password: 'password',
      firstName: 'User',
      lastName: 'Default',
      cellphone: '12346789'
    }
  ]
})
