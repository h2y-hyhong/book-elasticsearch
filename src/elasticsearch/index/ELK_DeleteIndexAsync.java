package elasticsearch.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ELK_DeleteIndexAsync {
	public static void main(String[] args) throws Exception {
		RestHighLevelClient client = null;
		
		String INDEX_NAME = "movie_rest";
		
		client = new RestHighLevelClient(
					RestClient.builder(
							new HttpHost("192.168.56.111", 9200, "http"),
							new HttpHost("192.168.56.112", 9200, "http")
					)
				);
		
		DeleteIndexRequest request = new DeleteIndexRequest(INDEX_NAME);
		
		ActionListener<AcknowledgedResponse> listener = new ActionListener<AcknowledgedResponse>() {
			
			@Override
			public void onResponse(AcknowledgedResponse response) {
				System.out.println("Acknowledged: " + response.isAcknowledged());
			}
			
			@Override
			public void onFailure(Exception e) {
				System.out.println("error: " + e.getMessage());
				e.printStackTrace();
			}
		};
		
		client.indices().deleteAsync(request, RequestOptions.DEFAULT, listener);
	}
}
