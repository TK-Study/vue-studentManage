<template>
    <el-container style="height: 100vh">
        <el-aside :class="collapsed?'main-aside-collapsed':'main-aside'">
            <AsideNav :collapsed="collapsed"></AsideNav>
        </el-aside>
        <el-container>
            <el-header>
                <HeaderNav @openCollasped="doClick"></HeaderNav>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import AsideNav from "@/components/AsideNav";
    import HeaderNav from "@/components/HeaderNav";

    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        components: {AsideNav, HeaderNav},
        data(){
            return{
                //接收来自子组件的值
                collapsed:false
            }
        },
        methods:{
            //接受来自HeaderNav组件的数据
            doClick(collapsed){
                this.collapsed=collapsed;
                console.log("Main组件的collapsed="+this.collapsed);
            }
        }
    }
</script>

<style scoped>
    /**自主样式*/
    .main-container {
        height: 100%;
        width: 100%;
        box-sizing: border-box;
    }

    .main-aside-collapsed {
        /* 在CSS中，通过对某一样式声明! important ，可以更改默认的CSS样式优先级规则，使该条样式属性声明具有最高优先级 */
        width: 64px !important;
    }

    .main-aside {
        width: 240px !important;
    }


    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
        padding: 0;
    }

    .el-aside {
        text-align: center;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
    }

    /*侧边导航栏样式*/
    .el-menu-vertical-demo{
        height: 100%;
        text-align: left;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

</style>