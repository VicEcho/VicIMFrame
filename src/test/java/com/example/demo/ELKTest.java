package com.example.demo;

import org.apache.http.HttpHost;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * @author Vic Zhang
 * @date 2020/3/31 2:11 PM
 */
public class ELKTest {

    public void searchMessage(RestHighLevelClient client) {
        SearchRequest searchRequest = new SearchRequest("person");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println("查询到的消息为=====" + response);
        } catch (Exception e) {

        }
    }

    public void getRequest(RestHighLevelClient client) {
        GetRequest getRequest = new GetRequest(
                "person",
                "3");
        String[] includes = new String[]{"name"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        try {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println("getResponse" + getResponse + "2222222" + getResponse.getSource());
        } catch (Exception e) {

        }

    }

    public void indexDocument(RestHighLevelClient client) {
        try {
            IndexRequest indexRequest = new IndexRequest("message", "_doc", "2");
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("user", "vic2");
            jsonMap.put("age", "28");
            jsonMap.put("message", "test2");
            indexRequest.source(jsonMap);
            IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("reponse" + response);
        } catch (Exception e) {
            System.out.println("e" + e);
        }
    }

    public static void main(String[] args) {
        try {
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http"),
                            new HttpHost("localhost", 9201, "http")));
            ELKTest elkTest = new ELKTest();
//            elkTest.searchMessage(client);
//            elkTest.indexDocument(client);
            elkTest.getRequest(client);
        } catch (Exception e) {
        }
    }
}
