<template>
    <el-row :gutter="20">
        <el-col :span="4">
            <div class="grid-content bg-purple">
                <el-menu
                        default-active="/userInfo/personalInfo"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        router>
                    <el-menu-item index="/userInfo/personalInfo">
                        <i class="el-icon-user"></i>
                        <span slot="title">个人简介</span>
                    </el-menu-item>
                    <el-menu-item index="/userInfo/personalUpdate">
                        <i class="el-icon-edit"></i>
                        <span slot="title">修改信息</span>
                    </el-menu-item>
                    <el-menu-item index="/userInfo/personalQuotes">
                        <i class="el-icon-chat-line-round"></i>
                        <span slot="title">个性名言</span>
                    </el-menu-item>
                    <el-menu-item index="/userInfo/personalSetting">
                        <i class="el-icon-setting"></i>
                        <span slot="title">设置</span>
                    </el-menu-item>
                </el-menu>
            </div>
        </el-col>
        <el-col :span="20">
            <div class="grid-content bg-purple">
                <router-view></router-view>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "MainUserInfo",
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>
    .bg-purple {
        background: #ffffff;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 100%;
        min-height: 400px;
    }
</style>