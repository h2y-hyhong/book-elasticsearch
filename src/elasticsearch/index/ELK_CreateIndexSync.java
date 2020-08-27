package elasticsearch.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class ELK_CreateIndexSync {
	public static void main(String[] args) throws Exception {
		RestHighLevelClient client = null;
		
		String INDEX_NAME = "movie_rest";
		
		client = new RestHighLevelClient(
					RestClient.builder(
							new HttpHost("192.168.56.111", 9200, "http"),
							new HttpHost("192.168.56.112", 9200, "http")
					)
				);
		
		// 매핑정보
        XContentBuilder indexBuilder = XContentFactory.jsonBuilder();
        
        indexBuilder.startObject()
	                .startObject("properties")
		                .startObject("movieCd")
			                .field("type","keyword")
			                .field("store","true")
			                .field("index_options","docs")
		                .endObject()
		                .startObject("movieNm")
			                .field("type","text")
			                .field("store","true")
			                .field("index_options","docs")
		                .endObject()
		                .startObject("movieNmEn")
			                .field("type","text")
			                .field("store","true")
			                .field("index_options","docs")
		                .endObject()
	                .endObject()
                .endObject();

        // 매핑 설정
        CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME);

        // Alias 설정
        String ALIAS_NAME = "moive_auto_alias";
        request.alias(new Alias(ALIAS_NAME));

        // 인덱스 생성
        CreateIndexResponse createIndexResponse = 
        		client.indices().create(request, RequestOptions.DEFAULT);
        
        
        boolean acknowledged = createIndexResponse.isAcknowledged();
		
        System.out.println("acknowledged: " + acknowledged);
		
		
		client.close();
	}
}
