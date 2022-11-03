<template>
    <el-row :gutter="20">
        <el-col :span="8">
            <div class="grid-content bg-purple">
                <!--卡片-->
                <el-card class="box-card">
                    <UserInfo></UserInfo>
                </el-card>
            </div>
        </el-col>
        <el-col :span="16">
            <div class="grid-content bg-purple">
                <!--人数面板-->
                <CountList></CountList>
                <br/>
                <informList></informList>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    import UserInfo from "./component/UserInfo";
    import CountList from "./component/CountList"
    import informList from "@/views/Home/component/InformList";
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        components:{UserInfo,CountList,informList},
        data() {
            return {};
        },
        methods: {}
    }
</script>

<style scoped>
    /*页面所有盒子的背景色*/
    .bg-purple {
        background: none;
    }
    .grid-content {
        border-radius: 4px;
    }
    .box-card {
        width: 100%;
        border-radius: 5px;
    }
</style>