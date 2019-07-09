# springboot-elasticsearch-samples

elasticsearch示例


- 集成了elasticsearch-analysis-ik分词器

## 端口

- 9300: Java程序访问的端口
- 9200: 浏览器、postman访问的端口

访问：http://localhost:9200

```
{
  "name" : "29r2Tay",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "BF6xHfehQuGP8yV9gE_SLQ",
  "version" : {
    "number" : "6.8.1",
    "build_flavor" : "default",
    "build_type" : "zip",
    "build_hash" : "1fad4e1",
    "build_date" : "2019-06-18T13:16:52.517138Z",
    "build_snapshot" : false,
    "lucene_version" : "7.7.0",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
  },
  "tagline" : "You Know, for Search"
}
```

## 注意事项

- 实体对象使用org.springframework.data.elasticsearch.annotations.Document注解，并且indexName属性必须小写

