<template>
	<div style="width: 50%; height: 50%; margin: 0 auto 0; text-align: center;" data-app>
		<v-row>
			<v-col style="text-align: right;"><br>名称：</v-col>
			<v-col>
				<v-text-field v-model="name"></v-text-field>
			</v-col>
			<v-col></v-col>
		</v-row>
		<v-row>
			<v-col style="text-align: right;"><br>分类：</v-col>
			<v-col>
				<v-select v-model="category" :items="categorys"></v-select>
			</v-col>
			<v-col></v-col>
		</v-row>
		<v-row>
			<v-col style="text-align: right;"><br>价格：</v-col>
			<v-col>
				<v-text-field v-model="price"></v-text-field>
			</v-col>
			<v-col></v-col>
		</v-row>
		<v-row>
			<v-col style="text-align: right;"><br>描述：</v-col>
			<v-col>
				<v-text-field v-model="description"></v-text-field>
			</v-col>
			<v-col></v-col>
		</v-row>
		<v-row>
			<v-col style="text-align: right;"><br>更新时间：</v-col>
			<v-col>
				<v-text-field v-model="update_time"></v-text-field>
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
			<v-btn @click="submit">添加</v-btn>
			<v-col></v-col>
		</v-row>
	</div>
</template>

<script>
	import {request} from "../../network/request.js"
	export default {
		data() {
			return {
				name: "",
				category: "",
				price: 0,
				admin_username: this.$store.state.account.username,
				description: "",
				update_time: "",
				image_url: "",
				categorys: ["月刊", "半月刊", "周刊", "日刊"]
			};
		},
		computed: {

		},
		watch: {

		},
		created() {

		},
		methods: {
			submit() {
				let formData = new window.FormData();
				let image_url = document.getElementById("image_url");
				formData.append("image", image_url.files[0]);
				formData.append("name", this.name);
				formData.append("category", this.category);
				formData.append("price", this.price);
				formData.append("description", this.description);
				formData.append("update_time", this.update_time);
				formData.append("admin_username", this.admin_username);
				let options = {
					url: '/AdminController/addNewspaper',
					data: formData,
					method: 'post',
					headers: {
						'Content-Type': 'multipart/form-data'
					}
				}
				request(options).then(res => alert(res.data)).catch(err => alert(err));
			}
		}
	}
</script>
