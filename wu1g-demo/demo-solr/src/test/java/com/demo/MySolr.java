package com.demo;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySolr {

    //solr url
    public static final String URL = "http://localhost:9299/solr";
    //solr应用
    public static final String SERVER = "new_core";
    public static SolrClient client = null;
    //待索引、查询字段
    public static String[] docs = {
            "美阻挠中资收购德企 中方回应：纯系市场行为2016-12-06 08:13:00 来源：参考消息网 责任编辑：张程核心提示：陆慷说，中方反对这种“从政治上进行干预的错误作法”。他说：“希望美方停止对中国企业捕风捉影，为中国企业投资提供公平环境和便利条件，从长远看，这符合各方共同利益。”参考消息网12月6日报道 美媒称，5日，中国要求美国方面停止阻挠中方对外国公司的商业并购行为。据美国之音电台网站12月6日报道，2日，美国总统奥巴马以国防为由，发布行政命令，阻止了中国公司对德国半导体生产商爱思强的收购。爱思强在美国有业务。中国外交部发言人陆慷回应称，这起收购“纯系市场行为”。美国政府表示，爱思强的科技应用于美国军方设备，因此有国防安全隐患。陆慷说，中方反对这种“从政治上进行干预的错误作法”。他说：“希望美方停止对中国企业捕风捉影，为中国企业投资提供公平环境和便利条件，从长远看，这符合各方共同利益。”这是三十年来美国总统第三次以安全为由阻止中国公司的商业收购。据报道，德国经济部也在审议这一并购案，目前还没有做出决定。"
    };

    public MySolr() {
        getSolrClient();
    }

    public static SolrClient getSolrClient() {
        if (client == null) {
            client = new HttpSolrClient(URL + "/" + SERVER);
            ;
        }
        return client;
    }

    /**
     * 新建索引
     */
    public static void createIndex(Integer max) {
        Long s = System.currentTimeMillis();
        int i = 0;
        List<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
        for (int n = 0; n < max; n++) {
            for (String str : docs) {
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id", i++);
                doc.addField("content", str + i);
                docList.add(doc);
            }
            if (n % 10000 == 0) {
                try {
                    getSolrClient().add(docList);
                    getSolrClient().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                docList = new ArrayList<SolrInputDocument>();
            }
        }
        try {
            getSolrClient().add(docList);
            getSolrClient().commit();
            System.out.println("==" + (max / 10000.0) + "W索引创建完成" + (System.currentTimeMillis() - s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    /**
     * 搜索
     */
    public static void search(Integer offset, String matchText) {
        SolrQuery query = new SolrQuery();
        query.setQuery("content:" + matchText);
        query.setStart(offset);
        query.setRows(20);
        query.setHighlight(true); // 开启高亮组件
        query.addHighlightField("content");// 高亮字段
        query.setHighlightSimplePre("<font color=\"red\">");// 标记
        query.setHighlightSimplePost("</font>");
        query.setHighlightSnippets(1);// 结果分片数，默认为1
        query.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100
        QueryResponse response = null;
        try {
            response = getSolrClient().query(query);
//            System.out.println(response.toString());
            SolrDocumentList docs = response.getResults();
            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                System.out.println("id: " + doc.getFieldValue("id") + "      content: " + doc.getFieldValue("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Integer id) throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", id);
        doc.addField("content", "今天天气真好");
        getSolrClient().add(doc);
        getSolrClient().commit();
    }

    public static void delete() {
        UpdateResponse response = null;
        try {
            Long s = System.currentTimeMillis();
            response = getSolrClient().deleteByQuery("content:*");
            getSolrClient().commit();
            System.out.println("删除：" + response);
            System.out.println(System.currentTimeMillis() - s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SolrServerException {
//        createIndex(5000000);
//        search(1);
//        search(4999999);
//        delete();
//        update(1200);
        search(0, "*今天*");
    }
}