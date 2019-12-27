package graph.pathGrapth;

/**
 * ��·������Ϣʵ����
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
     * name : ����1����
     * map : /images/metro/metro_001.png
     * sites : ["������","��ɽ����԰","�Ͼ�վ","��ģ����·","������","��¥","�齭·","�½ֿ�","�Ÿ�԰","��ɽ��","�л���","������","��¡��","������","������","�Ͼ���վ","˫�����","�Ӷ���","ʤ̫·","�ټҺ�","С����","��ɽ·","��ӡ���","���ߴ��","��ҽ���վ�óѧԺվ","�Ͼ���Ժ","�й�ҩ�ƴ�ѧ"]
     * time : [{"site":"������","starttime":"05:42:00","endtime":"23:19:00"},{"site":"�й�ҩ�ƴ�ѧ","starttime":"05:47:00","endtime":"23:27:00"}]
     * transfersites : ["�Ͼ���Ժ","�й�ҩ�ƴ�ѧ","�Ͼ�վ"]
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
         * site : ������
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
