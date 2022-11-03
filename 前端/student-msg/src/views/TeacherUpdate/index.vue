<template>
    <div>
        <el-row>
            <el-col :span="24">
                <div class="grid-content bg-purple-dark">
                    <div class="tableBox">
                        <div class="text"><i class="el-icon-s-custom">&nbsp;</i>教师信息修改</div>
                        <!--查询条件-->
                        <el-form :inline="true" :model="formSearch" class="demo-form-inline">
                            <el-form-item>
                                <el-input v-model="formSearch.teaId" placeholder="请输入教师工号"></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-button type="primary" icon="el-icon-search" @click="getTeacherList()">查询</el-button>
                            </el-form-item>
                        </el-form>
                        <!--表格-->
                        <div class="tabBox">
                            <el-table
                                    :data="tableData"
                                    height="550"
                                    style="width: 100%">
                                <el-table-column
                                        prop="teaId"
                                        label="教师工号"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="teaName"
                                        label="姓名"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="teaSex"
                                        label="性别"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="teaPolitical"
                                        label="政治面貌"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="teaAddress"
                                        label="地址">
                                </el-table-column>
                                <el-table-column
                                        label="查看更多信息"
                                        width="200">
                                    <template slot-scope="scope">
                                        <el-button type="primary" icon="el-icon-edit" @click="viewInfo(scope.row, scope.$index)"></el-button>
                                        <el-button type="danger" icon="el-icon-delete" @click="deleteData(scope.row)"></el-button>
                                        <el-dialog
                                                title="教师信息修改"
                                                :visible.sync="dialogVisible"
                                                width="50%"
                                                :before-close="handleClose">
                                            <!--修改学生信息模态框主体-->
                                            <el-form ref="form" :model="viewData" label-width="80px">
                                                <el-form-item label="姓名">
                                                    <el-input v-model="viewData.teaName"></el-input>
                                                </el-form-item>
                                                <el-form-item label="民族">
                                                    <el-input v-model="viewData.teaNational"></el-input>
                                                </el-form-item>
                                                <el-form-item label="性别">
                                                    <el-input v-model="viewData.teaSex"></el-input>
                                                </el-form-item>
                                                <el-form-item label="政治面貌">
                                                    <el-select v-model="viewData.teaPolitical" placeholder="请选择政治面貌">
                                                        <el-option label="党员" value="党员"></el-option>
                                                        <el-option label="预备党员" value="预备党员"></el-option>
                                                        <el-option label="共青团员" value="共青团员"></el-option>
                                                        <el-option label="群众" value="群众"></el-option>
                                                    </el-select>
                                                </el-form-item>
                                                <el-form-item label="教师工号">
                                                    <el-input v-model="viewData.teaId"></el-input>
                                                </el-form-item>
                                                <el-form-item label="身份证号">
                                                    <el-input v-model="viewData.sfId"></el-input>
                                                </el-form-item>
                                                <el-form-item label="出生日期">
                                                    <el-date-picker type="date" placeholder="选择日期" v-model="viewData.teaDate" value-format="yyyy-MM-dd"  style="width: 100%;"></el-date-picker>
                                                </el-form-item>
                                                <el-form-item label="联系电话">
                                                    <el-input v-model="viewData.teaPhone"></el-input>
                                                </el-form-item>
                                                <el-form-item label="家庭住址">
                                                    <el-input v-model="viewData.teaAddress"></el-input>
                                                </el-form-item>
                                                <el-form-item label="所属二级学院" label-width="110px">
                                                    <el-select v-model="viewData.teaSecondarySchool" placeholder="请选择所在二级学院">
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
                    teaId: '',
                },
                //表格最终展示数据
                tableData:[
                    {
                        sfId: 'aa',
                        teaAddress: 'aaa',
                        teaPolitical: 'aaaa',
                        teaDate: '2020-11-08',
                        teaId: 'aaaaaa',
                        teaName: 'aaaaaaa',
                        teaNational: 'aaaaaaaa',
                        teaPhone: 'aaaaaaaaa',
                        teaSecondarySchool: 'aaaaaaaaaaa',
                        teaSex: 'aaaaaaaaaaa'
                    }
                ],
                //模态框状态
                dialogVisible: false,
                //模态框具体数据
                viewData: {
                    sfId: '',
                    teaAddress: '',
                    teaPolitical: '',
                    teaDate: '',
                    teaId: '',
                    teaName: '',
                    teaNational: '',
                    teaPhone: '',
                    teaSecondarySchool: '',
                    teaSex: ''
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
            //在组件挂在时获取列表
            this.getTeacherList()
        },
        methods: {
            //获取教师信息
            getTeacherList(){
                request.get("/teacher/page",{
                    params:{
                        pageNum:this.pageNum,
                        pageSize:this.pageSize,
                        teaId:this.formSearch.teaId,
                    }
                }).then(res =>{
                    if(res.code === "200"){
                        console.log(res)
                        this.tableData = res.data.records;
                        this.total = res.data.total;
                    }else {
                        this.$message.error("获取教师信息列表出错，请检查")
                    }
                })
                this.formSearch.teaId = ''
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
                request.post("/teacher/save", viewData).then(res =>{
                    if(res.code === "200"){
                        this.$message.success("修改数据成功")
                        //重新发送请求刷新列表
                        this.getTeacherList()
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
                    request.delete(`/teacher/delete/${row.teaId}`).then(res =>{
                        if(res.code === "200"){
                            this.$message.success("删除操作成功")
                            //重新获取数据
                            this.getTeacherList()
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
                this.getTeacherList()
            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum
                //重新获取请求
                this.getTeacherList()
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