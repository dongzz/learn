<template>
  <div class="SideBar">
    <!-- 左侧二级菜单栏的组件封装 -->
    <el-menu
        class="sidebar-el-menu"
        :default-active="toIndex()"
        background-color="white"
        text-color="#7a8297"
        active-text-color="#2d8cf0"
        router>
      <template v-for="item in items">
        <el-menu-item :index="item.index" :key="item.index">
          <!-- 需要图标的在 item 对象中加上属性 icon -->
          <!-- <i :class="item.icon"></i> -->
          <span slot="title">{{ item.title }}</span>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'SideBar',
  props: ['items'],
  data() {
    return {

    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {

    },
    // 根据路径绑定到对应的二级菜单，防止页面刷新重新跳回第一个
    toIndex(){
      return this.$route.path.split('/')[2];
    },
  },
  beforeDestroy() {

  }
}
</script>

<style scoped lang='less'>
.SideBar {
  display: block;
  position: absolute;
  left: 0;
  top: 80px;
  bottom: 0;
  overflow-y: scroll;

  .sidebar-el-menu {
    width: 250px;
  }

  ul {
    height: 100%;
  }

  /* 左侧二级菜单项的样式 */
  .el-menu-item{
    font-size: 14px !important;
    padding-left: 35px !important;
    color: black !important;
  }

  /* 左侧二级菜单选中时的样式 */
  .el-menu-item.is-active {
    color: white !important;
    background: #3989fa!important;
  }
  .el-menu-item, .el-submenu__title {
    height: 50px !important;
    line-height: 50px !important;
  }
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
</style>