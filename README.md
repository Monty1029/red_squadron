
			//////////////////////////////////////////////////////////////////////
			//																	//
			//				SYSC 3110 - Software Development Project			//
			//								Fall 2015							//
			//																	//
			//					Course Project: Social Network					//
			//							  Milestone: 3							//
			//							  Nov 21, 2015							//
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

	Readme Author: Monty Dhanani
		
	Team Member:		Student Number:			Carleton Email:						GitHub User-name:
	Monty Dhanani		100926543				montydhanani@cmail.carleton.ca		Monty1029
	Garrett Steele		100920928				garrettsteele@cmail.carleton.ca		kersey1a
	Bronwyn Skelley		100880052				bronwynskelley@cmail.carleton.ca	bronwynk
	Evan Bottomley		100943340				EvanBottomley@cmail.carleton.ca		evanbottomley

	
		//////////////////////////////////
		//								//
		//		Important Note			//
		//								//
		//////////////////////////////////
		
		If opening and running the code from within an IDE, the Simulation.java class is the
	start of program execution and contains the main method to be executed. Refer to the "How To Use" in the "Help" option in the Menu Bar on the main GUI,
	for further clarification on how to run the program and what the strategy names are referring to.
	
	
		//////////////////////////////////////////////////
		//												//
		//		Responsibilities for Milestone 3		//
		//												//
		//////////////////////////////////////////////////
	
	   The following are the primary design responsibilities of each team member for "Milestone 3", project code is written individually,
	or in pairs where stated in the code. Where stated in the code, another team member has made contribution(s) to another team member's code
	where changes in the contributors class were directly related to that of another team member. The team member who had their code modified then reviewed
	the changes. Debugging activities were performed as a team during team meetings.
	
	
	Monty Dhanani:		Additions to the main GUI (JMenuBar, Help options), RankingGUI.java, RankingListener.java, ranking strategy 2 (StrategyTwo.java),
						unit tests for all classes modified/worked on, README.txt
	Garrett Steele:		Refactored Producer, Consumer, and User classes, Strategy Interface (Strategy.java), ranking strategy 1 (PopularityStrategy.java),
						unit tests for all classes modified/worked on, PayoffGraphUpdatable.java, Design_Decisions_M3.pdf
	Bronwyn Skelley:	Additions to the graph (PayoffGraph.java, PayoffGraphUpdatable.java), strategy 4 (LikeFollowSimilarity.java),
						unit tests for all classes modified/worked on, User_Manual_M3.pdf
	Evan Bottomley:		Refactored Simulation.java to allow for selecting number of documents to rank, refactored ButtonListener.java,
						strategy 3 (DistanceStrategy.java), unit tests for all classes modified/worked on, modified UML to reflect changes	
	
	All team members are responsible for the JavaDoc commenting of their code, 
	where the group reviews and edits the JavaDoc together prior to submission.
	
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//				Included Files in Milestone Submission					//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   In the ".zip" file submitted to CULearn is:
	   
	   - this "README.txt" file
	   - a ".jar" file containing the readable source code for the project(where the name of the student who wrote each class is included at the top of each source code)
	   - the same ".jar" is executable from the Java console, or if the computer is set up to do so, by double clicking on the executable
	   - a "User_Manual_M3.pdf" detailing how to extract and execute the ".jar" file, as well as using the GUI interface and interpreting the program's output
	   - a "Design_Decisions_M3.pdf" detailing the design decisions made for the project as a whole, and in the individual Java classes
	   - a "Milestone3_UML.jpg" of the project design
	
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//					  Changes since last Milestone						//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	Since "Milestone 2"", 4 ranking strategies and Strategy B for the Producer have been implemented. Unit tests for these strategies have also
	been made. These are the following strategies that were implemented:
	
	Ranking Strategies:
	Strategy 1 - Rank based on the number of 'likes' the Document has.
	Strategy 2 - Rank based on the number of times the User is 'followed'
	Strategy 3 - Rank based on the distance of the User in the social network.
	Strategy 4 - Rank based on the 'like' similarity of others Users liking the same kinds of Documents.
	
	Producer Strategies:
	Strategy B - Producer creates a document of another tag.
	Strategy A (previously implemented as part of earlier Milestone) - Producer creates a document of their own preferred tag.
	
	A ranking strategy and the producer's strategy can also be selected for each individual user.
	
	Fixes have also been made as per feedback from the TA from "Milestone 2". This includes:
	- removing unit tests from the UML
	- adding interface classes from the Java API to the UML
	- fixing the IndexOutOfBoundsException bug when setting all seed parameters to 1
	- made variables in the ButtonListener private
	- fixing the magic strings issues
	- fixing the MVC design pattern by using Observable and Observers
	- updating payoff the moment a user likes their documents, and not when the producer acts
	- now allowing to change the top 'k' documents to be ranked
	- displaying the entire social network (including user's followers and following)
	- tests updated to test for invalid/null inputs
	
	The project documentation comprised of "User_Manual_M3.pdf", "Design_Decisions_M3.pdf", and "Milestone3_UML.jpg"
	have also been updated from those for "Milestone 2" to reflect the above changes.
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//							  Known Issues								//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   The project currently has no known issues for "Milestone 3", operating as expected
	when tested with multiple seed values for the number of documents and users.
	
	
	//////////////////////////////////////////////////////////////////////////
	//																		//
	//					  		Roadmap Ahead								//
	//																		//
	//////////////////////////////////////////////////////////////////////////
	
	   For "Milestone 4" the goals are:
	   
	   - Implement ability to step back in the simulation, and ability to save and restore simulation at any point
	   - Re-factor the GUI to allow allow users to change a greater number of simulation factors, including:
			- the ranking strategy of each User
			- the Strategy used by Producers (a or b)
	   - Update all documentation to account for new project changes
	   - Include suggestions made by SYSC 3110 TA's on "Milestone 3"


