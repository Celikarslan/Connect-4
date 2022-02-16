public class Cell<anyType>{

   private anyType value;
   private int row, col;

   public Cell(int r,int c,anyType v){
      col = c;
      row = r;
      value = v;
   }

   public int getRow(){
      return row;
   }

   public int getCol(){
      return col;
   }
   public anyType getValue(){
      return value;
   }
}
