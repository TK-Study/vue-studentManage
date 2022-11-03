<template>
    <div class="personalInfoBox">
        <el-descriptions class="margin-top" title="关于我呀！" :column="3"  border>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    用户名
                </template>
                {{userList.userName}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-warning-outline"></i>
                    ID
                </template>
                {{userList.id}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    身份
                </template>
                <el-tag size="small">{{userList.identity}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-s-custom"></i>
                    真实姓名
                </template>
                {{userList.name}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-phone-outline"></i>
                    联系电话
                </template>
                {{userList.phone}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-message"></i>
                    Email
                </template>
                {{userList.email}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-message"></i>
                    部门
                </template>
                {{userList.department}}
            </el-descriptions-item>
        </el-descriptions>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "PersonalInfo",
        data(){
            return{
                //浏览器对象
                //用户登录信息
                userInfo:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
                userList:{}
            }
        },
        mounted() {
            //在页面挂载时加载信息
            request.get(`/user/userInfo/${this.userInfo.id}`).then(res=>{
                if(res.code === "200"){
                    this.userList = res.data
                }else {
                    //提示一下，出现错误
                    this.$message.error("请求用户列表出错，请检查")
                }
            })
        }
    }
</script>

<style scoped>
.personalInfoBox{
    padding: 20px;
}
</style>