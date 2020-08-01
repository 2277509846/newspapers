import Vue from 'vue'
import VueRouter from 'vue-router'
import UserProfile from "../views/user/Profile.vue"
import UserNewspapers from "../views/user/Newspapers.vue"
import UserAddresses from "../views/user/Addresses.vue"
import UserOrders from "../views/user/Orders.vue"
import AdminNewspapers from "../views/admin/Newspapers.vue"
import AdminProfile from "../views/admin/Profile.vue"
import AdminOrders from "../views/admin/Orders.vue"
import AdminAddNewspaper from "../views/admin/AddNewspaper.vue"

Vue.use(VueRouter)

const routes = [
	{
		path: "/user/profile",
		name: "UserProfile",
		component: UserProfile
	},
	{
		path: "/user/newspapers",
		name: "UserNewspapers",
		component: UserNewspapers
	},
	{
		path: "/user/addresses",
		name: "UserAddresses",
		component: UserAddresses
	},
	{
		path: "/user/orders",
		name: "UserOrders",
		component: UserOrders
	},
	{
		path: "/admin/profile",
		name: "AdminProfile",
		component: AdminProfile
	},
	{
		path: "/admin/newspapers",
		name: "AdminNewspapers",
		component: AdminNewspapers
	},
	{
		path: "/admin/add_newspaper",
		name: "AdminAddNewspaper",
		component: AdminAddNewspaper
	},
	{
		path: "/admin/orders",
		name: "AdminOrders",
		component: AdminOrders
	}
]

const router = new VueRouter({
	routes
})

export default router
