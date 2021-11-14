# JavaGlobalMentoringProgram
Java Global Mentoring Program

CREATE      HTTP POST           /events 
{
    "title":"JAVA Conference", 
    "place":"Vinnitsa", 
    "speaker":"Morgunov",
    "eventType":"conference",
    "dateTime":"2021-11-15T12:00"
}
UPDATE      HTTP PUT            /events/{id}   {<request body>}
{
    "title":"JAVA Conference",
    "place":"Vinnitsa",
    "speaker":"Vasilenko",
    "eventType":"conference",
    "dateTime":"2021-11-15T13:00"
}
READ        HTTP GET            /events/{id}
DELETE      HTTP DELETE         /events/{id}
READ        HTTP GET            /events
READ        HTTP GET            /events/{title}