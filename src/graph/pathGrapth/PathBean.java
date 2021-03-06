package graph.pathGrapth;

/**
 * 线路地铁信息实体类
 * 
 * @author ziker
 *
 */

import java.util.List;

/**
 * @author ziker
 * @version 1.0
 * @date 2019/12/18 20:09
 */
public class PathBean  {

    /**
     * id : 1
     * name : 地铁1号线
     * map : /images/metro/metro_001.png
     * sites : ["迈皋桥","红山动物园","南京站","新模范马路","玄武门","鼓楼","珠江路","新街口","张府园","三山街","中华门","安德门","天隆寺","软件大道","花神庙","南京南站","双龙大道","河定桥","胜太路","百家湖","小龙湾","竹山路","天印大道","龙眠大道","南医大江苏经贸学院站","南京交院","中国药科大学"]
     * time : [{"site":"迈皋桥","starttime":"05:42:00","endtime":"23:19:00"},{"site":"中国药科大学","starttime":"05:47:00","endtime":"23:27:00"}]
     * transfersites : ["南京交院","中国药科大学","南京站"]
     */

    private int id;
    private String name;
    private String map;
    private List<String> sites;
    private List<TimeBean> time;
    private List<String> transfersites;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public List<String> getSites() {
        return sites;
    }

    public void setSites(List<String> sites) {
        this.sites = sites;
    }

    public List<TimeBean> getTime() {
        return time;
    }

    public void setTime(List<TimeBean> time) {
        this.time = time;
    }

    public List<String> getTransfersites() {
        return transfersites;
    }

    public void setTransfersites(List<String> transfersites) {
        this.transfersites = transfersites;
    }


    public static class TimeBean {
        /**
         * site : 迈皋桥
         * starttime : 05:42:00
         * endtime : 23:19:00
         */

        private String site;
        private String starttime;
        private String endtime;



        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}
