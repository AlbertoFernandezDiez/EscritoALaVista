package packCordova;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

public class Encoder {
	private static Encoder myEncoder = null;
	
	private Encoder(){}
	
	public static Encoder getMyEncoder(){
		if (myEncoder == null)
			myEncoder = new Encoder();
		
		return myEncoder;
	}
	
	public String encodeInBase64(File pFile) throws FileNotFoundException, IOException{
		byte[] aux = IOUtils.toByteArray( new FileInputStream(pFile));
		
		String base64String = Base64.encodeBase64String(aux);
		
		return "data:image/" + getExtension(pFile) + ";base64," + base64String;
		
	}

	private String getExtension(File pFile) {
		// TODO Auto-generated method stub
		return FilenameUtils.getExtension(pFile.getAbsolutePath());
	}

}
