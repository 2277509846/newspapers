<template>
	<div style="width: 50%; height: 50%; margin: 0 auto 0">
		<v-container>
			<v-row>
				<v-col align="right">用户名：</v-col>
				<v-col>{{username}}</v-col>
				<v-col></v-col>
			</v-row>
			<v-row>
				<v-col align="right"><br>姓名：</v-col>
				<v-col><v-text-field :disabled="dis[0]" v-model="name"></v-text-field></v-col>
				<v-col><v-icon @click="editable(0)">mdi-pencil</v-icon></v-col>
			</v-row>
			<v-row>
				<v-col align="right"><br>所在地：</v-col>
				<v-col><v-text-field :disabled="dis[1]" v-model="address"></v-text-field></v-col>
				<v-col><v-icon @click="editable(1)">mdi-pencil</v-icon></v-col>
			</v-row>
			<v-row>
				<v-col align="right"><br>手机号：</v-col>
				<v-col><v-text-field :disabled="dis[2]" v-model="phone"></v-text-field></v-col>
				<v-col><v-icon @click="editable(2)">mdi-pencil</v-icon></v-col>
			</v-row>
			<v-row>
				<v-col align="right">余额：</v-col>
				<v-col>￥{{balance.toFixed(2)}}</v-col>
				<v-col></v-col>
			</v-row>
			<v-row>
				<v-col></v-col>
				<v-col><v-btn color="primary" dark @click="save">保存</v-btn></v-col>
				<v-col></v-col>
			</v-row>
		</v-container>
	</div>
</template>

<script>
	import {request} from "../../network/request.js"
	export default {
		data() {
			return {
				username: this.$store.state.account.username,
				name: this.$store.state.account.name,
				address: this.$store.state.account.address,
				phone: this.$store.state.account.phone,
				balance: this.$store.state.account.balance,
				dis: [true, true, true]
			}
		},
		methods: {
			editable(i) {
				this.$set(this.dis, i, !this.dis[i]);
			},
			save() {
				request({url: "/UserController/updateProfile", method: "post", params: {username: this.username, name: this.name, address: this.address, phone: this.phone}})
				.then(res => alert(res.data)).catch(err => alert(err));
			}
		}
	}
</script>

<style>
</style>
