PhotoAlbumManager

About - 
This is a REST based service that gets data form endpoints inserts into database. This app is developed using Java and hibernate and uses a MySql database in the backend.

##Installation
To run this application you will first need to install a couple packages: jdk 1.7 and maven (you can also install these packages with yum, brew, etc.)

you can install maven by downloading it from the maven website and extracting it. Please follow this link : https://www.mkyong.com/maven/install-maven-on-mac-osx/

This project is built using Java/Hibernate, which makes it very easy to get running. The Java web application can be deployed locally by downloading the zip and right clicking on the project and choosing the “Run As->Run on Server” option in an IDE. You can deploy the application on any native server that supports Tomcat by copying the WAR file (Right click on the project and choose Export as WAR File option) to appropriate tomcat directory and restarting the tomcat server.Alternatively, you can download the zip from the GitHub repo and use Eclipse to run it on the Tomcat server

The relevant endpoints for this application are '/webapi/init', '/webapi/albums',' /webapi/photos'. Both the albums and photos endpoints have the basic CRUD actions. For example, to view all photos, you can navigate to:


http://localhost:8080/webapi/albums

Which should return the JSON in the following format:

```
[
  {
    "albumID": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "http://placehold.it/600/92c952"
  },
  {
    "albumID": 1,
    "id": 2,
    "title": "reprehenderit est deserunt velit ipsam",
    "url": "http://placehold.it/600/771796"
  },
  {
    "albumID": 1,
    "id": 3,
    "title": "officia porro iure quia iusto qui ipsa ut modi",
    "url": "http://placehold.it/600/24f355"
  },
  {
    "albumID": 1,
    "id": 4,
    "title": "culpa odio esse rerum omnis laboriosam voluptate repudiandae",
    "url": "http://placehold.it/600/d32776"
  },
]
```

For testing all of the CRUD actions, I recommend using [Postman](www.getpostman.com).
