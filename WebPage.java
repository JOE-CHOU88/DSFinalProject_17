import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public String picUrl;
	public WordCounter counter;
	public double score;
	
	public WebPage(String url, String name, String picUrl){
		this.url = url;
		this.name = name;
		this.picUrl = picUrl;
		this.counter = new WordCounter(url);	
	}
	
	public void setScore(KeywordList keywords) throws IOException{
		score = 0;
//		3.calculate score
		
		for(Keyword k :keywords.lst()){	
			score += k.weight * counter.countKeyword(k.name);
			// System.out.println(score);
		}
		
		//score=keywords.outputScore();
		
	}
	
	public void setPicUrl(String newPicUrl) {
		this.picUrl = newPicUrl;
	}
	
}