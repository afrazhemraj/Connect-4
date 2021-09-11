//AFRAZ HEMRAJ
//ICS3UO FINAL: CONNECT 4 WITH AI
//MR.WRAY
//SUBMITTED JUNE 17th, 2019

// The "Connect4" class.
//Importing packets for entire game
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

//Main processing class, includes AI, winning conditions and and Mouse clicker
public class Connect4 extends JFrame
{
    //Declaring Variables for entire class
    ImageIcon chip, chip2, bottomPanel, bot;
    Container frame;
    int h = 6, w = 8;
    int player = 1;
    int bs = 100;
    int gamestate = 1;
    int[] [] board = new int [h] [w];
    int counter = 0;
    String playername = "";

    //Frame of the actual game
    public Connect4 (int gamestate)
    {
	super ("Connect4");     // Set the frame's name
	setSize (800, 800);     // Set the frame's size

	//Gamestate is the parameter passed by the GUI to the game to determine player 1 vs player 2 or AI mode.
	this.gamestate = gamestate;
	setUndecorated (true); //No top bar

	//Add mouse listener for click input
	addMouseListener (new CustomMouseListener ());

	//Importing toolkit and icons for panes and graphics
	Toolkit dir = Toolkit.getDefaultToolkit ();
	chip = new ImageIcon (dir.getImage ("bestchipred.png"));
	chip2 = new ImageIcon (dir.getImage ("bestchipyellow.fw.png"));
	bottomPanel = new ImageIcon (dir.getImage ("BottomPanel.png"));
	bot = new ImageIcon (dir.getImage ("Tobbie.png"));

	show ();                // Show the frame
    } // Constructor


    //Method to find the next space (from the bottom)
    int nextSpace (int x)
    {
	for (int y = h - 1 ; y >= 0 ; y--)
	    if (board [y] [x] == 0)
		return y; //Finds the empty y position for the inputted x position
	return -1; //If everything is full, return -1
    }


