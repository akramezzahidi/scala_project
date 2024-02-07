package com.example.employesmanagement

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class EmployeFX {
    def createContent(): VBox = {
        val label = new Label("Hello, JavaFX from Scala!")
        val vbox = new VBox(label)
        vbox
    }
}
