<template>
    <el-row :gutter="20">
        <el-col :span="4">
            <div class="block" style="text-align: center"><el-avatar :size="80" :src="userInfo.avatar"></el-avatar></div>
        </el-col>
        <el-col :span="14">
            <div class="grid-content bg-purple">
                <div class="userInfoBox">
                    <el-descriptions>
                        <el-descriptions-item label="用户名">{{userInfo.userName}}</el-descriptions-item>
                        <el-descriptions-item label="ID">{{userInfo.id}}</el-descriptions-item>
                        <el-descriptions-item label="姓名">{{userInfo.name}}</el-descriptions-item>
                        <el-descriptions-item label="身份">
                            <el-tag size="small">{{userInfo.identity}}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="联系电话">{{userInfo.phone}}</el-descriptions-item>
                    </el-descriptions>
                </div>
            </div>
        </el-col>
        <el-col :span="6">
            <div class="grid-content bg-purple"></div>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "HeaderUserInfo",
        data(){
            return{
                //浏览器里面保存的对象
                //用户登录信息
                userInfo:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
            }
        }
    }
</script>

<style scoped>
    .bg-purple {
        background: #ffffff;
    }
    .el-row{
        background: #ffffff;
        margin: 0;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    /**重写样式*/
    .block{
        padding: 20px;
    }
    .userInfoBox{
        padding-top: 45px;
    }
</style>