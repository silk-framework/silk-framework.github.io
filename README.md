Silk Website

### Editing

Do not edit the html files in this directory as they are generated from templates. Instead edit the files in the generator directory. The templates for the pages can be found in the `generator/app/views` directory. Assets such as images, css and javascript can be found in the `generator/public` directory. 

### Rendering templates live

* Navigate to generator folder
* sbt run
* The website can be previewed in the browser at http://localhost:9000

### Generating html files

* Navigate to generator folder
* sbt "run-main Render"
* After the next commit, the pages are live at http://silk-framework.com
