//http://localhost:8080/DS_FinalProject2/TestProject
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private WebPage page;
	private KeywordList keywords;
	private WordCounter counter;
	private URLDecode decoder;
	//private URLEncoder encoder;
	
	private static final long serialVersionUID = 1L;

    public TestProject() {
        super();
        keywords = new KeywordList();
        try {
	        String pwdJ = "C:\\Users\\Danny\\git\\Team17d\\DS_FinalProject2\\keyword.txt";
		    String pwdL = "/Users/ashleylai/git/Team17/DS_FinalProject2/keyword.txt";
			File file = new File("keyword.txt");		
			Scanner scanner = new Scanner(file);
			
			System.out.println("Self-defined keyword list:");
			while(scanner.hasNextLine()){
				
				double weight = Double.parseDouble(scanner.next());
				String name = scanner.next();
				keywords.add(new Keyword(name, weight));
				//test
				System.out.printf("%.2f %s\n", weight, name);
				//System.out.println();
			}
			scanner.close();
        }catch(FileNotFoundException e){
        	System.out.println(e.getMessage());
        }
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//System.out.println(request);
			//System.out.println();
			//System.out.println(response);
			return;
		}
		
		
		//@SuppressWarnings("deprecation")
		//System.out.println("1<--------->");
		String keyword = new String(request.getParameter("keyword")); //.replace(" ", "+")
		//System.out.println("User input keyword (encoded before): " + String.format("'%s'", keyword));
		//keyword = URLEncoder.encode(keyword, "UTF-8"); //"+藝文中心+舞蹈演出+youtube"
		//keyword = URLEncoder.encode(keyword, "UTF-8");
		//System.out.println("User input keyword (encoded after):  " + String.format("'%s'", keyword));
		//keyword = java.net.URLDecoder.decode(keyword, "ISO-8859-1");
		//decoder = new URLDecode(keyword);
		//String decodedKeyword = decoder.decode();
		//System.out.println("User input keyword (decoded after): " + String.format("'%s'", keyword));
		GoogleQuery google = new GoogleQuery(keyword + "+dance"); //+youtube
		//GoogleQuery google = new GoogleQuery(String.format("'%s'", keyword) +"+藝文中心+舞蹈演出+youtube"); //%20兩廳院%20現代舞
