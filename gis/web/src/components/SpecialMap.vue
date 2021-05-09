<template>
  <div>
    {{axiosdata}}
    专题地图
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      axiosdata: null
    }
  },
  methods: {
    getdata() {

      axios.get('http://localhost:8081/map/geometry?category=cntypts&start=-5000&end=-221').then((response) => {

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