import java.util.*;

public class SparseMatrix<anyType> implements Matrixable<anyType>
{	
   private ArrayList<Cell<anyType>> list;
   private  int numElements;                 
   private int numRows, numCols;
     
   public SparseMatrix (int r, int c)   {
      list = new ArrayList();
      numElements = r*c;
      numRows = r;
      numCols = c;
   }    

   public anyType get(int r, int c){
      for(int i =0;i < list.size(); i++){
         Cell temp = list.get(i);
         if(temp.getRow() == r && temp.getCol() == c)
            return (anyType)temp.getValue();
      }
      return null;
   } 
     	
   public anyType set(int r, int c, anyType x)	{
      int tempkey = getKey(r,c);
      int listkey = 0;
      for(int i = 0; i < list.size(); i++){
         listkey = getKey(list.get(i).getRow(),list.get(i).getCol());
         if (tempkey == listkey){
            return (anyType)(list.set(i,new Cell(r,c,x)).getValue());
         }
      }
      return null;  
   }
   
   public void add(int r,int c, anyType x){ 
   
      int tempkey = getKey(r,c);
      int listkey = 0;
      for(int i = 0; i < list.size(); i++){
         listkey = getKey(list.get(i).getRow(),list.get(i).getCol());
         if (tempkey < listkey){
            list.add(i,new Cell(r,c,x));
            break;
         }
      }
      list.add(new Cell(r,c,x));
   }
   

   	   //adds obj at row r, col c
   public anyType remove(int r, int c){
      for(int i =0;i < list.size(); i++){
         Cell temp = list.get(i);
         if(temp.getRow() == r && temp.getCol() == c){
            list.remove(i);
            return (anyType)temp.getValue();
         }
      
      }
      return null;
   }
   
   public int size(){
      return numElements; //returns # actual elements stored
   }
      
   public int numRows(){
      return numRows;
   }
   		//returns # rows set in constructor
   public int numColumns(){
      return numCols;
   }
   	//returns # cols set in constructor
   
   public int getKey(int r, int c){
      return r * numCols + c;
   }
   
   public String toString(){
      String ans = "";
      int key = 0;
      
      for(int i = 0; i < numRows; i++){
         for( int j = 0; j < numCols; j++){
            anyType val = this.get(i,j);
            if(val != null)
               ans = ans + " " + val;
            else
               ans = ans + "- ";
         }
         ans =  ans + "\n";
      }
      
                
      return ans;
   }
   
}