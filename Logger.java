package samsung_assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;

public class Logger implements Runnable {
	static Semaphore semaphore = new Semaphore(1);
	String severity;
	String logtext;
	String date;
	String time;
public Logger(String severity,String logtext) {
   this.severity=severity;
   this.logtext = logtext;
   this.date = LocalDate.now().toString();
   this.time = LocalTime.now().toString();
   System.out.println(this.date+":"+this.time);
}

public void run() {
	try {
		semaphore.acquire();
		
		  File output_file = new File("C:\\Users\\spala\\"+this.date + ".txt");
		   if(!output_file.exists()) {
			   try {
				output_file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		   }
		   try {
			FileWriter file1 = new FileWriter(output_file,true);
			file1.write(this.time+":"+this.severity+":"+this.logtext);
			file1.write("\n");
			file1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		   
		   
		   semaphore.release();
		   
		   
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 	   }
}