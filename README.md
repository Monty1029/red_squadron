
			//////////////////////////////////////////////////////////////////////
			//																	//
			//				SYSC 3110 - Software Development Project			//
			//								Fall 2015							//
			//																	//
			//					Course Project: Social Network					//
			//							  Milestone: 2							//
			//							  Nov 3, 2015							//
			//																	//
			//  Monty Dhanani, Garrett Steele, Bronwyn Skelley, Even Bottomley	//
			//																	//
			//					GitHub Repository: redSquadron					//
			//																	//
			//////////////////////////////////////////////////////////////////////


		//////////////////////////////////
		//								//
		//		Team Information		//
		//								//
		//////////////////////////////////

	Readme Author: Garrett Steele
		
	Team Member:		Student Number:			Carleton Email:						GitHub User-name:
	Monty Dhanani		100926543				montydhanani@cmail.carleton.ca		Monty1029
	Garrett Steele		100920928				garrettsteele@cmail.carleton.ca		kersey1a
	Bronwyn Skelley		100880052				bronwynskelley@cmail.carleton.ca	bronwynk
	Evan Bottomley		100943340				EvanBottomley@cmail.carleton.ca		evanbottomley

	
		//////////////////////////////////////////////////
		//												//
		//		Responsibilities for Milestone 2		//
		//												//
		//////////////////////////////////////////////////
	
	   The following are the primary responsibilities of each team member for "Milestone 2", project code is written separately 
	with changes made as a team during team meetings.
	
	Monty Dhanani:		GUI interface (GUI.java)
	Garrett Steele:		Unit tests (for User, Consumer, and Producer), Updated Readme.txt file, Design_Decisions_M2.pdf
	Bronwyn Skelley:	Unit Tests (for Document, and Simulation), Unit test Suite, User_Manual_M2.pdf
	Evan Bottomley:		GUI Listener (ButtonListener.java), Simulation.java re-factoring
	
	   All team members are responsible for the JavaDoc commenting of their own code, 
	where the group reviews and edits the JavaDoc together prior to submission. The UML diagram was 
	updated as a team at team meetings for "Milestone 2".
	
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//				Included Files in Milestone Submission					//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   In the ".zip" file submitted to CULearn is:
	   
	   - this "Readme.txt" file
	   - a ".jar" file containing the readable source code for the project(where the name of the student who wrote each class is included at the top of each source code)
	   - the same ".jar" is executable from the Java console, or if the computer is set up to do so, by double clicking on the executable
	   - a "User_Manual_M2.pdf" detailing how to extract and execute the ".jar" file, as well as using the GUI interface and interpreting the program's output
	   - a "Design_Decisions_M2.pdf" detailing the design decisions made for the project as a whole, and in the individual Java classes
	   - a "Milestone2_UML.jpg" of the project design
	
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//					  Changes since last Milestone						//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   Since the first Milestone, the project has been altered to use a GUI to control the simulation,
	with the ability to set a number of starting seed values for the number of documents and users in the simulation (a
	feature suggested by the TA and is part of "Milestone 2" requirements). The output of the program has also been 
	updated to use a text area within the GUI window to output the result as text to be interpreted by the user.
	Furthermore, unit tests created using JUnit have been included for no GUI related classes (no unit tests for GUI 
	or ButtonListener), and a test suite to run all unit tests.
	
	   The project documentation comprised of "User_Manual_M2.pdf", "Design_Decisions_M2.pdf", and "Milestone2_UML.jpg"
	have also been updated from those for "Milestone 1" to reflect the above changes.
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//							  Known Issues								//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   The project currently has no known issues for "Milestone 2", operating as expected
	when tested with multiple seed values for the number of documents and users.
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//					  		Roadmap Ahead								//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   For "Milestone 3" the goals are;
	   
	   - Implement all ranking strategies for Consumer and Producer
	   - Re-factor the GUI to allow allow users to change a greater number of simulation factors, including:
			- the ranking strategy of each User
			- the Strategy used by Producers (a or b)
	   - Allow the GUI to show the results of the simulation graphically over time with a line graph
	   - Update all documentation to account for new project changes
	   - Include suggestions made by SYSC 3110 TA's on "Milestone 2"

