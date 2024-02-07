file:///C:/Users/Asus%20VivoBook%2011TH/Documents/LP_SIBD/outils_BigData/scala_project/employes_management/src/main/java/com/example/employesmanagement/EmployeeManagementApp.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
offset: 52
uri: file:///C:/Users/Asus%20VivoBook%2011TH/Documents/LP_SIBD/outils_BigData/scala_project/employes_management/src/main/java/com/example/employesmanagement/EmployeeManagementApp.java
text:
```scala
package com.example.employesmanagement;

import ja@@vafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployeeManagementApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        EmployeFX employeFX = new EmployeFX();
        Scene scene = new Scene(employeFX.createContent(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:342)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator