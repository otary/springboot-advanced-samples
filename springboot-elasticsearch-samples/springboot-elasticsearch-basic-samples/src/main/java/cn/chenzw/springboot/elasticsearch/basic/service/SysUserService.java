package cn.chenzw.springboot.elasticsearch.basic.service;

import cn.chenzw.springboot.elasticsearch.basic.domain.entity.SysUser;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String indexName = "user_index2";

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 新建索引
     *
     * @throws IOException
     */
    public void createIndex() throws IOException {
        if (existsIndex(indexName)) {
            logger.info("{}索引已存在!", indexName);
            return;
        }

        CreateIndexRequest request = new CreateIndexRequest(indexName);

        // 配置settings
        request.settings(
                Settings.builder()
                        .put("index.number_of_shards", 3)
                        .put("index.number_of_replicas", 2)
        );

        // 创建文档类型映射
        request.mapping("{\"properties\":{\"userName\":{\"type\":\"text\"},\"age\":{\"type\":\"long\"},\"id\":{\"type\":\"long\"},\"orgId\":{\"type\":\"long\"}}}"
                , XContentType.JSON);

        // 设置别名
        request.alias(new Alias("user_index_alias"));

        // 同步执行
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        if (createIndexResponse.isAcknowledged()) {
            logger.info("所有节点已确认请求!");
        }

        if (createIndexResponse.isShardsAcknowledged()) {
            logger.info("在超时之前为索引中的每个分片启动了必需的分片副本数!");
        }

        // 异步执行
        /*restHighLevelClient.indices().createAsync(request, RequestOptions.DEFAULT, new ActionListener<CreateIndexResponse>() {

            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                // 执行成功回调
            }

            @Override
            public void onFailure(Exception e) {
                // 执行失败回调
            }
        });*/

    }


    /**
     * 删除索引
     */
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);

        //设置IndicesOptions控制如何解决不可用的索引以及如何扩展通配符表达式
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        // 同步执行
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            logger.info("所有节点都已确认请求!");
        }

        /*restHighLevelClient.indices().deleteAsync(request, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
            @Override
            public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                // 删除成功回调
            }

            @Override
            public void onFailure(Exception e) {
                // 删除失败回调
            }
        });*/

    }


    /**
     * 指定索引是否存在
     *
     * @return
     * @throws IOException
     */
    public boolean existsIndex(String indexName) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 字符串形式插入
     *
     * @throws IOException
     */
    public void insertByString() throws IOException {
        IndexRequest indexRequest = new IndexRequest(
                indexName,   // 索引名称
                "_doc",   // 类型名称
                "1"   // 文档ID
        ).source("{\"userName\":\"张三\",\"age\":12,\"orgId\":1,\"id\":1}", XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        logger.info("Result: {}", indexResponse.getResult());
        // DocWriteResponse.Result.CREATED => 首次创建文档
        // DocWriteResponse.Result.UPDATED => 文档被重写
        logger.info("index: {}， type: {}, id: {}, version: {}",
                indexResponse.getIndex(),
                indexResponse.getType(),
                indexResponse.getId(),
                indexResponse.getVersion());
    }


    /**
     * Map形式插入
     *
     * @throws IOException
     */
    public void insertMap() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", "王五");
        data.put("age", 20);
        data.put("orgId", 1);
        data.put("id", 2);

        IndexRequest indexRequest = new IndexRequest(
                indexName,   // 索引名称
                "_doc",   // 类型名称
                "2"   // 文档ID
        ).source(data);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        logger.info("Result: {}", indexResponse.getResult());
        // DocWriteResponse.Result.CREATED => 首次创建文档
        // DocWriteResponse.Result.UPDATED => 文档被重写
        logger.info("index: {}， type: {}, id: {}, version: {}",
                indexResponse.getIndex(),
                indexResponse.getType(),
                indexResponse.getId(),
                indexResponse.getVersion());
    }

    /**
     * XContent形式插入
     */
    public void insertXContent() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();

        builder.field("userName", "赵六");
        builder.field("age", 12);
        builder.field("orgId", 34);
        builder.field("id", 5);

        builder.endObject();

        IndexRequest indexRequest = new IndexRequest(
                indexName,   // 索引名称
                "_doc",   // 类型名称
                "3"   // 文档ID
        ).source(builder);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        logger.info("Result: {}", indexResponse.getResult());
        // DocWriteResponse.Result.CREATED => 首次创建文档
        // DocWriteResponse.Result.UPDATED => 文档被重写
        logger.info("index: {}， type: {}, id: {}, version: {}",
                indexResponse.getIndex(),
                indexResponse.getType(),
                indexResponse.getId(),
                indexResponse.getVersion());
    }

    /**
     * Object形式插入
     *
     * @throws IOException
     */
    public void insertByObject() throws IOException {
        IndexRequest indexRequest = new IndexRequest(
                indexName,   // 索引名称
                "_doc",   // 类型名称
                "5"   // 文档ID
        ).source("userName", "林七", "age", 1, "orgId", 9);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        logger.info("Result: {}", indexResponse.getResult());
        // DocWriteResponse.Result.CREATED => 首次创建文档
        // DocWriteResponse.Result.UPDATED => 文档被重写
        logger.info("index: {}， type: {}, id: {}, version: {}",
                indexResponse.getIndex(),
                indexResponse.getType(),
                indexResponse.getId(),
                indexResponse.getVersion());
    }


    /**
     * 批量插入
     */
    public void bulkInsert() throws IOException {
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userName", "张" + i + "娃");
            userMap.put("age", i);
            userMap.put("orgId", i);
            userMap.put("id", i);
            request.add(new IndexRequest(
                    indexName,
                    "_doc"
            ).source(userMap));
        }
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
    }


    /**
     * 根据指定条件更新
     *
     * @throws IOException
     */
    public void updateByQuery() throws IOException {
        /*UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest();
        updateByQueryRequest.setQuery(QueryBuilders.termQuery("id", "3"));

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("userName", "赵六神");
        builder.field("age", 12);
        builder.field("orgId", 34);
        builder.field("id", 5);
        builder.endObject();
        updateByQueryRequest.toXContent(builder);
        restHighLevelClient.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);*/
    }

    /**
     * 根据ID修改
     */
    public void update() throws IOException {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userName", "修改名称");
        userMap.put("age", 10);
        userMap.put("orgId", 111);
        userMap.put("id", 3);

        UpdateRequest updateRequest = new UpdateRequest(indexName, "_doc", "3").doc(userMap);
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        if (response.status().getStatus() == 200) {
            logger.info("更新成功!");
        }
    }

    private void search(QueryBuilder queryBuilder) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(100);
        sourceBuilder.query(queryBuilder);

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        logger.info("总条数: {}", totalHits);

        for (SearchHit hit : hits) {
            Map source = hit.getSourceAsMap();
            logger.info(source.toString());
        }
    }


    /**
     * 查询所有
     *
     * @throws IOException
     */
    public void matchAllQuery() throws IOException {
        // 全量查询
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

        this.search(queryBuilder);
    }

    /**
     * 匹配查询
     */
    public void matchQuery() throws IOException {
        // 模糊查询
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("userName", "张*");

        this.search(queryBuilder);
    }

    /**
     * 精准匹配？
     */
    public void commonTermsQuery() throws IOException {
        QueryBuilder queryBuilder = QueryBuilders.commonTermsQuery("userName", "张");
        this.search(queryBuilder);
    }

    /**
     * 多条件匹配查询
     */
    public void multiMatchQuery() throws IOException {
        // orgId 或 id 字段等于1
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("1", "orgId", "id");
        this.search(queryBuilder);
    }

    /**
     * 多条件查询union，score沿用子查询score的最大值
     *
     * @throws IOException
     */
    public void disMaxQuery() throws IOException {
        DisMaxQueryBuilder queryBuilder = QueryBuilders.disMaxQuery();
        queryBuilder
                .add(QueryBuilders.matchQuery("userName", "张"))
                .add(QueryBuilders.matchQuery("userName", "王"));
        this.search(queryBuilder);
    }

    /**
     * ID查询
     *
     * @throws IOException
     */
    public void idQuery() throws IOException {
        IdsQueryBuilder queryBuilder = QueryBuilders.idsQuery();
        queryBuilder.addIds("1", "2", "5");

        this.search(queryBuilder);
    }


    /**
     * 分词精确查询
     */
    public void termQuery() throws IOException {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("userName", "张三");

        this.search(queryBuilder);
    }

    /**
     * 模糊查询
     */
    public void fuzzyQuery() throws IOException {
        FuzzyQueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("userName", "五");

        this.search(queryBuilder);
    }

    /**
     * 前缀查询
     *
     * @throws IOException
     */
    public void prefixQuery() throws IOException {
        PrefixQueryBuilder queryBuilder = QueryBuilders.prefixQuery("userName", "六");

        this.search(queryBuilder);
    }

    /**
     * 范围查询
     *
     * @throws IOException
     */
    public void rangeQuery() throws IOException {
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("age").from(1).to(15)
                .includeLower(true)  // 包含下界
                .includeUpper(true); // 包含上界

        this.search(queryBuilder);
    }

    /**
     * 通配符查询
     */
    public void wildcardQuery() throws IOException {
        WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("userName", "张*");

        this.search(queryBuilder);
    }

    /**
     * 正则表达式查询
     */
    public void regexpQuery() throws IOException {
        RegexpQueryBuilder queryBuilder = QueryBuilders.regexpQuery("userName", "张");

        this.search(queryBuilder);
    }


    /**
     * @throws IOException
     */
    public void queryStringQuery() throws IOException {
        // 查询含2的值
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("2");

        this.search(queryBuilder);
    }

    /**
     * 嵌套查询
     */
    public void booleanQuery() throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("userName", "张"))
                .must(QueryBuilders.matchQuery("orgId", 1));

        this.search(queryBuilder);
    }


    /**
     * 相对于起始位置的偏移量
     */
    public void spanFirstQuery() throws IOException {
        // 相对于起始位置偏移大于3
        SpanFirstQueryBuilder queryBuilder = QueryBuilders.spanFirstQuery(
                QueryBuilders.spanTermQuery("userName", "娃"), 3);

        this.search(queryBuilder);
    }

}
