<template>
  <div class="SideMenu">

    <!-- 一级菜单下面所拥有的二级菜单 -->
    <el-aside>
      <SideBar :items='items'></SideBar>
    </el-aside>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar";
export default {
  name: 'SideMenu',
  data() {
    return {
      items: [
        {
          index: 'test1-1',
          title: '二级菜单1-1'
        },
        {
          index: 'test1-2',
          title: '二级菜单1-2'
        },
        {
          index: 'test1-3',
          title: '二级菜单1-3'
        },
        {
          index: 'test1-4',
          title: '二级菜单1-4'
        },
        {
          index: 'test1-5',
          title: '二级菜单1-5'
        }
      ],
    }
  },
  components: {
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