<template>
	<div>
		<User @exit="exit" v-if="$store.state.type == 'user'"></User>
		<Admin @exit="exit" v-else-if="$store.state.type == 'admin'"></Admin>
		<Login @login="login" v-else></Login>
	</div>
</template>

<script>

	import Vue from "vue"
	import User from "./views/user"
	import Admin from "./views/admin"
	import Login from "./views/Login.vue"

	export default {
		name: 'App',

		components: {
			User, Admin, Login
		},

		data: () => ({
		}),
		methods: {	
			login(type) {
				this.$store.state.type = type;
			},
			exit() {
				Vue.set(this.$store.state.account, {});
				this.$store.state.type = "";
			}
		}
	};
</script>
