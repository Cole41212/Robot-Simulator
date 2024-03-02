package roboSim;
import java.util.Scanner;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Simulator {
   
    public static int randomNumber(int n) {
        Random random = new Random();
        return random.nextInt(n-2)+1;
    }
    

    public static void main(String[] args) {
      	
        System.out.println("Robot Simulator");
        System.out.println("Navigate to the goal (heart) while collecting doodads for points and avoiding obstacles!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the width: ");
        int width = scanner.nextInt();
        System.out.print("Enter the height: ");
        int height = scanner.nextInt();
        
        Room room= new Room(height,width);
    	room.display();


      
        scanner.nextLine(); // Consume the newline character
        System.out.println("Press enter to begin");
        scanner.nextLine(); // Wait for enter
       
        int x=randomNumber(width);
        int y=randomNumber(height);
        Robot r = new Robot(y,x);
        room.display(r);
        
             
        boolean loop=true;
        boolean isvaid=true;

        
        
        Stack<String> commandStack = new Stack<>();
        Queue<String> stringQueue = new LinkedList<>();
        
        
        while(loop) {
        	System.out.println("Commands for the robot- forward, reverse, turn left, turn right, undo, rewind, batch, collect, deposit and quit");
        	System.out.print("Enter command: ");
            String userInput = scanner.nextLine();
            if(userInput.equals("forward")) {
            	System.out.println("Robot moves forward");
            	r.forward();            	
            	
            }else if(userInput.equals("reverse")){
            	System.out.println("Robot moves reverse");
            	r.reverse();
            	
            }else if(userInput.equals("turn left")){
            	r.turnLeft();            	
            	System.out.println("Robot moves turn left");
            	
            }else if(userInput.equals("turn right")){
            	r.turnRight();
            	System.out.println("Robot moves turn right");
            	
            }else if(userInput.equals("undo")){
            	r.undo();
            	System.out.println("Robot moves undo");
            	
            }else if(userInput.equals("rewind")){
            	
            	System.out.println("Robot moves rewind");
            	while (!commandStack.isEmpty()) {
                    String poppedString = commandStack.pop();
                    if(poppedString.equals("forward")) {
//                    	System.out.println("Robot moves forward");
                    	r.reverse();           	
                    	
                    }else if(poppedString.equals("reverse")){
//                    	System.out.println("Robot moves reverse");
                    	r.forward();
                    	
                    }else if(poppedString.equals("turn left")){
                    	r.turnRight();         	
//                    	System.out.println("Robot moves turn left");
                    	
                    }else if(poppedString.equals("turn right")){                  	
                    	r.turnLeft();
//                    	System.out.println("Robot moves turn right");
                    	
                    }
                    
                }
            	isvaid=false;
            	
            }else if(userInput.equals("batch")){
            	System.out.println("Enter commands to add to the queue (type 'exit' to stop):");
            	while (true) {
                    System.out.print("Enter a command: ");
                    String batchCommand = scanner.nextLine();
                    if (batchCommand.equalsIgnoreCase("exit")) {
                        break;
                    }
                    stringQueue.offer(batchCommand);
                }
            	while (!stringQueue.isEmpty()) {
                    String element = stringQueue.poll();
                    if(element.equals("forward")) {
//                    	System.out.println("Robot moves forward");
                    	r.forward();            	
                    	
                    }else if(element.equals("reverse")){
//                    	System.out.println("Robot moves reverse");
                    	r.reverse();
                    	
                    }else if(element.equals("turn left")){
                    	r.turnLeft();            	
//                    	System.out.println("Robot moves turn left");
                    	
                    }else if(element.equals("turn right")){
                    	r.turnRight();
//                    	System.out.println("Robot moves turn right");
                    	
                    }
                    
                }
            	isvaid=false;
            	
            }
            else if(userInput.equals("collect")){
            	String color=room.getoldColor();
            	System.out.println("Color: " +color);
            	if(color.equals("\uD83D\uDD34") || color.equals("\uD83D\uDD35") ||color.equals("\uD83D\uDD37") ) {
                	System.out.println("doodle ");
            		r.collect(color);
            		room.removedoodads(r.getY(), r.getX());           		
            	}           	
            }
            else if(userInput.equals("deposit")){
            	String color=room.getoldColor();
            	
            	if(color.equals("\uD83D\uDFE5") || color.equals("\uD83D\uDFEA") || color.equals("\uD83D\uDFE9")) {
            		if(r.getcolor().equals(color)) {
            			r.increseScore(2);
            			r.diposit();
            		}else {
            			r.increseScore(-1);
            			r.diposit();
            		}
            	}
            	else {
            		room.adddoodads(r.getY(), r.getX(), r.getcolor());
            		r.diposit();
            	}
            }
            else if(userInput.equals("quit")){
            	loop=false;;
            	
            }else {
            	System.out.println("Invalid Command");
            	isvaid=false;
            }
            int t=room.display(r);
            if(t==0) {
            	loop=false;
            }
            if(t==1 && isvaid) {
            	commandStack.push(userInput);
            }
            isvaid=true;
        }

        scanner.close();
        System.out.println("""
                Thanks for using the Robot Simulator!
                Made by Cole Brink
                CMP220L
                Prof. Johnson""");
 
    }
}
