<template>
	<v-data-table :headers="headers" :items="addresses" sort-by="calories" class="elevation-1" style="text-align: center; width: 90%; float: right;" data-app>
		<template v-slot:top>
			<v-toolbar flat color="white">
				<v-toolbar-title>我的收货地址</v-toolbar-title>
				<v-divider class="mx-4" inset vertical></v-divider>
				<v-spacer></v-spacer>
				<v-dialog v-model="dialog" max-width="500px">
					<template v-slot:activator="{ on, attrs }">
						<v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">添加地址</v-btn>
					</template>
					<v-card>
						<v-card-title>
							<span class="headline">{{ formTitle }}</span>
						</v-card-title>

						<v-card-text>
							<v-container>
								<v-row>
									<v-col cols="12" sm="6" md="4">
										<v-text-field v-model="editedItem.consignee" label="收货人"></v-text-field>
									</v-col>
									<v-col cols="12" sm="6" md="4">
										<v-text-field v-model="editedItem.address" label="地址"></v-text-field>
									</v-col>
									<v-col cols="12" sm="6" md="4">
										<v-text-field v-model="editedItem.phone" label="手机号"></v-text-field>
									</v-col>
								</v-row>
							</v-container>
						</v-card-text>

						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="blue darken-1" text @click="close">取消</v-btn>
							<v-btn color="blue darken-1" text @click="save">保存</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-toolbar>
		</template>
		<template v-slot:item.actions="{ item }">
			<v-icon small class="mr-2" @click="editItem(item)">
				mdi-pencil
			</v-icon>
			<v-icon small @click="deleteItem(item)">
				mdi-delete
			</v-icon>
		</template>
		<template v-slot:no-data>
			没有收货地址
		</template>
	</v-data-table>
</template>

<script>
	import {request} from "../../network/request.js"
	export default {
		data(){ 
			return {
				dialog: false,
				headers: [{
						text: 'id',
						align: 'start',
						sortable: false,
						value: 'id',
					},
					{
						text: '收货人',
						value: 'consignee'
					},
					{
						text: '地址',
						value: 'address'
					},
					{
						text: '手机号',
						value: 'phone'
					},
					{
						text: "编辑",
						value: 'actions'
					}
				],
				addresses: [],
				editedIndex: -1,
				editedItem: {
					consignee: '',
					address: '',
					phone: ''
				},
				username: this.$store.state.account.username,
				defaultItem: {
					consignee: '',
					address: '',
					phone: ''
				}
			}
		},
		computed: {
			formTitle() {
				return this.editedIndex === -1 ? '新增地址' : '编辑地址'
			},
		},
		watch: {
			dialog(val) {
				val || this.close()
			}
		},
		created() {
			this.init()
		},
		methods: {
			init() {
				request({
					url: "/UserController/addressList",
					params: {
						username: this.username
					}
				}).then(res => this.addresses = res.data).catch(err => alert(err));
			},
			editItem(item) {
				this.editedIndex = this.addresses.indexOf(item)
				this.editedItem = Object.assign({}, item)
				this.dialog = true
			},
			deleteItem(item) {
				if (confirm('你确定要删除这个收货地址吗？')) {
					request({
						url: "/UserController/deleteAddress",
						params: {
							id: item.id
						},
						method: "post"
					}).then(res => {
						alert(res.data);
					}).catch(err => alert(err));
					this.init();
				}
			},
			close() {
				this.dialog = false
				this.$nextTick(() => {
					this.editedItem = Object.assign({}, this.defaultItem)
					this.editedIndex = -1
				})
			},
			save() {
				if (this.editedIndex > -1) {
					request({
						url: "/UserController/updateAddress", 
						params: {
							id: this.editedItem.id,
							consignee: this.editedItem.consignee,
							address: this.editedItem.address,
							phone: this.editedItem.phone
						},
						method: "post"
					}).then(res => {alert(res.data); this.init()}).catch(err => alert(err));
				} else {
					request({
						url: "/UserController/addAddress", 
						params: {
							consignee: this.editedItem.consignee,
							address: this.editedItem.address,
							phone: this.editedItem.phone,
							username: this.$store.state.account.username
						},
						method: "post"
					}).then(res => {alert(res.data); this.init()}).catch(err => alert(err));
				}
				this.close()
			},
		},
	}
</script>
