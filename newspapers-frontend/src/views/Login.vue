<template>
	<div style="width: 300px; height: 200px; margin: 200px auto;">
		<v-form ref="form" lazy-validation>
			<v-text-field v-model="username" :counter="10" :rules="usernameRules" label="用户名" required></v-text-field>
			<v-text-field v-model="password" :counter="15" :rules="passwordRules" label="密码" required type="password"></v-text-field>

			<v-btn color="success" class="mr-4" @click="login" dark>
				登录
			</v-btn>
			&nbsp;
			<v-btn color="error" class="mr-4" @click="reset" dark>
				重置
			</v-btn>
			<v-radio-group v-model="type">
				<v-radio label="用户" value="user"></v-radio>
				<v-radio label="管理员" value="admin"></v-radio>
			</v-radio-group>
		</v-form>
	</div>
</template>

<script>
	import {
		request
	} from "../network/request.js"
	export default {
		data: () => ({
			username: '',
			usernameRules: [
				v => !!v || '用户名不能为空',
				v => (v && v.length <= 10) || '用户名长度必须小于10',
			],
			password: "",
			passwordRules: [
				v => !!v || "密码不能为空",
				v => (v && v.length <= 15) || "密码长度必须小于15",
			],
			type: "user"
		}),
		methods: {
			login() {
				if (this.type == "user") {
					request({
						url: "/LoginController/userLogin",
						method: "post",
						params: {
							username: this.username,
							password: this.password
						}
					}).then(res => {
						console.log(res.data);
						if (res.data != "") {
							this.$store.state.account = res.data;
							this.$emit("login", "user");
						} else {
							alert("用户名或密码错误");
						}
					})
				} else {
					request({
						url: "/LoginController/adminLogin",
						method: "post",
						params: {
							username: this.username,
							password: this.password
						}
					}).then(res => {
						console.log(res.data);
						if (res.data != "") {
							this.$store.state.account = res.data;
							this.$emit("login", "admin");
						} else {
							alert("用户名或密码错误");
						}
					})
				}
			},
			reset() {
				this.$refs.form.reset()
			}
		},
	}
</script>
