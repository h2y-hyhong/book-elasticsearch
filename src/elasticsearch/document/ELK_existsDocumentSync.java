package elasticsearch.document;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ELK_existsDocumentSync {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = null;
		
		String INDEX_NAME = "movie_rest";
		String _id = "1";
		
		client = new RestHighLevelClient(
					RestClient.builder(
							new HttpHost("192.168.56.111", 9200, "http"),
							new HttpHost("192.168.56.112", 9200, "http")
					)
				);

		GetRequest request = new GetRequest(INDEX_NAME, _id);
		boolean exists = client.exists(request, RequestOptions.DEFAULT);
		
		System.out.println("exists: " + exists);
		
		client.close();
	}

}
