package hearts;

class GameException extends RuntimeException{
  private static final long serialVersionUID = 1L;
  static RuntimeException noCardFound=new RuntimeException("No card found!");
  static RuntimeException exceedMaxPlayers=new RuntimeException("Too many players!");
  
  GameException(){
    this("Exception in this game.");
  }
  
  GameException(String msg){
    super(msg);
  }
}
