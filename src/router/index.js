import Vue from 'vue'
import VueRouter from 'vue-router'
import Profile from "../views/user/Profile.vue"
import Newspapers from "../views/user/Newspapers.vue"
import Addresses from "../views/user/Addresses.vue"
import Orders from "../views/user/Orders.vue"

Vue.use(VueRouter)

const routes = [
	{
		path: "/profile",
		name: "Profile",
		component: Profile
	},
	{
		path: "/newspapers",
		name: "Newspapers",
		component: Newspapers
	},
	{
		path: "/",
		name: "Newspapers1",
		component: Newspapers
	},
	{
		path: "/addresses",
		name: "Addresses",
		component: Addresses
	},
	{
		path: "/orders",
		name: "Orders",
		component: Orders
	}
]

const router = new VueRouter({
	routes
})

export default router
