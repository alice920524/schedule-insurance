function btnHover(e,a){e.hover(function(){$(this).addClass(a),e.hasClass("class-note")&&e.children(".note-icon").addClass(a)},function(){$(this).removeClass(a),e.hasClass("class-note")&&e.children(".note-icon").removeClass(a)})}function stopScroll(){$("body,html").css({"overflow-y":"hidden"})}function startScroll(){$("body,html").css({"overflow-y":"auto"})}function changeBorder(e){e.addClass("s-border abc")}function check(e){e.removeClass("s-border abc")}function getUrlParam(e){var a=new RegExp("(^|&)"+e+"=([^&]*)(&|$)"),t=window.location.search.substr(1).match(a);return null!=t?unescape(t[2]):null}function Pagination(){function e(e,t,n){i=parseInt(e);var s=new StringBuffer;if(t>1){if(t>1&&s.append('<li class="page-first clk_page '+(1==i?"disabled":"")+'" data_no="1"><a href="javascript:void(0);">首页</a></li>'),i>1&&s.append('<li class="page-first clk_page" data_no="'+(parseInt(i)-1)+'"><a href="javascript:void(0);">上一页</a></li>'),t<=7)for(o=1;o<=t;o++)s.append('<li class="pageNo '+(i==o?"on":"clk_page")+'" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>");else for(var o=1;o<=t;o++)i<5?(o<=5&&(s.append('<li class="pageNo '+(i==o?"on":"clk_page")+'" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>")),5==o&&(s.append('<li class="disabled">'),s.append('<a href="javascript:void(0);">...</a>'),s.append("</li>")),o+1>t&&(s.append('<li class="pageNo clk_page" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>"))):i>=5&&(i+4>t&&(o<=1?(s.append('<li class="pageNo clk_page" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>"),1==o&&(s.append('<li class="disabled">'),s.append('<a href="javascript:void(0);">...</a>'),s.append("</li>"))):i+5>t&&o+5>t&&(s.append('<li class="pageNo '+(i==o?"on":"clk_page")+'" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>"))),i+4<=t&&(o<=1?(s.append('<li class="pageNo clk_page" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>"),1==o&&(s.append('<li class="disabled">'),s.append('<a href="javascript:void(0);">...</a>'),s.append("</li>"))):i-o<2&&i>o?(s.append('<li class="pageNo clk_page" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>")):o==i?(s.append('<li class="pageNo on" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>")):o-i<2&&o>i?(s.append('<li class="pageNo clk_page" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>")):o+1==t?(s.append('<li class="disabled">'),s.append('<a href="javascript:void(0);">...</a>'),s.append("</li>")):o+1>t&&(s.append('<li class="pageNo clk_page" data_no="'+o+'">'),s.append('<a href="javascript:void(0);">'+o+"</a>"),s.append("</li>"))));i<t&&s.append('<li class="page-first clk_page" data_no="'+(parseInt(i)+1)+'"><a href="javascript:void(0);">下一页</a></li>'),t>1&&s.append('<li class="page-first clk_page '+(i==t?"disabled":"")+'" data_no="'+t+'"><a href="javascript:void(0);">尾页</a></li>'),$("#pagination").html(s.toString()),a(t,n)}}function a(a,i){$(".clk_page").each(function(){$(this).hasClass("disabled")||$(this).click(function(){$(".clk_page").removeClass("on"),$(this).addClass("on"),t(a);var n=$(this).attr("data_no");_pagination.pageNo=n,e(n,a,i),i()})})}function t(e){var a=$(".on").attr("data_no");a>1?$("#upPage").show():$("#upPage").hide(),a>=e?$("#nextPage").hide():$("#nextPage").show(),_pagination.setPageNo(a)}var i=1;return{pageNo:this.pageNo,totalNo:this.totalNo,countNum:this.countNum,setPageNo:function(e){this.pageNo=e},setPageSize:function(e){this.pageSize=e},setTotalNo:function(e){this.totalNo=e},initParams:function(e,a){return this.options={page:"page",size:"size"},a&&a.page&&(this.options.page=a.page),a&&a.size&&(this.options.size=a.size),e[this.options.page]=this.pageNo,e[this.options.size]=this.totalNo,e},initPage:function(a,t,i,n){this.pageNo=a,this.pageSize=t,this.totalNo=i,e(a,t,n)}}}btnHover($(".right-section-tip .class-note"),"s-bg"),btnHover($(".class-bulletin .more-btn"),"s-bg"),window.onload=function(){$(".course-icon-list>li").hover(function(){$(this).addClass("s-color"),$(this).children(".icon-one").addClass("s-bg")},function(){$(this).removeClass("s-color"),$(this).children(".icon-one").removeClass("s-bg")})},$("input").hover(function(){$(this).addClass("s-border")},function(){$(this).hasClass("abc")||$(this).removeClass("s-border")}),$("textarea").hover(function(){$(this).addClass("s-border")},function(){$(this).hasClass("abc")||$(this).removeClass("s-border")});var hash=window.location.hash;"#/live"==hash?$(".sub-header-list li").eq(0).addClass("s-border"):"#/mock"==hash?$("#courseMock").addClass("s-border"):"#/video"==hash?$(".sub-header-list li").eq(2).addClass("s-border"):"#/teaching"==hash?$(".sub-header-list li").eq(3).addClass("s-border"):"#/tiku"==hash?$(".sub-header-list li").eq(4).addClass("s-border"):"#/club"==hash?$(".sub-header-list li").eq(5).addClass("s-border"):"#/service"==hash?$("#courseService").addClass("s-border"):"#/soft"==hash?$("#courseStudy").addClass("s-border"):$(".sub-header-list li").removeClass("s-border"),$(".sub-header-list li").click(function(){$(this).addClass("s-border").siblings().removeClass("s-border")}),$(".header .head-right .client").mouseover(function(){$(this).find(".ul").show()}).mouseleave(function(){$(this).find(".ul").hide()}),$(".header .head-right .wx-class").click(function(){$(".wxClass").show(),$("body").css("overflow","hidden")}),$(".wxClass .close").click(function(){$("body").attr("style",""),$(".wxClass").hide()}),$(".header .head-right .photo").mouseover(function(){$(this).find(".ul").css("display","block")}).mouseleave(function(){$(this).find(".ul").hide()});var util={trim:function(e){return"string"!=typeof e?e:"function"==typeof e.trim?e.trim():e.replace(/^(\u3000|\s|\t|\u00A0)*|(\u3000|\s|\t|\u00A0)*$/g,"")},isEmpty:function(e){return void 0===e||(null==e||"string"==typeof e&&""==this.trim(e))},isNotEmpty:function(e){return!this.isEmpty(e)},currentTime:function(){return this.formatDate(new Date)},calcPercent:function(e,a){return isNaN(e)||0==Number(e)?"0":isNaN(a)||0==Number(a)?"0":Math.round(100*Number(e)/Number(a))},round:function(number,fractionDigits){with(fractionDigits=fractionDigits||2,Math)return round(number*pow(10,fractionDigits))/pow(10,fractionDigits)},timeDuration:function(e){if(e&&!isNaN(e)){var a="",t=(e=parseInt(e))/3600|0;a+=0!=t?checkTime(t)+":":"00:";var i=e%3600/60|0;a+=checkTime(i)+":";var n=e-3600*t-60*i|0;return a+=checkTime(n)}},formatDate:function(e){var a=e.getHours(),t=e.getMinutes(),i=e.getSeconds();return checkTime(a)+":"+checkTime(t)+":"+checkTime(i)},formatTime:function(e){var a=new Date;a.setTime(e);var t=a.getHours(),i=a.getMinutes(),n=a.getSeconds();return checkTime(t)+":"+checkTime(i)+":"+checkTime(n)},formatText:function(e){return e=e.replace(" ","&nbsp;"),e=e.replace(/\n/g,"<br/>")},formatUrl:function(e){return e=e.replace(/(?:<img.+?>)|(http[s]?|(www\.)){1}[\w\.\/\?=%&@:#;\*\$\[\]\(\){}'"\-]+([0-9a-zA-Z\/#])+?/gi,function(e){return/<img.+?/gi.test(e)?e:'<a class="msg-url" target="_blank" href="'+e.replace(/^www\./,function(e){return"http://"+e})+'">'+e+"</a>"})},replaceholder:function(e,a){return e.replace(/\{(\d+)\}/g,function(e,t){return a[t]})},setCookie:function(e,a,t){var i=new Date;a=encodeURI(a);var n=!1;void 0===t?n=!0:null==t?n=!0:"string"==typeof t&&""==this.trim(t)&&(n=!0),n?t*=1e3:t=864e5,i.setTime(i.getTime()+t),document.cookie=e+"="+escape(a)+";expires="+i.toGMTString()+";path=/"},getCookie:function(e){var a,t=new RegExp("(^| )"+e+"=([^;]*)(;|$)");return(a=document.cookie.match(t))?unescape(a[2]):null},delCookie:function(e){var a=new Date;a.setTime(a.getTime()-1);var t=getCookie(e);null!=t&&(document.cookie=e+"="+t+";expires="+a.toGMTString())},random:function(e,a){return Math.floor(e+Math.random()*(a-e))},GetQueryString:function(e){var a=new RegExp("(^|&)"+e+"=([^&]*)(&|$)"),t=window.location.search.substr(1).match(a);return null!=t?t[2]:null}},DuiaCenter={verifyStatus:function(e){if(!util.isNotEmpty(e))return!1;switch(e.code){case 200:return!0;case 401:return window.location.href="/userMain#/userMainNg",!1;case 500:case 400:case 404:return!1;case 600:var a=encodeURIComponent(window.location);return window.location.href=_ssoPath+"/uc?returnUrl="+a,!1;case 602:return window.location.href="/userMain#/userMainNg",!1;case 603:return!1;case 604:return $(".content-popup").show(),$(".go-stages-alert").show(),stopScroll(),aboutStyle.setCenter($(".go-stages-alert")),$(".go-stages-alert .stages-alert-bottom-span").click(function(){window.open(_itemDomain+"/order/payment?num="+e.data.payNum+"&appType=999","_blank")}),!1;case 605:case 606:case 607:case 608:return!1;case 612:return $(".content-popup").show(),$(".stages-alert").show(),stopScroll(),aboutStyle.setCenter($(".stages-alert")),!1;default:return!1}},addCs:function(e){var a=util.GetQueryString("cs");return e.cs=a,e}},StringBuffer=function(){this.array=new Array};StringBuffer.prototype.append=function(e){this.array.push(e)},StringBuffer.prototype.toString=function(){return this.array.join("")};var aboutStyle={init:function(e){},getHeight:function(e){return e.height()},getinnerWidth:function(e){return e.innerWidth()},getinnerHeight:function(e){return e.innerHeight()},getScrollTop:function(e){return e.scrollTop()},setCenter:function(e){var a=aboutStyle.getinnerWidth(e),t=aboutStyle.getinnerHeight(e);e.css({position:"fixed",left:"50%",top:"50%","margin-left":-a/2,"margin-top":-t/2,"z-index":"100"})},isHaveScrollBar:function(e,a){var t=aboutStyle.getHeight(e);if(aboutStyle.getHeight(a)>t)return!0},isScrollArriveBottom:function(e,a){if(aboutStyle.getScrollTop(e)+aboutStyle.getHeight(e)>=aboutStyle.getHeight(a)-13)return!0},brderToBottom:function(e,a,t){var i=aboutStyle.getinnerHeight(e)+1,n=aboutStyle.getHeight(a)-i,s=aboutStyle.getinnerHeight(t);n>s?t.height(n):t.height(s)}};$(function(){$(".stages-alert .close-popup,.stages-alert .stages-alert-bottom-span,.go-stages-alert .close-popup").click(function(){window.location.href="/userMain?nav=1#/userMainNg"})});