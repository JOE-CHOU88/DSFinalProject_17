import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLDecode {
	private String encodedURL;
	public URLDecode(String encodedURL){
		this.encodedURL = encodedURL;
	}
	
	public String decode() {

		// 待編碼的網址
	    //encodedURL = "https://www.sunderland.ac.uk/study/performing-arts/advanced-dance-performance/";
	
	    try {
	    	// 進行 URL 百分比解碼
	        String url = URLDecoder.decode(encodedURL, "UTF-8");
	
	        // 輸出結果
	        //System.out.println("Decode result: " + url);
	        return url;
	
	    } catch (UnsupportedEncodingException e) {
	      // 例外處理 ...
	    	//System.out.println("Cannot decode the URL");
	    	return "Cannot decode the URL";
	    }
	}
}

