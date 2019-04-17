<%@page import="com.journaldev.util.User"
import="java.util.ArrayList"
import="java.util.List"
import="com.journaldev.util.Images"

import="java.util.Arrays"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
  <title>Home Page</title>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  
    <style>
    
    * {
  -webkit-font-smoothing: antialiased;
  text-rendering: optimizeLegibility;
}

html, body {
  height: 100%;
}

body {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
      -ms-flex-direction: column;
          flex-direction: column;
  position: relative;
  background: -webkit-linear-gradient(315deg, rgba(36, 46, 77, 0.9), rgba(137, 126, 121, 0.9));
  background: linear-gradient(135deg, rgba(36, 46, 77, 0.9), rgba(137, 126, 121, 0.9));
  font-family: 'Roboto', helvetica, arial, sans-serif;
  font-size: 1.5em;
}
body:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAMAAAAp4XiDAAAAUVBMVEWFhYWDg4N3d3dtbW17e3t1dXWBgYGHh4d5eXlzc3OLi4ubm5uVlZWPj4+NjY19fX2JiYl/f39ra2uRkZGZmZlpaWmXl5dvb29xcXGTk5NnZ2c8TV1mAAAAG3RSTlNAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEAvEOwtAAAFVklEQVR4XpWWB67c2BUFb3g557T/hRo9/WUMZHlgr4Bg8Z4qQgQJlHI4A8SzFVrapvmTF9O7dmYRFZ60YiBhJRCgh1FYhiLAmdvX0CzTOpNE77ME0Zty/nWWzchDtiqrmQDeuv3powQ5ta2eN0FY0InkqDD73lT9c9lEzwUNqgFHs9VQce3TVClFCQrSTfOiYkVJQBmpbq2L6iZavPnAPcoU0dSw0SUTqz/GtrGuXfbyyBniKykOWQWGqwwMA7QiYAxi+IlPdqo+hYHnUt5ZPfnsHJyNiDtnpJyayNBkF6cWoYGAMY92U2hXHF/C1M8uP/ZtYdiuj26UdAdQQSXQErwSOMzt/XWRWAz5GuSBIkwG1H3FabJ2OsUOUhGC6tK4EMtJO0ttC6IBD3kM0ve0tJwMdSfjZo+EEISaeTr9P3wYrGjXqyC1krcKdhMpxEnt5JetoulscpyzhXN5FRpuPHvbeQaKxFAEB6EN+cYN6xD7RYGpXpNndMmZgM5Dcs3YSNFDHUo2LGfZuukSWyUYirJAdYbF3MfqEKmjM+I2EfhA94iG3L7uKrR+GdWD73ydlIB+6hgref1QTlmgmbM3/LeX5GI1Ux1RWpgxpLuZ2+I+IjzZ8wqE4nilvQdkUdfhzI5QDWy+kw5Wgg2pGpeEVeCCA7b85BO3F9DzxB3cdqvBzWcmzbyMiqhzuYqtHRVG2y4x+KOlnyqla8AoWWpuBoYRxzXrfKuILl6SfiWCbjxoZJUaCBj1CjH7GIaDbc9kqBY3W/Rgjda1iqQcOJu2WW+76pZC9QG7M00dffe9hNnseupFL53r8F7YHSwJWUKP2q+k7RdsxyOB11n0xtOvnW4irMMFNV4H0uqwS5ExsmP9AxbDTc9JwgneAT5vTiUSm1E7BSflSt3bfa1tv8Di3R8n3Af7MNWzs49hmauE2wP+ttrq+AsWpFG2awvsuOqbipWHgtuvuaAE+A1Z/7gC9hesnr+7wqCwG8c5yAg3AL1fm8T9AZtp/bbJGwl1pNrE7RuOX7PeMRUERVaPpEs+yqeoSmuOlokqw49pgomjLeh7icHNlG19yjs6XXOMedYm5xH2YxpV2tc0Ro2jJfxC50ApuxGob7lMsxfTbeUv07TyYxpeLucEH1gNd4IKH2LAg5TdVhlCafZvpskfncCfx8pOhJzd76bJWeYFnFciwcYfubRc12Ip/ppIhA1/mSZ/RxjFDrJC5xifFjJpY2Xl5zXdguFqYyTR1zSp1Y9p+tktDYYSNflcxI0iyO4TPBdlRcpeqjK/piF5bklq77VSEaA+z8qmJTFzIWiitbnzR794USKBUaT0NTEsVjZqLaFVqJoPN9ODG70IPbfBHKK+/q/AWR0tJzYHRULOa4MP+W/HfGadZUbfw177G7j/OGbIs8TahLyynl4X4RinF793Oz+BU0saXtUHrVBFT/DnA3ctNPoGbs4hRIjTok8i+algT1lTHi4SxFvONKNrgQFAq2/gFnWMXgwffgYMJpiKYkmW3tTg3ZQ9Jq+f8XN+A5eeUKHWvJWJ2sgJ1Sop+wwhqFVijqWaJhwtD8MNlSBeWNNWTa5Z5kPZw5+LbVT99wqTdx29lMUH4OIG/D86ruKEauBjvH5xy6um/Sfj7ei6UUVk4AIl3MyD4MSSTOFgSwsH/QJWaQ5as7ZcmgBZkzjjU1UrQ74ci1gWBCSGHtuV1H2mhSnO3Wp/3fEV5a+4wz//6qy8JxjZsmxxy5+4w9CDNJY09T072iKG0EnOS0arEYgXqYnXcYHwjTtUNAcMelOd4xpkoqiTYICWFq0JSiPfPDQdnt+4/wuqcXY47QILbgAAAABJRU5ErkJggg==);
  opacity: .3;
}