    //Built in method: Mouse listener
    public class CustomMouseListener implements MouseListener
    {
	//If mouse is clicked
	public void mouseClicked (MouseEvent e)
	{
	    //get x coordinate of mouse
	    int yeet = e.getX ();
	    //Divide by base to find column number of click, + find next empty y coordinate of that column
	    int x = (yeet / bs), rand, y = nextSpace (x), AI;
	    //Continue variable to tell AI what portion of code to run.
	    boolean contin = true;

	    //Counter for leaderboards (number of moves needed to beat AI)
	    counter = counter + 1;

	    //If colummn isn't full
	    if (y >= 0)
	    {
		board [y] [x] = player; //sets array postion to player whom's turn it is

		//If mode is player 1 vs player 2
		if (gamestate == 2)
		{
		    player = player == 1 ? 2:
		    1;                      //switch to the other player (1->2  2->1)
		}

	    }

	    ///*BASIC AI CODE
	    //If game mode is AI vs player 1
	    if (gamestate == 1)
	    {
		//Aesthetic changes to JOption Pane boxes (colours)
		Color babyblue = new Color (96, 186, 255);
		UIManager.put ("OptionPane.background", babyblue);
		UIManager.put ("Panel.background", babyblue);

		//Repaints graphics to show new chips
		repaint ();
		//Check if board is full
		checkFull ();

		//If winner is player 1
		if (winnerwinnerchickendinner () == 1)
		{
		    //Make button yellow (player one is yellow)
		    UIManager.put ("Button.background", Color.yellow);
		    //Output winner
		    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");

		    //Ask for player name for leaderboard, This pane has a custom Icon
		    playername = (String) JOptionPane.showInputDialog (null, "Enter your name for the Leaderboards!", "Go Down In History!", JOptionPane.INFORMATION_MESSAGE, bot, null, "");

		    //If name isn't entered, default name is "nobody"
		    if (playername != null && !playername.equals (""))
		    {

		    }
		    else
		    {
			playername = "Nobody";
		    }

		    //setVisible(false);
		    //Send player name and move counter to printwriter method
		    try
		    {
			countersend ();
		    }
		    catch (Exception c)
		    {

		    }
		    //Make game frame dissapear
		    setVisible (false);
		    //break loop
		    return;
		}

		//AI CODE
		//for loops for every position in the grid
		//VERTICAL CONNECT 4
		for (int m = 0 ; m < w ; m++)
		    for (int l = 0 ; l < h ; l++)

			{
			    //if postion is valid
			    if (p (l, m) != 0 && p (l, m) == p (l - 1, m) && p (l, m) == p (l - 2, m) && p (l - 3, m) != -3 && nextSpace (m) == (l - 3))
			    {
				if (m >= 0 || m < w || (l - 3) >= 0 || (l - 3) < h)
				{
				    //Set x coordinate
				    rand = m;
				    //find next y coordinate
				    AI = nextSpace (rand);
				    //If y is valid
				    if (AI != -1)

					{
					    //end AI turn
					    contin = false;
					    //Set AI's move position
					    board [AI] [rand] = 2;
					    //Switch player
					    player = 1;
					    //Check if board is full
					    checkFull ();
					    //If AI has won
					    if (winnerwinnerchickendinner () == 2)
					    {
						//Change pane button color to red (AI is red)
						UIManager.put ("Button.background", Color.red);
						//Output winner
						JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
						//Make frame dissapear
						setVisible (false);
					    }
					    //break loop
					    return;
					}
				}


			    }
			}

		//HORIZONTAl CONNECT 4s
		//each line does the same thing as the commented section above, refer to that section for comments.
		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l, (m + 1)) && p (l, m) == p (l, (m + 2)) && p ((l - 1), (m + 3)) != -3 && p ((l), (m + 3)) != p (l, m) && p (l, m + 3) != -3 && nextSpace (m + 3) == (l))
			{
			    if ((m + 3) >= 0 || (m + 3) < w || (l) >= 0 || (l) < h)
			    {
				rand = (m + 3);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l, (m - 1)) && p (l, m) == p (l, (m - 2)) && p ((l), (m - 3)) != -3 && p ((l), (m - 3)) != p (l, m) && p (l, m - 3) != -3 && nextSpace (m - 3) == (l))
			{
			    if ((m - 3) >= 0 || (m - 3) < w || (l) >= 0 || (l) < h)
			    {
				rand = (m - 3);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		//DIAGONAL CONNECT 4s
		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 1, m + 1) && p (l, m) == p (l - 2, m + 2) && p (l - 3, m + 3) != -3 && nextSpace (m + 3) == (l - 3))
			{
			    if ((m + 3) >= 0 || (m + 3) < w || (l - 3) >= 0 || (l - 3) < h)
			    {
				rand = (m + 3);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 1, m - 1) && p (l, m) == p (l - 2, m - 2) && p (l - 3, m - 3) != -3 && nextSpace (m - 3) == (l - 3))
			{
			    if ((m - 3) >= 0 || (m - 3) < w || (l - 3) >= 0 || (l - 3) < h)
			    {
				rand = (m - 3);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		//HORIZONTAL CONNECT 4s WITH GAPS
		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l, (m + 1)) && p (l, m) == p (l, (m + 3)) && p ((l - 1), (m + 2)) != -3 && p ((l), (m + 2)) != p (l, m) && p (l, m + 2) != -3 && nextSpace (m + 2) == (l))
			{
			    if ((m + 2) >= 0 || (m + 2) < w || (l) >= 0 || (l) < h)
			    {
				rand = (m + 2);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }


		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l, (m + 2)) && p (l, m) == p (l, (m + 3)) && p ((l - 1), (m + 1)) != -3 && p ((l), (m + 1)) != p (l, m) && p (l, m + 1) != -3 && nextSpace (m + 1) == (l))
			{
			    if ((m + 1) >= 0 || (m + 1) < w || (l) >= 0 || (l) < h)
			    {
				rand = (m + 1);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		//DIAGONAL CONNECT 4s WITH GAPS
		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 1, m - 1) && p (l, m) == p (l - 3, m - 3) && p (l - 2, m - 2) != -3 && nextSpace (m - 2) == (l - 2))
			{
			    if ((m - 2) >= 0 || (m - 2) < w || (l - 2) >= 0 || (l - 2) < h)
			    {
				rand = (m - 2);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 2, m - 2) && p (l, m) == p (l - 3, m - 3) && p (l - 1, m - 1) != -3 && nextSpace (m - 1) == (l - 1))
			{
			    if ((m - 1) >= 0 || (m - 1) < w || (l - 1) >= 0 || (l - 1) < h)
			    {
				rand = (m - 1);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 1, m + 1) && p (l, m) == p (l - 3, m + 3) && p (l - 2, m + 2) != -3 && nextSpace (m + 2) == (l - 2))
			{
			    if ((m + 2) >= 0 || (m + 2) < w || (l - 2) >= 0 || (l - 2) < h)
			    {
				rand = (m + 2);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 2, m + 2) && p (l, m) == p (l - 3, m + 3) && p (l - 1, m + 1) != -3 && nextSpace (m + 1) == (l - 1))
			{
			    if ((m + 1) >= 0 || (m + 1) < w || (l - 1) >= 0 || (l - 1) < h)
			    {
				rand = (m + 1);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 1, m + 1) && p (l, m) == p (l - 2, m + 2) && p (l + 1, m - 1) != -3 && nextSpace (m - 1) == (l + 1))
			{
			    if ((m - 1) >= 0 || (m - 1) < w || (l + 1) >= 0 || (l + 1) < h)
			    {
				rand = (m - 1);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }
		for (int l = 0 ; l < h ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l - 1, m - 1) && p (l, m) == p (l - 2, m - 2) && p (l + 1, m + 1) != -3 && nextSpace (m + 1) == (l + 1))
			{
			    if ((m + 1) >= 0 || (m + 1) < w || (l + 1) >= 0 || (l + 1) < h)
			    {
				rand = (m + 1);
				AI = nextSpace (rand);
				if (AI != -1)

				    {
					contin = false;
					board [AI] [rand] = 2;
					player = 1;
					checkFull ();
					if (winnerwinnerchickendinner () == 2)
					{
					    UIManager.put ("Button.background", Color.red);
					    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					    setVisible (false);
					}
					return;
				    }
			    }


			}
		    }

		//DEFENSE FOR EARLY BOTTOM ROW TRAP
		for (int l = h - 1 ; l == h - 1 ; l++)
		    for (int m = 0 ; m < w ; m++)
		    {
			if (p (l, m) != 0 && p (l, m) == p (l, (m + 1)) && p (l, (m + 2)) != -3 && nextSpace (m + 2) == (l))
			{

			    rand = (m + 2);
			    AI = nextSpace (rand);
			    if (AI != -1)

				{
				    contin = false;
				    board [AI] [rand] = 2;
				    player = 1;
				    checkFull ();
				    if (winnerwinnerchickendinner () == 2)
				    {
					UIManager.put ("Button.background", Color.red);
					JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
					setVisible (false);
				    }
				    return;
				}



			}
		    }


		//If none of the above conditions are present
		if (contin = true)
		{
		    //Choose random column
		    rand = (int) (Math.random () * 8);
		    //Find empty Y coordinate
		    AI = nextSpace (rand);
		    //If valid, place chip
		    if (AI != -1)

			{
			    board [AI] [rand] = 2;
			}
		    //if column is full, find new column
		    else
		    {
			rand = (int) (Math.random () * 8);
			AI = nextSpace (rand);

			//Run until valid column is found
			do
			{
			    rand = (int) (Math.random () * 8);
			    AI = nextSpace (rand);
			}
			while (AI == -1);

			//When valid, set chip position
			board [AI] [rand] = 2;

		    }
		    //Switch player
		    player = 1;
		}

		//*/
	    }
	    //Repaint to show new chips
	    repaint ();
	    //Check if the board is full
	    checkFull ();

	    //If game mode is player 1 vs player 2
	    if (gamestate == 2)
	    {
		//If winner is found
		if (winnerwinnerchickendinner () > 0)
		{
		    //Aesthetic changes to JOptionPane boxes
		    Color babyblue = new Color (96, 186, 255);
		    UIManager.put ("OptionPane.background", babyblue);
		    UIManager.put ("Panel.background", babyblue);

		    //If player 1 wins, make buttons yellow
		    if (winnerwinnerchickendinner () == 1)
		    {
			UIManager.put ("Button.background", Color.yellow);
		    }
		    //If player 2 wins, make button red
		    if (winnerwinnerchickendinner () == 2)
		    {
			UIManager.put ("Button.background", Color.red);
		    }
		    //Display winner and make frame dissapear
		    JOptionPane.showMessageDialog (null, "Player " + winnerwinnerchickendinner () + " Won the game!");
		    setVisible (false);
		}
	    }

	}



	//Other mouse functions in the built in method that were not utilized.
	public void mousePressed (MouseEvent e)
	{
	}


	public void mouseReleased (MouseEvent e)
	{
	}


	public void mouseEntered (MouseEvent e)
	{
	}


	public void mouseExited (MouseEvent e)
	{
	}
    }


    //Printwriter method to write winning players name and number of moves they took to beat the AI to the leaderboards file.
    public void countersend () throws IOException //Throws exception to allow code to run
    {
	//New file writer
	PrintWriter counterwriter = new PrintWriter (new FileWriter ("Leaderboards.txt", true));
	counterwriter.println (playername); //First print line contains name
	counterwriter.println (counter); //Second print line contains moves taken

	//Close path
	counterwriter.close ();

    }


    //Method to check if postions are within the board. The AI uses this method to avoid out of bounds errors.
    public int p (int y, int x)
    {
	int valid = 0;
	//If position is not on the boards, send -3
	if (x < 0 || x >= w || y < 0 || y >= h)
	{
	    valid = -3;
	    //When -3 is sent to the AI code, that move will not be made as the postion is off the board.
	}


	else
	{
	    //If valid send the value of the array postion, either empty (0) or full (1 or 2),
	    valid = board [y] [x];
	}


	//Return the corresponding value where the method is called
	return valid;
    }


    //Method to check if a player has won
    public int winnerwinnerchickendinner ()
    {
	//Declaring variables
	int winner = 0;

	//For loops check each postion of the board.
	for (int y = 0 ; y < h ; y++)
	    for (int x = 0 ; x < w ; x++)

		{
		    //If all postions are valid and a vertical Connect 4 is found, winner is returned as the player who has the connect 4.
		    if (p (y, x) != 0 && p (y, x) == p (y + 1, x) && p (y, x) == p (y + 2, x) && p (y, x) == p (y + 3, x))
			winner = p (y, x);
		}


	for (int y = 0 ; y < h ; y++)
	    for (int x = 0 ; x < w ; x++)

		{
		    //If all postions are valid and a horizontal Connect 4 is found, winner is returned as the player who has the connect 4.
		    if (p (y, x) != 0 && p (y, x) == p (y, x + 1) && p (y, x) == p (y, x + 2) && p (y, x) == p (y, x + 3))
			winner = p (y, x);


		}


	for (int y = 0 ; y < h ; y++)
	    for (int x = 0 ; x < w ; x++)

		{
		    //If all postions are valid and a postive diagonal Connect 4 is found, winner is returned as the player who has the connect 4.
		    if (p (y, x) != 0 && p (y, x) == p (y + 1, x + 1) && p (y, x) == p (y + 2, x + 2) && p (y, x) == p (y + 3, x + 3))
			winner = p (y, x);
		}


	for (int y = 0 ; y < h ; y++)
	    for (int x = 0 ; x < w ; x++)

		{
		    //If all postions are valid and a negative diagonal Connect 4 is found, winner is returned as the player who has the connect 4.
		    if (p (y, x) != 0 && p (y, x) == p (y + 1, x - 1) && p (y, x) == p (y + 2, x - 2) && p (y, x) == p (y + 3, x - 3))
			winner = p (y, x);

		}

	//Return winner value to where the method is called. If winner remains 0, no one has won and moves can continue.
	return winner;
    }


    //Method to check if board is full
    public void checkFull ()
    {
	//declaring variables
	int counter2 = 0;
	//For loop checks each postion on board
	for (int y = 0 ; y < h ; y++)
	    for (int x = 0 ; x < w ; x++)
	    {
		//If the board at a postion is not empty, add one to the counter
		if (board [y] [x] != 0)
		{
		    counter2++;

		}
	    }

	//If the counter hits 48 (all spaces are not empty) output a tie message
	if (counter2 == 48)
	{
	    JOptionPane.showMessageDialog (null, "Tie Game, Board is Full!");
	    //Make the frame dissapear.
	    System.exit (0);
	}
    }


    //Graphics method
    public void paint (Graphics g)
    {
	//Create white background for board
	g.setColor (Color.white);
	g.fillRect (0, 0, 800, 800);

	//Create blue panel over white background
	g.setColor (Color.blue);
	g.fillRect (0, 0, 800, 600);
	//Paints yellow chip to indicate player one's turn
	chip2.paintIcon (this, g, 200, 650);

	//Create bottom panel using image icon that was imported
	bottomPanel.paintIcon (this, g, 0, 600);
	chip2.paintIcon (this, g, 200, 650);

	//For each postion on the board
	for (int j = 0 ; j < h ; j++)
	    for (int i = 0 ; i < w ; i++)
	    {

		//Draw a black outline for the square
		g.setColor (Color.black);
		g.drawRect (i * bs, j * bs, bs, bs);

		//Draw a white circle in the middle of each blue box to make the connect 4 board
		g.setColor (Color.white);
		g.fillOval (i * bs, j * bs, bs, bs);

		//If player 1 is occupying a postion on the board
		if (board [j] [i] == 1)
		{
		    //Paint the yellow chip icon in the corresponding position
		    chip2.paintIcon (this, g, i * bs, j * bs);

		    //repaint the bottom panel with the right chip to indicate whos turn it is.
		    bottomPanel.paintIcon (this, g, 0, 600);
		    if (player == 1)
		    {
			chip2.paintIcon (this, g, 200, 650); //yellow chip
		    }

		    else if (player == 2)
		    {

			chip.paintIcon (this, g, 500, 650); //red chip
		    }

		    //g.setColor (Color.yellow);
		    //g.fillOval (i * bs, j * bs, bs, bs);
		}

		//If player 2 is occupying a postion on the board
		else if (board [j] [i] == 2)
		{
		    //Paint the red chip icon in the corresponding position
		    chip.paintIcon (this, g, i * bs, j * bs);

		    //repaint the bottom panel with the right chip to indicate whos turn it is.
		    bottomPanel.paintIcon (this, g, 0, 600);
		    if (player == 1)
		    {
			chip2.paintIcon (this, g, 200, 650); //yellow chip
		    }

		    else if (player == 2)
		    {

			chip.paintIcon (this, g, 500, 650); //red chip
		    }
		    //g.setColor (Color.red);
		    //g.fillOval (i * bs, j * bs, bs, bs);
		}


	    }


	// Place the drawing code here
    } // paint method


    public static void main (String[] args)
    {
	//Create a GUI fram (next class) to start off the program as the GUI is the control center.
	new C4GUI ();

    } // main method
} // Connect4 class


