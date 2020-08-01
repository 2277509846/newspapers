<template>
	<v-data-table :headers="headers" :items="orders" multi-sort class="elevation-1" style="text-align: center; width: 90%; float: right"
	 data-app>
		<template v-slot:no-data>
			没有订单记录
		</template>
	 </v-data-table>
</template>

<script>
	import {request} from "../../network/request.js"
	export default {
		data() {
			return {
				headers: [{
						text: '报刊id',
						align: 'start',
						value: 'newspaperName',
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
						text: '开始时间',
						value: 'startDateStr'
					},
					{
						text: '数量',
						value: 'count'
					},
					{
						text: '结束时间',
						value: 'endDateStr'
					},
					{
						text: '状态',
						value: 'statusStr'
					}
				],
				orders: []
			}
		},
		created() {
			request({
				url: "/UserController/submitList",
				params: {
					username: this.$store.state.account.username
				}
			}).then(res => this.orders = res.data).catch(err => alert(err));
		}
	}
</script>
