<template>
    <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :collapse="collapsed"
            router>

        <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
        </el-menu-item>

        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>学生管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/studentAdd">添加学生</el-menu-item>
                <el-menu-item index="/studentQuery">信息查询</el-menu-item>
                <el-menu-item index="/studentUpdate">信息修改</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-s-custom"></i>
                <span>教师管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/teacherAdd">添加教师</el-menu-item>
                <el-menu-item index="/teacherQuery">信息查询</el-menu-item>
                <el-menu-item index="/teacherUpdate">信息修改</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title">
                <i class="el-icon-s-claim"></i>
                <span>公告管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/announcement">发布公告</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group>
                <el-menu-item index="/managementAnnouncement">管理公告</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="/userInfo">
            <i class="el-icon-s-help"></i>
            <span slot="title">个人中心</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "AsideNav",
        //接收从index传递过来的值
        props:['collapsed'],
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>
    /*解决侧边导航栏伸缩存在边框*/
    .el-menu{
        border: none;
    }
</style>