//		String k = java.net.URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
//		System.out.println("---------");
//		System.out.println(k);
		//System.out.println("2<--------->");
		System.out.println("User input keyword (after googleQuery): " + String.format("'%s'", keyword));
		System.out.println();
		HashMap<String, String> query = google.query();
		
		//String[][] s = new String[query.size()][2];
		//request.setAttribute("query", s);
		int num = 0;
		WebList webList = new WebList();
		for(Entry<String, String> entry : query.entrySet()) {
			//String sublinkInfo;
		    String key = entry.getKey();
		    String value = entry.getValue();
		    value = value.substring(7, value.indexOf("&"));
		    System.out.println();
		    System.out.println(key);  //title
		    //System.out.println(value); //url
		    //s[num][0] = key;
		    //s[num][1] = value;
		    
		  //count the running time of each web site. If too long, then stop
	    	long startTime = System.currentTimeMillis() / 1000;
	    	long endTime   = startTime + 10;
//	    	while ((System.currentTimeMillis() / 1000) < endTime) {
//		    ExecutorService executor = Executors.newSingleThreadExecutor();
//	        Future<String> future = executor.submit(new Task());
			    //construct a webpage
			    try {		    	
			    	//try to decode the url
			    	decoder = new URLDecode(value);
			    	String decodedValue = decoder.decode();
			    	
				    page = new WebPage(decodedValue, key, ""); //fill in
				    
				    
				    //test
				    System.out.println("decodedValue: "+decodedValue);
				    //counter = new WordCounter(page.url);
				    
				    // construct a webtree
				    WebTree tree = new WebTree(page);
				    HtmlMatcher matcher = new HtmlMatcher(page.url);
				    HashMap<String, String> children = matcher.match();
				    //update picture url
				    String picUrl = matcher.findPic();
				    page.setPicUrl(picUrl);
				    System.out.println("picUrl: " + picUrl);
					//String[][] cs = new String[children.size()][2];
					//int cnum=0;
					ArrayList<String> checkDuplicate = new ArrayList<String> ();
					for(Entry<String, String> entry2 : children.entrySet()) {
						String ckey = entry2.getKey();
					    String cvalue = entry2.getValue();
					    //System.out.println("TestProject126:");
					    //System.out.println("child title: " + ckey);  //title
					    //System.out.println("child url: " + cvalue); //url
					    //check if the sublink appears before
					    if(! checkDuplicate.contains(cvalue)) {
					    	WebNode child = new WebNode(new WebPage(cvalue, ckey, ""));
						    tree.root.addChild(child);
						    //tree.root.nodeScore += child.nodeScore;
						    //System.out.println("<<----------------->>");
						    //cs[cnum][0] = ckey;
						    //cs[cnum][1] = cvalue;
					    	checkDuplicate.add(cvalue);
					    }
					    
					    //cnum++;
					}
					
					
					/*
					for (int i=0; i<cs.size(); i++) {
						//test
						System.out.println("cs[i][1]: "+cs[i][1]);
						tree.root.addChild(new WebNode(new WebPage(cs[i][1], cs[i][0])));
						//page.setScore(keywords);
					}
					*/
					tree.setPostOrderScore(keywords);
					//test
					webList.getLst().add(tree);
				    webList.add(tree);
					//System.out.println("WebTree:");
					//System.out.println(tree.eularPrintTree());
					
					
					//count the running time of each website within 20 sec
					endTime   = System.currentTimeMillis() / 1000;
			    	long totalTime = endTime - startTime;
			    	System.out.println("Total run time: " + totalTime + " sec");
					
					//page.setScore(keywords);
			    }catch(RuntimeException e) {
			    	System.out.println("runtime error");
			    }catch(Exception e) {		    	
					System.out.println("URL may not be linked or other errors!");
					System.out.println(e.getMessage());
				}finally{
					num++;
				}
	    	//}
		}
		System.out.println();
		System.out.println("webList size: " + webList.size());
		System.out.println();
		webList.sort();
//		webList.output();
//		System.out.println(webList.size());
		
		String[][] sortedWebList = new String[18][5]; //webList.getLst().size()
		request.setAttribute("sortedWebList", sortedWebList);
		int count=0;
		int maxSizeOfTitle=20;//webList.getLst().size()-1
		for(int j=webList.getLst().size()-1;j>=webList.getLst().size()-1-17;j--) {
			System.out.println("========="+(count+1)+"=============");
			if(webList.getLst().get(j).root.webPage.name.length() > maxSizeOfTitle) {
				sortedWebList[count][0] = webList.getLst().get(j).root.webPage.name.substring(0,maxSizeOfTitle) + "...";

			}else {

				sortedWebList[count][0] = webList.getLst().get(j).root.webPage.name;

			}
			System.out.println(sortedWebList[count][0]);
			sortedWebList[count][1] = webList.getLst().get(j).root.webPage.url;
			System.out.println(sortedWebList[count][1]);
			sortedWebList[count][2] = String.format("%.1f",webList.getLst().get(j).root.nodeScore);
			//System.out.println("sublinkInfo:\n" + webList.getLst().get(j).eularPrintTree());
			System.out.println("Each web total score: " + sortedWebList[count][2]);
			sortedWebList[count][3] = webList.getLst().get(j).eularPrintTree();
			sortedWebList[count][4] = webList.getLst().get(j).root.webPage.picUrl;
			System.out.println("Each web pic url: " + sortedWebList[count][4]);
			
			System.out.println();
			count++;	
		}
		
		
		request.getRequestDispatcher("searchResult.jsp").forward(request, response); 
		//System.out.println("test:");

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
