<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
    export default {
        //如果未登录，跳转到登录页面
        mounted() {
            if(localStorage.getItem("user")==null){
                this.$router.push('/login')
            }
        }
    }
</script>
<style>
    a{
        text-decoration: none;
    }
    /*登录页面的背景图片*/
    body{
        background-image: url("/src/assets/loginbag.png") ;
        background-size:cover;
    }
</style>
