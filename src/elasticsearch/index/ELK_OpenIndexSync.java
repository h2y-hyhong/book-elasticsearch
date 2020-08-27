package elasticsearch.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ELK_OpenIndexSync {
	public static void main(String[] args) throws Exception {
		RestHighLevelClient client = null;
		
		String INDEX_NAME = "movie_rest";
		
		client = new RestHighLevelClient(
					RestClient.builder(
							new HttpHost("192.168.56.111", 9200, "http"),
							new HttpHost("192.168.56.112", 9200, "http")
					)
				);
		
		OpenIndexRequest request = new OpenIndexRequest(INDEX_NAME);
		
		OpenIndexResponse response = client.indices().open(request, RequestOptions.DEFAULT);
        
        boolean acknowledged = response.isAcknowledged();
		
        System.out.println("acknowledged: " + acknowledged);
		
		
		client.close();
	}
}
