<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>舞松打狗</title>

    <link rel="stylesheet" href="index,feedback.css">
</head>



<body>

    <div class="menubar ">
        <ul>
            <div class="topbar">
                <img class="img-topbar" src="greenbar.png" alt="greenbar.png">
                <li class="active"><a href="#">售票系統</a>
                    <!-- href是代表要連結到的地方 -->
                    <div class="sub-menu-1">
                        <ul>
                            <li><a rel="noopener" target="_blank" href="https://kktix.com/">KKTIX</a></li>
                            <li><a rel="noopener" target="_blank" href="https://www.indievox.com/">indivox</a></li>
                            <li><a rel="noopener" target="_blank" href="https://tixcraft.com/">拓元售票</a></li>
                            <li><a rel="noopener" target="_blank"
                                    href="https://kham.com.tw/application/utk01/UTK0101_03.aspx">寬宏售票</a>
                            </li>
                            <li><a rel="noopener" target="_blank" href="https://www.opentix.life/">OPENTIX售票</a></li>
                            <li><a rel="noopener" target="_blank" href="https://ticket.ibon.com.tw/">ibon售票</a></li>
                            <li><a rel="noopener" target="_blank"
                                    href="https://tickets.udnfunlife.com/application/utk01/utk0101_.aspx">udn售票</a></li>
                        </ul>
                    </div>
                </li>
            </div>

            <div class="topbar">
                <img class="img-topbar" src="yellowbar.png" alt="greenbar.png">
                <li class="active"><a href="#">大型表演場館</a>
                    <div class="sub-menu-3">
                        <ul>
                            <li class="hover me"><a href="#">北部</a>
                                <div class="sub-menu-2">
                                    <ul>
                                        <li><a rel="noopener" target="_blank" href="https://tmc.taipei/">台北流行音樂中心</a>
                                        </li>
                                        <li><a rel="noopener" target="_blank" href="https://npac-ntch.org/zh">兩廳院</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="hover me"><a href="#">中部</a>
                                <div class="sub-menu-2">
                                    <ul>
                                        <li><a rel="noopener" target="_blank"
                                                href="https://www.npac-ntt.org/index">台中歌劇院</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="hover me"><a href="#">南部</a>
                                <div class="sub-menu-2">
                                    <ul>
                                        <li><a rel="noopener" target="_blank"
                                                href="https://www.npac-weiwuying.org/">衛武營國家藝術文化中心</a></li>
                                        <li><a rel="noopener" target="_blank" href="https://kpmc.com.tw/">高雄流行音樂中心</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li><a href="#">東部</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </div>

            <div class="topbar">
                <img class="img-topbar" src="greenbar.png" alt="greenbar.png">
                <li class="active"><a href="feedback.jsp">意見反饋</a>
                </li>
            </div>

        </ul>
    </div>



    <div class="card-list">
        <div class="left-card">
            <img width="300" src="logo.png" margin=auto alt="武松打狗圖.png">
        </div>

        <div class="right-card">
            <h1 class="title">舞松打狗</h1>
            <h6 class="subtitle"> 最方便的舞蹈表演搜尋引擎</h6>
			
			<form action="${requesturi}" method='get'>
            	<input class="search-bar" type="text" name="keyword">
            	<button id="searchBtn" class="search-btn" type="submit">Search</button>
            	<% //onclick="window.location.href='searchResult.jsp'"  ${requesturl} %>
            </form>
        </div>

    </div>



    <div class="buttombar-list">
        <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
        <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
        <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
        <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
        <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
        <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
    </div>
	
	<!-- 連結網頁主程式 -->
    <script src="index.js"></script>
</body>

</html>