//New class for the GUI Frame and its interface
class C4GUI extends JFrame implements ActionListener
{
    //declaring variables
    Container frame;
    JButton btnLeaderboards, btnInstructions, btnPlay, btnAI;
    JLabel lblIn;
    String playername;

    //GUI frame
    public C4GUI ()
    {
	// Set the frame's name
	super ("Afraz Hemraj's Connect 4");

	//set frame
	frame = getContentPane ();

	//Colour utilized throughout GUI for buttons etc.
	Color babyblue = new Color (96, 186, 255);

	//BUTTONS
	//Aesthetic changes in font and colour to make GUI more interesting.

	//Leaderboard button
	btnLeaderboards = new JButton ("Leaderboards"); //Sets functional text
	btnLeaderboards.setBackground (babyblue);
	btnLeaderboards.setForeground (Color.red);
	btnLeaderboards.setFont (new Font ("SansSerif", Font.BOLD, 18));

	//How to play button
	btnInstructions = new JButton ("How to Play?"); //Sets functional text
	btnInstructions.setBackground (babyblue);
	btnInstructions.setForeground (Color.yellow);
	btnInstructions.setFont (new Font ("SansSerif", Font.BOLD, 18));

	//Play player 1 vs player 2 button
	btnPlay = new JButton ("Play Connect 4: Player 1 vs. Player 2"); //Sets functional text
	btnPlay.setBackground (babyblue);
	btnPlay.setForeground (Color.yellow);
	btnPlay.setFont (new Font ("SansSerif", Font.BOLD, 18));

	//Play AI button
	btnAI = new JButton ("Play Connect 4: Vs AI"); //Sets functional text
	btnAI.setBackground (babyblue);
	btnAI.setForeground (Color.red);
	btnAI.setFont (new Font ("SansSerif", Font.BOLD, 18));

	//adds the action listener to the buttons to tell if a button has been clicked
	btnLeaderboards.addActionListener (this);
	btnInstructions.addActionListener (this);
	btnPlay.addActionListener (this);
	btnAI.addActionListener (this);

	//LABELS
	//Importing a background image icon and scaling it to add to the GUI
	ImageIcon imageIcon = new ImageIcon ("GUIpanel.fw.png"); // load the image to a imageIcon
	Image image = imageIcon.getImage (); // Tranform icon into image
	Image newimg = image.getScaledInstance (400, 400, java.awt.Image.SCALE_SMOOTH); // scale it to maximum frame size
	imageIcon = new ImageIcon (newimg); // transform it back to an icon

	//Setting labels to contain new image
	lblIn = new JLabel (imageIcon);

	//layout of the interface grid bag layout so each components size can be manually set.
	frame.setLayout (new GridBagLayout ());
	GridBagConstraints c = new GridBagConstraints ();
	c.fill = GridBagConstraints.HORIZONTAL;

	//ADDING CONTENTS WITH X AND Y POSITIONS AS WELL AS HEIGHT
	frame.setBackground (Color.blue); //background colour

	//icon
	frame.add (lblIn, c);

	//play button
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 1;
	c.ipady = 40;
	frame.add (btnPlay, c);

	//AI Button
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 2;
	c.ipady = 40;
	frame.add (btnAI, c);

	//Instructions Button
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 3;
	c.ipady = 40;
	frame.add (btnInstructions, c);

	//Leaderboards Button
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 4;
	c.ipady = 40;
	frame.add (btnLeaderboards, c);

	//Set the frame's size
	setSize (400, 725);
	setResizable (false); //Frame cannot be edited, which keeps components looking clean and fitted
	//Show the frame
	show ();
    } // Constructor


