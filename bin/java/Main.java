//front-end index.jsp link：http://localhost:8080/DS_FinalProject/index.jsp
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Main {//
	public static void main(String[] args) throws IOException {
//		WordCounter counter = new WordCounter("https://npac-ntch.org/zh");
//		WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Welcome.html", "Welcome");		
//		WebTree tree = new WebTree(rootPage);
//		//build childnode (at least retrieve one sublink)
//		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html", "Publications")));
//		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Projects.html", "Projects")));
//		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://vlab.cs.ucsb.edu/stranger/", "Stranger")));
//		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Members.html", "Members")));
//		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/course.htm", "Course")));
		
		
//		//You need to change scanner into reading file
//		KeywordList lst = new KeywordList();
//		File file = new File("keyword.txt");		
//		Scanner scanner = new Scanner(file);
//	
//		while(scanner.hasNextLine()){
//			String operation = scanner.next();
//			
//			switch (operation){
//				case "add":
//					double weight = Double.parseDouble(scanner.next());
//					System.out.println(weight);
//					String name = scanner.next();
//					System.out.println(name);
//					int count = counter.countKeyword(name);
//					System.out.println(count);
//					lst.add(new Keyword(name, count, weight));
//					// lst.output();
//					System.out.println();
//					break;
//				case "sort":
//					lst.sort();
//					break;
//				case "output":
//					lst.output();
//					break;
//				default:
//					System.out.println("InvalidOperation");
//					System.out.println("^^");
//					break;
//			}	
//		}
//		
//		scanner.close();

		//read 2 Yu 1.2 Fang 1.8 
//		scanner = new Scanner(System.in);
//		
//		while(scanner.hasNextLine()){
//			int numOfKeywords = scanner.nextInt();//2
//			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
//			
//			for(int i =0;i<numOfKeywords;i++)
//			{
//				String name = scanner.next();//Yu
//				double weight = scanner.nextDouble();//1.2
//				int count = counter.countKeyword(name);
//				Keyword k = new Keyword(name, count, weight);//store key
//				keywords.add(k);
//			}
//			
//			tree.setPostOrderScore(keywords);
//			tree.eularPrintTree();
//		}
//		scanner.close();
	}
	static {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
	}
}