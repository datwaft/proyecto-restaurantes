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
  bills: []
};

/* ============================================================================================ 
 * INICIO DE LA PROGRAMACIÓN RELACIONADA CON VUEJS
 * ============================================================================================
 */
var additional = {
  props: {
    object: {
      type: Object,
      required: true
    }
  },
  template: `
    <div class="dish">
      <table>
        <tr>
          <td class="title">description</td>
          <td class="title">price</td>
        </tr>
        <tr>
          <td> {{ object.additional.description }} </td>
          <td> {{ object.additional.price }} </td>
        </tr>
      </table>
    </div>
  `
}

var category = {
  data: function () {
    return {
      isShown: false
    }
  },
  props: {
    object: {
      type: Object,
      required: true
    }
  },
  components: {
    'component-additional': additional
  },
  template: `
    <div class="dish">
      <table>
        <tr>
          <td class="title">description</td>
          <td class="title">multiple</td>
          <td class="title">required</td>
          <td rowspan="2" @click="isShown=!isShown">
            <div class="arrow">
              <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
            </div>
          </td>
        </tr>
        <tr>
          <td> {{ object.category.additionalCategory.description }} </td>
          <td> {{ object.category.additionalCategory.multiple }} </td>
          <td> {{ object.category.additionalCategory.required }} </td>
        </tr>
        <tr v-show="isShown">
          <td colspan="5">
            <component-additional
              v-for="(additional, key) in object.additionals"
              :object="object.additionals[key]"
              :key="key">
            </component-additional>
          </td>
        </tr>
      </table>
    </div>
  `
}

var dish = {
  data: function () {
    return {
      isShown: false
    }
  },
  props: {
    object: {
      type: Object,
      required: true
    }
  },
  components: {
    'component-category': category
  },
  computed: {
    totalPrice: function () {
      if (!this.object.categories) return -1;
      var price = this.object.dish.dish.price;
      for (e in this.object.categories) {
        for (a in e.aditionals) {
          price += a.price;
        }
      }
      return price * this.object.dish.quantity;
    }
  },
  template: `
    <div class="dish">
      <table>
        <tr>
          <td class="title">category</td>
          <td class="title">price</td>
          <td class="title">name</td>
          <td class="title">quantity</td>
          <td rowspan="2" @click="isShown=!isShown" v-if="object.categories.length > 0">
            <div class="arrow">
              <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
            </div>
          </td>
        </tr>
          <td>{{ object.dish.dish.category.description }}</td>
          <td>{{ totalPrice }}</td>
          <td>{{ object.dish.dish.name }}</td>
          <td>{{ object.dish.quantity }}</td>
        <tr>
        </tr>
        <tr v-show="isShown" v-if="object.categories.length > 0">
          <td colspan="5">
            <component-category
              v-for="(category, key) in object.categories"
              :object="object.categories[key]"
              :key="key">
            </component-category>
          </td>
        </tr>
      </table>
    </div>
  `
}

var bill = {
  data: function () {
    return {
      isShown: false,
      statusList: [
        'received',
        'preparation',
        'completed'
      ]
    }
  },
  props: {
    object: {
      type: Object,
      required: true
    }
  },
  methods: {
    async change(event) {
      await updateOrderStatus(
        this.object.id,
        event.target.value
      );
      this.update();
    },
    async update() {
      data.bills = await getAllBills();
      for(e of data.bills) {
        Vue.set(e, 'selecteds', await getSelecteds(e.id));
      }
    }
  },
  computed: {
    time: function () {
      return this.object.orderTime.replace(/[TZ]/g, ' ').slice(0,16);
    },
    dishes: function() {
      var categories = [];
      for(e of this.object.selecteds.SelectedAdditionalCategory) {
        categories.push({
          category: e,
          additionals: this.object.selecteds.SelectedAdditional.filter((x) => x.selectedAdditionalCategory.id === e.id)
        });
      }
      var result = [];
      for(e of this.object.selecteds.SelectedDishes) {
        result.push({
          dish: e,
          categories: categories.filter((x) => x.category.selectedDish.id === e.id)
        });
      }
      return result;
    },
    totalPrice: function () {
      if (!this.object.dishes) return -1;
      var price = 0;
      for (dish in this.object.dishes) {
        var pricel = dish.dish.dish.price;
        for (e in dish.categories) {
          for (a in e.aditionals) {
            pricel += a.price;
          }
        }
        price += pricel * dish.dish.quantity;
      }
      return price;
    },
    name: function () {
      if (this.object.user) {
        return this.object.user.email;
      } else {
        return this.object.name;
      }
    }
  },
  components: {
    'component-dish': dish
  },
  template: `
    <div class="bill">
      <table>
        <tr>
          <td class="title">id</td>
          <td class="title">user</td>
          <td class="title">type</td>
          <td class="title">time</td>
          <td class="title">status</td>
          <td rowspan="2" @click="isShown=!isShown">
            <div class="arrow">
              <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
            </div>
          </td>
        </tr>
        <tr>
          <td>{{ object.id }}</td>
          <td>{{ name }}</td>
          <td>{{ object.orderType }}</td>
          <td>{{ time }}</td>
          <td>
            <select v-model="object.status" @change="change($event)">
              <option v-for="status in statusList" v-bind:value="status">
                {{ status }}
              </option>
            </select>
          </td>
        </tr>
        <tr v-show="isShown">
          <td colspan="5" v-if="object.selecteds">
            <component-dish
              v-for="(dish, key) in dishes"
              :object="dishes[key]"
              :key="key">
            </component-dish>
          </td>
        </tr>
      </table>
    </div>
  `
}

var vmApp = new Vue({
  el: '#app',
  data: data,
  components: {
    'component-bill': bill
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
  data.bills = await getAllBills();
  for(e of data.bills) {
    Vue.set(e, 'selecteds', await getSelecteds(e.id));
  }
});
