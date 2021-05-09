<template>
  <div>
    {{axiosdata}}
    专题地图
  </div>
</template>

<script>
import { getGeometry } from '@/api'

export default {
  data() {
    return {
      axiosdata: null
    }
  },
  methods: {
    getdata() {
      getGeometry().then((response) => {
        this.axiosdata = response.data
        console.log(response.data)
      }).catch((response) => {
        console.log(response)
      })
    }
  },

  mounted() {

    this.getdata()

  }

}



</script>