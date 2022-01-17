import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;

import java.util.HashMap;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class GoogleQuery 

{

	public String searchKeyword;

	public String url;

	public String content;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;


		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=50";
		System.out.println("url: " + url);

	}

	

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();
		
		conn.setConnectTimeout(3000); //test if it takes too long to link
		
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		
		//count the running time of fetching each web site. If too long, then stop
		long startTime = System.currentTimeMillis() / 1000;
		long endTime   = startTime + 10;
		while((line=bufReader.readLine())!=null)
		{
			retVal += line;
			if((System.currentTimeMillis() / 1000) > endTime) {
		    	return "runtime error";
		    }

		}
		return retVal;
	}
	public HashMap<String, String> query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}
		
		if (content != "runtime error") {
			HashMap<String, String> retVal = new HashMap<String, String>();
			
			Document doc = Jsoup.parse(content);
	//		System.out.println(doc.text());
			Elements lis = doc.select("div");
	//		 System.out.println(lis);
			lis = lis.select(".kCrYT");
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
					
					String citeUrl = li.select("a").get(0).attr("href");
					String title = li.select("a").get(0).select(".vvjwJb").text();
					if(title.equals("")) {
						continue;
					}
					
					//test
					System.out.println(title + ","+citeUrl);
					System.out.println("<----------------------->");
					retVal.put(title, citeUrl);
					
				} catch (IndexOutOfBoundsException e) {
	
	//				e.printStackTrace();
	
				}
	
				
	
			}
	
			
			return retVal;
		}else {
			return null;
		}

	}

	

	

}
