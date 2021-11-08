<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Events portal</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

<#include "macros/navbar.ftl"/>

<div>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class=""></li>
            <li data-target="#myCarousel" data-slide-to="1" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="2" class=""></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item">
                <img src="images/events_background.png"/>
                <div class="container">
                    <div class="carousel-caption text-left">
                        <h1>Microsoft Ignite</h1>
                        <p>Microsoft Ignite has provided a single, combined home for one very large event (TechEd)
                            and a handful of smaller ones (Microsoft Management Summit, SharePoint Conference,
                            and Project Conference, among others). </p>
                    </div>
                </div>
            </div>
            <div class="carousel-item active">
                <img src="images/events_background.png"/>
                <div class="container">
                    <div class="carousel-caption">
                        <h1>CodeMash</h1>
                        <p>Is CodeMash a Ruby conference? A Java conference? A security conference?
                            Try all of the above: The event that likes to call itself the “everything conference”
                            returns to Sandusky, a city located on Lake Erie about halfway between Cleveland and Toledo.</p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/events_background.png"/>
                <div class="carousel-caption text-right">
                    <h1>QCon London</h1>
                    <p>Like its New York-based sibling event, the UK version of QCon aims its programming clearly
                        at front-line development practitioners. </p>
                </div>
            </div>
        </div>
    </div>
</div>

<a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev" style="height: 50%">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
</a>
<a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next" style="height: 50%">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
</a>

<footer class="footer">
    <div class="container">
        <span class="text-muted">© 2021 EPAM SYSTEMS. ALL RIGHTS RESERVED.</span>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="/js/logout.js"></script>
</body>
</html>