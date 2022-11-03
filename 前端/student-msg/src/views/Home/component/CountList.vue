<template>
    <el-row :gutter="20">
        <el-col :span="12">
            <div class="grid-content bg-purple">
                <el-card class="box-card">
                    <div class="box-1">
                        <img src="../img/teacher.png" width="70">
                    </div>
                    <div class="box-2">
                        <span style="font-size: 26px">当前教师人数：</span><span style="font-size: 24px"><strong>{{teacher}}</strong></span>
                    </div>
                </el-card>
            </div>
        </el-col>
        <el-col :span="12">
            <div class="grid-content bg-purple">
                <el-card class="box-card">
                    <div class="box-1">
                        <img src="../img/student.png" width="70">
                    </div>
                    <div class="box-2">
                        <span style="font-size: 26px">当前学生人数：</span><span style="font-size: 24px"><strong>{{student}}</strong></span>
                    </div>
                </el-card>
            </div>
        </el-col>
    </el-row>

</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "countList",
        data(){
            return{
                teacher:'',
                student:''
            }
        },
        mounted() {
            //在组件挂在时获取到教师和学生的总数
            request.get("/teacher/teacherCount").then(res=>{
                if(res.code==="200"){
                    this.teacher = res.data;
                }
            })
            request.get("/student/studentCount").then(res=>{
                if(res.code === "200"){
                    this.student = res.data
                }
            })
        }
    }
</script>

<style scoped>
    .grid-content {
        border-radius: 4px;
    }
    .box-card {
        width: 100%;
        border-radius: 5px;
        text-align: center;
    }
    /*列表盒子样式*/
    .box-1{
        float: left;
        width: 30%;
        line-height: 120px;
    }
    .box-2{
        float: left;
        width: 65%;
        line-height: 120px;
    }
    img{
        vertical-align: middle;
    }
</style>