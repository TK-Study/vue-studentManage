<template>
    <el-row :gutter="20">
        <el-col :span="20" :offset="2">
            <div class="grid-content bg-purple">
                <div class="text"><i class="el-icon-edit-outline">&nbsp;</i>发布公告</div>
                <div class="fromBox">
                    <el-form ref="form" :model="form" label-width="80px" label-position="top" :rules="rules">
                        <el-form-item label="公告标题" prop="title">
                            <el-input v-model="form.annuTitle"></el-input>
                        </el-form-item>
                        <el-form-item label="公告类型" prop="type">
                            <el-select v-model="form.annuType" placeholder="请选择活动区域">
                                <el-option label="教学通知" value="教学通知"></el-option>
                                <el-option label="日常生活" value="日常生活"></el-option>
                                <el-option label="疫情防控" value="疫情防控"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="主体内容" prop="body">
                            <el-input type="textarea" v-model="form.annuBody" :rows="13"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
                            <el-button @click="resetForm('form')">重置</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    import request from "@/utils/request";

    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        data() {
            return {
                form: {
                    annuTitle:'',       //文章标题
                    annuType:'',        //公告类型
                    annuBody:'',        //公告主体
                    annuName:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).name : '',     //发布人
                    annuIdentity:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).identity : '',    //身份
                },
                //表单验证
                rules: {
                    annuTitle: [
                        { required: true, message: '请输入公告标题', trigger: 'blur' },
                        { min: 5, max: 40, message: '长度在 5 到 40 个字符', trigger: 'blur' }
                    ],
                    annuType: [
                        { required: true, message: '请选择公告的类型', trigger: 'change' }
                    ],
                    annuBody: [
                        { required: true, message: '请编辑公告主体内容', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            //表单提交按钮
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //添加数据
                        request.post("/notice/save", this.form).then(res=>{
                            if(res.code === "200"){
                                this.$message.success("添加数据成功")
                            }else {
                                this.$message.error("添加数据失败，请检查")
                            }
                        })
                    }else {
                        this.$message.error("表单验证未通过，请检查")
                    }
                });
            },
            //表单重置按钮
            resetForm(formName) {
                this.$refs[formName].resetFields();
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
    }
    .text {
        text-align: center;
        font-size: 24px;
        padding: 40px;
    }
    .fromBox{
        padding: 20px;
        margin: 0 auto;
        width: 70%;
    }
</style>