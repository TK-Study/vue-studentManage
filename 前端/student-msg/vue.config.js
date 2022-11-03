const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
    lintOnSave:false,
    // 代理跨域,配置代理服务器
    devServer:{
        proxy:{
            '/api':{
                target:'http://localhost:9090',
                // 路径重写
                // pathRewrite:{'^/api':''},
            }
        }
    }
})
