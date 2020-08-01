<template>
	<div style="width: 90%; height: 90%; float: right">
		<div>
			<div>
				<v-row>
					<v-col>
						<v-text-field label="请输入要查找的报刊" v-model="condition"></v-text-field>
					</v-col>
					<v-col>
						<v-btn @click="query">查找</v-btn>
					</v-col>
				</v-row>
			</div>
		</div>
		<hr><br><br><br><br>
		<div v-show="!showDetail">
			<a v-for="newspaper in newspapers" href="#" @click="show(newspaper)">
				<div style="float: left; margin-right: 30px; color: #bcbcbc; border: 1px solid #abbdcc; padding: 20px;">
					<ul>
						<img :src="newspaper.image_url" style="width: 120px; height: 120px;" /><br>
						<span style="color: #000000">{{newspaper.name}}</span><br>
						{{newspaper.category}}<br>
						￥{{newspaper.price}}<br>
						{{newspaper.admin_username}}
					</ul>
				</div>
			</a>
		</div>

		<div v-show="!showDetail">
			<v-pagination v-model="page" @input="init" :length="pageCount" total-visible="10" circle color="rgb(18, 58, 188)"></v-pagination>
			总共有{{total}}条记录
		</div>

		<div v-show="showDetail">
			<div style="float: left;">
				<img :src="newspaperDetail.image_url" style="width: 200px; height: 300px;" />
			</div>
			id: {{newspaperDetail.id}}<br><br>
			名称：{{newspaperDetail.name}}<br><br>
			分类：{{newspaperDetail.category}}<br><br>
			价格：￥{{newspaperDetail.price}}<br><br>
			管理员用户名：{{newspaperDetail.admin_username}}<br><br>
			描述：{{newspaperDetail.description}}<br><br>
			更新时间：{{newspaperDetail.update_time}}<br><br><br>
			<v-date-picker :min="new Date().toISOString().substring(0, 10)" range v-model="date" color="rgb(18, 58, 188)" small style="position: fixed; top: 30%; left: 50%"></v-date-picker>
			<div style="width: 30%; height: 30%;" data-app>
				<v-row>
					<v-col style="text-align: right;" disabled><br>开始时间：</v-col>
					<v-col><v-text-field disable v-model="date[0]" :rules="startDateRules"></v-text-field></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;" disabled><br>结束时间：</v-col>
					<v-col><v-text-field disable v-model="date[1]" :rules="endDateRules"></v-text-field></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>数量：</v-col>
					<v-col><v-select :items="counts" v-model="count"></v-select></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>收货地址：</v-col>
					<v-col><v-select :items="addressNames" :rules="addressRules" v-model="address"></v-select></v-text-field></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;">总期数：</v-col>
					<v-col>{{periods}}</v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;">费用：</v-col>
					<v-col>￥{{fee.toFixed(2)}}</v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><v-btn dark @click="submit" color="primary" style="margin-right: 20px;">订阅</v-btn></v-col>
					<v-col><v-btn dark color="primary" @click="showDetail = false">返回</v-btn></v-col>
				</v-row>
			</div>
		</div>

	</div>
</template>

