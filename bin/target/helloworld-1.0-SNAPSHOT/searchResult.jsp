<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>舞松打狗</title>
    <link rel="icon shortcut" href="./img/favicon.ico">
    <!-- Bootstrap官方網站 https://getbootstrap.com/ -->
    <!-- 連結Bootstrap.min.css -->
    <!-- 客製化Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap.css">
    <!-- 使用font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
        integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <!-- 使用style.css -->
    <link rel="stylesheet" href="searchResult.css">
</head>

<body>
		<% 
		/*
		<a href='<%=url'><%=title</a> <br>Total score: <%=score<br>
		<br>
		*/ 
		%>
		
    <!-- header -->
    <!-- introSection -->
    <!-- 設定introSection 上下padding為5倍 -->
    <section id="introSection">
        <div class="buttombar-list">
            <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
            <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
            <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
            <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
            <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
            <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
        </div>
        <div class="card-list">
            <div class="left-card">
                <h1 class="title"><a href='http://localhost:8080/DS_FinalProject2/TestProject'>舞松打狗</a></h1>
            </div>
            <div class="right-card">
                <div class="select-card-list">
                    <select class="select-card">
                        <option>依地區</option>
                        <option>全部</option>
                        <option>北部</option>
                        <option>中部</option>
                        <option>南部</option>
                        <option>東部</option>
                    </select>
                    <select class="select-card">
                        <option>依時間</option>
                        <option>全部</option>
                        <option>一個月內</option>
                        <option>三個月內</option>
                        <option>六個月內</option>
                    </select>
                    <select class="select-card">
                        <option>依價格</option>
                        <option>由低到高</option>
                        <option>由高到低</option>
                    </select>
                </div>
            </div>

        </div>
<%
		String[][] orderList = (String[][]) request.getAttribute("sortedWebList");
		//System.out.println(orderList[0][0]); //title
		//System.out.println(orderList[0][1]); //url
		//try{

			//for (int i = 0; i < orderList.length; i+=1) {
				//String url = orderList[i][1];
				//String title = orderList[i][0];
				//String score =  orderList[i][2];
				//s=s.substring(7);
%>
	        
		        <div class="container">
		            <div class="row">
		            <% 
		            for (int i = 0; i < orderList.length; i+=1) {
		            %>
		                <div class="col-md-4">
		                    <div class="work-box">
		                        <div class="icon text-primary py-3">
		                        	<%
		                        	if (orderList[i][4] != "") {
		                        	%>
		                            	<img src='<%=orderList[i][4]%>' alt="" width="300">
		                            <%
		                        	}else{
		                            %>
		                            	<img src="https://picsum.photos/id/1037/300/300" alt="">
		                            <%
		                        	}
		                            %>
		                        </div>
		                        <div>
		                            <p>
		                                <strong><a href='<%=orderList[i][1]%>'><%=orderList[i][0]%> </a><br></strong>
		                            	<br>Total score: <%=orderList[i][2]%><br>
		                            	Web Tree:<br><%=orderList[i][3] %>
										<br>
		                            </p>
		                        </div>
		                    </div>
		                </div>
		                <%
					}
					%>
		            </div>
		        </div>
		    </section>  
		     
		
	<!--<%-->
	//}catch(ArrayIndexOutOfBoundsException e){
		
	//}finally{
		
	//}
	<!--%>-->
    <!-- introSection end -->
    <!-- 設定置底返回按鈕 返回按鈕滾動動畫 -->
    <button id="goBackBtn" class="bg-primary">
        <i class="fas fa-chevron-up"></i>
    </button>

    <!-- jquery.min.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- popper.min.js -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <!-- bootstrap.min.js -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
    <!-- 連結網頁主程式 -->
    <script src="searchResult.js"></script>
    
</body>

</html>