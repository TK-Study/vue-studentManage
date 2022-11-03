<template>
    <div>
        <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
            <h3 class="loginTitle">
                系统登录
            </h3>
            <el-form-item prop="userName">
                <el-input type="text" v-model="loginForm.userName" placeholder="亲，请输入用户名"  prefix-icon="el-icon-user">
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginForm.password" placeholder="亲，请输入密码" prefix-icon="el-icon-goods">
                </el-input>
            </el-form-item>
            <el-button type="primary" style="width:100%" @click="submitLogin">登录</el-button>
        </el-form>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        data(){
            return{
                loginForm:{
                    userName:"",
                    password:"",
                },
                //模拟登录的数据
                mockList:{
                    userName:'admin',
                    password:'123456',
                },
                rules:{
                    userName:[{required:true,message:"请输入用户名",trigger:"blur"},{ min: 2, max: 10, message: '长度在 5 到 14 个字符', trigger: 'blur' }
                    ],
                    password:[{required:true,message:"请输入密码",trigger:"blur"},{ min: 6,  message: '密码长度要大于6', trigger: 'blur' }],
                }
            }
        },
        methods:{
            submitLogin(){
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        request.post("/user/login", this.loginForm).then(res=>{
                            if(res.code === "200"){
                                localStorage.setItem("user", JSON.stringify(res.data))      //存储用户信息到浏览器
                                this.$message.success("登录成功")
                                this.$router.push('/')
                            }else {
                                this.$message.error(res.msg)
                            }
                        })
                    } else {
                        this.$message.error('登录出错，请重新检查');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .loginContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 35px 35px;
        background: aliceblue;
        border:1px solid #c6c6c6;
        box-shadow: 0 0 25px #030303;
    }
    .loginTitle{
        margin: 10px auto 40px auto;
        text-align: center;
        font-size: 30px;
    }
</style>