## Github-user-activity
Hi. This is a Java CLi app to fetch github user activity.

## Features
* Fetch user activity 
* Filter user activity of different type like **push event, commit comment event, watch event, and more**
  
## Installation 
1. clone the project: 
```bash 
    git clone https://github.com/ruxlsr/github-user-activity.git
    cd github-user-activity
```
2. Compile the source code:
```bash
    javac --module-path lib --add-modules org.json src/*.java src/model/*.java -d bin
```
3. run
```bash
cd bin
# Display activities of a user
java --module-path ../lib --add-modules org.json  App <username>
# ex: java --module-path ../lib --add-modules org.json  App ruxlsr
# or 
# Display activity with filter
java --module-path ../lib --add-modules org.json  App <username> [EventType]
# ex: java --module-path ../lib --add-modules org.json  App ruxlsr PushEvent
```
**Nb:** *all event type are supported*. Refers to [github Event Type](https://docs.github.com/en/rest/using-the-rest-api/github-event-types?apiVersion=2022-11-28)



# Others
That was provided by **[roadmap.sh](https://roadmap.sh/projects/github-user-activity)**