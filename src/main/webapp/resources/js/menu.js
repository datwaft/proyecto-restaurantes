var categories = []
var dishes = []
var time = {
  val: null,
  listener: changeTime,
  set value(val) {
    if (this.val != val) {
      this.val = val
      this.listener(this)
    }
  },
  get value() {
    return this.val
  }
}
var mode = {
  delivery: false,
  pickup: false,
  listener: changeMode,
  set mode(val) {
    if (val == "delivery" && !this.delivery) {
      this.delivery = true
      this.pickup = false
      this.listener(this)
    } else if (val == "pick-up" && !this.pickup) {
      this.pickup = true
      this.delivery = false
      this.listener(this)
    }
  },
  get mode() {
    if (this.delivery) return "delivery"
    if (this.pickup) return "pick-up"
  }
}
var cart = {
  array: [],
  listener: modifyCart,
  push: function(e) {
    this.array.push(e)
    this.listener(this)
  },
  pop: function(callback) {
    let x = this.array.findIndex(callback)
    if (x >= 0) {
      this.array.splice(x, x+1)
      this.listener(this)
    }
  }
}

$(window).on('load', async () => {
  time.value = "ASAP"
  mode.mode = "delivery"
  await updateCategories()
  renderCategories()
  await updateDishes()
  renderDishes()
})

function renderCategories() {
  let ul =  $('#categories')
  ul.html(`
    <li id="clear-selection-button" style="display: none; color: red;">
      <a onclick="renderDishes()">
        <i class="fas fa-times"></i>
        Clear selection
      </a>
    </li>
  `)
  categories.forEach((e) => {
    let li = $('<li>')
    li.html(`
      <a onclick="renderDishes(${e.id})">
        ${e.description}
      </a>
    `)
    ul.append(li)
  })
}
async function updateCategories() {
  categories = [
    {id: 0, description: "Appetizer"},
    {id: 1, description: "Main Course"},
    {id: 2, description: "Salads"},
    {id: 3, description: "Seafoods"},
    {id: 4, description: "Traditional"},
    {id: 5, description: "Vegetarian"},
    {id: 6, description: "Soups"},
    {id: 7, description: "Desserts"},
    {id: 8, description: "Drinks"},
    {id: 9, description: "Specials"},
    {id: 10, description: "Rice Dishes"}
  ]
}

function renderDishes(category = null) {
  let div =  $('#dishes')
  div.html('')
  if (category === null) {
    var to_render = dishes
    $('#clear-selection-button').css({
      display: "none"
    })
  } else {
    var to_render = dishes.filter((e) => e.category === category)
    $('#clear-selection-button').css({
      display: "block"
    })
  }
  to_render.forEach((e) => {
    let box = $('<div>', {
      class: 'box'
    })
    box.html(`
      <div class="dropdown-inline">
        <div class="dropdown-inline-trigger">
          <h3>${categories.find((x) => e.category === x.id).description}</h3>
          <i class="fas fa-arrow-down"></i>
        </div>
        <div class="dropdown-inline-item">
          <div>
            <div class="title">${e.name}</div>
            <div class="description">${e.description}</div>
          </div>
          <div class="content-left">
            <b>$${e.price}</b>
            <button class="button" onclick="cart.push(${e.id})">
              <i class="fas fa-plus"></i>
            </button>
          </div>
        </div>
      </div>
    `)
    div.append(box)
  })
  var dropdown = $('.dropdown-inline');
  dropdown.each((_, e) => {
    let button = $(e).children('.dropdown-inline-trigger')
    button.click(() => {
      $(e).children('.dropdown-inline-item').toggleClass('inactive')
      button.children('.fa-arrow-down, .fa-arrow-up')
        .toggleClass('fa-arrow-up')
        .toggleClass('fa-arrow-down')
    })
  })
}
async function updateDishes() {
  dishes = [
    {id: 0, name: "PUFF-PUFF", description: "Is very tasty", price: '4.99', category: 0},
    {id: 1, name: "Pika Pika", description: "Do not eat pickachu", price: '199.99', category: 0},
    {id: 2, name: "Pika Pika Not", description: "Un rico plato", price: '50', category: 1}
  ]
}

function modifyCart(result) {
  console.log("TODO: Cart has been modified")
  console.log(result.array)
}

function changeMode(mode) {
  if (mode.delivery) {
    $('#pick-up').removeClass('active')
    $('#delivery').addClass('active')
  } else {
    $('#delivery').removeClass('active')
    $('#pick-up').addClass('active')
  }
}

function processDate() {
  let date = $('#date').val()
  if (date !== "") {
    time.value = date
  }
}

function changeTime(time) {
  console.log("TODO: Time has been changed")
  console.log(time.value)
  $('#time').text(time.value)
}
