package technical.cms;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TiCMSContentGroupBean {

	public String YamlDesc;
	public String Title;
	public String MetaDescription;
	public String MetaKeywords;
	public String Author;
	public String ContentName;
	public String GroupTemplate;
	public String GroupName;
	public String GroupSeqNum;
	public String GroupElementDesc;
	public String HTML;

	// The WebPageDS
	public Table<String, Integer, String> WebPageDS = HashBasedTable.create();

	// Generate the WebPageDS
	public Table<String, Integer, String> GetWebPageDS() {

		WebPageDS.put("Title", 1, Title);
		WebPageDS.put("MetaDescription", 1, MetaDescription);
		WebPageDS.put("MetaKeywords", 1, MetaKeywords);
		WebPageDS.put("Author", 1, Author);
		WebPageDS.put("ContentName", 1, ContentName);
		WebPageDS.put("GroupTemplate", 1, GroupTemplate);
		WebPageDS.put("GroupName", 1, GroupName);
		WebPageDS.put("GroupSeqNum", 1, GroupSeqNum);
		WebPageDS.put("GroupElementDesc", 1, GroupElementDesc);
		WebPageDS.put("HTML", 1, HTML);

		return WebPageDS;
	}

	// POJO bean data assignment using the constructor
	public TiCMSContentGroupBean(Map<String, Object> p_indexmd) {

		// System.out.println(p_indexmd);
		// Extract and assign Yaml data to bean variables
		YamlDesc = (String) p_indexmd.get("YamlDesc");

		if (YamlDesc.equals("CONTENT-GROUP")) {
			Title = (String) p_indexmd.get("Title");
			MetaDescription = (String) p_indexmd.get("MetaDescription");
			MetaKeywords = (String) p_indexmd.get("MetaKeywords");
			Author = (String) p_indexmd.get("Author");
			ContentName = (String) p_indexmd.get("ContentName");
			GroupTemplate = (String) p_indexmd.get("GroupTemplate");
			GroupName = (String) p_indexmd.get("GroupName");

			GroupSeqNum = (String) p_indexmd.get("GroupSeqNum");
			GroupElementDesc = (String) p_indexmd.get("GroupElementDesc");
			HTML = (String) p_indexmd.get("HTML");
		}
	}
}
