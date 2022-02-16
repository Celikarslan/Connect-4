import java.util.*;


public class Connect4
{
   static String name;
   
   static int turn = 0;
   int WinColour = 0;
   public static boolean hasWon(SparseMatrix<Character>board){
      for (int row = 0; row < 6; row++) {   //check horizontal left to right
         int count = 0;
         for (int col = 0; col < 7; col++) {
            if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row,col-1))//checks for 4 in a row of either color
               count++;
            else
               count = 1;
                    
            if (count >= 4) {
               if(board.get(row,col) == 'R'){  //checks for if the winner is R or Y                       
                  System.out.println("Red wins");
               }
               else
                  System.out.println("Yellow wins");
               return true; //return true if goes through
            
               
            
            }
         }
      }
      
      
      for (int col = 0; col < 7; col++) { //check vertical left to right
         int count = 0;
         for (int row = 5; row >= 0; row--) { 
            if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row+1,col))//checks for 4 in a row of either color
               count++;
            else
               count = 1;
                    
            if (count >= 4) {
               if(board.get(row,col) == 'R'){      //checks for if the winner is R or Y                
                  System.out.println("Red wins");
               }
               else
                  System.out.println("Yellow wins");
               return true; //return true if goes through
            
               
            
            }
         }
      }
      
      for (int col = 0; col < 7; col++) { //check horizontal top left to bottom right
         int count = 0;
         for (int row = 0; row < 6; row++) {
            if (col + row >= 7) 
               break;
            if (board.get(row,col+row) != null &&
                    board.get(row-1,col + row - 1) == board.get(row,col+row))//checks for 4 in a row of either color
               count++;
            else
               count = 1;
                    
            if (count >= 4) {
               if(board.get(row,col) == 'R'){       //checks for if the winner is R or Y                  
                  System.out.println("Red wins");
               }
               else
                  System.out.println("Yellow wins");
               return true;
            }
         }
      }
   
      for (int row = 0; row < 6; row++) {
         int count = 0;
         for (int col = 0; col < 7; col++) {
            if (col + row >= 7) 
               break;
            if (board.get(row + col,col) != null &&
                    board.get(row+col - 1,col - 1) == board.get(row + col,col))//checks for 4 in a row of either color
               count++;
            else
               count = 1;
            if (count >= 4){
               if(board.get(row+col,col) == 'R'){     //checks for if the winner is R or Y                    
                  System.out.println("Red wins");
               }
               else
                  System.out.println("Yellow wins");
               return true;
            }
         }
      }
   
      for (int col = 0; col < 7;col++) {
         int count = 0;
         for (int row = 0; row < 6; row++) {
            if (col - row < 0) 
               break;
            if (board.get(row,col-row) != null &&
                    board.get(row - 1,col - row + 1) == board.get(row,col-row))//checks for 4 in a row of either color
               count++;
            else
               count = 1;
            if (count >= 4){ 
               if(board.get(row,col-row) == 'R'){    //checks for if the winner is R or Y                    
                  System.out.println("Red wins");
               }
               else
                  System.out.println("Yellow wins");
               return true;
            }
         }
      }
   
      for (int row = 0; row < 6; row++) { //checks top right to bottom left
         int count = 0;
         for (int col = 6; col >= 0; col--) {
            if (col - row < 0) 
               break;
            if (board.get(col - row,col) != null &&
                    board.get(col - row - 1,col + 1) == board.get(col - row,col))//checks for 4 in a row of either color
               count++;
            else
               count = 1;
            if (count >= 4){ 
               if(board.get(col-row,col) == 'R'){                        
                  System.out.println("Red wins");
               }
               else
                  System.out.println("Yellow wins");
               return true;
            }
         }
      }
      for(int col = 6;col>-1;col--){ //checks for tie
         for(int row = 5;row>-1;row--){
            int count = 0;
            if(board.get(row,col) != null){
               count++;
            }
            else
               count = 0;
         
         
            if(count == 30){//if the board is full and none of the colors have won
               System.out.println("It's a tie");//calls a tie
            }
         }
      }
   
      
        // Otherwise return false
      return false;
   }
      
   public static void takeTurn(SparseMatrix<Character>board,char player,String name)
   {
   
      Scanner column = new Scanner(System.in);
      
            
      if(!isbotsTurn()){
         System.out.println(name + ", pick a column");//picking where the player is going to place their piece
         int Col = column.nextInt();
         while(Col < 0 || Col > 6 || board.get(0,Col)!=null){
            System.out.println(name + ", pick another column"); //makes player pick other place if not available
            Col = column.nextInt();
         }
         int Row = 0;
         if(board.get(0,Col)== null){
            for(int r = 0; r < 6;r++){
               if(board.get(r,Col) == null){
                  Row = r;
               }
            }
         }
      
      
      
         board.add(Row,Col,player); //adds the piece if condition goes through
      
      
      }
   }
   
   public static void AI(SparseMatrix<Character>board){
   
      if(isbotsTurn()){ //checks if its the bots turn then checks horizontal left to right for winning move or block
         for (int row = 0; row < 6; row++) {
            int count = 0;
            int tempCol1;
            for (int col = 0; col < 7; col++) {
               if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row,col-1))
                  count++;
               else
                  count = 1;
                    
               if (count >= 3) {
                  tempCol1 = col;
                  tempCol1++;
                  if(row != 5 ){
                     if( board.get(row,tempCol1) == null && board.get(row + 1,tempCol1) != null && isbotsTurn()){//checks for pieces to not overtake and to not float
                        board.add(row,tempCol1,'Y');
                        turn++;//makes it the players turn
                     }
                  }
                  else
                     if( board.get(row,tempCol1) == null && isbotsTurn()){ //adds to the bottom if the row is 5
                        board.add(row,tempCol1,'Y');
                        turn++;//makes it the players turn
                     }
                  
               }
            }
         }
      }
      if(isbotsTurn()){  //checks if its the bots turn then checks horizontal right to left for winning move or block
         for (int row = 0; row < 6; row++) {
            int count = 0;
            int tempCol1;
            for (int col = 6; col >= 0; col--) {
               if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row,col+1))
                  count++; //counts how many are next to each other horizontally
               else
                  count = 1;
                    
               if (count >= 3) {
                  tempCol1 = col;
                  tempCol1--;
                  if(row == 5){
                     if(board.get(row,tempCol1) == null && isbotsTurn() ){
                        board.add(row,tempCol1,'Y');
                        turn++;
                     }
                  }
                  else if(board.get(row,tempCol1) == null && board.get(row+1,tempCol1) != null && isbotsTurn()){ //checks if the row below isn't empty to place piece
                     board.add(row,tempCol1,'Y'); 
                     turn++;
                  }
               
               }
            }
         }
      }
   
      if(isbotsTurn()){ //checks if its the bots turn then checks horizontal left to right spaces for winning move or block
         for (int row = 0; row < 6; row++) {
            int count = 0;
            int tempCol1;
            for (int col = 0; col < 7; col++) {
               for(int skip = 1;skip<4;skip=skip+2){
                  if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row,col+skip))
                     count++;
                  else
                     count = 1;
                    
                  if (count >= 3) {
                     tempCol1 = col;
                     tempCol1= tempCol1+2;
                     if(row != 5 ){
                        if( board.get(row,tempCol1) == null && board.get(row - 1,tempCol1) != null && isbotsTurn()){//checks for pieces that are next to each other with a gap to fill
                           board.add(row,tempCol1,'Y');
                           turn++;
                        }
                     }
                     else
                        if( board.get(row,tempCol1) == null && isbotsTurn()){//makes sure it doesn't overtake
                           board.add(row,tempCol1,'Y');
                           turn++;
                        }
                  }
               }
            }
         }
      }
      
      if(isbotsTurn()){ //checks if its the bots turn then checks horizontal right to left spaces for winning move or block
         for (int row = 0; row < 6; row++) {
            int count = 0;
            int tempCol1;
            for (int col = 6; col > 1; col--) {
               for(int skip = 1;skip<4;skip=skip+2){
                  if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row,col-skip))
                     count++;
                  else
                     count = 1;
                    
                  if (count >= 3) {
                     tempCol1 = col;
                     tempCol1= tempCol1-2;
                     if(row != 5 ){
                        if( board.get(row,tempCol1) == null && board.get(row - 1,tempCol1) != null && isbotsTurn()){ //checks for horizontal chips that are 4 in a row with a gap in the middle so it can fill
                           board.add(row,tempCol1,'Y');
                           turn++;
                        }
                     }
                     else
                        if( board.get(row,tempCol1) == null && isbotsTurn()){//makes sure it doesn't overtake a piece
                           board.add(row,tempCol1,'Y');
                           turn++;
                        }
                  }
               }
            }
         }
      }
   
   
      
      if(isbotsTurn()){ //checks if its the bots turn then checks vertical for winning move or block
         for (int col = 0; col < 7; col++) {
            int count = 0;
            int tempRow1;
            for (int row = 6; row >= 0; row--) {
               if (board.get(row,col) != null &&
                    board.get(row,col) == board.get(row+1,col))
                  count++; // counts how many are on top of each other
               else
                  count = 1;
                    
               if (count >= 3) {
                  tempRow1 = row;
                  tempRow1= tempRow1 - 1;
                  if(board.get(tempRow1,col) == null && isbotsTurn()){
                     board.add(tempRow1,col,'Y');
                     turn++;
                  
                  }
               
               }
            }
         }
      }
    
      if(isbotsTurn()){ //checks bottom right to top left
         for (int col = 6; col > 2; col--) {
            for (int row = 5; row > 2; row--) {
               int count =0;
               for (int delta = 0; delta < 4; delta++) {
                  int x1 = row - delta;
                  int y1 = col - delta;
                  
                  if (board.get(x1,y1) == board.get(x1+1,y1+1)&&board.get(x1,y1) != null) //checks for space being empty,makes sure it doesn't count nulls
                     count++;
                  else
                     count = 1;
                  
                  
                  if (count >= 3) {
                     int tempY1 = y1;
                     int tempX1 = x1;
                     tempX1--;
                     tempY1--;
                     if (tempX1 != 5 && tempY1 < 7 && tempX1 > -1 && board.get(tempX1+1,tempY1)!= null) { //makes sure it isn't out of bounds and it isn't floating
                        if (board.get(tempX1,tempY1) == null && isbotsTurn()){
                           board.add(tempX1,tempY1,'Y');
                           turn++;
                           
                        }
                     }
                  }
               }
            }
         }
      }      
         
                       
      
      if(isbotsTurn()){ //checks top left to bottom right
         for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3; row++) {
               int count =0;
               for (int delta = 0; delta < 4; delta++) {
                  int x1 = row + delta;
                  int y1 = col + delta;
               
                  if (board.get(x1,y1) == board.get(x1-1,y1-1)&&board.get(x1,y1) != null) //checks for 3 in a row
                     count++;
                  else
                     count = 1;
               
               
                  if (count >= 3) {
                     int tempY1 = y1;
                     int tempX1 = x1;
                     tempX1++;
                     tempY1++;
                     if (tempX1 < 6 && tempY1 < 7 ) {
                        if(tempX1==5){ //checks if chip is on the bottom or not
                           if (board.get(tempX1,tempY1) == null && isbotsTurn()){
                              board.add(tempX1,tempY1,'Y'); //adds piece
                              turn++;
                           }
                        }
                        else
                           if(board.get(tempX1+1,tempY1) !=null){ //if chip isn't at row 5 makes sure it isnt floating
                              if (board.get(tempX1,tempY1) == null && isbotsTurn()){
                                 board.add(tempX1,tempY1,'Y'); //adds piece
                                 turn++;
                              }
                              
                           }
                     }
                  }
               }
            }
         }
      }
         
      if(isbotsTurn()){ //checks bottom left to top right
         for (int col = 0; col < 4; col++) {
            for (int row = 5; row > 2; row--) {
               int count =0;
               for (int delta = 0; delta < 4; delta++) {
                  int x1 = row - delta;
                  int y1 = col + delta;
                  
                  if (board.get(x1,y1) == board.get(x1+1,y1-1)&&board.get(x1,y1) != null) //checks for 3 in a row diagonally
                     count++;
                  else
                     count = 1;
                  
                  
                  if (count >= 3) {
                     int tempY1 = y1;
                     int tempX1 = x1;
                     tempX1--; //moves piece left
                     tempY1++; //moves it down
                     if (tempX1 != 5 && tempY1 < 7 && tempX1 > -1 && board.get(tempX1+1,tempY1)!= null) { //checks if the chip is in bounds and if the space under isn't null
                        if (board.get(tempX1,tempY1) == null && isbotsTurn()){
                           board.add(tempX1,tempY1,'Y');
                           turn++;
                           
                        }
                     }
                  }
               }
            }
         }
      }
         
      if(isbotsTurn()){ //checks top right to bottom left
         for (int col = 6; col > 2; col--) {
            for (int row = 0; row < 3; row++) {
               int count =0;
               for (int delta = 0; delta < 4; delta++) { //number to go through diagonal
                  int x1 = row + delta;
                  int y1 = col - delta;
               
                  if (board.get(x1,y1) == board.get(x1-1,y1+1)&&board.get(x1,y1) != null) //checks for 3 in a row
                     count++;
                  else
                     count = 1;
               
               
                  if (count >= 3) {
                     int tempY1 = y1;
                     int tempX1 = x1;
                     tempX1++;
                     tempY1--;
                     if (tempX1 < 6 && tempY1 < 7 ) {
                        if(tempX1==5){ //checks if chip is on the bottom or not
                           if (board.get(tempX1,tempY1) == null && isbotsTurn()){
                              board.add(tempX1,tempY1,'Y'); //adds piece
                              turn++;
                           }
                        }
                        else
                           if(board.get(tempX1+1,tempY1) !=null){ //if chip isn't at row 5 makes sure it isnt floating
                              if (board.get(tempX1,tempY1) == null && isbotsTurn()){
                                 board.add(tempX1,tempY1,'Y'); //adds piece
                                 turn++;
                              }
                              
                           }
                     }
                  }
               }
            }
         }
      }
      
       
                
      
      if(isbotsTurn()){ //picks random column to place the piece 
         int col = (int)(Math.random() * 7); //picks random column between 0-6
         int row = 0;
         if(board.get(0,col)== null){
            for(int r = 0; r < 6;r++){
               if(board.get(r,col) == null){
                  row = r;
               }
            }
         }
      
         board.add(row,col,'Y'); //adds the random piece at column chosen before
         turn++;
      }
   }
   
   public static boolean isbotsTurn(){//checks for if it's the bots turn or not
      if(turn%2 == 0){
         return false;
      }
      else
         return true;
   }
   

   public static void main(String[] arg)
   {
      SparseMatrix<Character> board = new SparseMatrix(6, 7);
      char player = 'R'; //Player is R, AI is yellow
      int nameNum = 0;
      String name = "";
      Scanner input = new Scanner(System.in);
      
      if(nameNum == 0){ //Lets player pick what they want to call themselves,I prefer meme overlorde
         System.out.println("Enter your name");
         name = input.nextLine();
         nameNum = 1; //makes sure the player won't be asked for name till next game
      }
   
      while(!hasWon(board))
      {   
         System.out.println(board);					//show the contents of the board before the last piece of the game is placed
         takeTurn(board, player,name);
         if(!hasWon(board)){//makes sure the game isn't over so the AI doesn't go
            turn++; //changes the turn to the bots turn
            AI(board); //AI either wins,blocks, or places a random piece
         }
      }
      System.out.println(board); //shows the board after the game is over to show who won
   }
}
