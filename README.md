		//////////////////////////////////////////////////////////////////////
        //                                                                  //
        //              SYSC 3110 - Software Development Project            //
        //                              Fall 2015                           //
        //                                                                  //
        //                  Course Project: Social Network                  //
        //                            Milestone: 4                          //
        //                            Nov 21, 2015                          //
        //                                                                  //
        //  Monty Dhanani, Garrett Steele, Bronwyn Skelley, Even Bottomley  //
        //                                                                  //
        //                  GitHub Repository: redSquadron                  //
        //                                                                  //
        //////////////////////////////////////////////////////////////////////


    //////////////////////////////////
    //                              //
    //      Team Information        //
    //                              //
    //////////////////////////////////

Readme Author: Monty Dhanani

Team Member:        Student Number:         Carleton Email:                     GitHub User-name:
Monty Dhanani       100926543               montydhanani@cmail.carleton.ca      Monty1029
Garrett Steele      100920928               garrettsteele@cmail.carleton.ca     kersey1a
Bronwyn Skelley     100880052               bronwynskelley@cmail.carleton.ca    bronwynk
Evan Bottomley      100943340               EvanBottomley@cmail.carleton.ca     evanbottomley


    //////////////////////////////////
    //                              //
    //      Important Note          //
    //                              //
    //////////////////////////////////

    If opening and running the code from within an IDE, the Simulation.java class is the
start of program execution and contains the main method to be executed. Refer to the "How To Use" in the "Help" option in the Menu Bar on the main GUI,
for further clarification on how to run the program and what the strategy names are referring to.


    //////////////////////////////////////////////////
    //                                              //
    //      Responsibilities for Milestone 4        //
    //                                              //
    //////////////////////////////////////////////////

   The following are the primary design responsibilities of each team member for "Milestone 4", project code is written individually,
or in pairs where stated in the code. Where stated in the code, another team member has made contribution(s) to another team member's code
where changes in the contributors class were directly related to that of another team member. The team member who had their code modified then reviewed
the changes. Debugging activities were performed as a team during team meetings.


Monty Dhanani:      Refactored the GUI for more cohesion, MenuBarListener.java, fixed the unit test in StrategyTwoTest, added scrolling bar to JList,
					README.txt
Garrett Steele:     Load and save feature, social network graph (with Even Bottomley and Bronwyn Skelley),
					fixed problems as per TA feedback in DistanceStrategy.java and SimulationTest.java, Milestone4_UML.jpg
Bronwyn Skelley:    Social network graph (with Garrett Steele and Evan Bottemley), User_Manual_M4.pdf
Evan Bottomley:     Social network graph (with Garrett Steele and Bronwyn Skelley), Design_Decisions_M4.pdf

All team members are responsible for the JavaDoc commenting of their code, 
where the group reviews and edits the JavaDoc together prior to submission.



//////////////////////////////////////////////////////////////////////////
//                                                                      //
//              Included Files in Milestone Submission                  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

   In the ".zip" file submitted to CULearn is:

   - this "README.txt" file
   - a ".jar" file containing the readable source code for the project(where the name of the student who wrote each class is included at the top of each source code)
   - the same ".jar" is executable from the Java console, or if the computer is set up to do so, by double clicking on the executable
   - a "User_Manual_M4.pdf" detailing how to extract and execute the ".jar" file, as well as using the GUI interface and interpreting the program's output
   - a "Design_Decisions_M4.pdf" detailing the design decisions made for the project as a whole, and in the individual Java classes
   - a "Milestone4_UML.jpg" of the project design



//////////////////////////////////////////////////////////////////////////
//                                                                      //
//                    Changes since last Milestone                      //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

Since "Milestone 3", a save and load feature was implemented to select a state from the simulation at any point.
A "step back" option was also added under the Load submenu. Also, code was refactored in the GUI for more cohesion.

Fixes have also been made as per feedback from the TA from "Milestone 3". This includes:
- Fixed the UML diagram so lines do not cross
- Created a graph to display the social network
- Fixed the magic numbers in the rank() method in DistanceStrategy.java
- Added unit tests for start and step in SimulationTest.java
- Added unit test for sortDocument() in StrategyTwoTest.java
- Closed all the finished issues on GitHub

The project documentation comprised of "User_Manual_M4.pdf", "Design_Decisions_M4.pdf", and "Milestone4_UML.jpg"
have also been updated from those for "Milestone 3" to reflect the above changes.


//////////////////////////////////////////////////////////////////////////
//                                                                      //
//                            Known Issues                              //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

   The project currently has no known issues for "Milestone 4", operating as expected
when tested with multiple seed values for the number of documents and users.
