import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlMatcher {
	private String urlStr;
    private String content;
    
    public HtmlMatcher(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		//System.out.println("1Hey!!!!!!!!!!!!!!!");
		URLConnection conn = url.openConnection();
		//System.out.println("2Hey!!!!!!!!!!!!!!!");
		conn.setConnectTimeout(3000); //test if it takes too long to link
		InputStream in = conn.getInputStream();
		//System.out.println("3Hey!!!!!!!!!!!!!!!");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//System.out.println("4Hey!!!!!!!!!!!!!!!");
		String retVal = "";
	
		String line = null;
		long startTime = System.currentTimeMillis() / 1000;
		long endTime   = startTime + 10;
		while((line=br.readLine())!=null)
		{
			retVal += line;
			if((System.currentTimeMillis() / 1000) > endTime) {
		    	return "runtime error";
		    }

		}
		return retVal;
    }
    public HashMap<String, String> match() throws IOException

	{
    	//ArrayList<String> array= new ArrayList<String>(); 
		if(content==null)

		{

			content= fetchContent();

		}
		
		if (content != "runtime error") {
			HashMap<String, String> retVal = new HashMap<String, String>();
			//HashMap<String, String> retTitleUrl = new HashMap<String, String>();
			Document doc = Jsoup.parse(content);
	//		System.out.println(doc.text());
			Elements lis = doc.select("a");
	//		 System.out.println(lis);
			//lis = lis.select(".kCrYT");
//			System.out.println("----------------------------");
//			System.out.println(lis);
//			System.out.println("----------------------------");
	//		System.out.println(lis.size());
			
			
			for(Element li : lis)
			{
				try 
	
				{
					//test
					//System.out.println(li);
					String citeUrl = li.attr("href");
					citeUrl=citeUrl.substring(citeUrl.indexOf("http"), citeUrl.indexOf("&"));
					URLDecode decoder = new URLDecode(citeUrl);
			    	String decodedValue = decoder.decode();
					String title = li.text();
					/*
					if(title.equals("")) {
						continue;
					}
					*/
					//test
					//System.out.println(title + ","+citeUrl);
					//System.out.println("<----------------------->");
					
					retVal.put(title, decodedValue);
					//retTitleUrl.put(title, decodedValue);
					//retVal.put(retTitleUrl, childPicUrl);
					
					//array.add(citeUrl);
//					System.out.println("HtmlMatcher: 88");
//					for (String ss: array) {
//						System.out.println("child link: " + ss);
//						//retVal.put("title", ss);
//					}
	
				} catch (IndexOutOfBoundsException e) {
	
	//				e.printStackTrace();
	
				}
	
				
	
			}
//			for(String ss: array) {
//				retVal.put("title", ss);
//			}
//			System.out.println("HtmlMatcher: 107");
//			for(String value:retVal.values()) {
//				System.out.println(value);
//			}
			
			return retVal;
		}else {
			return null;
		}

	}
    
//    private String fetchChildContent(String sublink) throws IOException{
//		URL url = new URL(sublink);
//		URLConnection conn = url.openConnection();
//		InputStream in = conn.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//	
//		String retVal = "";
//	
//		String line = null;
//		long startTime = System.currentTimeMillis() / 1000;
//		long endTime   = startTime + 10;
//		while((line=br.readLine())!=null)
//		{
//			retVal += line;
//			if((System.currentTimeMillis() / 1000) > endTime) {
//		    	return "runtime error";
//		    }
//
//		}
//		return retVal;
//    }
  
    public String findPic() throws Exception{
    	if(content==null)

		{

			content= fetchContent();

		}
		
		if (content != "runtime error") {
			String retPicUrl = "";
			
			Document doc = Jsoup.parse(content);
	//		System.out.println(doc.text());
			Elements lis = doc.select("link[rel=\"icon\"]");
			System.out.println("lis:");
			System.out.println(lis);
			//lis = lis.select("[rel=shortcut icon]");
			
			
			for(Element li : lis)
			{
				try 
	
				{
					//test
					//System.out.println(li);
					String citeUrl = li.attr("href");
					//some links just have relative path
					if(citeUrl.indexOf("http") == -1) {
						if(urlStr.contains(".com.tw")) {
							citeUrl = urlStr.substring(0, urlStr.indexOf(".com.tw")+7) + citeUrl;
						}else if(urlStr.contains(".com")){
							citeUrl = urlStr.substring(0, urlStr.indexOf(".com")+4) + citeUrl;
						}
						
					}
					//citeUrl=citeUrl.substring(citeUrl.indexOf("http"), citeUrl.indexOf("&"));
					URLDecode decoder = new URLDecode(citeUrl);
					retPicUrl = decoder.decode();
					
					if (HtmlMatcher.testConnection(retPicUrl) != 200) {
						retPicUrl = "";
					}
					/*
					if(title.equals("")) {
						continue;
					}
					*/
					//test
					//System.out.println(title + ","+citeUrl);
					//System.out.println("<----------------------->");
					
					//array.add(citeUrl);
//					System.out.println("HtmlMatcher: 88");
//					for (String ss: array) {
//						System.out.println("child link: " + ss);
//						//retVal.put("title", ss);
//					}
	
				} catch (IndexOutOfBoundsException e) {
	
	//				e.printStackTrace();
	
				}
	
				
	
			}
			return retPicUrl;
		}else {
			return null;
		}
    }
    
    public static int testConnection(String address) throws Exception {
    	int status = 404;
    	try {
    		URL urlObj = new URL(address);
    		HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
    		oc.setUseCaches(false);
    		oc.setConnectTimeout(3000); // 設定超時時間
    		status = oc.getResponseCode();// 請求狀態
    		if (200 == status) {
    			// 200是請求地址順利連通。。
    			return status;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	return status;
    }

 /* 
    public HashMap<String, String> match() throws IOException{	
    	HashMap<String, String> retVal = new HashMap<String, String>();
    	if(content==null){
		    
			content = fetchContent();
		
		}
		
		if (content != "runtime error") {
			//HashMap<String, String> retVal = new HashMap<String, String>();
			
			Document doc = Jsoup.parse(content);
		//	System.out.println(doc.text());
			Elements lis = doc.select("a");
		//	Elements lis = doc.select("a");
		//	System.out.println(lis);
		//	lis = lis.select(".kCrYT");
//			System.out.println("----------------------------");
//			System.out.println(lis);
//			System.out.println("----------------------------");
//			System.out.println(lis.size());
				
				
			for(Element li : lis)
			{
				try 
	
				{
					//test
					//System.out.println("test htmlMatcher!!!!!!!!!!!");
					//System.out.println(li);
					//System.out.println();
					String citeUrl = li.attr("href");
					citeUrl=citeUrl.substring(citeUrl.indexOf("http"), citeUrl.indexOf("&"));
					URLDecode decoder = new URLDecode(citeUrl);
			    	String decodedValue = decoder.decode();
					//String title = li.select("a").get(0).select(".vvjwJb").text();
					String title="title";
					/*
					if(title.equals("")) {
						continue;
					}
					
					//test
					System.out.println("htmlmatcher:84");
					System.out.println(title + ","+decodedValue);
					retVal.put(title, decodedValue);
					for(String value : retVal.values()) {

			            System.out.println(value);

			        }
					System.out.println();
					
				} catch (IndexOutOfBoundsException e) {
	
	//				e.printStackTrace();
	
				}
				
			}
			System.out.println("htmlMatcher:96");
			for(String value : retVal.values()) {

	            System.out.println(value);

	        }
			System.out.println();
			
			return retVal;
		}else {
			return null;
		}
    }
*/    
}		
		
		// del
/*
		//Create a stack to store the tag
		Stack<String> tagStack = new Stack<>();
		
		int indexOfOpen = 0;
 		
		while((indexOfOpen = content.indexOf("<",indexOfOpen))!=-1){
			//Get full tag. e.g. "<div id="abcdefg">","</a>","</div>"...
			
			int indexOfClose = content.indexOf(">", indexOfOpen);//**here
			String fullTag = content.substring(indexOfOpen, indexOfClose+1);//**here
			
			//Extract tag name from fullTag. e.g. "div","/a","/div"...
			String tagName = null;
			int indexOfSpace = -1; //��l�]�䤣��Ů�
			if((indexOfSpace=fullTag.indexOf(" "))==-1){
				//If there is no space in the fullTag (e.g. "<li>","</a>","</div>") 
				//then the tag name will be the words between first and last character.
				//For example, if  fullTag is "<li>" then the tagName will be "li";
				//For example, if fullTag is  "</li>" then the tagName will be "/li" (Note that we preserve the slash'/' so we can tell that this is a close tag in the future)
				//1. **here
				tagName = fullTag.substring(1, fullTag.length()-1);

			}else{
				//If there are some space in the fullTag (e.g "<li id='theID'>","<a href='http://www.google.com.tw/'>") 
				//then the tag name will be the words between first character and the first space.
				//For example, if fullTag is "<li id='theID'>" the tagName will be "li";
				//For example, if fullTag is "<a href='http://www.google.com.tw/'>" the tagName will be "a"
			    //2. **here   
				tagName = fullTag.substring(1, indexOfSpace);

			}
			if(tagName.equals("meta") || tagName.equals("!doctype")) {
				indexOfOpen = indexOfClose;
				continue;
			}
			
			//Determine whether this tag is an open tag (e.g. "<div>") or close tag (e.g. "</div>")
			int indexOfSlash = -1;
			if((indexOfSlash = tagName.indexOf("/"))==-1){
				//This is an open tag, so simply 'push' it into stack
				//3. **here
				tagStack.push(tagName);
			}
			else{  	
				//This is an close tag, so we should compare it to the topmost tag in the stack
				//Remove the slash '/' (the first character of tagName), so that we can compare it with the open tag name in stack
				//4. **here
				tagName = tagName.substring(indexOfSlash+1);
				
			    //But...what if there is no topmost tag in the stack
				if(tagStack.isEmpty()){
				    //stack is empty, this tag is an invalid tag
				    System.out.println("False");
				    return;
				}				
				
				//Compare to topmost tag in the stack
				String topMostTag = tagStack.peek();
				
				if(topMostTag.equals(tagName)){
					//This tagName is equal to the tag name in the stack!
					//'Pop' out the top tag in the stack.
					//5.**here
					tagStack.pop();
				}else{
					//This tagName is not equal to the tag name in the stack!
					//So we found that this tag is an invalid tag
					System.out.println("False "+getStackString(tagStack));
					return;
				}
			}			
			//Move the searching start point, so that we can search the next tag in htmlContent
			indexOfOpen = indexOfClose;
		}		
		
		//After search and compare all the tag in the htmlContent,
		//We should also check whether the stack is empty or not.
		if(!tagStack.isEmpty()){
			//The stack is not empty, this mean the tags is invalid
			//...
		    	System.out.println("False "+getStackString(tagStack));
		}else 
		{
			//The stack is empty, all tags successfully matched.
			System.out.println("True");
		} 
*/
/*    
private String getStackString(Stack<String> stack){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<stack.size();i++){
		    if(i>0){
		    	sb.append(" ");
		    }
		    sb.append(stack.get(i));
		}	
		return sb.toString();
    }
}
*/