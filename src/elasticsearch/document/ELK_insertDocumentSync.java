package elasticsearch.document;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;

public class ELK_insertDocumentSync {

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

		IndexRequest request = new IndexRequest(INDEX_NAME);
		request.id(_id);
		
		XContentBuilder builder = XContentFactory.jsonBuilder();
		
		builder.startObject()
			.field("movieCd", "20173732")
			.field("movieNm", "살아남은 아이")
			.field("movieNmEn", "Last Child")
			.endObject();
		
		request.source(builder);
		
		IndexResponse response = null;
		
		try {
			response = client.index(request, RequestOptions.DEFAULT);
		} catch(ElasticsearchException e) {
			if(e.status() == RestStatus.CONFLICT) {
				System.out.println("문서 생성에 실패하였습니다.");
			}
		} finally {
			System.out.println(response.getResult());
			System.out.println(response.getId());
		}
		
		client.close();
	}

}
