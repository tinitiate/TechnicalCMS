package technical.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TiCMSFolderBean {

	// Bean Public Data
	public String YamlDesc;
	public String Slug;
	public Boolean GenerateTemplateFlag;
	public Boolean GenerateFolderNavURLNamesJS;
	public List<String> ProcessFilePathList = new ArrayList();
	
	
	// Create Later
	// if the "GenerateFolderNavURLNamesJS" is true create the FolderNav JSON data for 
	// the JS theme engine to process
	public void GenerateFolderNavJSON() {
		
	}
	
	
	// The WebPageDS
	public Table<String, Integer, String> WebPageDS = HashBasedTable.create();

	// POJO bean data assignment using the constructor
	public TiCMSFolderBean(Map<String, Object> p_data) {

		// Non array Bean elements
		YamlDesc = (String) p_data.get("YamlDesc");
		if (YamlDesc.toUpperCase().equals("FOLDER-YAML")) {
			Slug = (String) p_data.get("Slug");

			// Get Boolean value from the folder.yaml file
			// GenerateTemplateFlag =  Boolean.valueOf(((String) p_data.get("GenerateTemplateFlag")));
			// GenerateFolderNavURLNamesJS = Boolean.valueOf(((String) p_data.get("GenerateFolderNavURLNamesJS")));

	  	    // Read and assign the LIST values
			ProcessFilePathList = (List<String>) p_data.get("ProcessFilePathList");
		}
	}
//
}