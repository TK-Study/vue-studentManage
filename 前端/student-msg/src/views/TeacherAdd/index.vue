<template>
    <el-row>
        <el-col :span="24">
            <div class="grid-content bg-purple-dark">
                <div class="fromBox">
                    <div class="text"><i class="el-icon-s-custom">&nbsp;</i>添加教师信息</div>
                    <el-form ref="form" :rules="rules" :model="form" label-width="80px" status-icon>
                        <!--姓名-->
                        <el-form-item label="姓名" prop="teaName">
                            <el-input v-model="form.teaName"></el-input>
                        </el-form-item>
                        <!--民族-->
                        <el-form-item label="民族" prop="teaNational">
                            <el-input v-model="form.teaNational"></el-input>
                        </el-form-item>
                        <!--性别-->
                        <el-form-item label="性别" prop="teaSex">
                            <el-input v-model="form.teaSex"></el-input>
                        </el-form-item>
                        <!--政治面貌-->
                        <el-form-item label="政治面貌" prop="teaPolitical" >
                            <el-select v-model="form.teaPolitical" placeholder="请选择政治面貌">
                                <el-option label="党员" value="党员"></el-option>
                                <el-option label="预备党员" value="预备党员"></el-option>
                                <el-option label="共青团员" value="共青团员"></el-option>
                                <el-option label="群众" value="群众"></el-option>
                            </el-select>
                        </el-form-item>
                        <!--教师工号-->
                        <el-form-item label="教师工号" prop="teaId">
                            <el-input v-model="form.teaId"></el-input>
                        </el-form-item>
                        <!--身份证号-->
                        <el-form-item label="身份证号" prop="sfId">
                            <el-input v-model="form.sfId"></el-input>
                        </el-form-item>
                        <!--出生日期-->
                        <el-form-item label="出生日期" prop="teaDate">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.teaDate"
                                            style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
                        </el-form-item>
                        <!--联系电话-->
                        <el-form-item label="联系电话" prop="teaPhone">
                            <el-input v-model="form.teaPhone"></el-input>
                        </el-form-item>
                        <!--家庭住址-->
                        <el-form-item label="家庭住址" prop="teaAddress">
                            <el-input v-model="form.teaAddress"></el-input>
                        </el-form-item>
                        <!--所属二级学院-->
                        <el-form-item label="所属二级学院" prop="teaSecondarySchool" label-width="110px">
                            <el-select v-model="form.teaSecondarySchool" placeholder="请选择所在二级学院">
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
                    teaAddress: '',
                    teaPolitical:'',
                    teaDate: '',
                    teaId: '',
                    teaName: '',
                    teaNational: '',
                    teaPhone: '',
                    teaSecondarySchool: '',
                    teaSex: ''
                },
                //表单验证
                rules: {
                    teaName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    teaNational: [
                        {required: true, message: '请输入民族', trigger: 'change'}
                    ],
                    teaSex: [
                        {required: true, message: '请输入性别', trigger: 'change'}
                    ],
                    teaId: [
                        {required: true, message: '请输入学号', trigger: 'change'}
                    ],
                    sfId: [
                        {required: true, message: '请输入身份证号', trigger: 'change'}
                    ],
                    teaDate: [
                        {required: true, message: '请选择日期', trigger: 'change'}
                    ],
                    teaPhone: [
                        {required: true, message: '请输入联系电话', trigger: 'change'}
                    ],
                    teaAddress: [
                        {required: true, message: '请输入地址', trigger: 'change'}
                    ],
                    teaSecondarySchool: [
                        {required: true, message: '请选择所在二级学院', trigger: 'change'}
                    ],
                    teaPolitical: [
                        {required: true, message: '请选择政治面貌', trigger: 'change'}
                    ],
                }
            }
        },
        methods:{
            //表单验证规则
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //添加教师信息请求
                        request.post("teacher/save", this.form).then(res =>{
                            if(res.code === "200"){
                                this.$message.success("添加老师成功")
                                //重置表单
                                this.resetForm(formName);
                            }else {
                                this.$message.error("添加信息失败，请检查字段，重新添加")
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