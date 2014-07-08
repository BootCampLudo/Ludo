public class Token {
	private boolean isfree;
	private int iscomplete;
	private int position;
	private int playerId;
	private int stepsMoved;
	public Token(int playerId) {
		
		this.playerId = playerId;
		this.position = playerId * 12;
		this.isfree = false ;
		this.iscomplete = false;
		this.stepsMoved = 0;
		
	}
	public int getPlayerId(){
		return playerId;
	}
	public boolean canMove(int steps) {
		return (isfree && (stepsMoved + steps) > 60 &&  )
		return (coordinate - playerId * 13 + steps <= 60  && state != GAME_CONFIG.LOCKED) || (state == GAME_CONFIG.LOCKED && steps == 6);
	}
	public void reset(){
		coordinate = playerId * 12;
		isfree = false;
	}
	public boolean moveToken(int steps) {
		if (!canMove(steps))
			return false;
		coordinate = coordinate + steps;
		if (coordinate - playerId*13 == 60) {
			state = GAME_CONFIG.FINISHED;
		}else if(coordinate - playerId*13 >=55) {
			state = GAME_CONFIG.SAFE;
		}
		return true;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return this.id;
	}
	public int getCoordinate() {
		return coordinate;
	}
	public int getState() {
		return state;
	}
	
}
