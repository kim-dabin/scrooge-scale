<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>survey</title>
    <c:import url="/WEB-INF/template/link.jsp" />
    <link rel="stylesheet" href="/css/survey.css" />

</head>
<body>
<div class="container container-sm">
<form id="submitForm" method="post" action="/scrooge/survey">
  <div class="d-flex justify-content-between">
    <div class="p-2">
    <button type="button" class="prev hide btn btn-outline-dark btn-sm">
      <i class="bi bi-chevron-compact-left"></i>
    </button>
    </div><!--//.p-2 -->
    <div class="p-2 btn-submit hide">
        <button type="submit" class="btn btn-outline-dark">제출</button>
    </div><!--//.p-2 -->
    <div class="p-2">
    <span class="badge rounded-pill bg-dark"> <span class="page-now"></span> / 11 </span>
    </div><!--//.p-2 -->
  </div><!--//.d-flex -->
  <div class="progress" style="height: 4px;">
    <div class="progress-bar bg-dark" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
  </div><!--//.progress -->

<div class="box_question"> <!-- 질문 생성 --> </div><!--//.box_question -->
</form>
</div><!--//.container -->



<script type="text/template" id="questionTmp">
<div class="question question-<@=question.id @>">
    <div class="contents">
      <figure class="text-center">
      <h4><i class="icon-responsive bi bi-cursor"></i> <!-- <i class="bi bi-cursor-fill"></i> -->
      <@=question.contents @></h4>
      <h6 class="text-muted"><@=question.contents @></h6>
      </figure>
    </div><!--//.contents -->

    <div class="btn-group d-flex align-items-center flex-column" role="group" aria-label="Basic radio toggle button group">
            <div class="btn-group p-2" role="group" aria-label="First group">
            <input type="radio" class="btn-check"  value="<@=question.id @>,<@=question.factorId @>,<@if(question.factorId!='E'){ @>1<@} else{ @>7<@} @>" name="btnradio-<@=question.id @>" id="<@=question.id @>-1" autocomplete="off">
            <label class="answer btn btn-outline-success" for="<@=question.id @>-1">전혀 그렇지 않다</label>

            <input type="radio" class="btn-check" value="<@=question.id @>,<@=question.factorId @>,<@if(question.factorId!='E'){ @>2<@} else{ @>6<@} @>" name="btnradio-<@=question.id @>" id="<@=question.id @>-2" autocomplete="off">
            <label class="answer btn btn-outline-success" for="<@=question.id @>-2">그렇지 않다</label>

            <input type="radio" class="btn-check" value="<@=question.id @>,<@=question.factorId @>,<@if(question.factorId!='E'){ @>3<@} else{ @>5<@} @>" name="btnradio-<@=question.id @>" id="<@=question.id @>-3" autocomplete="off">
            <label class="answer btn btn-outline-success" for="<@=question.id @>-3">대체로 그렇지 않다</label>
            </div>

            <div class="btn-group p-2" role="group" aria-label="Mid group">
            <input type="radio" class="btn-check" value="<@=question.id @>,<@=question.factorId @>,4" name="btnradio-<@=question.id @>" id="<@=question.id @>-4" autocomplete="off">
            <label class="answer btn btn-outline-secondary" for="<@=question.id @>-4">보통이다</label>
            </div>

            <div class="btn-group p-2" role="group" aria-label="Last group">
            <input type="radio" class="btn-check" value="<@=question.id @>,<@=question.factorId @>,<@if(question.factorId!='E'){ @>5<@} else{ @>3<@} @>" name="btnradio-<@=question.id @>" id="<@=question.id @>-5" autocomplete="off">
            <label class="answer btn btn-outline-danger" for="<@=question.id @>-5">그렇다</label>

            <input type="radio" class="btn-check" value="<@=question.id @>,<@=question.factorId @>,<@if(question.factorId!='E'){ @>6<@} else{ @>2<@} @>" name="btnradio-<@=question.id @>" id="<@=question.id @>-6" autocomplete="off">
            <label class="answer btn btn-outline-danger" for="<@=question.id @>-6">대체로 그렇다</label>

            <input type="radio" class="btn-check" value="<@=question.id @>,<@=question.factorId @>,<@if(question.factorId!='E'){ @>7<@} else{ @>1<@} @>" name="btnradio-<@=question.id @>" id="<@=question.id @>-7" autocomplete="off">
            <label class="answer btn btn-outline-danger" for="<@=question.id @>-7">매우 그렇다</label>
            </div>
    </div><!--//.btn-group -->
    </div><!--//.question1 -->
</script>

<script>
    _.templateSettings = {interpolate: /\<\@\=(.+?)\@\>/gim,evaluate: /\<\@([\s\S]+?)\@\>/gim,escape: /\<\@\-(.+?)\@\>/gim};
    moment.locale("ko");
    const questionTmp = _.template($("#questionTmp").html());
    const $questionBox = $(".box_question");
    const $answerBtn = $(".answer");
    const $nowPage = $(".page-now");
    const $progressBar = $(".progress-bar");
    const $prevBtn = $(".prev");
    const $unresponsive = $(".bi-cursor");
    const $responsive = $(".bi-cursor-fill");
    const $submitBtn = $(".btn-submit");
    const $submitForm = $("#submitForm");

    let questionNum = 1;
    let totalQuestionP = 11*100;

    getQuestion(questionNum);//처음 문제 불러옴
    $questionBox.on("click",".answer", clickAnswer);
    $submitBtn.click(isFinish);
    $prevBtn.click(function(){
        $questionBox.children(".question").css("display","none");
        if(questionNum>1){
            questionNum-=1;
            $questionBox.children(".question-"+questionNum).css("display","block");
            calculProgress(questionNum);
        }
    });

    function clickAnswer(){
        const $this = $(this);
        const $parents = $this.parents(".question");
        const $iconResponsive = $parents.find(".icon-responsive");
        if($iconResponsive.hasClass("bi-cursor")){
          $iconResponsive.removeClass("bi-cursor");
          $iconResponsive.addClass("bi-cursor-fill");
          if(questionNum<11){
            $parents.css("display","none");
            questionNum+=1;
            getQuestion(questionNum);
          }else{
            $submitBtn.removeClass("hide");
            $submitBtn.addClass("show");
          }

        }else if($iconResponsive.hasClass("bi-cursor-fill")&&questionNum<11){
            $parents.css("display","none");
            questionNum+=1;
            $questionBox.children(".question-"+questionNum).css("display","block");
            calculProgress(questionNum);
        }

    }
    function isFinish(){
        $submitForm.submit(function() {
        if($(':radio[name="btnradio-11"]').is(":checked")){
            return true;
         }
         });
    }



    function getQuestion(questionNum){
        $.ajax({
            url:"/ajax/survey/question/num/"+questionNum,
            dataType : "json",
            type : "post",
            error : function() {
            	alert("상태점검중");
            },
            success : function(data) {
                $questionBox.append(questionTmp({question:data}));
                //console.log(data);
                questionNum = data.id;
                calculProgress(questionNum);
            }
        });
    }

    function calculProgress(questionNum){
        let per = (questionNum/11)*100;
        $nowPage.text(questionNum);
        $progressBar.css("width", per+"%");
        if(questionNum>1&&$prevBtn.hasClass('hide')){
            $prevBtn.removeClass('hide');
            $prevBtn.addClass('show');
        }else if(questionNum==1&&$prevBtn.hasClass('show')){
            $prevBtn.removeClass('show');
            $prevBtn.addClass('hide');
        }
    }
</script>
</body>
</html>