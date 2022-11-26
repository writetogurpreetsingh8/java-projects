Hey:) 
Hope you all are doing great!!
I am mentioning few deatils of this Project , I have given the name as "king-score"

JAVA-Version:- 8
Build-Tool:- Maven

1) HOW To Build
-----------------
Along with the source-code I will be sending the jar file also named king-score.jar in case if there is any problem to execute it, you can directly execute the jar

 Import score-code in any IDE ( I Pefer Eclipse /  Intellji ) or you can use CMD , open CMD go to the location of "pom.xml" then execute mvn clean install command it will clean compile build and insall the jar 
 in side target you will see "king-score.jar" file there

2) HOW TO START
---------------------------
I have create application.json file to store few basic details of this project like
{
	"serverPort": 1010,  // Server Port
	"webContext": "/king", // I have used king as webContext/webRoot
	"backlog": 10, // request backlog 
	"sessionValidUptoMins": 10, // session valid time in mins
	"evictObsoleteSessionAfterEveryMins": 60
}

king-score.jar would need this file while executing so put this file in the same folder where you have put the jar file
 
3) Thoughts and considerations around the program
----------------------------------------------------------
We would have three following url:-
A) http://localhost:1010/<userid>login
B) http://localhost:1010/<levelid>/score?sessionkey=<sessionkey>
C) http://localhost:1010/<levelid>/highscorelist


A) http://localhost:1010/<userid>login => It will create the user in the memory and it's session also then

return session-id for subsequent requests , if same user invoke this url multiple time I consider it same user
has loggedin from different machine/browser so I didn't override existing session instead I create different user
with the same userId but different session-id ( I know this is GET request not POST but there is not such default
user in the memory so I decided to create the User(s) on the fly instead of having default user in memory ) 

follow below structure for user , session and levelid

HTTPSession{
	session-id:string, // refer session-id
	User:user, // refer user
	Instant:createdOn, //  when session is created
	Instant:validateUpto //  session expire time
} 

User{
	userId:Integer, // refer user-id
	levels:Map<Integer,Level> // map to store multiple level if user(s) has more than one level
}
Level{
	_id:Integer, // refer level-id
	levelName:String // refer levelName, didn't use it yet, in case if we need
	scores:List<Integer> // refer list of score if having more than one score
}

Map<String,HTTPSession> sessions = new ConcurrentHashMap<Integer,Level>();
i have used ConcurrentHashMap to store the session in memory whenever need to access I used 
ConcurrentHashMap to get it back!!


B) http://localhost:1010/<levelid>/score?sessionkey=<sessionkey> 
Request body: <score>                                         
=> It will first check whether session key exist or not if exist session shouldn't be obsolete otherwise it will be removed from MAP and return 401 

if all goes fine then it will create level for particular user and assign the score for given Level 


C) http://localhost:1010/<levelid>/highscorelist  => It will also first check whether sessions exist or not if exist sessions shouldn't be obsolete otherwise it will be removed from MAP and return 401 

after that it will accumulate all the scores of level of all the users but not more than 15 as per requirement
and return CSV as a key=value pair
<userid>=<score>
If a user hasn't submitted a score for the level, no score is present for that user. A request for a high score list of a level without any scores submitted shall be an empty string

sample data like
101=4565
102=3455
103=
104=2341

or not such score or level found for given levelid for any user
then empty string will return 


HOPE, I HAVE MADE MYSELF CLEAR , 

I have completed the assignment as per the requirements mentioned but unfortunately I couldn't able to implement
Junit since last 3/4 days I wasn't well at all, somehow I tried to finish the actual code part
I would request you to review it!!

Rest we can discuss if we will further Thanks!! :) 
