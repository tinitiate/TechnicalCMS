package technical.cms;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Governer {

	// Public Constants
	public static String FolderYaml = "folder.yaml";

	
	// =================================================================================================================
	// UTIL Methods for the Governer
	// =================================================================================================================
	// Convert a string to folder name string, [Append the "\" if missing]
	public String String2Folder(String p_folderName_str) {

		String l_LastChar = p_folderName_str.substring(p_folderName_str.length() - 1);
		String l_FolderName = (l_LastChar == "\\" || l_LastChar == "/") ? p_folderName_str : p_folderName_str + File.separator;
		return l_FolderName;
	}

	
	// Extract the YamlDesc from the YAML head
	public String GetYAMLDesc(Map<String, Object> p_yaml_md_map) {

		return ((String) (p_yaml_md_map.get("YamlDesc"))).trim();
	}

	
	
	// Content.Article BEAN
	public Table<String, Integer, String> ContentArticleProcessor(Map<String, Object> p_yamlmd_map) {

		TiCMSContentArticleBean ContentArticleBean = new TiCMSContentArticleBean(p_yamlmd_map);
		return ContentArticleBean.GetWebPageDS();
	}

	
	// Content.Group BEAN
	public Table<String, Integer, String> ContentGroupProcessor(Map<String, Object> p_yamlmd_map) {

		TiCMSContentGroupBean ContentCodeBean = new TiCMSContentGroupBean(p_yamlmd_map);
		return ContentCodeBean.GetWebPageDS();
	}

	
	// Build the final WebPageDS
	public Map<String, Object> WebFileDataBuilder(Table<String, Integer, String> pFileWebPageDS, Map<String, String> pMasterData) {
		
		// Create the WebPageDS Object
		Table<String, Integer, String> lWebPageDS = pFileWebPageDS;
		String l_home_url = "";
		String l_slug_url = "";
		String l_content_url = "";
		String l_breadCrumb_JSON = "";
		String l_FileName = "";
		Map<String, Object> WebFileData = new HashMap<String, Object>();
		
		l_home_url = pMasterData.get("HomeURL");
		l_slug_url = l_home_url + "/" + pMasterData.get("Slug") + "/";
		l_content_url = l_slug_url + lWebPageDS.get("ContentName", 1) + ".html";

		l_breadCrumb_JSON = "[" + "{ \"url\": " + "\"" + l_home_url + "\"" + ",\"display\": \"home\" },"
				+ "{ \"url\": " + "\"" + l_slug_url + "\"" + ",\"display\": \"" + pMasterData.get("Slug") + "\" }," + "{ \"url\": "
				+ "\"" + l_content_url + "\"" + ",\"display\": \"" + lWebPageDS.get("ContentName", 1) + "\" }" + "]";

		// System.out.println(l_breadCrumb_JSON);
		lWebPageDS.put("BreadCrumbJSON", 1, l_breadCrumb_JSON);

		// Prep File Name and generate data
		l_FileName = String2Folder(pMasterData.get("StaticSiteDir")) + lWebPageDS.get("ContentName", 1) + ".html";

		// Final WebFile Data Map
		WebFileData.put(l_FileName, lWebPageDS);

		// System.out.println(WebFileData);
		// System.out.println(l_FileName);

		// Create HTML files using the theme generator
		// TiCMSBasicBSTheme o1 = new TiCMSBasicBSTheme(WebFileData);

		// Reset WebFileData
		// WebFileData = new HashMap<String, Object>();
		
		return WebFileData;
	}
	
	// This method processes the files that are optional to the TiCMSj engine
	// These are the CONTENT-CODE and CONTENT-ARTICLE YAMLHEAD+MD files
	public Map<String, Object> Fileprocessor(String p_filename, Map<String, String> pMasterData) {

		// System.out.println(p_filename);
		YamlMDExtractor x = new YamlMDExtractor(p_filename);
		Table<String, Integer, String> WebPageDS = HashBasedTable.create();

		// process YAML data for CODE-FILE into Bean
		if (GetYAMLDesc(x.l_yaml_html_map).toUpperCase().equals("CONTENT-GROUP")) {

			// Add all group elements to a list
			WebPageDS = ContentGroupProcessor(x.l_yaml_html_map);

		}
		// process YAML data for ARTICLE into Bean
		else if (GetYAMLDesc(x.l_yaml_html_map).toUpperCase().equals("CONTENT-ARTICLE")) {
		   
		   // System.out.println(x.l_yaml_html_map);
			WebPageDS = ContentArticleProcessor(x.l_yaml_html_map);
		}
		
		return WebFileDataBuilder(WebPageDS, pMasterData);

	}

	
	// Process files to see the folder level info and the FILEs YAMLHead MD data
	// in the folder and pass every file to the renderer
	public void FolderProcessor(String p_dirname, Map<String, String> pMasterData) {

		// STEP 1
		// ======
		// Process the folder.yaml in each folder
		YamlMDExtractor FolderYamlMap = new YamlMDExtractor(String2Folder(p_dirname) + FolderYaml);
		TiCMSFolderBean FolderBean = new TiCMSFolderBean( FolderYamlMap.l_yaml_html_map);
		String l_yamlmd_file = null;
		// Update the MasterData Map
		pMasterData.put("Slug", FolderBean.Slug);

		
		// STEP 2
		// ======
		// From the ProcessFilePathList read all items and process files
		for (String l_FileName : FolderBean.ProcessFilePathList) {

		    System.out.println(l_FileName);
			// Read all files from the folder, except the mandatory files
			// Attempt to read all files in the folder and generate "YamlHeadMDBody"
			l_yamlmd_file = String2Folder(p_dirname) + l_FileName;
			File file = new File(l_yamlmd_file);
			
			if (file.isFile()) {

				// System.out.println(l_yamlmd_file);
				// Pass the WebFileData to the Theme
				// Fileprocessor(l_yamlmd_file, pMasterData);

				// Theme processor
				if (((String)pMasterData.get("Theme")).equals("tinitiate-bs3-basic")) {
					
					TiCMSBasicBSTheme GenFile = new TiCMSBasicBSTheme(Fileprocessor(l_yamlmd_file, pMasterData));					
				}
				
			}
		} 
	}	


	// =================================================================================================================
	// CONSTRUCTOR
	// =================================================================================================================

	// Constructor for Automatic run
	public Governer(Map<String, Object> p_yaml_html_data) {

		// Create a Bean with the POJO Map to Object conversion
		// inside the constructor
		TechnicalBean bean = new TechnicalBean(p_yaml_html_data);

		// Upstream CMS level master Data
		Map<String, String> MasterData = new HashMap <String, String>();
		MasterData.put("HomeURL", (String)bean.HomeURL);
		MasterData.put("StaticSiteDir", (String)bean.StaticSiteDir);
		MasterData.put("Theme", (String)bean.Theme);
		
		
		// Process folder list info from the TiCMSj.YAML
		if (!bean.ProcessFolders.isEmpty()) {

			for (String l_folderName : bean.ProcessFolders) {

				FolderProcessor(l_folderName, MasterData);
				// System.out.println(l_folderName);
			}
		}
	}

// END CLASS
}
