package technical.cms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

// POJO for the TiCMSj Config YAML File
// Capture all the required initial settings with this bean
public class TechnicalBean implements Serializable {

	// Config File
	public String YamlDesc;
	public String Theme;
	public String HomeURL;
	public String RootDir;
	public String GenerateTemplateFlag;
	public String StaticSiteDir;
	public List<String> ProcessFolders;

	// POJO bean data assignment using the constructor
	public TechnicalBean(Map<String, Object> p_data) {

		// Non array Bean elements
		YamlDesc = (String) p_data.get("YamlDesc");
		Theme = (String) p_data.get("Theme");
		HomeURL = (String) p_data.get("HomeURL");
		RootDir = (String) p_data.get("RootDir");
		StaticSiteDir = (String) p_data.get("StaticSiteDir");

		// Read and assign the LIST values
		ProcessFolders = (List<String>) p_data.get("ProcessFolders");
	}
}
