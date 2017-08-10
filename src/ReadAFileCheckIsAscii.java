
import java.util.*;
import java.io.*;
import org.apache.commons.lang3.*;

public class ReadAFileCheckIsAscii {
	public static void main(String args[])
	{
		try {
	      BufferedReader in = new BufferedReader(new FileReader(args[0]));
          int lineCnt = 0;
          String str;
          while ((str = in.readLine()) != null)
          {
            lineCnt++;
            if (StringUtils.isAsciiPrintable(str) == false) {
              System.out.println("Bad data at: " + Integer.toString(lineCnt) + " " + str);
            }
          }
          in.close();
          System.out.println("read: " + Integer.toString(lineCnt));
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
			return;
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
	}
}
