package technical.cms;

public class RapidCreator {

	// Logging setup
	// private static final Log log = LogFactory.getLog(RapidCreator.class);
   // log.error("Example error message ..");

	// This is the main function that starts the program to execute the process
	public static void main(String[] args) {

		// STEP 1
		// ======
		// First Parameter from the command line argument
		// Read the technical.yaml data
		YamlMDExtractor YamlMDMap = new YamlMDExtractor(args[0]);

		// Get The theme info
		// TiCMSBasicBSTheme o1 = new TiCMSBasicBSTheme(WebFileData);

		// STEP 2
		// ======
		// Path to the TechnicalCMS YAML file
		Governer RunProcess = new Governer(YamlMDMap.l_yaml_html_map);
	}
}
