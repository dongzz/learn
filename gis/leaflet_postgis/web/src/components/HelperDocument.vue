<template>
  <div id="map"></div>
</template>
<script>

import 'leaflet/dist/leaflet.css'
import 'leaflet/dist/leaflet.js'
import {TXTileLayer} from '@/assets/js/txTileLayer.js'

export default {
  data() {
    return {
      map: null
    }
  },
  methods: {
    initLeaflet() {
      this.map = L.map('map', {
        center: [39.916527, 116.397128],
        zoom: 10,
        maxZoom: 23,
        minZoom: 3
      })
      //按照新定义瓦片规则加载底图
      let url = 'http://rt1.map.gtimg.com/realtimerender/?z={z}&x={x}&y={y}&type=vector&style=1&v=1.1.1'
      let getUrlArgs = function (tilePoint) {
        return {
          z: tilePoint.z,
          x: tilePoint.x,
          y: Math.pow(2, tilePoint.z) - 1 - tilePoint.y
        }
      }
      let options = {
        subdomain: '012',
        getUrlArgs: getUrlArgs
      }
      const txMap = new TXTileLayer(url, options)
      txMap.addTo(this.map)
      //marker
      var myDivIcon = L.divIcon({className: 'my-div-icon', html: '这是一个测试用的marker', iconSize: 150});
      var marker = L.marker([39.916527, 116.397128], {icon: myDivIcon}).addTo(this.map);
      marker.on('click', function (e) {
        console.log(e);
        alert('我是Marker。');
      })
      //point
      var myIcon = L.icon({
        iconUrl: require('@/assets/icon/dian.png'), //icon图片
        iconSize: [30, 30], //icon的尺寸
        iconAnchor: [15, 15], //锚点在icon上的坐标，左下角为原点
        popupAnchor: [40, 0], //弹出框的锚点
        shadowUrl: require('leaflet/dist/images/marker-shadow.png'), //阴影图片
        shadowSize: [30, 30], //阴影尺寸
        shadowAnchor: [15, 15] //阴影锚点
      });

      var point = L.marker([39.916527, 116.397128], {icon: myIcon, title: '这是一个测试用的point'}).addTo(this.map);
      point.on('click', function (e) {
        console.log(e);
        alert('我是point。');
      })

      //polygon
      var polygon = L.polygon([
        [
          [40.0, 116.4],
          [40.1, 116.4],
          [40.1, 116.5],
          [40.0, 116.5]
        ], // 外部
        [
          [40.05, 116.45],
          [40.06, 116.45],
          [40.06, 116.46],
          [40.05, 116.46]
        ] // 孔洞
      ], {
        color: 'green',
        fillColor: '#f03',
        fillOpacity: 0.5
      }).addTo(this.map);
      // 绑定一个提示标签
      polygon.bindTooltip('这是个多边形');

      // 飞到这个多边形的位置
      // this.map.fitBounds(polygon.getBounds());
      polygon.on('click', function (e) {
      // console.log(e);
        alert('我是polygon');
      })
    }
  },
  mounted() {
    this.initLeaflet()
  }
}
</script>
<style>
#map {
  width: 100%;
  height: 100%;
}

.my-div-icon {
  font-size: 15px;
  width: 5px;
  color: red;
}
</style>