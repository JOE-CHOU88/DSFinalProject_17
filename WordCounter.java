import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException, RuntimeException{
		
    	URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		//System.out.println("test!!!!!!!!!!!!!!!!!");
		//System.out.println(in);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		//count the running time of fetching each web site. If too long, then stop
		long startTime = System.currentTimeMillis() / 1000;
		long endTime   = startTime + 10;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		    if((System.currentTimeMillis() / 1000) > endTime) {
		    	throw new RuntimeException("runtime error");
		    	//return "runtime error";
		    }
//		    System.out.println("-------------------------");
//		    System.out.println(retVal);
//		    System.out.println("-------------------------");
		}
	
		return retVal;
    }
    
    public int countKeyword(String keyword) throws IOException, RuntimeException{
		if (content == null){
			//test
			//System.out.println("urlStr: "+urlStr);
		    content = fetchContent();
		    //System.out.println(content);
		}
//		if (content != "runtime error") {
			//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
			content = content.toUpperCase();
			//System.out.println(content);
			keyword = keyword.toUpperCase();
			//System.out.println(keyword);
		
			int retVal = 0; 
			// 1. calculates appearances of keyword
			while(content.indexOf(keyword) != -1) {
				content = content.substring(content.indexOf(keyword) + keyword.length());
				retVal += 1;
				//System.out.println(content);
				//System.out.println("----------------");
			}
			
			return retVal;
//		}else {
//			return -1;
//		}
    }
}
