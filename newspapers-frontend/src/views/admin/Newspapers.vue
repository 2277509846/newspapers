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
		<hr><br><br>
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
			<v-pagination @input="init" v-model="page" :length="pageCount" total-visible="10" circle color="rgb(18, 58, 188)"></v-pagination>
			总共有{{total}}条记录
		</div>

		<div v-show="showDetail">
			<div style="float: left;">
				<img :src="newspaperDetail.image_url" style="width: 200px; height: 300px;" />
			</div>
			<div style="float: left">
				id: {{newspaperDetail.id}}<br><br>
				名称：{{newspaperDetail.name}}<br><br>
				分类：{{newspaperDetail.category}}<br><br>
				价格：{{newspaperDetail.price}}<br><br>
				管理员用户名：{{newspaperDetail.admin_username}}<br><br>
				描述：{{newspaperDetail.description}}<br><br>
				更新时间：{{newspaperDetail.update_time}}<br><br><br>
				<v-btn dark color="primary" @click="showDetail = false">返回</v-btn>
				&nbsp;
				<v-btn dark color="primary" @click="remove(newspaperDetail.id)">删除</v-btn>
			</div>
			<div style="width: 50%; height: 50%; text-align: center; float: left" data-app>
				<v-row>
					<v-col style="text-align: right;"><br>名称：</v-col>
					<v-col>
						<v-text-field v-model="newspaperDetail.name"></v-text-field>
					</v-col>
					<v-col></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>分类：</v-col>
					<v-col>
						<v-select v-model="newspaperDetail.category" :items="categorys"></v-select>
					</v-col>
					<v-col></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>价格：</v-col>
					<v-col>
						<v-text-field v-model="newspaperDetail.price"></v-text-field>
					</v-col>
					<v-col></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>描述：</v-col>
					<v-col>
						<v-text-field v-model="newspaperDetail.description"></v-text-field>
					</v-col>
					<v-col></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>更新时间：</v-col>
					<v-col>
						<v-text-field v-model="newspaperDetail.update_time"></v-text-field>
					</v-col>
					<v-col></v-col>
				</v-row>
				<v-row>
					<v-col style="text-align: right;"><br>图片：</v-col>
					<v-col>
						<v-file-input id="image_url"></v-file-input>
					</v-col>
					<v-col></v-col>
				</v-row>
				<v-row>
					<v-col></v-col>
					<v-btn @click="update(newspaperDetail)" color="primary" dark>修改</v-btn>
					<v-col></v-col>
				</v-row>
			</div>
		</div>
	</div>
</template>

<script>
	import {request} from "../../network/request.js"
	import Vue from "vue"
	export default {
		data() {
			return {
				page: 1,
				count: 1,
				newspapers: [],
				pageCount: 5,
				showDetail: false,
				newspaperDetail: {

				},
				categorys: ["月刊", "半月刊", "周刊", "日刊"],
				condition: "",
				total: 0,
				condition2: ""
			}
		},
		methods: {
			show(newspaper) {
				this.newspaperDetail = newspaper;
				this.showDetail = true;
			},
			update(newspaper) {
				let formData = new window.FormData();
				let image_url = document.getElementById("image_url");
				formData.append("id", this.newspaperDetail.id);
				formData.append("image", image_url.files[0]);
				formData.append("name", this.newspaperDetail.name);
				formData.append("category", this.newspaperDetail.category);
				formData.append("price", this.newspaperDetail.price);
				formData.append("description", this.newspaperDetail.description);
				formData.append("update_time", this.newspaperDetail.update_time);
				formData.append("admin_username", this.newspaperDetail.admin_username);
				formData.append("image_url", this.newspaperDetail.image_url);
				let options = {
					url: '/AdminController/updateNewspaper',
					data: formData,
					method: 'post',
					headers: {
						'Content-Type': 'multipart/form-data'
					}
				}
				request(options).then(res => {alert(res.data); this.init()}).catch(err => alert(err));
				this.showDetail = false;
			},
			init() {
				request({url: "/AdminController/newspaperList", params: 
				{adminUsername: this.$store.state.account.username, page: this.page - 1, pageCount: 14, condition: this.condition2}})
				.then(res => {this.newspapers = res.data.newspapers; this.pageCount = Math.floor((res.data.total - 1) / 14 + 1); this.total = res.data.total});
			},
			remove(id) {
				if (confirm("你确定要删除此报刊吗？")) {
					request({url: "/AdminController/deleteNewspaper", params: {id}, method: "post"}).then(res => {alert(res.data); this.init()}).catch(err => alert(err));
					this.showDetail = false;
				}
			},
			query() {
				this.page = 1;
				this.condition2 = this.condition;
				this.init();
				this.showDetail = false;
			}
		},
		created() {
			this.init();
		},
		components: {}
	}
</script>

<style>
</style>
