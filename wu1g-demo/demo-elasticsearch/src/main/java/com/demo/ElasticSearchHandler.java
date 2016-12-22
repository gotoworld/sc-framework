package com.demo;

import com.fasterxml.jackson.core.JsonParser;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 *
 */
public class ElasticSearchHandler {

    private static TransportClient client;
    private static final String indexname = "indexdemo";
    private static final String type = "typedemo";
    private static final String jsonData = "";

    public ElasticSearchHandler() {
        this("127.0.0.1");
    }

    public ElasticSearchHandler(String ipAddress) {
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /**
     * 建立索引,索引建立好之后,会在elasticsearch\data\elasticsearch\nodes\0创建所以你看
     *
     * @return
     */
    public static void createIndex(Integer max) {
        Long s = System.currentTimeMillis();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (int i = 0; i < max; i++) {
            try {
                bulkRequest.add(client.prepareIndex(indexname, type, "" + (i)).setSource(
                        jsonBuilder()
                                .startObject()
                                .field("name", "美阻挠中资收购德企 中方回应：纯系市场行为2016-12-06 08:13:00 来源：参考消息网 责任编辑：张程核心提示：陆慷说，中方反对这种“从政治上进行干预的错误作法”。他说：“希望美方停止对中国企业捕风捉影，为中国企业投资提供公平环境和便利条件，从长远看，这符合各方共同利益。”参考消息网12月6日报道 美媒称，5日，中国要求美国方面停止阻挠中方对外国公司的商业并购行为。据美国之音电台网站12月6日报道，2日，美国总统奥巴马以国防为由，发布行政命令，阻止了中国公司对德国半导体生产商爱思强的收购。爱思强在美国有业务。中国外交部发言人陆慷回应称，这起收购“纯系市场行为”。美国政府表示，爱思强的科技应用于美国军方设备，因此有国防安全隐患。陆慷说，中方反对这种“从政治上进行干预的错误作法”。他说：“希望美方停止对中国企业捕风捉影，为中国企业投资提供公平环境和便利条件，从长远看，这符合各方共同利益。”这是三十年来美国总统第三次以安全为由阻止中国公司的商业收购。据报道，德国经济部也在审议这一并购案，目前还没有做出决定。")
                                .field("funciton", "")
                                .endObject())
                );
                if (i % 10000 == 0) {
                    System.out.println(i);
                    bulkRequest.get();
                    bulkRequest = client.prepareBulk();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BulkResponse bulkResponse = bulkRequest.get();
        System.out.println("==" + (max / 10000.0) + "W索引创建完成" + (System.currentTimeMillis() - s));
    }

    /**
     * 执行搜索
     *
     * @return
     */
    public static List<Medicine> search(Integer offset,String matchText) {
        Long s = System.currentTimeMillis();
        //查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", matchText);

        List<Medicine> list = new ArrayList<Medicine>();
        SearchResponse scrollResponse = client.prepareSearch(indexname)
                .setQuery(queryBuilder)
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setFrom(offset)
                .setSize(100)
                .setScroll(TimeValue.timeValueMinutes(1))
                .execute().actionGet();
        SearchHits hits = scrollResponse.getHits();
        System.out.println("查询到记录数=" + hits.getTotalHits());
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length > 0) {
            for (SearchHit hit : searchHists) {
                String id =hit.getId();// (Integer) hit.getSource().get("id");
                String name = (String) hit.getSource().get("name");
                String function = (String) hit.getSource().get("funciton");
                list.add(new Medicine(id, name, function));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Medicine medicine = list.get(i);
            System.out.println("(" + medicine.getId() + ")name:" + medicine.getName());
        }
        System.out.println(System.currentTimeMillis() - s);
        return list;
    }
    public static Medicine searchById(String id) {
        Medicine medicine=null;
        Long s = System.currentTimeMillis();
        GetResponse scrollResponse = client.prepareGet(indexname, type, id).get();
        Map map = scrollResponse.getSource();
        if (map != null){
            String name = (String) map.get("name");
            String function = (String) map.get("funciton");
            medicine= new Medicine(scrollResponse.getId(), name, function);
        }
        System.out.println("==根据id获取" + (System.currentTimeMillis() - s));
        return medicine;
    }
    /**
     * 删除单个索引
     */
    public static void deleteById(String id) {
        try {
            Long s = System.currentTimeMillis();
            client.prepareDelete(indexname,type,id).get();
            System.out.println("==索引删除完成" + (System.currentTimeMillis() - s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除单个索引
     */
    public static void deleteByType() {
        try {
            Long s = System.currentTimeMillis();
            DeleteIndexResponse deleteIndexResponse = client.admin().indices()
                    .prepareDelete(indexname)
                    .execute().actionGet();
            System.out.println("==索引删除完成" + (System.currentTimeMillis() - s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void update(String id) {
        try {
            Long s = System.currentTimeMillis();
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index(indexname);
            updateRequest.type(type);
            updateRequest.id(id);
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("name", "今天天气真好")
                    .endObject());
            client.update(updateRequest).get();
            System.out.println("==索引更新完成" + (System.currentTimeMillis() - s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ElasticSearchHandler es = new ElasticSearchHandler();
//        createIndex(2500000);
//        search(9900,"*中方回应*");
//        deleteById("555");
//        deleteByType();
//        update("100");
        search(1,"*天气*");

//        System.out.println(JsonUtil.obj2JsonData(searchById("556")));
    }
}