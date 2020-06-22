var data = {
  dishes: [],
  categories: [],
  mode: null,
  dish: {
    id: null,
    category: null,
    price: '',
    name: '',
    description: ''
  },
  additionalCategories: [],
  additionals: [],
  lastCategoryId: 0,
  isOverlayShown: false,
  filter: '',
  filterMode: '',
  filters: [
    'id',
    'name',
    'description',
    'category',
    'price'
  ]
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

var listitem = {
  props: ['item'],
  methods: {
    update() {
      this.$emit('update', this.item);
    }
  },
  template: `
    <div class="list-item">
      <div>
        <slot></slot>
      </div>
      <div>
        <button class="button" @click="update">
          <i class="fas fa-edit"></i>
        </button>
      </div>
    </div>
  `
};

var additional = {
  model: {
    prop: 'object',
    event: 'change'
  },
  props: {
    object: {
      type: Object,
      required: true
    }
  },
  computed: {
    isDescriptionValid: function () {
      var conditions = [
        this.object.description.length > 0,
        this.object.description.length < 25
      ];
      return conditions.every((e) => e);
    },
    isPriceValid: function () {
      var conditions = [
        this.object.price > 0,
        !isNaN(this.object.price)
      ];
      return conditions.every((e) => e);
    }
  },
  template: `
    <div class="additional-item">
      <div class="input-group">
        <input
          type="text"
          v-model.trim="object.description"
          placeholder="Description"
          :class="{invalid: !isDescriptionValid}">
        <input
          type="text"
          v-model.number="object.price"
          placeholder="Price"
          :class="{invalid: !isPriceValid}">
      </div>
    </div>
  `
};

var additionalcategory = {
  model: {
    prop: 'additionalCategory',
    event: 'change'
  },
  props: {
    additionalCategory: {
      type: Object,
      required: true
    }
  },
  data: function() {
    return {
      isShown: false
    }
  },
  computed: {
    isDescriptionValid: function () {
      var conditions = [
        this.additionalCategory.description.length > 0,
        this.additionalCategory.description.length < 25
      ];
      return conditions.every((e) => e);
    }
  },
  methods: {
    addAdditional() {
      this.$emit('add', this.additionalCategory);
    }
  },
  template: `
    <div class="additional-category dropdown-inline">
      <div class="additional-category-header">
        <div class="input-group">
          <input
            type="text"
            v-model.trim="additionalCategory.description"
            placeholder="Description"
            :class="{invalid: !isDescriptionValid}">
          <div class="checkbox">
            <input type="checkbox" v-model.trim="additionalCategory.multiple">Multiple
          </div>
          <div class="checkbox">
            <input type="checkbox" v-model.trim="additionalCategory.required">Required
          </div>
          <button class="dropdown-inline-trigger button square-button" @click="isShown=!isShown">
            <i class="fas" :class="{'fa-arrow-up': !isShown, 'fa-arrow-down': isShown}"></i>
          </button>
        </div>
      </div>
      <div class="additionals-list" v-show="isShown">
        <slot></slot>
        <button class="button" @click="addAdditional()">
          Add new additional
        </button>
      </div>
    </div>
  `
};

var vmDishes = new Vue({
  el: '#dishes',
  data: data,
  components: {
    'overlay-box': overlay,
    'list-item': listitem,
    'additional-category': additionalcategory,
    'additional-item': additional
  },
  computed: {
    additionalsFiltered: {
      get() {
        var result = []
        for(e of this.additionalCategories) {
          result.push({
            category: e,
            additionals: this.additionals.filter((x) => x.additionalCategory.id == e.id)
          });
        }
        return result;
      },
      set(a) {
        var categories = [];
        var additionals = [];
        for(e of a) {
          categories.push(e.category);
          for (i of e.additionals) {
            additionals.push(i);
          }
        }
        this.categories = categories;
        this.additionals = additionals;
      }
    },
    isNameValid: function () {
      var conditions = [
        this.dish.name.length > 0,
        this.dish.name.length < 45
      ];
      return conditions.every((e) => e);
    },
    isCategoryValid: function () {
      var conditions = [
        this.dish.category !== null
      ];
      return conditions.every((e) => e);
    },
    isPriceValid: function () {
      var conditions = [
        this.dish.price > 0,
        !isNaN(this.dish.price)
      ];
      return conditions.every((e) => e);
    },
    isDescriptionValid: function () {
      var conditions = [
        this.dish.description.length > 0,
        this.dish.description.length < 45
      ];
      return conditions.every((e) => e);
    },
    isValid: function () {
      var conditions = [
        this.isNameValid,
        this.isPriceValid,
        this.isCategoryValid,
        this.isDescriptionValid
      ];
      return conditions.every((e) => e);
    },
    filtered: function () {
      try {
        var list = this.dishes;
        var regex = RegExp(this.filter);
        return list.filter((object) => {
          if (this.filter.length === 0) return true;
          switch (this.filterMode) {
            case 'id':
              if (regex.test(object.id)) {
                return true;
              }
              break;
            case 'name':
              if (regex.test(object.name)) {
                return true;
              }
              break;
            case 'description':
              if (regex.test(object.description)) {
                return true;
              }
              break;
            case 'category':
              if (regex.test(object.category.description)) {
                return true;
              }
              break;
            case 'price':
              if (regex.test(object.price)) {
                return true;
              }
              break;
            default:
              return true;
          }
        });
      } catch(ex) {
        console.error(ex);
        return this.dishes;
      }
    }
  },
  methods: {
    addCategory() {
      this.additionalCategories.push({
        id: ++this.lastCategoryId,
        description: "",
        required: false,
        multiple: false,
        dish: this.dish,
        type: 'new'
      });
    },
    addAditional(category) {
      this.additionals.push({
        id: null,
        price: "",
        description: "",
        additionalCategory: category
      });
    },
    async update() {
      this.dishes = await loadDishes();
      this.categories = await loadCategory();
      try {
        this.lastCategoryId = await getLastAdditionaC();
      } catch(ex) { }
    },
    async addmodify(dish = null) {
      if (dish != null) {
        this.mode = 'modify';
        this.dish = {
          id: dish.id,
          category: dish.category,
          price: dish.price,
          name: dish.name,
          description: dish.description
        };
        var petition = await getDishComplements(this.dish.id);
        this.additionalCategories = petition.AdditionalCategory;
        this.additionals = petition.Additionals;
      } else {
        this.mode = 'add';
        this.dish = {
          id: null,
          category: null,
          price: '',
          name: '',
          description: ''
        };
        this.additionalCategories = [];
        this.additionals = [];
      }
      this.isOverlayShown = true;
    },
    async submit() {
      try {
        if (this.mode === 'add') {
          await createDish(
            null,
            this.dish.category,
            this.dish.price,
            this.dish.name,
            this.dish.description)
        } else {
          await updateDish(
            this.dish.id,
            this.dish.category,
            this.dish.price,
            this.dish.name,
            this.dish.description)
          var categories = this.additionalCategories.filter((e) => {
            var conditions = [
              e.description.length > 0,
              e.description.length < 25
            ];
            return conditions.every((e) => e);
          });
          var additionals = this.additionals.filter((e) => {
            var conditions = [
              e.description.length > 0,
              e.description.length < 25,
              e.price > 0,
              !isNaN(e.price)
            ];
            return conditions.every((e) => e);
          });
          for(category of categories) {
            var processed_category = null;
            if(category.type) {
              processed_category = await createAdditionalCategory(
                this.dish,
                category.description,
                category.multiple,
                category.required);
            } else {
              processed_category = await updateAdditionalCategory(
                category.id,
                this.dish,
                category.description,
                category.multiple,
                category.required);
            }
            for(additional of additionals.filter((e) => category.id == e.additionalCategory.id)) {
              if (additional.id != null) {
                await updateAdditional(
                  additional.id,
                  additional.price,
                  additional.description,
                  processed_category);
              } else {
                await createAdditional(
                  additional.price,
                  additional.description,
                  processed_category);
              }
            }
          }
        }
        this.update();
        vmNotification.showNotification(
          "Transaction processed",
          "A Dish has been created or updated",
          "information")
      } catch (ex) {
        console.log(ex);
        vmNotification.showNotification(
          "An error ocurred while trying to create/update dish",
          "Please try again later",
          "error")
      }
      this.isOverlayShown = false;
    }
  }
})

$(window).on('load', () => {
  vmDishes.update();
})
