package roboSim;

public class Room {
	private String[][] entity;
	private int height,width;
	private String[][] temp;
	private String oldColor="";
	
	public void setoldColor(String c) {
		this.oldColor=c;
	}
	public String getoldColor() {
		return this.oldColor;
	}
	
	public String getpositionColor(int y, int x) {
		return entity[y][x];
	}
	
	public String removedoodads(int y, int x) {
		String colour= entity[y][x];
		entity[y][x]="\uD83D\uDD33";
		this.temp = copyArray(this.entity);
		return colour;
	}
	public void adddoodads(int y, int x,String c) {
		entity[y][x]=c;
		this.temp = copyArray(this.entity);
	}
	
	
	
	
	private static String[][] copyArray(String[][] original) {
        int height = original.length;
        int width = original[0].length;

        // Create a new array with the same dimensions
        String[][] copy = new String[height][width];

        // Copy each element from the original to the new array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }

	public Room(int height, int width) {
		
		entity = new String[height][width];
		this.width=width;
		this.height=height;
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	entity[i][j] = "\uD83D\uDD33";
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                	entity[i][j] = "\uD83E\uDDF1";
                }
            }
        }
        
        
		int total_play_area=height*width;
		int numofobsticals=total_play_area/10;
		Obstacle o= new Obstacle();
		for(int i=0;i<numofobsticals;i++) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]=o.toString();	  
	        	}
	        else {
	        	i--;
	        }
		}
		
		Goal g = new Goal();
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]=g.toString();
	        	break;
	        	}
		}
		//place 3 colour box
		//red
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]="\uD83D\uDFE5";
	        	break;
	        	}
		}
		
		//blue
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]="\uD83D\uDFEA";
	        	break;
	        	}
		}
		
		//green
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]="\uD83D\uDFE9";
	        	break;
	        	}
		}
		
		//doodads
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]="\uD83D\uDD34";
	        	break;
	        	}
		}
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]="\uD83D\uDD35";
	        	break;
	        	}
		}
		while(true) {
			int x=Simulator.randomNumber(width);
	        int y=Simulator.randomNumber(height);	        
	        if(entity[y][x].equals("\uD83D\uDD33")) {	        	
	        	entity[y][x]="\uD83D\uDD37";
	        	break;
	        	}
		}
		
		 this.temp = copyArray(this.entity);		
	}
	
	private void initiate() {
		this.entity = copyArray(this.temp);
	}
	
	public void display() {
		for (int i = 0; i < entity.length; i++) {
            for (int j = 0; j < entity[0].length; j++) {
                System.out.print(entity[i][j]);
            }
            System.out.println(); // Move to the next line after each row
        }
	}
	
	public int display(Robot r) {
		initiate();
		
		if(entity[r.getY()][r.getX()].equals("\uD83D\uDD33") ||entity[r.getY()][r.getX()].equals("\uD83D\uDFE9") || entity[r.getY()][r.getX()].equals("\uD83D\uDFE5") || entity[r.getY()][r.getX()].equals("\uD83D\uDFEA") || entity[r.getY()][r.getX()].equals("\uD83D\uDD34") || entity[r.getY()][r.getX()].equals("\uD83D\uDD35") || entity[r.getY()][r.getX()].equals("\uD83D\uDD37")) {
			setoldColor(entity[r.getY()][r.getX()]);
			entity[r.getY()][r.getX()]=r.toString();
			for (int i = 0; i < entity.length; i++) {
	            for (int j = 0; j < entity[0].length; j++) {
	                System.out.print(entity[i][j]);
	            }
	            System.out.println(); // Move to the next line after each row
	        }
			return 1;
		}
		else if(entity[r.getY()][r.getX()].equals("\u2764\uFE0F")) {
			r.increseScore(10);
			System.out.println("You won the Game. Score: "+r.getScore());			
			return 0;
		}
		else {
			System.out.println("Collition. Can not move robot");
			r.undo();
			return 2;
		}
		
	}
	
	
	

}
