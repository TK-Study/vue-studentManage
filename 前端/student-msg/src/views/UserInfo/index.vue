<template>
    <el-row :gutter="20">
        <el-col :span="18" :offset="3">
            <div class="grid-content bg-purple">
                <HeaderUserInfo></HeaderUserInfo>
                <br/>
                <MainUserInfo></MainUserInfo>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    import HeaderUserInfo from './component/HeaderUserInfo'
    import MainUserInfo from "@/views/UserInfo/component/MainUserInfo";
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        components:{HeaderUserInfo, MainUserInfo}
    }
</script>

<style scoped>
    .bg-purple {
        background: #E9EEF3;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
</style>