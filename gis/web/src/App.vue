<template>
  <el-container style="height: 100%">
    <el-header class="header">
      <vheader />
    </el-header>
    <el-main class="el-main">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>
<script>
import Header from '@/components/Header'
export default {
  name: 'app',
  components: {
    'vheader': Header
  }
}
</script>
<style>
html,body,#app{
  height: 100%;
}
.header {
  background-color: #c00000;
  color: #fff;
  line-height: 60px;
}
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 100%;
}
/*更改全局的滚动条样式*/
::-webkit-scrollbar {
  width: 8px;
  height: 8px;
  background-color: #fff;
}
::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
  background-color: rgba(0, 0, 0, .1)
}
</style>