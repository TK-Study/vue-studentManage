<template>
    <div>
        <el-row>
            <el-col :span="24">
                <div class="grid-content bg-purple-dark">
                    <div class="tableBox">
                        <div class="text"><i class="el-icon-user-solid">&nbsp;</i>学生信息修改</div>
                        <!--查询条件-->
                        <el-form :inline="true" :model="formSearch" class="demo-form-inline">
                            <el-form-item>
                                <el-input v-model="formSearch.stuId" placeholder="请输入学号"></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-button type="primary" icon="el-icon-search" @click="getStudentList()">查询</el-button>
                            </el-form-item>
                        </el-form>
                        <!--表格-->
                        <div class="tabBox">
                            <el-table
                                    :data="tableData"
                                    height="550"
                                    style="width: 100%">
                                <el-table-column
                                        prop="stuId"
                                        label="学号"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="stuName"
                                        label="姓名"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="stuSex"
                                        label="性别"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="stuClass"
                                        label="班级"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="stuAddress"
                                        label="地址">
                                </el-table-column>
                                <el-table-column
                                        label="查看更多信息"
                                        width="200">
                                    <template slot-scope="scope">
                                        <el-button type="primary" icon="el-icon-edit" @click="viewInfo(scope.row, scope.$index)"></el-button>
                                        <el-button type="danger" icon="el-icon-delete" @click="deleteData(scope.row)"></el-button>
                                        <el-dialog
                                                title="学生信息修改"
                                                :visible.sync="dialogVisible"
                                                width="50%"
                                                :before-close="handleClose">
                                            <!--修改学生信息模态框主体-->
                                            <el-form ref="form" :model="viewData" label-width="80px">
                                                <el-form-item label="姓名">
                                                    <el-input v-model="viewData.stuName"></el-input>
                                                </el-form-item>
                                                <el-form-item label="民族">
                                                    <el-input v-model="viewData.stuNational"></el-input>
                                                </el-form-item>
                                                <el-form-item label="性别">
                                                    <el-input v-model="viewData.stuSex"></el-input>
                                                </el-form-item>
                                                <el-form-item label="学号">
                                                    <el-input v-model="viewData.stuId"></el-input>
                                                </el-form-item>
                                                <el-form-item label="身份证号">
                                                    <el-input v-model="viewData.sfId"></el-input>
                                                </el-form-item>
                                                <el-form-item label="出生日期">
                                                    <el-date-picker type="date" placeholder="选择日期" v-model="viewData.stuDate" value-format="yyyy-MM-dd"  style="width: 100%;"></el-date-picker>
                                                </el-form-item>
                                                <el-form-item label="联系电话">
                                                    <el-input v-model="viewData.stuPhone"></el-input>
                                                </el-form-item>
                                                <el-form-item label="家庭住址">
                                                    <el-input v-model="viewData.stuAddress"></el-input>
                                                </el-form-item>
                                                <el-form-item label="二级学院">
                                                    <el-select v-model="viewData.stuSecondarySchool" placeholder="请选择所在二级学院">
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
                                                <el-form-item label="所在班级">
                                                    <el-input v-model="viewData.stuClass"></el-input>
                                                </el-form-item>

                                            </el-form>

                                            <span slot="footer" class="dialog-footer">
                                                <el-button @click="dialogVisible = false">取 消</el-button>
                                                <el-button type="primary" @click="updateData(viewData)">确 定</el-button>
                                            </span>
                                        </el-dialog>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                        <!--分页器-->
                        <div class="pageBox">
                            <el-pagination
                                    @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange"
                                    :current-page="pageNum"
                                    :page-sizes="[10, 30, 50, 60]"
                                    :page-size="pageSize"
                                    layout="total, sizes, prev, pager, next, jumper"
                                    :total=total>
                            </el-pagination>
                        </div>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        data() {
            return {
                //表单查询数据
                formSearch: {
                    stuId: '',
                },
                //表格最终展示数据
                tableData:[],
                //模态框状态
                dialogVisible: false,
                //模态框具体数据
                viewData: {
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
                //修改数据行索引值
                updateIndex:'',
                //数据总条数
                total:0,
                //pageNum表示当前在第几页
                pageNum:1,
                //pageSize表示一页展示多少条数据
                pageSize:10
            }
        },
        mounted() {
            //页面加载时就把数据渲染出来
            this.getStudentList();
        },
        methods: {
            getStudentList(){
                request.get("/student/page",{
                    params:{
                        pageNum:this.pageNum,
                        pageSize:this.pageSize,
                        stuId:this.formSearch.stuId,
                    }
                }).then(res =>{
                    console.log(res)
                    if(res.code === "200"){
                        this.tableData = res.data.records;
                        this.total = res.data.total;
                    }else {
                        this.$message.error("获取学生列表失败，请检查")
                    }

                })
                this.formSearch.stuId = ''
            },

            //模块框原始方法
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            },

            //显示模态框里面内容
            viewInfo(row, index) {
                //打开模态框
                this.dialogVisible = true
                // console.log(index)
                //深拷贝赋值
                this.viewData = JSON.parse(JSON.stringify(row))
                //顺便保存当前点击行的index值，为修改当前行数据提供索引
                this.updateIndex = index;
            },

            //修改选中行数据操作
            updateData(viewData){
                //发送请求，更新数据
                request.post("/student/save", viewData).then(res =>{
                    if(res.code === "200"){
                        this.$message.success("修改数据成功")
                        //重新发送请求刷新列表
                        this.getStudentList()
                        //关闭模态框
                        this.dialogVisible = false
                    }else {
                        this.$message.error("修改数据错误")
                    }
                })
                // console.log(this.tableData[this.updateIndex])
                // this.tableData[this.updateIndex] = viewData
                // console.log(this.tableData[this.updateIndex])
                // console.log(this.tableData)

                //实时更新表格数据
                //this.tableData是表格展示的数据源,this.updateIndex是修改当前行的索引，viewData是改后的数据
                this.$set(this.tableData,this.updateIndex, viewData);
            },

            //删除选中行数据操作
            deleteData(row) {
                this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //删除选中行数据
                    request.delete(`/student/delete/${row.stuId}`).then(res =>{
                        if(res.code === "200"){
                            this.$message.success("删除操作成功")
                            //重新获取数据
                            this.getStudentList()
                        }else {
                            this.$message.error("删除操作失败")
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //分页器方法
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                //重新获取请求
                this.getStudentList()
            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum
                //重新获取请求
                this.getStudentList()
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

    /*页面样式*/
    .tableBox {
        margin: 0 auto;
        padding: 10px 40px;
    }

    /*表格样式*/
    .tabBox {
        padding: 10px 0 20px 10px;
    }
    /*分页器样式*/
    .pageBox{
        text-align: center;
    }
</style>