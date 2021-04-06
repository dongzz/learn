<template>
  <div id="app">
    <side-menu />
    <!-- 页面左侧二级菜单栏，和主体内容区域部分 -->
    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
import SideMenu from "@/components/SideMenu";
export default {
  components: {
    SideMenu
  }
}
</script>

<style lang="less">
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  position: relative;
  background-color: #eee;

  .el-main {
    height: 100%;
    padding: 80px 0 0 260px;
  }
}
</style>
