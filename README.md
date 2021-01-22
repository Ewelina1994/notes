# notes

Application for saving tasks divided into different categories. We can also send message in to user mail

Running spring boot aplication, command: mvn clean install in folder: notes-api-new

This api is running in HEROKU: https://notes-api-kl.herokuapp.com/

Runing angular aplication, command: npm start, in folder: notes-ng-app

This application web is running in HEROKU: https://notes-app-kl.herokuapp.com/

API describtion:
This Api is security basic authenicated. 
User is save in database. 
The credentials is: login: jan, password: 1234


 Controllers: 

                NotebookController in path/api/notebooks 
                save new Notebook in the same path
                delete Notebook: /{id}
                get All Notebooks: /all 

                NoteController in path/api/notes
                get all and save in the same path
                get note by Id: /byId/{id}
                get all by Notebook: /byNotebook/{notebookId}
                delete note: /{id} 
                show removed notes GET: /deleted

                FeedbackController in path: /api/feedback 
                send Feedback: in the same port, @RequestBody Feedback
                get All: /all 
                add Send Message To Database: /add, @RequestBody Feedback
                
      
