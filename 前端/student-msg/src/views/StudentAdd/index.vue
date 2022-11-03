<template>
    <el-row>
        <el-col :span="24">
            <div class="grid-content bg-purple-dark">
                <div class="fromBox">
                    <div class="text"><i class="el-icon-user-solid">&nbsp;</i>添加学生信息</div>
                    <el-form ref="form" :rules="rules" :model="form" label-width="80px" status-icon>
                        <!--姓名-->
                        <el-form-item label="姓名" prop="stuName">
                            <el-input v-model="form.stuName"></el-input>
                        </el-form-item>
                        <!--民族-->
                        <el-form-item label="民族" prop="stuNational">
                            <el-input v-model="form.stuNational"></el-input>
                        </el-form-item>
                        <!--性别-->
                        <el-form-item label="性别" prop="stuSex">
                            <el-input v-model="form.stuSex"></el-input>
                        </el-form-item>
                        <!--学号-->
                        <el-form-item label="学号" prop="stuId">
                            <el-input v-model="form.stuId"></el-input>
                        </el-form-item>
                        <!--身份证号-->
                        <el-form-item label="身份证号" prop="sfId">
                            <el-input v-model="form.sfId"></el-input>
                        </el-form-item>
                        <!--出生日期-->
                        <el-form-item label="出生日期" prop="stuDate">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.stuDate"
                                            style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
                        </el-form-item>
                        <!--联系电话-->
                        <el-form-item label="联系电话" prop="stuPhone">
                            <el-input v-model="form.stuPhone"></el-input>
                        </el-form-item>
                        <!--家庭住址-->
                        <el-form-item label="家庭住址" prop="stuAddress">
                            <el-input v-model="form.stuAddress"></el-input>
                        </el-form-item>
                        <!--二级学院-->
                        <el-form-item label="二级学院" prop="stuSecondarySchool">
                            <el-select v-model="form.stuSecondarySchool" placeholder="请选择所在二级学院">
                                <el-option label="智能科学与工程学院" value="智能科学与工程学院"></el-option>
                                <el-option label="教育学院" value="教育学院"></el-option>
                                <el-option label="会计学院" value="会计学院"></el-option>
                                <el-option label="大健康学院" value="大健康学院"></el-option>
                                <el-option label="经济与管理学院" value="经济与管理学院"></el-option>
                                <el-option label="建筑工程学院" value="建筑工程学院"></el-option>
                                <el-option label="人文艺术学院" value="人文艺术学院"></el-option>
                                <el-option label="丝路学院" value="丝路学院"></el-option>
                            </el-select>
                        </el-form-item>
                        <!--所在班级-->
                        <el-form-item label="所在班级" prop="stuClass">
                            <el-input v-model="form.stuClass"></el-input>
                        </el-form-item>
                        <!--政治面貌-->
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
            //表单验证规则
            return {
                //接收表单数据
                form: {
                    sfId: '',
                    stuAddress: '',
                    stuClass: '',
                    stuDate: '',
                    stuId: '',
                    stuName: '',
                    stuNational: '',
                    stuPhone: '',
                    stuSecondarySchool: '',
                    stuSex: ''
                },
                //表单验证
                rules: {
                    stuName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    stuNational: [
                        {required: true, message: '请输入民族', trigger: 'change'}
                    ],
                    stuSex: [
                        {required: true, message: '请输入性别', trigger: 'change'}
                    ],
                    stuId: [
                        {required: true, message: '请输入学号', trigger: 'change'}
                    ],
                    sfId: [
                        {required: true, message: '请输入身份证号', trigger: 'change'}
                    ],
                    stuDate: [
                        {required: true, message: '请选择日期', trigger: 'change'}
                    ],
                    stuPhone: [
                        {required: true, message: '请输入联系电话', trigger: 'change'}
                    ],
                    stuAddress: [
                        {required: true, message: '请输入地址', trigger: 'change'}
                    ],
                    stuSecondarySchool: [
                        {required: true, message: '请选择所在二级学院', trigger: 'change'}
                    ],
                    stuClass: [
                        {required: true, message: '请输入班级', trigger: 'change'}
                    ],
                }
            }
        },
        methods: {
            //表单验证规则
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        request.post("/student/save", this.form).then(res =>{
                            if(res.code === "200"){
                                this.$message.success("添加学生信息成功")
                                //重置表单
                                this.resetForm(formName);
                            }else {
                                this.$message.error("添加学生信息失败，请检查字段，重新添加")
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //表单重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
    .bg-purple-dark {
        background: #fdfdfd;
    }

    .grid-content {
        border-radius: 4px;
    }

    .text {
        text-align: center;
        font-size: 24px;
        padding: 40px;
    }

    /*设置表单盒子的样式*/
    .fromBox {
        /*改变表单宽度*/
        width: 600px;
        margin: 0 auto;
        padding: 10px;
    }


</style>