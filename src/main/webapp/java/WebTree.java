import java.io.IOException;
import java.util.ArrayList;


public class WebTree {
	public WebNode root;
	public String sublinkInfo = "";
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	
	public void setPostOrderScore(KeywordList keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, KeywordList keywords) throws IOException{
		//1.compute the score of children nodes postorder
		for(WebNode child : startNode.children){
			child.setNodeScore(keywords);
		}
		//**setNode score of startNode
		startNode.setNodeScore(keywords);
	}
	
	public String eularPrintTree(){
		return eularPrintTree(root);
	}
	
	private String eularPrintTree(WebNode startNode){
		
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) {
			System.out.print("\n" + repeat("\t", nodeDepth-1));
			sublinkInfo += "<br>" + repeat("&emsp;&emsp;", nodeDepth-1);
		}
		
		//print "("
		System.out.print("(");
		sublinkInfo += "(";
		//print "name","score"
		
		System.out.print(startNode.webPage.name+","+startNode.nodeScore);
		//sublinkInfo += startNode.webPage.name.substring(0, 15)+","+startNode.nodeScore;
		if(startNode.webPage.name.length()>18) {
			sublinkInfo += String.format("%-18s, %.1f",startNode.webPage.name.substring(0, 15)+"...", startNode.nodeScore);
		}else {
			sublinkInfo += String.format("%-18s, %.1f",startNode.webPage.name, startNode.nodeScore);
		}
		
		//2.print child preorder
		for(WebNode child : startNode.children){
			eularPrintTree(child);
		}
		
		//print ")"
		System.out.print(")");
		sublinkInfo += ")";
		
		/*for example
		(Soslab,459.0
				(Publication,286.2)
				(Projects,42.0
						(Stranger,0.0)
				)
				(MEMBER,12.0)
				(Course,5.3999999999999995)
		)
		*/
		if(startNode.isTheLastChild()) {
			System.out.print("\n" + repeat("\t", nodeDepth-2));
			sublinkInfo += "<br>" + repeat("&emsp;&emsp;", nodeDepth-2);
		}
		return sublinkInfo;
	}
	
	private String repeat(String str,int repeat){
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			retVal+=str;
		}
		return retVal;
	}
}
