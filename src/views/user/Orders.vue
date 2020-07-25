<template>
	<v-data-table :headers="headers" :items="orders" multi-sort class="elevation-1" style="text-align: center; width: 90%; float: right"
	 data-app>
		<template v-slot:no-data>
			没有订单记录
		</template>
	 </v-data-table>
</template>

<script>
	export default {
		data() {
			return {
				headers: [{
						text: '报刊名称',
						align: 'start',
						value: 'newspaper',
					},
					{
						text: '订阅时间',
						value: 'submit_time'
					},
					{
						text: '费用',
						value: 'fee'
					},
					{
						text: '收货地址',
						value: 'address'
					},
					{
						text: '开始时间',
						value: 'start_time'
					},
					{
						text: '结束时间',
						value: 'end_time'
					},
					{
						text: '状态',
						value: 'status'
					}
				],
				orders: [{
						newspaper: '人民日报',
						submit_time: '2020-7-16 16:00:00',
						fee: 12.6,
						address: 'xx省xx市xx县',
						start_time: '2020-7-18',
						end_time: '2020-7-25',
						status: '未办理'
					},
					{
						newspaper: '家庭医生报',
						submit_time: '2020-7-17 16:00:00',
						fee: 3.8,
						address: 'xx省xx市xx县',
						start_time: '2020-7-24',
						end_time: '2020-7-25',
						status: '未办理'
					},
					{
						newspaper: '汽车商报',
						submit_time: '2020-7-18 16:00:00',
						fee: 3,
						address: 'xx省xx市xx县',
						start_time: '2020-7-25',
						end_time: '2020-7-25',
						status: '未办理'
					},
					{
						newspaper: '广东电视周报',
						submit_time: '2020-7-15 16:00:00',
						fee: 7.2,
						address: 'xx省xx市xx县',
						start_time: '2020-7-23',
						end_time: '2020-7-25',
						status: '未办理'
					},
					{
						newspaper: '综艺报',
						submit_time: '2020-7-14 16:00:00',
						fee: 20,
						address: 'xx省xx市xx县',
						start_time: '2020-7-26',
						end_time: '2020-7-26',
						status: '未办理'
					},
					{
						newspaper: '可乐生活',
						submit_time: '2020-7-15 16:00:00',
						fee: 5,
						address: 'xx省xx市xx县',
						start_time: '2020-7-25',
						end_time: '2020-7-25',
						status: '未办理'
					}
				]
			}
		},
	}
</script>
