package technical.cms;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.yaml.snakeyaml.Yaml;

public class YamlMDExtractor {

	// Create the Main handler MAP, with YAML and HTML data as Objects
	// "YAML":Object AND "HTML":Object(cast'able as String)
	public Map<String, Object> l_yaml_html_map = new HashMap<String, Object>();

	// Generate YAML MAP from the input string in YAML format
	public Map<String, Object> String2YAML(String p_yaml_data) {

		// YAML Object
		Yaml YObj = new Yaml();

		return (Map<String, Object>) YObj.load(p_yaml_data);
	}

	
	// Generate MD MAP from the input string in MD format
	public Map<String, Object> StringMD2HTML(String p_md_data) {

		// Using common-mart to generate HTML from MD data
		Parser parser = Parser.builder().build();
		Node document = parser.parse(p_md_data);

		HtmlRenderer renderer = HtmlRenderer.builder().escapeHtml(true).build();

		Map<String, Object> l_html_map = new HashMap<String, Object>();
		String l_html_data = renderer.render(document);

		l_html_map.put("HTML", l_html_data);

		// System.out.println(l_html_data);

		return l_html_map;
	}

	
	// Pass a file path and name, and get FileType, YAML and MD Map
	// this is a wrapper for the StringYamlMDExtractor
	public Map<String, Object> FileYamlMDExtractor1(String p_file_path_name) {

		String l_md_data = null;
		String l_yaml_data = null;
		String l_line = null;

		// return this MAP with YAML and/or MD data
		Map<String, Object> yaml_html_data = new HashMap<String, Object>();

		int l_md_start = 0;
		int l_yaml_count = 0;

		// Read the input file and extract YAML data from it
		try {
			List<String> l_fileLines = Files.readAllLines(Paths.get(p_file_path_name), StandardCharsets.UTF_8);

			Iterator itr = l_fileLines.iterator();

			while (itr.hasNext()) {
				Object string_line = itr.next();
				l_line = ((String) string_line).trim();
				// System.out.println(l_yaml_count);
				// System.out.println(l_md_start);

				if (l_line.equals("---")) {
					l_yaml_count++;
				}
				if (l_yaml_count == 2) {
					l_md_start = 1;
				}

				// Append to YAML data
				if (l_yaml_count == 1 && !l_line.equals("---")) {
					l_yaml_data = l_yaml_data == null ? l_line : l_yaml_data + "\n" + l_line;
					// l_yaml_data = l_yaml_data + "\n" + l_line;
				}

				// Append to MD data
				if (l_md_start == 1 && !l_line.equals("---")) {
					l_md_data = l_md_data == null ? l_line : l_md_data + "\n" + l_line;
					// l_md_data = l_md_data + "\n" + l_line;
				}
			}
			// Test Print to console
			// System.out.print(l_md_data);
			// System.out.print(l_yaml_data);

			// Add YAML and MD to the return MAP
			if (l_md_data != null && !l_md_data.isEmpty()) {
				yaml_html_data.putAll(StringMD2HTML(l_md_data));
			}
			if (l_yaml_data != null && !l_yaml_data.isEmpty()) {
				yaml_html_data.putAll(String2YAML(l_yaml_data));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yaml_html_data;
	}


	// Pass a file path and name, and get FileType, YAML and MD Map
	// this is a wrapper for the StringYamlMDExtractor
	public Map<String, Object> FileYamlMDExtractor(String p_file_path_name) {

		String l_md_data = null;
		String l_yaml_data = null;
		String l_line = null;
		String l_line_code = null;
		String l_comment_char = "N.A";
		List<String> CommentList = Arrays.asList("## >", "// >", "-- >");
		String l_web_code_yamlmd_start = "<!-- #";
		String l_web_code_yamlmd_end = "# -->";

		// return this MAP with YAML and/or MD data
		Map<String, Object> yaml_html_data = new HashMap<String, Object>();

		int l_md_start = 0;
		int l_yaml_count = 0;

		// Read the input file and extract YAML data from it
		try {
			List<String> l_fileLines = Files.readAllLines(Paths.get(p_file_path_name), StandardCharsets.UTF_8);

			Iterator itr = l_fileLines.iterator();

			while (itr.hasNext()) {
				Object string_line = itr.next();
				l_line_code = (String) string_line;
				l_line = ((String) string_line).trim();

				// System.out.println(l_yaml_count);
				// System.out.println(l_md_start);
				if (!l_line.equals(l_web_code_yamlmd_start) && !l_line.equals(l_web_code_yamlmd_end)) {

					// Check if the given file is a Code file
					// Identify and remove the COMMENT CHARs
					if (l_comment_char.equals("N.A")) {
						if (l_line.length() > 4) {
						   // System.out.println(l_line.substring(0, 4).trim());
							if ((CommentList.contains(l_line.substring(0, 4).trim()) ? 1 : 0) == 1) {
								l_comment_char = l_line.substring(0, 4).trim();
							}
						}
						// System.out.println(l_comment_char);
					}
					// l_line
					l_line = l_line.replaceFirst("^" + l_comment_char, "");
					l_line_code = l_line_code.replaceFirst("^" + l_comment_char, "");
					// System.out.println(l_line);
					
					if (l_line.equals("---")) {
						l_yaml_count++;
					}

					if (l_yaml_count == 2) {

						l_md_start = 1;
					}

					// Append to YAML data
					if (l_yaml_count == 1 && !l_line.equals("---")) {

						l_yaml_data = l_yaml_data == null ? l_line : l_yaml_data + "\n" + l_line;
						// l_yaml_data = l_yaml_data + "\n" + l_line;
					}

					// Append to MD data
					if (l_md_start == 1 && !l_line.equals("---")) {
						// Ignore the l_web_code_yamlmd_start and
						// l_web_code_yamlmd_end
					   // System.out.println(l_line_code);
					   
						l_md_data = l_md_data == null ? l_line_code : l_md_data + "\n" + l_line_code;
						// l_md_data = l_md_data + "\n" + l_line;
					}
				}
			}
			// Test Print to console
			// System.out.print(l_md_data);
			// System.out.print(l_yaml_data);

			// Add YAML and MD to the return MAP
			if (l_md_data != null && !l_md_data.isEmpty()) {
				yaml_html_data.putAll(StringMD2HTML(l_md_data));
			}
			if (l_yaml_data != null && !l_yaml_data.isEmpty()) {
				yaml_html_data.putAll(String2YAML(l_yaml_data));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yaml_html_data;
	}

	// Constructor, Execute based on metadata in the YAML files
	public YamlMDExtractor(String p_ticms_yaml) {

		l_yaml_html_map = FileYamlMDExtractor(p_ticms_yaml);

		// Pass the l_yaml_html_map to the Handler
		// System.out.print(l_yaml_html_map.get("yaml-desc"));
	}
}