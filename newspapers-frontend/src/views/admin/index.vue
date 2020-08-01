<template>
	<div class="ma-12 pa-12">
		<template>
			<v-navigation-drawer permanent expand-on-hover dark fixed>
				<v-list>
					<v-list-item class="px-2">
						<v-list-item-avatar>
							<v-img :src="portrait"></v-img>
						</v-list-item-avatar>
					</v-list-item>

					<v-list-item>
						<v-list-item-content>
							<v-list-item-title class="title">{{username}}
							<v-btn @click="exit">退出</v-btn></v-list-item-title>
						</v-list-item-content>
					</v-list-item>
				</v-list>

				<v-divider></v-divider>

				<v-list nav dense>
					<v-list-item link @click="profile">
						<v-list-item-icon>
							<v-icon>mdi-account</v-icon>
						</v-list-item-icon>
						<v-list-item-title>个人信息</v-list-item-title>
					</v-list-item>
					<v-list-item link @click="newspapers">
						<v-list-item-icon>
							<v-icon>mdi-newspaper</v-icon>
						</v-list-item-icon>
						<v-list-item-title>报刊列表</v-list-item-title>
					</v-list-item>
					<v-list-item link @click="add_newspaper">
						<v-list-item-icon>
							<v-icon>mdi-plus</v-icon>
						</v-list-item-icon>
						<v-list-item-title>添加报刊</v-list-item-title>
					</v-list-item>
					<v-list-item link @click="orders">
						<v-list-item-icon>
							<v-icon>mdi-ticket</v-icon>
						</v-list-item-icon>
						<v-list-item-title>订单记录</v-list-item-title>
					</v-list-item>
				</v-list>
			</v-navigation-drawer>
		</template>
		<router-view></router-view>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				username: this.$store.state.account.username,
				portrait: "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1870932651,3659050372&fm=26&gp=0.jpg"
			}
		},
		methods: {
			profile() {
				this.$router.replace("/admin/profile");
			},
			newspapers() {
				this.$router.replace("/admin/newspapers");
			},
			orders() {
				this.$router.replace("/admin/orders");
			},
			add_newspaper() {
				this.$router.replace("/admin/add_newspaper");
			},
			exit() {
				this.$router.replace("/");
				this.$emit("exit");
			}
		}
	}
</script>

<style>
</style>
