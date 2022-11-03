<template>
    <div>
        <el-row>
            <el-col :span="24">
                <div class="grid-content bg-purple-dark">
                    <div class="tableBox">
                        <div class="text"><i class="el-icon-user-solid">&nbsp;</i>学生信息查询</div>
                        <!--查询条件-->
                        <el-form :inline="true" :model="searchFrom" class="demo-form-inline">
                            <el-form-item>
                                <el-input v-model="searchFrom.stuId" placeholder="请输入学号"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="searchFrom.stuName" placeholder="姓名"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-select v-model="searchFrom.stuSecondarySchool" placeholder="二级学院">
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
                            <el-form-item>
                                <el-button type="primary" icon="el-icon-search" @click="getStudentList()">查询</el-button>
                            </el-form-item>
                            <el-form-item>
                                <template>
                                    <download-excel
                                            class="export-excel-wrapper"
                                            :data="tableData"
                                            :fields="exportData"
                                            :header="exportTitle"
                                            name="学生表.xls"
                                    >
                                        <!-- 上面可以自定义自己的样式，还可以引用其他组件button -->
                                        <el-button type="primary" icon="el-icon-download">导出</el-button>
                                    </download-excel>
                                </template>
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
                                        <el-button type="text" @click="viewInfo(scope.row)">查看更多信息</el-button>

                                        <el-dialog
                                                title="提示"
                                                :visible.sync="dialogVisible"
                                                width="80%"
                                                :before-close="handleClose">
                                            <!--模态框主体-->
                                            <el-descriptions title="用户信息" border>
                                                <el-descriptions-item label="姓名">{{viewData.stuName}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="民族">{{viewData.stuNational}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="性别">{{viewData.stuSex}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="学号">{{viewData.stuId}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="身份证号">{{viewData.sfId}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="出生日期">{{viewData.stuDate}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="联系电话">{{viewData.stuPhone}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="家庭住址">{{viewData.stuAddress}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="二级学院">{{viewData.stuSecondarySchool}}
                                                </el-descriptions-item>
                                                <el-descriptions-item label="所在班级">{{viewData.stuClass}}
                                                </el-descriptions-item>
                                            </el-descriptions>

                                            <span slot="footer" class="dialog-footer">
                                        <el-button @click="dialogVisible = false">取 消</el-button>
                                        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
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
                searchFrom: {
                    //学号查询
                    stuId: '',
                    stuName: '',
                    stuSecondarySchool: ''
                },
                //表格最终数据
                tableData: [],
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
                //定义导出文件表头
                exportData: {
                    "学号": 'stuId',
                    "姓名": 'stuName',
                    "民族": 'stuNational',
                    "性别": 'stuSex',
                    "身份证号码": 'sfId',
                    "出生日期": 'stuDate',
                    "联系电话": 'stuPhone',
                    "家庭住址": 'stuAddress',
                    "二级学院": 'stuSecondarySchool',
                    "所在班级": 'stuClass'
                },
                //导出表格标题
                exportTitle: "学生表",
                //数据总条数
                total: 0,
                //pageNum表示当前在第几页
                pageNum: 1,
                //pageSize表示一页展示多少条数据
                pageSize: 10
            }
        },
        mounted() {
            //发送请求
            this.getStudentList();
        },
        methods: {
            //获取学生表所有信息
            getStudentList() {
                request.get("/student/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        stuName: this.searchFrom.stuName,
                        stuId: this.searchFrom.stuId,
                        stuSecondarySchool: this.searchFrom.stuSecondarySchool
                    }
                }).then(res => {
                    if(res.code === "200"){
                        this.tableData = res.data.records;
                        this.total = res.data.total;
                    }else {
                        this.$message.error("获取学生列表信息失败，请检查")
                    }
                })
                this.searchFrom.stuId = ''
                this.searchFrom.stuName = ''
                this.searchFrom.stuSecondarySchool = ''
            },
            //模块框方法
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            //显示模态框里面内容
            viewInfo(row) {
                //打开模态框
                this.dialogVisible = true
                // console.log(row);
                //深拷贝赋值
                this.viewData = JSON.parse(JSON.stringify(row))
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
    .pageBox {
        text-align: center;
    }
</style>