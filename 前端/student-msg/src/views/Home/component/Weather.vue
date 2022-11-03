<template>
    <div id="he-plugin-standard"></div>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "weather",
        mounted() {
            window.WIDGET = {
                "CONFIG": {
                    "layout": "1",
                    "width": "490",
                    "height": "150",
                    "background": "1",
                    "dataColor": "FFFFFF",
                    "language": "zh",
                    "borderRadius": "15",
                    "city": "CN101290110",
                    "key": "9b12c7c8eb7f46ea9c76fe36e99ca78a"
                }
            }
            window.setTimeout(()=>{
                let script = document.createElement('script')
                script.type='text/javascript'
                script.src="https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0"
                document.getElementsByTagName('head')[0].appendChild(script)
            },0)
        }
    }
</script>

<style scoped>

</style>