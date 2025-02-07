import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import TownService from '../services/towns.service'
import CharacService from '../services/persos.service'

export default new Vuex.Store({
    // state = les données centralisées
    state: () => ({
        villes: [],
        persos: [],
        currentPerso: null,
        currentShop: null
    }),
    // mutations = fonctions synchrones pour mettre à jour le state (!!! interdit de modifier directement le state)
    mutations: {
        updateVilles(state, villes) {
            state.villes = villes
        },
        updatePersos(state, persos) {
            state.persos = persos
        },
        setCurrentPerso(state, perso) {
            state.currentPerso = perso
        },
        setCurrentShop(state, shop) {
            state.currentShop = shop
        },
        sell(state, item) {
            if (state.currentPerso != null && state.currentShop != null) {
                state.currentPerso.itemsAchetes.push(item);
                state.currentPerso.or -= item.prix;
                state.currentShop.itemStock = state.currentShop.itemStock.filter(i => i != item);
                // state.currentShop.itemCommande.push(item);
            }
        },
        resell(state, data) {
            if (state.currentPerso != null && state.currentShop != null) {
                state.currentShop.itemStock.push(data.item);
                state.currentPerso.or += data.gold;
                state.currentPerso.itemsAchetes = state.currentPerso.itemsAchetes.filter(i => i != data.item);
            }
        },
        stock(state, item) {
            state.currentShop.itemStock.push(item);
        },

        // =================================================================================================================================================== mutation ou méthode dans persoview
        enlever(state, data) {
            state.currentPerso.itemsAchetes.push(data.item);
            data.slot.items = data.slot.items.filter(i => i != data.item);
        },
        mettre(state, data) {
            state.currentPerso.emplacements[parseInt(data.pos) - 1].items.push(data.item);
            state.currentPerso.itemsAchetes = state.currentPerso.itemsAchetes.filter(i => i != data.item);
        }
    },
    getters: {
        currentOr(state) {
            if (state.currentPerso != null)
                return state.currentPerso.or;
            return 0;
        },
        currentShop(state) {
            return state.currentShop;
        }
    },
    // actions = fonctions asynchrone pour mettre à jour le state, en faisant appel aux mutations, via la fonction commit()
    actions: {
        async getAllTowns({commit}) {
            console.log('récupération des villes');
            let response = await TownService.getAllTowns()
            if (response.error === 0) {
                commit('updateVilles', response.data)
            } else {
                console.log(response.data)
            }
        },
        async getAllCharacs({commit}) {
            console.log('récupération des personnages');
            let response = await CharacService.getAllCharacs()
            if (response.error === 0) {
                commit('updatePersos', response.data)
            } else {
                console.log(response.data)
            }
        },
        async order(context, data) {
            setTimeout(() => {
                context.commit("stock", data.item)
            }, data.time);
        }
    }
});
