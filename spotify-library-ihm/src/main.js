import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'
import i18n from './i18n'
import moment from 'moment'

Vue.config.productionTip = false

Vue.use(require('vue-underscore'));
Vue.use(require('vue-moment'));

Vue.filter('formatDuration', function(value) {
  if (value) {
    return moment.utc(Number(value)).format("HH[:]mm[:]ss")
  }
});

new Vue({
  vuetify,
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
