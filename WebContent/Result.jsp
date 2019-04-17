<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style>
body {
font-family: Verdana, arial;
 }

.notation-text {
color: #000000;
font-size: 18px;
text-align: center;
margin: 18px;
}

.notation-block-star {
display: table;
margin: 0 auto;
width: inherit;
}


.notation-star {
background-image: url("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Empty_Star.svg/2000px-Empty_Star.svg.png");
background-repeat: no-repeat;
cursor: pointer;
display: table-cell;
float: right;
height: 20px;
width: 20px;
padding: 10 5px;
background-size: cover;
}


.notation-star:hover,
.notation-star:hover ~ .notation-star {
background-image: url("http://findicons.com/files/icons/1035/human_o2/128/bookmark_new.png");
}

.notation-star-selected {
background-image: url("../images/etoile_jaune.png");
background-repeat: no-repeat;
cursor: pointer;
display: table-cell;
float: right;
height: 64px;
width: 64px;
padding: 0 5px;
}

.notation-star-selected  ~ .notation-star {
background-image: url("../images/etoile_jaune.png");
}



img
{
    max-width: 100%;
    max-height: 100%;
    display: block;
    margin: auto auto;
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div class="outer">
    <div class="inner">
        <img src="http://1.bp.blogspot.com/_74so2YIdYpM/TEd09Hqrm6I/AAAAAAAAApY/rwGCm5_Tawg/s320/tall+copy.jpg"  width="200" height="300" alt="Subject Name" />
    </div>
</div>

<form method="post" action="voting">
   <div id="star5" class="notation-star" onClick="notation(this.id);"></div>
   <div id="star4" class="notation-star" onClick="notation(this.id);"></div>
   <div id="star3" class="notation-star" onClick="notation(this.id);"></div>
   <div id="star2" class="notation-star" onClick="notation(this.id);"></div>
   <div id="star1" class="notation-star" onClick="notation(this.id);"></div>
   <input type="hidden" id="notationNote" name="notation_note" value="0">
   <input type="submit" value="ok">
   
   </form >
    <br>

   <p id="Rate to Subject"></p>



<table>
  <c:forEach items="${Images}" var="item">
    <tr>
      <td><c:out value="${item.getName()}" /></td>
      <td><Image src="${item.getPhoto()}" />
            <td><a href="${item.getId()}">Details</a></td>
    </tr>
  </c:forEach>
</table>


</body>
</html>