    //If a button is clicked
    public void actionPerformed (ActionEvent e)
    {
	//if play is pressed
	if (e.getSource () == btnPlay)
	{
	    //Create a new game frame with game mode 2 as its gamestate (player 1 vs player 2)
	    int gamestate = 2;
	    new Connect4 (gamestate);
	    //setVisible(false);

	}

	//if play vs AI is pressed
	else if (e.getSource () == btnAI)
	{
	    //Create a new game frame with game mode 1 as its gamestate (player 1 vs AI)
	    int gamestate = 1;
	    new Connect4 (gamestate);

	    //setVisible(false);


	}

	//if Leaderboards button is pressed
	else if (e.getSource () == btnLeaderboards)
	{
	    //Create a new leaderboards frame
	    new Leaderboards ();

	}

	//if How to play is pressed
	else if (e.getSource () == btnInstructions)
	{
	    //Create a new instructions frame
	    new Instructions ();

	}
    }


    /* Old player name printing method. Moved to Game class

	public void namesend () throws IOException
	{
	    PrintWriter namewriter = new PrintWriter (new FileWriter ("Leaderboards.txt", true));
	    namewriter.println (playername);

	    namewriter.close ();
	}
    */


    public static void main (String[] args)
    {
	new C4GUI ();     // Create a C4GUI frame
    } // main method
} // C4GUI


