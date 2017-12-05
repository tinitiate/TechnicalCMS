package technical.cms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TiCMSBasicBSTheme {

	public static String l_content_template = "content.ftl";

	// Read theme.yaml to get the required paths and setting details
	public static void GetDetails() {

		// Read the theme.yaml from the themes folder path from the ticms.yaml
		// file
	}

	public static void GenerateContentFile(String p_file_name, Map<String, Object> p_web_data) {

		// Freemarker configuration object
		Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File("E:\\Bitnami\\wampstack\\apache2\\htdocs\\tinitiate\\technicalcms\\themes\\tinitiate-bs3-basic\\templates\\"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		try {
			// Load template from source folder
			Template template = cfg.getTemplate(l_content_template);

			// Console output
			// Writer out = new OutputStreamWriter(System.out);
			// template.process(p_web_data, out);
			// out.flush();

			// File output
			Writer file = new FileWriter(new File(p_file_name));
			template.process(p_web_data, file);
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	public TiCMSBasicBSTheme(Map<String, Object> p_WebFileData) {

		// Web File elements
		String WebFileName = "";
		Map<String, Object> WebFileElements = new HashMap<String, Object>();

		Table<String, Integer, String> WebPageDS = HashBasedTable.create();
		Set keys = p_WebFileData.keySet();

		for (Object key : keys) {

			WebFileName = (String) key;
			WebPageDS = (Table<String, Integer, String>) p_WebFileData.get(key);

			// Load WebFileElements for Content Article
			WebFileElements.put("Title", WebPageDS.get("Title", 1));
			WebFileElements.put("MetaDescription", WebPageDS.get("MetaDescription", 1));
			WebFileElements.put("MetaKeywords", WebPageDS.get("MetaKeywords", 1));
			WebFileElements.put("Author", WebPageDS.get("Author", 1));
			WebFileElements.put("HTML", WebPageDS.get("HTML", 1));
			WebFileElements.put("BreadCrumbJSON", WebPageDS.get("BreadCrumbJSON", 1));

			// Generate File
			// System.out.println(WebFileName);
			// System.out.println(WebFileElements);
			GenerateContentFile(WebFileName, WebFileElements);
		}

	}
}