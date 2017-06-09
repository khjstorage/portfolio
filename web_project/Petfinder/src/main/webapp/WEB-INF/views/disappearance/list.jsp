<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>실종게시판</title>
</head>
<script type="text/javascript">
function goPage(pageNo){
	document.listForm.pageNo.value = pageNo;
	document.listForm.submit();
	console.log("pageNo : " + pageNo);
}
$(document).ready(function() {
	$("#searchBtn").click(function(){
		if(!$("#keyWord_search").val()){
			alert("검색어를 적어주세요");
			$("#keyWord_search").focus();
			return false;
		}
	});
});
</script>
<body>
	<div class="containor">
		<div class="mainContents">
			<h2 style="float: left;">실종게시판</h2>
			<a href="<c:url value='/disappearance/write.do'/>"><input type="button" id="write" class="rightTopButtons" value="글쓰기" /></a>
				<form action="<c:url value='/disappearance/search.do'/>" method="get" enctype="multipart/form-data">
		            <input type="submit" id="searchBtn" class="rightTopButtons" value="조회" />
		               <input type="text" id="keyWord_search" class="rightTopButtons" name="keyWord_search"/>
		               <select class="rightTopButtons" name="selection_search" style="height:52px;">
		                  <option value="all">제목/내용</option>
		                  <option value="title">제목만</option>
		                  <option value="contents">내용만</option>
		               </select>
	            </form>
			<div class="page">
				<c:choose>
					<c:when test="${fn:length(boardList) > 0}">
						<c:forEach items="${boardList}" var="dog">
							<a href="<c:url value='/disappearance/contents.do?idx=${dog.D_IDX }'/>">
								<div class="card">
									<c:choose>
										<c:when test="${empty dog.D_STORED_FILE_NAME}">
											<img src="${pageContext.request.contextPath}/resources/img/nofile.png" />
										</c:when>
										<c:otherwise>
											<img src="${pageContext.request.contextPath}/image/disappearancefile/${dog.D_STORED_FILE_NAME}" />
										</c:otherwise>
									</c:choose>
									<div class="card_info">
										<h2>제목 : ${dog.D_TITLE}</h2>
										<h2>견종 : ${dog.D_DOG}</h2>
										<h2>지역 : ${dog.D_REGION}</h2>
									</div>
								</div>
							</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/resources/img/nodata.png" style="width:1000px;" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:if test="${not empty paging.firstPageNo}">
				<form name="listForm" action="<c:url value='/disappearance/list.do'/>" method="get">
					<input type="hidden" name="pageNo" value="" />
					<div class="boardNavigation" style="float: right; padding:10px 0;">
						<a href="javascript:goPage(${paging.firstPageNo})" class="first arrow_4">처음</a>
						<a href="javascript:goPage(${paging.prevPageNo})" class="prev arrow_3">이전</a>
						<span>
							<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
									<c:choose>
										<c:when test="${i eq param.pageNo}">
											<a href="javascript:goPage(${i})" class="selected paging">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:goPage(${i})" class="paging">${i}</a>
										</c:otherwise>
									</c:choose>
							</c:forEach>
						</span>
						<a href="javascript:goPage(${paging.nextPageNo})" class="next arrow_1">다음</a>
						<a href="javascript:goPage(${paging.finalPageNo})" class="last arrow_2">마지막</a>
					</div>	
				</form>
		</c:if>
	</div>
</body>
</html>