//New Class for the Instructions frame
class Instructions extends JFrame implements ActionListener
{
    //declaring variables
    Container frame;
    JButton btnReturn;
    JLabel lblIn;

    //Instructions/ How to play frame
    public Instructions ()
    {
	// Set the frame's name
	super ("How to Play Connect 4");

	//set frame
	frame = getContentPane ();

	//Colour used for buttons, etc.
	Color babyblue = new Color (96, 186, 255);

	//BUTTONS
	//Aesthetic changes in font and colour to make GUI more interesting.

	//Return to menu button
	btnReturn = new JButton ("Return to Main Menu"); //Sets functional text
	btnReturn.setBackground (babyblue);
	btnReturn.setForeground (Color.red);
	btnReturn.setFont (new Font ("SansSerif", Font.BOLD, 18));


	//adds the action listener to the buttons to tell if a button has been clicked
	btnReturn.addActionListener (this);

	//LABELS
	//Importing the instructions as an image icon and scaling it to add to the GUI
	ImageIcon imageIcon = new ImageIcon ("Instructions.fw.png"); // load the image to a imageIcon
	Image image = imageIcon.getImage (); // Tranform icon into image
	Image newimg = image.getScaledInstance (400, 625, java.awt.Image.SCALE_SMOOTH); // scale it to maximum frame size
	imageIcon = new ImageIcon (newimg); // transform it back to an icon

	//Setting insstruction label icon to contain the instructions image
	lblIn = new JLabel (imageIcon);

	//layout of the interface
	//Grid bag layout so each component can be sized individually
	frame.setLayout (new GridBagLayout ());
	GridBagConstraints c = new GridBagConstraints ();
	c.fill = GridBagConstraints.HORIZONTAL;

	//ADDING CONTENTS WITH X AND Y POSITIONS AS WELL AS HEIGHT
	frame.setBackground (Color.blue); //background colour
	//Add instructions image
	frame.add (lblIn, c);

	//Add button to return to menu.
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 4;
	c.ipady = 40;
	frame.add (btnReturn, c);

	//Set the frame's size
	setSize (400, 725);
	setResizable (false); //Frame cannot be edited, which keeps components looking clean and fitted
	//Show the frame
	show ();
    } // Constructor


