<template>
    <el-row>
        <el-col :span="24">
            <div class="grid-content bg-purple-dark">
                <div class="informTextBox">
                    <i class="el-icon-chat-dot-round"></i>通知
                </div>
                <el-divider></el-divider>
                <div class="textBox">
                    <el-collapse v-model="activeName"
                                 accordion
                                 v-for="(item, index) in noticeList"
                                :key="index">
                        <el-collapse-item  :name="index+1">
                            <template slot="title">
                                <span style="font-size: 18px">{{item.annuTitle}}</span>
                                <div style="padding: 0 10px 0 20px; color: #B3C0D1">{{item.annuTime}}</div>
                            </template>
                            <div>{{item.annuBody }}</div>
                        </el-collapse-item>
                    </el-collapse>
                </div>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "informList",
        data() {
            return {
                activeName: '1',
                noticeList:{},
            };
        },
        mounted() {
            request.get('/notice/list').then(res => {
                if(res.code === '200'){
                    this.noticeList = res.data;
                }
            })
        }
    }
</script>

<style scoped>
    .bg-purple-dark {
        background: #ffffff;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .informTextBox {
        padding: 20px 20px 0 20px;
        font-size: 24px;
    }

    .textBox {
        padding: 20px;
        height: 500px;
        overflow-x: hidden;
        overflow-y: scroll;
    }

    /*隐藏滚动条*/
    .textBox::-webkit-scrollbar {
        display: none;
    }
</style>
