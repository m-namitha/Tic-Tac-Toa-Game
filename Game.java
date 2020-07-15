import java.util.*;
class Game 
{
	static ArrayList<Integer> PlayerPos = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
	static ArrayList<Integer> xPlayerPos = new ArrayList<Integer>();
	static String name1 = "", name2 = "";
	static String result= "";
	static Random rand = new Random();
	static char[][] board={{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			{'_','_','_','_','_','|','_','_','_','_','_','|','_','_','_','_','_'},
			{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			{'_','_','_','_','_','|','_','_','_','_','_','|','_','_','_','_','_'},
			{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
			};
	public static void main(String[] args) 
	{
		char ch = 'y';
		String val;
		char[][] board1={{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
				{' ',' ','-',' ',' ','|',' ',' ','-',' ',' ','|',' ',' ','-',' ',' '},
				{'_','_','_','_','_','|','_','_','_','_','_','|','_','_','_','_','_'},
				{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
				{' ',' ','-',' ',' ','|',' ',' ','-',' ',' ','|',' ',' ','-',' ',' '},
				{'_','_','_','_','_','|','_','_','_','_','_','|','_','_','_','_','_'},
				{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
				{' ',' ','-',' ',' ','|',' ',' ','-',' ',' ','|',' ',' ','-',' ',' '},
				{' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' '},
				};
		
		Scanner s= new Scanner(System.in);
		System.out.println("\n		XOX GAME		\n");
		display(board1);
		
		while(ch=='y'||ch=='Y') {
			System.out.println("\nEnter your choice\n1: Player v/s Computer		2: 2-Player");
			int choice = s.nextInt();
			switch(choice)
			{
				case 1: comp();
					break;
				case 2: play();
					break;
				default: System.out.println("Invalid choice");
					break;
			}
			System.out.println("\n***********************************************");
			System.out.println("Do you want to play again???");
			val = s.next();
			ch= val.charAt(0);
			
			PlayerPos.removeAll(PlayerPos);
			cpuPos.removeAll(cpuPos);
			xPlayerPos.removeAll(xPlayerPos);
		}
		System.out.println("\nOk....Bye!!");
	}
	public static void comp() 
	{	
		board[1][2] = board[1][8]=board[1][14]=board[4][2]=board[4][8]=board[4][14]=board[7][2]=board[7][8]=board[7][14]=' ';
		Scanner s1= new Scanner(System.in);
		System.out.println("\nLet\'s paly\n");
		System.out.print("Enter Player name:");
		name1 = s1.nextLine();

		while(true){
	
			System.out.println("Enter place of choice to enter X(1-9)");
			int Playerplace=s1.nextInt();
			while(PlayerPos.contains(Playerplace)||cpuPos.contains(Playerplace))
			{
				System.out.println("Position already taken. Re-enter");
				Playerplace=s1.nextInt();
			}
			
			insert(Playerplace,board,name1);
			display(board);
			result= checkWinner();
			if(result.length()>0){
				System.out.println(result);
				break;
			}
				
			int cpuPlace = rand.nextInt(9)+1;
			while(PlayerPos.contains(cpuPlace) || cpuPos.contains(cpuPlace))
			{
				//System.out.println("Position already taken! Re-enter");
				cpuPlace = rand.nextInt(9)+1;
			}
			System.out.println("Enter Computer place\n"+ cpuPlace);
			insert(cpuPlace,board,"Computer");
			display(board);
			result= checkWinner();
	
			if(result.length()>0){
				System.out.println(result);
				break;
			}
		}
	}
	public static void play() 
	{	
		board[1][2] = board[1][8]=board[1][14]=board[4][2]=board[4][8]=board[4][14]=board[7][2]=board[7][8]=board[7][14]=' ';
		Scanner s2= new Scanner(System.in);
		
		System.out.print("Enter Player-1 name:");
		name1 = s2.nextLine();
		System.out.print("Enter Player-2 name:");
		name2 = s2.nextLine();
		System.out.println("\nLet\'s paly\n");
		
		while(true){
			System.out.println(name1+": Enter place of choice to enter X(1-9)");
			int Playerplace=s2.nextInt();
			while(PlayerPos.contains(Playerplace)||xPlayerPos.contains(Playerplace))
			{
				System.out.println("Position already taken. Re-enter");
				Playerplace=s2.nextInt();
			}
			
			insert(Playerplace,board,name1);
			display(board);
			result= checkWinner();
			if(result.length()>0){
				System.out.println(result);
				break;
			}
			
			System.out.println(name2+": Enter place of choice to enter O(1-9)");
			int xPlace = s2.nextInt();
			while(PlayerPos.contains(xPlace) || xPlayerPos.contains(xPlace))
			{
				System.out.println("Position already taken! Re-enter");
				xPlace = rand.nextInt(9)+1;
			}
			
			insert(xPlace,board,name2);
			display(board);
			result= checkWinner();
	
			if(result.length()>0){
				System.out.println(result);
				break;
			}
		}
	}
	public static String checkWinner()
	{
		//winning conditions
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midtCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List diag1 = Arrays.asList(1,5,9);
		List diag2 = Arrays.asList(3,5,7);

		//list of list
		List<List> WinCond = new ArrayList<List>();

		WinCond.add(topRow);
		WinCond.add(midRow);
		WinCond.add(botRow);
		WinCond.add(leftCol);
		WinCond.add(midtCol);
		WinCond.add(rightCol);
		WinCond.add(diag1);
		WinCond.add(diag2);

		for(List<List> l:WinCond)
		{
			if(PlayerPos.containsAll(l))
			{
				return ("\nCongratulations!! "+name1+" has won");
			}
			else if (cpuPos.containsAll(l))
			{
				return ("\nCongratulations!! Computer has won");
			}
			else if (xPlayerPos.containsAll(l))
			{
				return ("\nCongratulations!! "+name2+" has won");
			}
			else if (PlayerPos.size()+ cpuPos.size()==9)
			{
				for(List<List> l1:WinCond)
				{
					if(PlayerPos.containsAll(l1))
					{
						return ("\nCongratulations!! "+name1+" has won");
					}
					else if (cpuPos.containsAll(l1))
					{
						return ("\nCongratulations!! Computer has won");
					}
				}
				return ("\nDraw");
			}
			else if (PlayerPos.size()+ xPlayerPos.size()==9)
			{
				for(List<List> l1:WinCond)
				{
					if(PlayerPos.containsAll(l1))
					{
						return ("\nCongratulations!! "+name1+" has won");
					}
					else if (xPlayerPos.containsAll(l1))
					{
						return ("\nCongratulations!! "+name2+" has won");
					}
				}
				return ("\nDraw");
			}
		}
		return "";

	}

	public static void display(char[][] board)
	{
		for(char[] x:board)
		{
			for(char y:x)
			{
				System.out.print(y);
			}
			System.out.println();
		}
	}
	public static void insert(int Playerplace, char[][] board, String user)
	{
		char sym= ' '; 
		if(user.equals(name1))
		{
			sym='X';
			PlayerPos.add(Playerplace);
		}

		else if(user.equals("Computer"))
		{
			sym='O';
			cpuPos.add(Playerplace);
		}
		else if(user.equals(name2))
		{
			sym='O';
			xPlayerPos.add(Playerplace);
		}
		switch(Playerplace)
		{
			case 1:
				board[1][2]=sym;
				break;
			case 2:
				board[1][8]=sym;
				break;
			case 3:
				board[1][14]=sym;
				break;
			case 4:
				board[4][2]=sym;
				break;
			case 5:
				board[4][8]=sym;
				break;
			case 6:
				board[4][14]=sym;
				break;
			case 7:
				board[7][2]=sym;
				break;
			case 8:
				board[7][8]=sym;
				break;
			case 9:
				board[7][14]=sym;
				break;
			default:
				System.out.println("Invalid choice");
		}
	}
}
