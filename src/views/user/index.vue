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
							<v-list-item-title class="title">{{username}}</v-list-item-title>
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
					<v-list-item link @click="addresses">
						<v-list-item-icon>
							<v-icon>mdi-flag</v-icon>
						</v-list-item-icon>
						<v-list-item-title>收货地址</v-list-item-title>
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
				username: "zhangsan",
				portrait: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595659217310&di=7c78c16bfe25614948160530c76dfe5c&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201309%2F26%2F20130926095128_SiPMh.jpeg"
			}
		},
		methods: {
			profile() {
				this.$router.replace("/profile");
			},
			newspapers() {
				this.$router.replace("/newspapers");
			},
			addresses() {
				this.$router.replace("/addresses");
			},
			orders() {
				this.$router.replace("/orders");
			}
		}
	}
</script>

<style>
</style>
