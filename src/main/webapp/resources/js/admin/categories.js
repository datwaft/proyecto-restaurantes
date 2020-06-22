var data = {
  categories: [],
  id: null,
  description: '',
  isOverlayShown: false,
  filter: '',
  filterMode: '',
  filters: [
    'id',
    'description'
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
}

var vmCategories = new Vue({
  el: '#categories',
  data: data,
  components: {
    'overlay-box': overlay,
    'list-item': listitem
  },
  computed: {
    isDescriptionValid: function () {
      var conditions = [
        this.description.length > 0,
        this.description.length <= 45
      ];
      return conditions.every((e) => e);
    },
    isValid: function () {
      var conditions = [
        this.isDescriptionValid
      ];
      return conditions.every((e) => e);
    },
    filtered: function () {
      try {
        var list = this.categories;
        var regex = RegExp(this.filter);
        return list.filter((object) => {
          if (this.filter.length === 0) return true;
          switch (this.filterMode) {
            case 'id':
              if (regex.test(object.id)) {
                return true;
              }
              break;
            case 'description':
              if (regex.test(object.description)) {
                return true;
              }
              break;
            default:
              return true;
          }
        });
      } catch(ex) {
        console.error(ex);
        return this.categories;
      }
    }
  },
  methods: {
    addmodify(category = null) {
      if (category === null) {
        this.id = null;
        this.description = '';
      } else {
        this.id = category.id;
        this.description = category.description;
      }
      this.isOverlayShown = true;
    },
    async update() {
      this.categories = await loadCategory();
    },
    async submit() {
      try {
        if (this.id === null) {
          await createCategory(this.description);
        } else {
          await updateCategory(this.id, this.description);
        }
        this.update();
        vmNotification.showNotification(
          "Transaction processed",
          "A Category has been created or updated",
          "information")
      } catch (ex) {
        console.log(ex);
        vmNotification.showNotification(
          "An error ocurred while trying to create/update category",
          "Please try again later",
          "error")
      }
      this.isOverlayShown = false;
    }
  }
})

$(window).on('load', () => {
  vmCategories.update();
})
