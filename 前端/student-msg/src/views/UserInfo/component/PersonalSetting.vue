<template>
    <div>
        <div class="settingBox">
            <el-button type="primary" @click="dialogVisible = true">修改密码</el-button>
            <el-button type="danger" @click="open()">注销账号</el-button>
        </div>
        <el-dialog
                title="修改密码"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <!--修改密码表单-->
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    import request from "@/utils/request";

    export default {
        name: "PersonalSetting",
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                dialogVisible: false,
                ruleForm: {
                    pass: '',
                    checkPass: '',
                },
                //从浏览器获得用户信息
                user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

                rules: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                }
            };
        },
        methods: {
            //修改密码弹出框
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            //修改密码操作
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        request.post("/user/updatePassword",{
                            id:this.user.id,
                            password:this.ruleForm.pass
                        }).then(res=>{
                            if(res.code === "200"){
                                this.dialogVisible = false
                                this.$message.success("修改密码成功，请重新登录")
                                localStorage.removeItem("user")
                                this.$router.push("/login")
                            }else {
                                this.$message.error("修改密码出现错误，请稍后再试");
                            }
                        })
                    }
                });
            },
            //注销账户弹出框
            open() {
                this.$confirm('此操作将永久注销账号，信息将不复存在, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //注销账户
                    request.delete(`/user/${this.user.id}`).then(res=>{
                        if(res.code === "200"){
                            this.$message.success("注销账户成功")
                            localStorage.removeItem("user")
                            this.$router.push("/login")
                        }else {
                            this.$message.error("注销账户失败")
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消账户注销'
                    });
                });
            },
        }
    }
</script>

<style scoped>
    .settingBox {
        height: 300px;
        line-height: 300px;
        text-align: center;
    }
</style>