.login-form {
  width: 100%;
  padding: 2em;
  position: relative;
  background: rgba(0, 0, 0, 0.15);
}

.subject-form {
  width: 100%;
  padding: 2em;
  position: relative;
  background: rgba(0, 0, 0, 0);
}
.login-form:before {
  content: '';
  position: absolute;
  top: -2px;
  left: 0;
  height: 2px;
  width: 100%;
  background: -webkit-linear-gradient(left, #35c3c1, #00d6b7);
  background: linear-gradient(to right, #35c3c1, #00d6b7);
}
@media screen and (min-width: 600px) {
  .login-form {
    width: 50vw;
    max-width: 15em;
  }
}

.flex-row {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  margin-bottom: 1em;
  
    margin-top: 50px;
}

.flex-row_images {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  
    margin-top: 50px;
}

.lf--label {
  width: 2em;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  background: #f5f6f8;
  cursor: pointer;
}

.lf--input {
  -webkit-box-flex: 1;
      -ms-flex: 1;
          flex: 1;
  padding: 1em;
  border: 0;
  color: #8f8f8f;
  font-size: 1rem;
}
.lf--input:focus {
  outline: none;
  -webkit-transition: -webkit-transform .15s ease;
  transition: -webkit-transform .15s ease;
  transition: transform .15s ease;
  transition: transform .15s ease, -webkit-transform .15s ease;
  -webkit-transform: scale(1.1);
          transform: scale(1.1);
}

.lf--submit {
  display: block;
  padding: 1em;
  width: 100%;
  background: -webkit-linear-gradient(left, #35c3c1, #00d6b7);
  background: linear-gradient(to right, #35c3c1, #00d6b7);
  border: 0;
  color: #fff;
  cursor: pointer;
  font-size: .75em;
  font-weight: 600;
  text-shadow: 0 1px 0 rgba(0, 0, 0, 0.2);
}

.lf--submitTWO {
  display: block;
  padding: 1em;
  width: 100%;
  margin-top: 1em;
  background: -webkit-linear-gradient(left, #35c3c1, #00d6b7);
  background: linear-gradient(to right, #35c3c1, #00d6b7);
  border: 0;
  color: #fff;
  cursor: pointer;
  font-size: .75em;
  font-weight: 600;
  text-shadow: 0 1px 0 rgba(0, 0, 0, 0.2);
}



.lf--submit:focus {
  outline: none;
  -webkit-transition: -webkit-transform .15s ease;
  transition: -webkit-transform .15s ease;
  transition: transform .15s ease;
  transition: transform .15s ease, -webkit-transform .15s ease;
  -webkit-transform: scale(1.1);
          transform: scale(1.1);
}

.lf--forgot {
      margin-top: 1em;
    color: #00d6b7;
    font-size: 17px;
    text-align: center !important;
    width: 50%;
    margin: 0 auto;
    display: block;
    margin-top: 30px;
}

::-webkit-input-placeholder {
  color: #8f8f8f;
}

::-moz-placeholder {
  color: #8f8f8f;
}

:-ms-input-placeholder {
  color: #8f8f8f;
}

::placeholder {
  color: #8f8f8f;
}





.row {
  display: flex; /* equal height of the children */
}

.col {
  flex: 1; /* additionally, equal width */
  
  padding: 1em;
 overflow: auto;
max-height: 100vh;
  padding: 1em;
  border: solid;
}

.colTop {
  flex: 1; /* additionally, equal width */
  
  padding: 1em;
    padding: 1em;
  border: solid;
}

    </style>

  
</head>

<body>
<%User user = (User) session.getAttribute("User"); 
 List<Images> list= (List) session.getAttribute("Images"); %>

<h1 class="flex-row">Hi <%=user.getName() %></h1>

  <div class='row' >
  
  <div class='colTop' >
  
  <div  class='login-form' >
  <div class="flex-row">
  <strong>Your Email</strong>: <%=user.getEmail() %><br>
  </div>
  <div class="flex-row">
<strong>Your Country</strong>: <%=user.getCountry() %><br>
  </div>
  <form >
  <input class='lf--submit' type='submit' value='Upload Subject' formaction="Upload.jsp"  name='UploadSubject'>
 </form>
    <form action="Logout"  method="post">
  <input class='lf--submitTWO' type='submit' value='Logout'   name='Logout'>
  </form>
  </div>
            </div>
    
  <div class='col' >
    <table   class='subject-form' >
  <c:forEach items="${Images}" var="item">

    <tr>
      <td><c:out value="${item.getName()}" /></td>
        <td > <img style="padding: 5px;"
          src=" data:image/jpeg;base64,${item.getPhoto()}"
            height="160" width="260" /></td>   
           
           <td >
             <form action="Result"  method="post">
    <input type="hidden" name="imageButton"  value='${item.getId()}'>
  
               
    <input class='lf--submitTWO'  name='Details' type='submit' value='Details'>
    </form>
    
    </td>

    </tr>
    
  </c:forEach>
</table>
    </div>
    
    </div>

 
  
  </body>
</html>


