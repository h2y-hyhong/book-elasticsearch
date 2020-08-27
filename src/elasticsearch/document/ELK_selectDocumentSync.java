package elasticsearch.document;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ELK_selectDocumentSync {

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
		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		
		if(response.isExists()) {
			long version = response.getVersion();
			Map<String, Object> sourceAsMap = response.getSourceAsMap();
			
			System.out.println("version: " + version);
			System.out.println(sourceAsMap.toString());
			
		} else {
			System.out.println("문서가 존재하지 않습니다.");
		}
		
		
		client.close();
	}

}
