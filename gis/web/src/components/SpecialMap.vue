<template>
  <div>
    <div id="map">map</div>
    <div style="background-color: #fff; height: 100vh;width: 15%;position:absolute;left:0px;">
      <el-scrollbar style="height: 100%;">
        <el-tree :data="treedata" show-checkbox node-key="id" :props="defaultProps" @check="changecheck" default-expand-all>
        </el-tree>
      </el-scrollbar>
    </div>
  </div>
</template>
<script>
import { treedata } from '@/assets/js/treedata.js'
import 'leaflet/dist/leaflet.css'
import 'leaflet/dist/leaflet.js'
import { TXTileLayer } from '@/assets/js/txTileLayer.js'
import { getGeometry } from '@/api'

export default {
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      treedata: treedata
    };
  },
  methods: {
    initLeaflet() {
      window.map = L.map('map', {
        center: [36.0, 114.0],
        zoom: 4,
        maxZoom: 23,
        minZoom: 3
      })
      //按照新定义瓦片规则加载底图
      let txurl = 'http://rt1.map.gtimg.com/realtimerender/?z={z}&x={x}&y={y}&type=vector&style=1&v=1.1.1'
      let getUrlArgs = function(tilePoint) {
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
      const txMap = new TXTileLayer(txurl, options)
      txMap.addTo(window.map)
    },
    changecheck(e, data) {
      if (e['level'] === 1) {
        e['ischeck'] = !e['ischeck'];
        e['children'].forEach(o => {
          o['ischeck'] = e['ischeck']
          o['children'].forEach(p => {
            p['ischeck'] = e['ischeck']
          })
        })
      } else if (e['level'] === 2) {
        e['ischeck'] = !e['ischeck'];
        e['children'][0]['ischeck'] = e['ischeck'];
        e['children'][1]['ischeck'] = e['ischeck'];
        e['children'][2]['ischeck'] = e['ischeck'];
      } else {
        e['ischeck'] = !e['ischeck'];
      }
      // console.log(e);
      this.getdata();
    },
    getdata() {
      //这步是把除底图外的所有覆盖物清空。
      for (let a in window.map._layers) {
        if (!window.map._layers[a]._container) {
          window.map.removeLayer(window.map._layers[a])
        }
      }
      const dynastylist = this.treedata[0]['children']
      //定义黑点
      const blackIcon = L.icon({
        iconUrl: require('@/assets/icon/black.png'), //icon图片
        iconSize: [8, 8], //icon的尺寸
        iconAnchor: [4, 4] //锚点在icon上的坐标，左下角为原点
      });
      //定义红点
      const redIcon = L.icon({
        iconUrl: require('@/assets/icon/red.png'), //icon图片
        iconSize: [12, 12], //icon的尺寸
        iconAnchor: [6, 6] //锚点在icon上的坐标，左下角为原点
      });

      const blackpointlist = [];
      const cntyindexlist=[];
      const redpointlist = [];
      const prefptsindexlist=[];
      const polygonlist = [];
      const prefpgnindexlist=[];
      //prefpgn
      dynastylist.filter(o => o['children'][2]['ischeck']).forEach(o => {
        const dynastypolygon = [];
        let polygonindex = 0;
        let params = {
          category: 'prefpgn',
          start: o['timerange'][0],
          end: o['timerange'][1]
        }
        getGeometry(params).then((response) => {
          const prefpgnlist = response.data.data

          prefpgnlist.filter(p => !prefpgnindexlist.includes(p['gid'])).forEach(p => {
            prefpgnindexlist.push(p['gid']);
            const mutlipolygon = p['geometry']['coordinates'];
            mutlipolygon.forEach(q => {
              const polygonpath = [];
              q.forEach(e => {
                const ringpath = [];
                e.forEach(r => ringpath.push([r[1], r[0]]))
                polygonpath.push(ringpath);
              })

              dynastypolygon[polygonindex] = L.polygon(polygonpath, {
                color: '#C00000',
                fillColor: '#C00000',
                fillOpacity: 0.5
              }).addTo(window.map);
              dynastypolygon[polygonindex].bindTooltip(p['namech']);
              dynastypolygon[polygonindex].bindPopup('namech:' + p['namech'] +
                  '<br>nameft:' + p['nameft'] +
                  '<br>namepy:' + p['namepy'] +
                  '<br>typech:' + p['typech'] +
                  '<br>presloc:' + p['presloc'] +
                  '<br>begyr:' + p['begyr'] +
                  '<br>begrule:' + p['begrule'] +
                  '<br>begchgty:' + p['begchgty'] +
                  '<br>endyr:' + p['endyr'] +
                  '<br>endrule:' + p['endrule'] +
                  '<br>endchgty:' + p['endchgty'] +
                  '<br>geosrc:' + p['geosrc'] +
                  '<br>compiler:' + p['compiler'] +
                  '<br>gecomplr:' + p['gecomplr'] +
                  '<br>checker:' + p['checker']
              );
              polygonindex = polygonindex + 1;
              polygonlist.push(dynastypolygon[polygonindex]);
            })
          })
        }).catch((response) => {
          console.log(response)
        })
      })
      // console.log(prefpgnindexlist);
      //prefpts
      dynastylist.filter(o => o['children'][1]['ischeck']).forEach(o => {
        const dynastypoint = [];
        let params = {
          category: 'prefpts',
          start: dynastylist[i]['timerange'][0],
          end: dynastylist[i]['timerange'][1]
        }
        getGeometry(params).then((response) => {

          const prefptslist = response.data.data

          for (let j = 0; j < prefptslist.length; j++) {

            if(prefptsindexlist.indexOf(prefptslist[j]['gid'])>0){

              continue;

            }

            prefptsindexlist.push(prefptslist[j]['gid']);



            dynastypoint[j] = new L.marker([prefptslist[j]['geometry']['coordinates'][1], prefptslist[j]['geometry']['coordinates'][0]], { icon: redIcon, title: prefptslist[j]['namech'] });

            dynastypoint[j].addTo(window.map);

            redpointlist.push(dynastypoint[j]);

            dynastypoint[j].bindPopup('namech:' + prefptslist[j]['namech'] +

                '<br>nameft:' + prefptslist[j]['nameft'] +

                '<br>namepy:' + prefptslist[j]['namepy'] +

                '<br>typech:' + prefptslist[j]['typech'] +

                '<br>presloc:' + prefptslist[j]['presloc'] +

                '<br>begyr:' + prefptslist[j]['begyr'] +

                '<br>begrule:' + prefptslist[j]['begrule'] +

                '<br>begchgty:' + prefptslist[j]['begchgty'] +

                '<br>endyr:' + prefptslist[j]['endyr'] +

                '<br>endrule:' + prefptslist[j]['endrule'] +

                '<br>endchgty:' + prefptslist[j]['endchgty'] +

                '<br>geosrc:' + prefptslist[j]['geosrc'] +

                '<br>compiler:' + prefptslist[j]['compiler'] +

                '<br>gecomplr:' + prefptslist[j]['gecomplr'] +

                '<br>checker:' + prefptslist[j]['checker']

            );

          };

        }).catch((response) => {

          console.log(response)

        })
      })
//cntypts

      for (let i = 0; i < dynastylist.length; i++) {

        if (dynastylist[i]['children'][0]['ischeck'] == true) {
          const dynastypoint = [];
          let params = {
            category: 'cntypts',
            start: dynastylist[i]['timerange'][0],
            end: dynastylist[i]['timerange'][1]
          }
          getGeometry(params).then((response) => {

            const cntyptslist = response.data.data

            for (let j = 0; j < cntyptslist.length; j++) {

              if(cntyindexlist.indexOf(cntyptslist[j]['gid'])>0){

                continue;

              }

              cntyindexlist.push(cntyptslist[j]['gid']);

              dynastypoint[j] = new L.marker([cntyptslist[j]['geometry']['coordinates'][1], cntyptslist[j]['geometry']['coordinates'][0]], { icon: blackIcon, title: cntyptslist[j]['namech'] });

              dynastypoint[j].addTo(window.map);

              blackpointlist.push(dynastypoint[j]);

              dynastypoint[j].bindPopup('namech:' + cntyptslist[j]['namech'] +

                  '<br>nameft:' + cntyptslist[j]['nameft'] +

                  '<br>namepy:' + cntyptslist[j]['namepy'] +

                  '<br>typech:' + cntyptslist[j]['typech'] +

                  '<br>presloc:' + cntyptslist[j]['presloc'] +

                  '<br>begyr:' + cntyptslist[j]['begyr'] +

                  '<br>begrule:' + cntyptslist[j]['begrule'] +

                  '<br>begchgty:' + cntyptslist[j]['begchgty'] +

                  '<br>endyr:' + cntyptslist[j]['endyr'] +

                  '<br>endrule:' + cntyptslist[j]['endrule'] +

                  '<br>endchgty:' + cntyptslist[j]['endchgty'] +

                  '<br>geosrc:' + cntyptslist[j]['geosrc'] +

                  '<br>compiler:' + cntyptslist[j]['compiler'] +

                  '<br>gecomplr:' + cntyptslist[j]['gecomplr'] +

                  '<br>checker:' + cntyptslist[j]['checker']

              );

            };

          }).catch((response) => {

            console.log(response)

          })

        }

      }

    }

  },

  mounted() {

    this.initLeaflet()

  }

};

</script>

<style>

#map {

  position: absolute;

  left: 15%;

  top: 60px;

  width: 85%;

  height: 100vh;

  z-index: 80;

}

</style>