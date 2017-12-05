package technical.cms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TiLog {

	public String pLogFileName = null;
	public String pLog2Console = null;
	public String pLog2File = null;
	
	// Assign the needed logparams
	public String GenerateLogString(String pClassName, String pMessageCode, String pMessage) {
		return pClassName + pMessageCode + pMessage;
	}
	
	
	// This will write to file
	public void WriteToFile(String Content) {
		
	}

	// This will write to console
	public void WriteToConsole(String Content) {
		
	}

	
	// Log Message
	public void m(String pClassName, String pMessageCode, String pMessage) {
		
	}

	// Log Error
	public void e(String pClassName, String pMessageCode, String pMessage) {
		
	}

	// Logger constructor, return a log object with file name
	public TiLog() {
		// Generate the log file name once for each run, pass the same object to all the classes
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = new Date();
		pLogFileName = "TinitiateCMSLog" + dateFormat.format(date);
	}
}
