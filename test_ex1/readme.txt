I see this mangnigicent code, however how in the world do I use it?
Don't you worry fellow child, the following steps will have instructions on how to just JavaFX

The project is broken into three classes(for now)

	The test_ex1.java: this is the main class it loads all the assets from the fxml document. This file does not need to be modified at all(for now)

	FXMLDocumentController.java:
	This is where all the action happens(literally). This is where all the onactions will be programmed. 

	FXMLDocument.fxml:
	This is where all the assets get "stored" not literally, this basically tells the program "here is a button with x id and its XY location is this" DO NOT alter this file outside of the sceneBuilder Program 


1.
	The first step is to downlaod maven netbeans(this is absolutely nessary to compile).

2.
	This step is optional if you are not working on GUI. Download JavaFX scene builder. https://www.oracle.com/java/technologies/javafxscenebuilder-1x-archive-downloads.html .

3.
	Open the project file in Netbeans and then, open SceneBuilder. Once SceneBuilder is open, go to File->Open->/path_to_src/FXMLDocument.fxml.

4.
	You will now see a gui version of the project. How to use the SceneBuilder will be shown in the next few steps.

5.
	SceneBuilder is really easy to use, however it takes a little to learn how to navigate. 

	This is basic rundown:
	Containers(this is where all the elements go if you want it structured(think of it as div from HTML)). NOTE: this is not nessary but highly encouraged to use to line things up.
	
	b. Controls. Menu, and Misc. This is where all the buttons, text fields, drop down menus, and date picker(basically anything the user will interface through the program).
	c. Heiarchy(Bottom left): this shows the parent and child relationship of entities. 

6. 
	For simplify, we will use a basic button(this applies to any item though) to show 	how the program handles entries on the canvas. On the canvas select the button and 	on the right side you will see a selection of options(this will be under three 	tabs: Properties, Layout, Code). For simplicity, I will only be talking about the nessary 	things(The rest will be nessary for more sophisticated designs). 

	Properties:
	Text: This is what the user sees on the screen on the button.

	Layout:
	This should not be messed with if you are using the canvas to drag and move around 	things. Unless you want to manually control it. This is kinda like HTML, you can 	set the size, padding, and location of the button.

	Code:
	This is where the onclick actions happen. 
	fx:id: this is very important to give an appropriate ID 

	On Action: This is how the program interacts with the onclick. Everything is 		required to have a unique name that is easily identifiable. 

7.
	This step will show you how to program a action to the given button in example 6.
	In netbeans, it may ask you to reload to the changes. Click "yes"
	All the actions happen in: FXMLDocumentController.java
	First step is to declare the "id" variable.
	Ex button
	@FXML 
	private Button "ID used for that button"

	this is the format for onClicks:

	@FXML
	private void "On actionID from step 6"(ActionEvent event){
		//Do somthing
	}

8.
	Thats it! its that simple all you have to do is place the item on scencebulder. assign it an ID and then give it a action using the template above. A small caveat being for text feilds instead of a onclick it will be a "on key pressed" however thats just a simple change in under code dropdown. 

Checkout the example code, it will make more sense.








