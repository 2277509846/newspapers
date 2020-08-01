<template>
	<v-data-table :headers="headers" :items="orders" multi-sort class="elevation-1" style="text-align: center; width: 90%; float: right"
	 data-app>
		<template v-slot:no-data>
			没有订单记录
		</template>
		<template v-slot:item.actions="{ item }">
			<v-btn v-if="item.statusStr=='未办理'" color="primary" dark @click="manage(item)">办理</v-btn>
			<span v-else>已办理</span>
		</template>
	 </v-data-table>
</template>

<script>
	import {request} from "../../network/request.js"
	export default {
		data() {
			return {
				headers: [{
						text: '报刊名称',
						align: 'start',
						value: 'newspaperName',
					},
					{
						text: '订阅人',
						value: 'username'
					},
					{
						text: '订阅时间',
						value: 'submitTimeStr'
					},
					{
						text: '费用',
						value: 'feeStr'
					},
					{
						text: '收货地址',
						value: 'addressName'
					},
					{
						text: '数量',
						value: 'count'
					},
					{
						text: '开始时间',
						value: 'startDateStr'
					},
					{
						text: '结束时间',
						value: 'endDateStr'
					},
					{
						text: '状态',
						value: 'actions'
					}
				],
				orders: []
			}
		},
		methods: {
			init() {
				request({
					url: "/AdminController/submitList",
					params: {
						adminUsername: this.$store.state.account.username
					}
				}).then(res => this.orders = res.data).catch(err => alert(err));
			},
			manage(item) {
				request({
					url: "/AdminController/manage",
					params: {
						newspaperId: item.newspaperId,
						username: item.username,
						submitTimeStr: item.submitTimeStr
					},
					method: "post"
				});
				this.init();
			}
		},
		created() {
			this.init();
		}
	}
</script>