    //Action listener for if a button is pressed
    public void actionPerformed (ActionEvent e)
    {

	//if return to menu is pressed
	if (e.getSource () == btnReturn)
	{
	    //Make instructions frame dissapear
	    setVisible (false);

	}
    }


    public static void main (String[] args)
    {
	new Instructions ();     // Create a ne Instructions frame
    } // main method
} // Instructions class


//Leaderboards class that contains searching, sorting and highscores
class Leaderboards extends JFrame implements ActionListener
{
    //declaring variables
    Container frame;
    JButton btnReturn, btnSearch;
    JLabel lblIn, lblSearch, lblOutput;
    JTextArea Leaders, SearchName, SearchOutput;
    JScrollPane scrollSearch, scrollName, scrollOutput;
    int linenumber = 0;

    //Leaderboards frame
    public Leaderboards ()
    {
	// Set the frame's name
	super ("Leaderboards!");

	//set frame
	frame = getContentPane ();

	//Color used for buttons etc.
	Color babyblue = new Color (96, 186, 255);

	//TEXT AREAS
	//Leaderboard text area
	Leaders = new JTextArea (""); //Sets starting text
	Leaders.setBackground (Color.white);
	Leaders.setFont (new Font ("Franklin Gothic Demi", Font.BOLD, 14));
	Leaders.setForeground (Color.black);
	Leaders.setEditable (false);

	//Search Output text area
	SearchOutput = new JTextArea (""); //Sets starting text
	SearchOutput.setBackground (Color.white);
	SearchOutput.setFont (new Font ("Franklin Gothic Demi", Font.BOLD, 14));
	SearchOutput.setForeground (Color.black);
	SearchOutput.setEditable (false);

	//Search name text area
	SearchName = new JTextArea (""); //Sets starting text
	SearchName.setBackground (Color.white);
	SearchName.setFont (new Font ("Franklin Gothic Demi", Font.BOLD, 14));
	SearchName.setForeground (Color.black);

	//Creating scroll panes for each text area
	scrollSearch = new JScrollPane (Leaders); //Scrollbar for first text Area.
	scrollName = new JScrollPane (SearchName);
	scrollOutput = new JScrollPane (SearchOutput);

	//BUTTONS
	//Aesthetic changes in font and colour to make GUI more interesting.

	//Return to menu button
	btnReturn = new JButton ("Return to Main Menu"); //Sets functional text
	btnReturn.setBackground (babyblue);
	btnReturn.setForeground (Color.red);
	btnReturn.setFont (new Font ("SansSerif", Font.BOLD, 18));

	//Search button
	btnSearch = new JButton ("Search"); //Sets functional text
	btnSearch.setBackground (babyblue);
	btnSearch.setForeground (Color.yellow);
	btnSearch.setFont (new Font ("SansSerif", Font.BOLD, 18));

	//adds the action listener to the buttons to tell if a button has been clicked
	btnReturn.addActionListener (this);
	btnSearch.addActionListener (this);

	//LABELS
	//Importing a background image icon and scaling it to add to the GUI

	//Leaderboards header
	ImageIcon imageIcon = new ImageIcon ("LeaderboardsHeader.fw.png"); // load the image to a imageIcon
	Image image = imageIcon.getImage (); // Tranform icon into image
	Image newimg = image.getScaledInstance (400, 200, java.awt.Image.SCALE_SMOOTH); // scale it to maximum frame size
	imageIcon = new ImageIcon (newimg); // transform it back to an icon

	//Search label text
	ImageIcon imageIcon2 = new ImageIcon ("SearchHeader.png"); // load the image to a imageIcon
	Image image2 = imageIcon2.getImage (); // Tranform icon into image
	Image newimg2 = image2.getScaledInstance (400, 20, java.awt.Image.SCALE_SMOOTH); // scale it to maximum frame size
	imageIcon2 = new ImageIcon (newimg2); // transform it back to an icon

	//Output label text
	ImageIcon imageIcon3 = new ImageIcon ("OutputHeader.png"); // load the image to a imageIcon
	Image image3 = imageIcon3.getImage (); // Tranform icon into image
	Image newimg3 = image3.getScaledInstance (400, 20, java.awt.Image.SCALE_SMOOTH); // scale it to maximum frame size
	imageIcon3 = new ImageIcon (newimg3); // transform it back to an icon

	//Setting labels to contain new image
	lblIn = new JLabel (imageIcon);
	lblSearch = new JLabel (imageIcon2);
	lblOutput = new JLabel (imageIcon3);

	//layout of the interface
	frame.setLayout (new GridBagLayout ());
	//Grid bag layout so each component can be sized individually
	GridBagConstraints c = new GridBagConstraints ();
	c.fill = GridBagConstraints.HORIZONTAL;

	//ADDING CONTENTS WITH X AND Y POSITIONS AS WELL AS HEIGHT
	frame.setBackground (Color.blue); //background colour
	//Add leaderboard header
	frame.add (lblIn, c);

	//Leaderboards text area
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 1;
	c.ipady = 205;
	frame.add (scrollSearch, c);

	//Search label header
	c.weightx = 0.0;
	c.gridx = 0;
	c.gridy = 2;
	c.ipady = 0;
	frame.add (lblSearch, c);

	//Search name text area
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 3;
	c.ipady = 5;
	frame.add (scrollName, c);

	//Output label header
	c.weightx = 0.0;
	c.gridx = 0;
	c.gridy = 5;
	c.ipady = 0;
	frame.add (lblOutput, c);

	//Output text area
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 6;
	c.ipady = 30;
	frame.add (scrollOutput, c);

	//Search button
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 7;
	c.ipady = 40;
	frame.add (btnSearch, c);

	//Return to menu Button
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 8;
	c.ipady = 40;
	frame.add (btnReturn, c);

	//Set the frame's size
	setSize (400, 725);
	setResizable (false); //Frame cannot be edited, which keeps components looking clean and fitted
	//Show the frame
	show ();

	//Try catch to catch errors that occur during file reading and sorting
	try
	{
	    //New file reader to red leaderboards file
	    BufferedReader Leaderwriter = new BufferedReader (new FileReader ("Leaderboards.txt"));

	    //Call on linecounter method to find dynamic number of lines in file being read
	    linenumber = linecounter ();

	    //New arrays for names and moves take that were previously written to the leaderboards file.
	    //Both are stored in the same file so they each take half of the total number of lines hence "/2".
	    String names[] = new String [linenumber / 2];
	    int moves[] = new int [linenumber / 2];

	    //For loop to read all of the information and store it into the corresponding arrays.
	    for (int i = 0 ; i < names.length ; i++)
	    {
		names [i] = Leaderwriter.readLine (); //First line read is a name.
		moves [i] = Integer.parseInt (Leaderwriter.readLine ()); //Second line read is the number of moves take by the above player name.
	    }

	    //BUBBLE SORT
	    //For loop to ensure all information is switched and organized COMPLETELY
	    for (int f = 0 ; f < moves.length - 1 ; f++)
	    {
		//For loop to compare each array postion.
		for (int k = 0 ; k < moves.length - 1 ; k++)
		{
		    //If information is not in order from least to greatest
		    if (moves [k] > moves [k + 1])
		    {
			//Switch the two values and their postions in the array
			int temp = moves [k];
			moves [k] = moves [k + 1];
			moves [k + 1] = temp;

			//Switch the corresponding player names as well to keep information together.
			String temp2 = names [k];
			names [k] = names [k + 1];
			names [k + 1] = temp2;

		    }
		}
	    }

	    //Declaring accumulator variable
	    String info = "";

	    //For loop to collect top six scores
	    for (int j = 0 ; j < 6 ; j++)
	    {
		//add player names and move numbers of the top six players to their own lines
		info = info + (j + 1) + ". " + names [j] + "                                                          " + moves [j] + "\n\n";
	    }
	    //Set the leaderboard text to contain the top six player names, their postions and how many moves it took them to defeat the AI.
	    Leaders.setText (info);

	    //Close the path.
	    Leaderwriter.close ();

	}
	catch (Exception t)
	{
	    //If an error is caught along the way, display this user friendly message.
	    JOptionPane.showMessageDialog (null, "File Error, Please try again.");
	}



    } // Constructor


