<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-button @click="doToggle()">
            <i :class="collapsed?classSq:classShow"></i>
        </el-button>
        <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <router-link to="/userInfo/personalInfo"><el-menu-item index="2-1">个人中心</el-menu-item></router-link>
            <router-link to="/login"><el-menu-item index="2-2" @click="loginOut()">退出</el-menu-item></router-link>
            <el-menu-item index="2-3">注销账号</el-menu-item>
        </el-submenu>
        <el-menu-item index="3">
            <div>
                <el-avatar :src="userInfo.avatar"></el-avatar>
            </div>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "HeaderNav",
        data() {
            return {
                //图标状态标识
                collapsed:false,
                //不同的状态显示不同的图标
                classShow:'el-icon-s-fold',
                classSq:'el-icon-s-unfold',
                activeIndex: '1',
                activeIndex2: '1',
                //用户登录信息
                userInfo:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            //通过按钮点击事件折叠和展开侧边导航栏，把数据传递给Main，子传父
            doToggle(){
                this.collapsed=!this.collapsed;
                console.log("TopNav组件的collapsed="+this.collapsed)
                this.$emit('openCollasped',this.collapsed);
            },
            //退出登录
            loginOut(){
                this.$router.push("/login")     //跳转到登录页面
                localStorage.removeItem("user")     //移除用户信息
                this.$message.success("退出成功")
            },
        },
    }
</script>

<style scoped>
    /*导航栏元素在页面右边*/
    .el-menu--horizontal > .el-submenu {
        float: right;
    }

    .el-menu--horizontal>.el-menu-item{
        float: right;
    }

    /*伸缩按钮样式*/
    .el-button{
        border: none;
        font-size: 24px;
    }
</style>