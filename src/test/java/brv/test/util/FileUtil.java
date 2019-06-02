package brv.test.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

	private static String resourcesPath = "src/test/resources/";
	
	/**
	 * Obtains the file content.
	 * The path must be relative to src/test/resources path.
	 * @param relativePath
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public String getFile(String relativeFilepath) throws URISyntaxException, IOException {
		
		String content = "";
	    try {
	        content = new String ( Files.readAllBytes( Paths.get(resourcesPath+relativeFilepath) ) );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return content;
	}
}