    //Linecounter Method
    public static int linecounter () throws IOException
    {
	//declaring variables
	int linenumber = 0;
	//Reads user desired file (see main method)
	BufferedReader lc = new BufferedReader (new FileReader ("Leaderboards.txt"));

	//If line isn't empty counter increases
	while (lc.readLine () != null)
	{
	    linenumber++;

	}
	//Returns the number of lines to make arrays dynamic to the number of lines in the input file.
	return linenumber;

    }


    //Action listener to check if any buttons are pressed.
    public void actionPerformed (ActionEvent e)
    {

	//if return to menu is pressed
	if (e.getSource () == btnReturn)
	{
	    //Make the leaderboards frame dissapear.
	    setVisible (false);

	}

	//If search is pressed
	if (e.getSource () == btnSearch)
	{
	    //Try catch to catch errors that occur during file searching and reading
	    try
	    {
		//New file reader to red leaderboards file
		BufferedReader Searchwriter = new BufferedReader (new FileReader ("Leaderboards.txt"));

		//Call on linecounter method to find dynamic number of lines in file being read
		linenumber = linecounter ();

		//New arrays for names and moves take that were previously written to the leaderboards file.
		//Both are stored in the same file so they each take half of the total number of lines hence "/2".
		String names[] = new String [linenumber / 2];
		int moves[] = new int [linenumber / 2];

		//For loop to read all of the information and store it into the corresponding arrays.
		for (int i = 0 ; i < names.length ; i++)
		{
		    names [i] = Searchwriter.readLine (); //First line read is a name.
		    moves [i] = Integer.parseInt (Searchwriter.readLine ()); //Second line read is the number of moves take by the above player name.
		}

		//BUBBLE SORT
		//For loop to ensure all information is switched and organized COMPLETELY
		for (int f = 0 ; f < moves.length - 1 ; f++)
		{
		    //For loop to compare each array postion.
		    for (int k = 0 ; k < moves.length - 1 ; k++)
		    {
			//If information is not in order from least to greatest
			if (moves [k] > moves [k + 1])
			{
			    //Switch the two values and their postions in the array
			    int temp = moves [k];
			    moves [k] = moves [k + 1];
			    moves [k + 1] = temp;

			    //Switch the corresponding player names as well to keep information together.
			    String temp2 = names [k];
			    names [k] = names [k + 1];
			    names [k + 1] = temp2;

			}
		    }
		}
		//Declaring Variables
		String nam = SearchName.getText ();
		String namefound = "";
		int returned = 0;
		int movenumber = 0;

		//For loop to search each name in the names array
		for (int l = 0 ; l < names.length ; l++)
		{

		    //If the name is found exactly (not case senstive) in the array
		    if (nam.equalsIgnoreCase (names [l]))
		    {
			//Finds array index in which name is found
			returned = l;

			//Collects all relevant data for that index and stores it.
			namefound = (names [returned]);
			movenumber = (moves [returned]);

			//Set the output text to contain the player name, postion and number of moves it took them to beat the AI
			SearchOutput.setText ("The top Player with the name " + namefound + " beat the AI in " + movenumber + " moves\nand is in the number " +
				(returned + 1) + " spot.");

			//Make input textArea blank again.
			SearchName.setText ("");

			//Break loop, NOTE IF MULTIPLE PLAYERS HAVE THE SAME NAME, ONLY THE TOP RESULT WILL BE DISPLAYED.
			break;
		    }
		}

		//If the text field isn't empty because the name is not part of the leaderboards,
		if (SearchName.getText () != null && !SearchName.getText ().equals (""))
		{
		    //Say that the name was not found,
		    SearchOutput.setText (SearchName.getText () + " was not found in our leaderboards.\nPlease enter a valid name.");
		    //Set input textArea to blank.
		    SearchName.setText ("");
		}



	    }
	    catch (Exception t)
	    {
		//If an error is caught along the way, display this user friendly message.
		JOptionPane.showMessageDialog (null, "File Error, Please try again.");
	    }


	}
    }



    public static void main (String[] args)
    {
	new Leaderboards ();     // Create a Leaderboards frame
    } // main method
} // Leaderboards class


