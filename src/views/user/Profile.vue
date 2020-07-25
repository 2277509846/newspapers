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
				<v-col align="right"><br>余额：</v-col>
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
	export default {
		data() {
			return {
				username: "zhangsan",
				name: "张三",
				address: "xx省xx市xx县",
				phone: "12345678910",
				balance: 999,
				dis: [true, true, true]
			}
		},
		methods: {
			editable(i) {
				this.$set(this.dis, i, !this.dis[i]);
			},
			save() {
				alert("name:" + this.name)
				alert("address:" + this.address)
				alert("phone:" + this.phone)
			}
		}
	}
</script>

<style>
</style>
