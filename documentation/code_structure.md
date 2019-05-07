## Code Structure

Application resides in sub directory `\app`.

The source code is organized following MVVM, stands for Model, View, ViewModel:

`\src`

  * `\main` contains the source code files

    * `\models` contains the classes that represent each entity.

    * `\view` It represents the user interface of the app. It avoids business logic. It contains activities.
    
    * `\viewmodels` It represents the link between view and models.

    * `\res` contains non-java files, which are required at runtime (images, icons, etc)

