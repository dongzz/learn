<template>
  <div class="SideMenu">
    <layout :titles="titles"/>
    <!-- 一级菜单下面所拥有的二级菜单 -->
    <el-aside>
      <SideBar :items='items'></SideBar>
    </el-aside>
  </div>
</template>

<script>
import Layout from "@/components/Layout";
import SideBar from "@/components/SideBar";
export default {
  name: 'SideMenu',
  data() {
    return {
      titles: [    // 水平一级菜单栏的菜单
        {index: 'Home', title: '首页'},
        {index: 'datasource', title: '数据库'},
      ],
      items: [
        {
          index: 'test1-1',
          title: '二级菜单1-1'
        },
      ],
    }
  },
  components: {
    Layout,
    SideBar
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {

    },
  },
  beforeDestroy() {

  }
}
</script>

<style scoped lang='less'>
.SideMenu {
  position: fixed;
  height: 100%;
}
</style>