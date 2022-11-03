<template>
    <div>
        <div class="UserNickNameBox">
            <span>关于我</span>
        </div>
        <el-divider></el-divider>
        <!--用户头像-->
        <div class="userImgBox">
            <el-avatar :size="100" :src="userInfo.avatar"></el-avatar>
        </div>
        <!--用户信息列表-->
        <div class="userInfoBox">
            <el-descriptions title="用户信息" :column="2" >
                <el-descriptions-item label="用户名">{{userList.userName}}</el-descriptions-item>
                <el-descriptions-item label="ID">{{userList.id}}</el-descriptions-item>
                <el-descriptions-item label="IP-地址">昆明市</el-descriptions-item>
                <el-descriptions-item label="身份">
                    <el-tag size="small">{{userList.identity}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="联系电话">{{userList.phone}}</el-descriptions-item>
            </el-descriptions>
        </div>
        <el-divider><i class="el-icon-cloudy-and-sunny"></i></el-divider>
        <!--引入天气组件-->
        <div class="weatherBox">
            <Weather></Weather>
        </div>
    </div>
</template>

<script>
    import Weather from './Weather'
    import request from "@/utils/request";
    export default {
        name: "UserInfo",
        data(){
            return{
                //浏览器里面存储的对象
                userInfo:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
                //展现在页面上的数据
                userList:{}
            }
        },
        components:{Weather},
        mounted() {
            //在组件挂在时，获取到用户信息
            request.get(`/user/userInfo/${this.userInfo.id}`).then(res =>{
                if(res.code === "200"){
                    this.userList = res.data
                }else {
                    //获取不到提醒一下
                    this.$message.error("请求用户列表失败了，请检查")
                }
            })
        },
    }
</script>

<style scoped>
    .UserNickNameBox{
        padding: 18px 20px 0 20px;
        font-size: 24px;
    }
    .userImgBox{
        text-align: center;
    }
    .userInfoBox{
        padding: 20px 50px 0 50px;
    }
</style>