<script>
	import {request} from "../../network/request.js"
	export default {
		data() {
			return {
				page: 1,
				count: 1,
				newspapers: [
						
				],
				pageCount: 5,
				showDetail: false,
				newspaperDetail: {

				},
				date: [new Date().toISOString().substring(0, 10), new Date().toISOString().substring(0, 10)],
				addressNames: [],
				addresses: [],
				address: "",
				counts: [1, 2, 3, 4, 5],
				condition: "",
				condition2: "",
				total: 0,
				periods: 0,
				startDateRules: [
					v => !!v || "开始时间不能为空"
				],
				endDateRules: [
					v => !!v || "结束时间不能为空",
					v => v > this.date[0] || "开始时间不能超过结束时间"
				],
				addressRules: [
					v => !!v || "收货地址不能为空"
				]
			}
		},
		created() {
			request({
				url: "/UserController/addressList",
				params: {username: this.$store.state.account.username}
			}).then(res => {
				this.addresses = res.data;
				for (let i in res.data) {
					this.addressNames.push(res.data[i].id + "," + res.data[i].consignee + "," + res.data[i].address);
				}
			});
			this.init();
		},
		methods: {
			show(newspaper) {
				this.newspaperDetail = newspaper;
				this.showDetail = true;
			},
			submit() {
				request({
					url: "/UserController/submit",
					params: {
						newspaperId: this.newspaperDetail.id,
						username: this.$store.state.account.username,
						fee: this.fee,
						addressId: Number.parseInt(this.address.substring(0, this.address.indexOf(","))),
						startDateStr: this.date[0],
						endDateStr: this.date[1],
						count: this.count
					},
					method: "post"
				}).then(res => alert(res.data)).catch(err => alert(err));
			},
			init() {
				request({url: "/UserController/newspaperList",
				 params: {condition: this.condition2,
					page: this.page - 1, pageCount: 14}})
					.then(res => {this.newspapers = res.data.newspapers; this.total = res.data.total; 
					this.pageCount = Math.floor((res.data.total - 1) / 14) + 1}).catch(err => alert(err))
			},
			query() {
				this.page = 1;
				this.condition2 = this.condition;
				this.showDetail = false;
				this.init();
			},
			getWeeksBetweenTwoDate(startDate, endDate, weekX) {
			    let result = 0;
			    const start = new Date(startDate).getTime();
			    const end = new Date(endDate).getTime();
			    for (let i = start; i <= end; i += 86400000) {
			        if(new Date(i).getDay() == weekX){
			            result++;
			        }
			    }
			    return result;
			},
			getMonthDaysBetweenTwoDate(startDate, endDate, monthDay) {
			    let result = 0;
			    const start = new Date(startDate).getTime();
			    const end = new Date(endDate).getTime();
			    for (let i = start; i <= end; i += 86400000) {
			        if(new Date(i).getDate() == monthDay){
			            result++;
			        }
			    }
			    return result;
			},
			getMonthDaysBetweenTwoDate(startDate, endDate, monthDay1, monthDay2) {
			    let result = 0;
			    const start = new Date(startDate).getTime();
			    const end = new Date(endDate).getTime();
			    for (let i = start; i <= end; i += 86400000) {
			        if(new Date(i).getDate() == monthDay1 || new Date(i).getDate() == monthDay2){
			            result++;
			        }
			    }
			    return result;
			},
			getDaysBetweenTwoDate(startDate, endDate) {
			    return (new Date(endDate).getTime() - new Date(startDate).getTime()) / 86400000 + 1;
			}
		},
		components: {},
		computed: {
			fee() {
				if (this.date[0] == null || this.date[1] == null) {
					this.periods = 0;
					return 0;
				}
				if (this.newspaperDetail.category == "月刊") {
					this.periods = this.getMonthDaysBetweenTwoDate(this.date[0], this.date[1], Number.parseInt(this.newspaperDetail.update_time));
					return this.newspaperDetail.price * this.periods * this.count;
				} else if (this.newspaperDetail.category == "半月刊") {
					const day1 = Number.parseInt(this.newspaperDetail.update_time.split("+")[0]);
					const day2 = Number.parseInt(this.newspaperDetail.update_time.split("+")[1]);
					this.periods = this.getMonthDaysBetweenTwoDate(this.date[0], this.date[1], day1, day2);
					return this.newspaperDetail.price * this.periods * this.count;
				} else if (this.newspaperDetail.category == "周刊") {
					this.periods = this.getWeeksBetweenTwoDate(this.date[0], this.date[1], Number.parseInt(this.newspaperDetail.update_time));
					return this.newspaperDetail.price * this.periods * this.count;
				} else if (this.newspaperDetail.category == "日刊") {
					this.periods = this.getDaysBetweenTwoDate(this.date[0], this.date[1]);
					if (this.periods < 0) {
						this.periods = 0;
						return 0;
					}
					return this.newspaperDetail.price * this.periods * this.count;
				}
				return 0;
			}
		}
	}
</script>

<style>
</style>
