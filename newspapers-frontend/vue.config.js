module.exports = {
	devServer: {
		open: true,
		port: 8081,
		proxy: {
			'/api': {
				target: 'http://127.0.0.1:8080/', 
				changeOrigin: true, 
				pathRewrite: {
					'^/api': '' 
				}
			}
		}
	},
	"publicPath": "./",
	"outputDir": "dist",
	"assetsDir": "static",
  "transpileDependencies": [
    "vuetify"
  ]
}