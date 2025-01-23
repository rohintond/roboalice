# RoboAlice

RoboAlice is a trial implementation of a service that handles two types of questions - 
a) Arithmetic equations(this includes real and negative numbers, it does not include variables)
b) Answers to text based questions

# Running RoboAlice
From the RovoAlice folder run
./grade bootRun
or
.\gradlew bootRun

In a browser navigate to http://localhost:8080/query
Gets without a query parameter will prompt a request for a question. To ask a question add the question query parameter

http://localhost:8080/query/What is your name
http://localhost:8080/query/What+is+your+name
http://localhost:8080/query/2%2B3

You should receive a json response letting you know your query was understood and the answer to your question.

{
  "succeeded": true,
  "evaluatedAs": "Basic Questions",
  "message": "RoboAlice"
}

{
  "succeeded": true,
  "evaluatedAs": "Basic Arithmetic",
  "message": "5"
}

# Question difference tolerance
Answers to text questions are pre-prepared. There is some tolerance for a slightly different/misspelled question. A question will be matched if it has a Levenshtein distance of 5 or less from one of the pre-prepared questions.


# Using additional/alternate questions
You can modify the list of questions and answers by editing src/main/resources/questions.txt. Each question and corresponding answer should be on it's own line in pairs.
To use an external file create a questions.txt file in the current format in your working directory. To use an external file with a different name, change question.file in application.properties.

# Logging
logs are written to roboalice.log in the current working directory.