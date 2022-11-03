<template>
    <el-row>
        <el-col :span="24">
            <div class="grid-content bg-purple-dark">
                <div class="tableBox">
                    <div class="text"><i class="el-icon-s-comment">&nbsp;</i>公告管理</div>
                    <!--查询条件-->
                    <el-form :inline="true" :model="formInline" class="demo-form-inline">
                        <el-form-item>
                            <el-date-picker
                                    v-model="formInline.annuTime"
                                    type="date"
                                    placeholder="发布日期"
                                    format="yyyy 年 MM 月 dd 日"
                                    value-format="yyyy-MM-dd">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-select v-model="formInline.annuType" placeholder="公告类型">
                                <el-option label="教学通知" value="教学通知"></el-option>
                                <el-option label="日常生活" value="日常生活"></el-option>
                                <el-option label="疫情防控" value="疫情防控"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-input v-model="formInline.annuName" placeholder="发布者"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="getNoticeList()">查询</el-button>
                        </el-form-item>
                    </el-form>
                    <!--表格-->
                    <div class="tabBox">
                        <el-table
                                height="550"
                                :data="tableData"
                                style="width: 100%">
                            <el-table-column
                                    header-align="center"
                                    prop="annuId"
                                    label="公告ID"
                                    width="80">
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    prop="annuTitle"
                                    label="公告标题"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    prop="annuType"
                                    label="标签"
                                    width="80"
                                    filter-placement="bottom-end">
                                <template slot-scope="scope">
                                    <el-tag
                                            :type="scope.row.annuType === '疫情防控' ? 'primary' : 'success'"
                                            disable-transitions>{{scope.row.annuType}}
                                    </el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    prop="annuBody"
                                    label="公告内容"
                                    width="400"
                                    :formatter="stateFormat">
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    prop="annuName"
                                    label="发布者"
                                    width="100">
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    prop="annuIdentity"
                                    label="身份"
                                    width="100">
                            </el-table-column>
                            <el-table-column
                                    header-align="center"
                                    prop="annuTime"
                                    label="发布时间"
                                    width="180">
                            </el-table-column>
                            <el-table-column label="是否显示" prop="isShow" width="100" header-align="center">
                                <template slot-scope="scope">
                                    <el-switch
                                            @change="updateValue(scope.row)"
                                            v-model="scope.row.isShow"
                                            active-color="#13ce66"
                                            inactive-color="#ff4949">
                                    </el-switch>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作" align="right">
                                <template slot-scope="scope">
                                    <el-button
                                            size="mini"
                                            @click="handleEdit(scope.row)">编辑
                                    </el-button>
                                    <el-dialog
                                            align="left"
                                            title="公告修改"
                                            :visible.sync="dialogVisible"
                                            width="30%"
                                            :before-close="handleClose">
                                        <!--模态框主体-->
                                        <el-form ref="priveView" :model="priveView" label-width="80px">
                                            <el-form-item label="公告标题">
                                                <el-input v-model="priveView.annuTitle"></el-input>
                                            </el-form-item>
                                            <el-form-item label="公告类型">
                                                <el-select v-model="priveView.annuType" placeholder="修改公告类型">
                                                    <el-option label="疫情防控" value="疫情防控"></el-option>
                                                    <el-option label="教学通知" value="教学通知"></el-option>
                                                    <el-option label="日常生活" value="日常生活"></el-option>
                                                </el-select>
                                            </el-form-item>
                                            <el-form-item label="公告主体">
                                                <el-input type="textarea" v-model="priveView.annuBody" :rows="8"></el-input>
                                            </el-form-item>
                                        </el-form>
                                        <span slot="footer" class="dialog-footer">
                                            <el-button @click="dialogVisible = false">取 消</el-button>
                                            <el-button type="primary" @click="updateInfo(priveView, scope.$index)">确 定</el-button>
                                        </span>
                                    </el-dialog>
                                    &nbsp;
                                    <!--删除操作-->
                                    <el-button
                                            size="mini"
                                            type="danger"
                                            @click="handleDelete(scope.row)">删除
                                    </el-button>
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
                                :total="total">
                        </el-pagination>
                    </div>
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
        //组件挂载时，发送请求
        mounted() {
            this.getNoticeList();
        },
        data() {
            return {
                value: true,
                tableData: [],
                //分页器数据
                currentPage1: 5,
                currentPage2: 5,
                currentPage3: 5,
                currentPage4: 4,
                //查询表单数据
                formInline: {
                    annuTime: '',
                    annuType: '',
                    annuName: ''
                },
                //数据总条数
                total: 0,
                //pageNum表示当前在第几页
                pageNum: 1,
                //pageSize表示一页展示多少条数据
                pageSize: 10,
                //模态框数据
                priveView: {},
                //模态框状态
                dialogVisible: false,
            }
        },
        methods: {
            //获取公告所有信息
            getNoticeList() {
                request.get("/notice/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        annuName: this.formInline.annuName,
                        annuTime: this.formInline.annuTime,
                        annuType: this.formInline.annuType
                    }
                }).then(res => {
                    if (res.code === "200") {
                        this.tableData = res.data.records;
                        this.total = res.data.total;
                    } else {
                        this.$message.error("获取公告列表信息失败，请检查")
                    }
                })
                this.formInline.annuType = ''
                this.formInline.annuTime = ''
                this.formInline.annuName = ''
            },
            //分页器方法
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                //重新获取请求
                this.getNoticeList()
            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum
                //重新获取请求
                this.getNoticeList()
            },
            //是否显示
            updateValue(value){
                //发送请求，更新数据
                request.post("/notice/save", value).then(res =>{
                    if(res.code === "200"){
                        this.$message.success("修改状态成功")
                        //重新发送请求刷新列表
                        this.getNoticeList()
                    }else {
                        this.$message.error("修改状态错误")
                    }
                })
            },
            //模态框方法
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            //操作框按钮
            handleEdit(row) {
                this.dialogVisible = true
                this.priveView = row;
            },
            //数据更新方法
            updateInfo(priveView,index){
                //发送请求，更新数据
                request.post("/notice/save", priveView).then(res =>{
                    if(res.code === "200"){
                        this.$message.success("修改数据成功")
                        //重新发送请求刷新列表
                        this.getNoticeList()
                        //关闭模态框
                        this.dialogVisible = false
                    }else {
                        this.$message.error("修改数据错误")
                    }
                })
                this.$set(this.tableData,index, priveView);
            },
            //删除数据方法
            handleDelete(row) {
                console.log(row)
                this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //删除选中行数据
                    request.delete(`/notice/delete/${row.annuId}`).then(res =>{
                        if(res.code === "200"){
                            this.$message.success("删除操作成功")
                            //重新获取数据
                            this.getNoticeList()
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
            // 格式化表格消息内容
            stateFormat(row, column, cellValue) {
                // console.log(row , column , cellValue)
                if (!cellValue) return "";
                if (cellValue.length > 25) {
                    //最长固定显示4个字符
                    return cellValue.slice(0, 25) + "...";
                }
                return cellValue;
            },
        },
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