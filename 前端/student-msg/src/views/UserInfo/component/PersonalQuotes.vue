<template>
    <div class="personalInfoBox">
        <div class="TextBox">
            <br/>
            <h3 style="font-family: beg;"><i class="el-icon-medal-1"></i>&nbsp;个人名言</h3>
            <el-divider><i class="el-icon-ship"></i></el-divider>
            <div class="Text">
                {{user.motto}}
            </div>
            <br/>
            <br/>
            <div class="Text" style="text-align: center">
                <div style="text-align: left"><i class="el-icon-edit"></i>修改签名</div>
                <br/>
                <el-input type="textarea" :rows="4" v-model="user.motto" ></el-input>
                <br/>
                <div class="butBox">
                    <el-button type="primary" plain icon="el-icon-s-promotion" @click="submitQuotes(user)">提交修改</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "PersonalQuotes",
        data(){
          return{
              user:{
              },
          }
        },
        //在挂在时请求出用户信息的个性签名
        mounted() {
            this.getMotto();
        },
        methods:{
            //提交个性签名的修改
            submitQuotes(user){
                if(user.motto !== undefined){
                    request.post("/user/save",user).then(res=>{
                        if(res.code === "200"){
                            this.$message.success("修改成功")
                            this.getMotto();
                        }
                    })
                }else {
                    this.$message.error("修改签名不能为空")
                }
            },
            //获取用户motto
            getMotto(){
                this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
                request.get(`/user/userMotto/${this.user.userName}`).then(res=>{
                    if(res.code === "200"){
                        this.user = res.data;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    @font-face {
        font-family: 'beg';
        src: url("../font/font.ttf") format("truetype");
    }
    .personalInfoBox{
        padding: 20px;
    }
    .TextBox{
        text-align: center;
        height: 500px;
        background-image: url("../img/bg.png");
    }
    .Text{
        margin: 0 auto;
        width: 85%;
        /*渐变色*/
        background-image: linear-gradient(to top, rgba(207, 217, 223, 0.5) 0%, rgba(226, 235, 240, 0.68) 100%);
        padding: 10px;
        text-align: left;
        border-radius: 10px;
        /*字符间距*/
        letter-spacing:2px;
        line-height: 30px;
        font-family: beg;
        font-size: 18px;
    }
    .butBox{
        margin-top: 20px;
    }
</style>