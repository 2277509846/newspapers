<template>
	<div style="width: 90%; height: 90%; float: right">
		<div>
			<div>
				<v-row>
					<v-col>
						<v-text-field label="请输入要查找的报刊"></v-text-field>
					</v-col>
					<v-col>
						<v-btn>查找</v-btn>
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
						{{newspaper.price}}<br>
						{{newspaper.admin_username}}
					</ul>
				</div>
			</a>
		</div>

		<div v-show="!showDetail">
			<v-pagination v-model="page" :length="pageCount" total-visible="10" circle color="rgb(18, 58, 188)"></v-pagination>
		</div>

		<div v-show="showDetail">
			<div style="float: left;">
				<img :src="newspaperDetail.image_url" style="width: 200px; height: 300px;" />
			</div>
			id: {{newspaperDetail.id}}<br><br>
			名称：{{newspaperDetail.name}}<br><br>
			分类：{{newspaperDetail.category}}<br><br>
			价格：{{newspaperDetail.price}}<br><br>
			管理员用户名：{{newspaperDetail.admin_username}}<br><br>
			描述：{{newspaperDetail.description}}<br><br>
			更新时间：{{newspaperDetail.update_time}}<br><br><br>
			<v-date-picker :min="new Date().toISOString().substring(0, 10)" range v-model="date" color="rgb(18, 58, 188)" small style="position: fixed; top: 30%; left: 50%"></v-date-picker>
			<div style="width: 30%; height: 30%;" data-app>
				<v-row>
					<v-col style="text-align: right;"><br>开始时间：</v-col>
					<v-col><v-text-field disable v-model="date[0]"></v-text-field></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>结束时间：</v-col>
					<v-col><v-text-field disable v-model="date[1]"></v-text-field></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>数量：</v-col>
					<v-col><v-select :items="counts" v-model="count"></v-select></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>收货地址：</v-col>
					<v-col><v-select :items="addresses" label="请选择收货地址" v-model="address"></v-select></v-text-field></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;">费用：</v-col>
					<v-col>￥{{fee.toFixed(2)}}</v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><v-btn dark @click="submit" color="primary" style="margin-right: 20px;">订阅</v-btn></v-col>
					<v-col><v-btn dark color="primary" @click="show">返回</v-btn></v-col>
				</v-row>
			</div>
		</div>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				page: 1,
				count: 1,
				newspapers: [{
						id: 1,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 2,
						name: "我们爱科学",
						category: "半月刊",
						price: 6.7,
						admin_username: "admin",
						description: "报纸于2020年6月15日创刊。",
						update_time: "每月1、15日更新",
						image_url: 'https://bic.11185.cn/zxpt-sc-cnt/upload/2/2-155/20160715160600_1.jpg'
					},
					{
						id: 3,
						name: "儿童文学",
						category: "月刊",
						price: 10,
						admin_username: "admin",
						description: "报纸于2020年6月25日创刊。",
						update_time: "每月1日更新",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/988_1535638042749_80-747_1.jpg'
					},
					{
						id: 4,
						name: "东方娃娃",
						category: "半月刊",
						price: 12,
						admin_username: "admin",
						description: "报纸于2020年6月15日创刊。",
						update_time: "每月1、19更新",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/197_1565143057900_381_SYS_159_M.jpg'
					},
					{
						id: 5,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 6,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 7,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 8,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 9,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 10,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 11,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 12,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
					{
						id: 13,
						name: "人民日报",
						category: "日刊",
						price: 1.8,
						admin_username: "admin",
						description: "报纸于1948年6月15日在河北省平山县里庄创刊。",
						update_time: "日更",
						image_url: 'https://bic.11185.cn/zxpt-sc-sys/news/576_1557219079515_816_SYS_345.jpg'
					},
				],
				pageCount: 5,
				showDetail: false,
				newspaperDetail: {

				},
				date: [new Date().toISOString().substring(0, 10)],
				address: "",
				addresses: ["xx省xx市xx县1", "xx省xx市xx县2", "xx省xx市xx县3", "xx省xx市xx县4"],
				fee: 10,
				counts: [1, 2, 3, 4, 5]
			}
		},
		methods: {
			show(newspaper) {
				this.newspaperDetail = newspaper;
				this.showDetail = !this.showDetail;
			},
			submit() {
				alert("订阅" + this.newspaperDetail.name + ", 数量：" + this.count + ", 开始时间：" + this.date[0] + ", 结束时间：" + this.date[1] + ", 收货地址：" + this.address)
			}
		},
		components: {}
	}
</script>

<style>
</style>
