package elasticsearch.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CloseIndexRequest;
import org.elasticsearch.client.indices.CloseIndexResponse;

public class ELK_CloseIndexSync {
	public static void main(String[] args) throws Exception {
		RestHighLevelClient client = null;
		
		String INDEX_NAME = "movie_rest";
		
		client = new RestHighLevelClient(
					RestClient.builder(
							new HttpHost("192.168.56.111", 9200, "http"),
							new HttpHost("192.168.56.112", 9200, "http")
					)
				);
		
		CloseIndexRequest request = new CloseIndexRequest(INDEX_NAME);
		
		CloseIndexResponse response = client.indices().close(request, RequestOptions.DEFAULT);
        
        boolean acknowledged = response.isAcknowledged();
		
        System.out.println("acknowledged: " + acknowledged);
		
		
		client.close();
	}
}
