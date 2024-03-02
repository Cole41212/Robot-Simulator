package roboSim;

public class Robot {
	
	private String avater="\uD83D\uDC46";
	private int dir=0;
	private int x,y;
	private int temp_x,temp_y,temp_dir=dir;
	private int score=0;
	private boolean iscollect=false;
	private String color="\\uD83D\\uDD33";
	
	public void collect(String c) {
		this.iscollect=true;
		this.color=c;
	}
	public String getcolor() {
		return this.color;
	}
	
	public void diposit() {
		this.iscollect=false;
		this.color="\\uD83D\\uDD33";
	}
	
	public int getScore() {
		return score;
	}
	
	public void increseScore(int x) {
		this.score=this.score+x;
	}

	public Robot(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void forward() {
		this.temp_x=this.x;
		this.temp_y=this.y;
		if(dir==1) {
    		this.x++;
    	}else if(dir==3){
    		this.x--;
    	}else if(dir==0){
    		this.y--;
    	}else if(dir==2){
    		this.y++;
    	}
	}
	
	public void reverse() {
		this.temp_x=this.x;
		this.temp_y=this.y;
		if(dir==1) {
    		this.x--;
    	}else if(dir==3){
    		this.x++;
    	}else if(dir==0){
    		this.y++;
    	}else if(dir==2){
    		this.y--;
    	}
	}
	
	
	public void turnRight() {
		temp_dir=dir;
    	dir++;
    	dir=dir%4;
    	
	}
	public void turnLeft() {
		temp_dir=dir;
		dir--;
    	if(dir<0) {
    		dir=3;
    	}
    	dir=dir%4;
    	
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public void undo() {
		this.x=this.temp_x;
		this.y=this.temp_y;
		this.dir=this.temp_dir;
	}
	
	
	public String toString() {
		if(dir==0) {
			return this.avater;
		}else if(dir==1) {
			return "\uD83D\uDC49";
		}else if(dir==2) {
			return "\uD83D\uDC47";
		}
		return "\uD83D\uDC48";
	}

	

}
