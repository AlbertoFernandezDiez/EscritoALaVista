package packCordova;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import javax.imageio.ImageIO;

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

	public String decodeInBase64 (String imageString, String path){
		byte[] aux = Base64.decodeBase64(imageString);
		File outputFile = new File(path,"imagenes/" + System.currentTimeMillis() + ".jpg");
		FileOutputStream writer = null;
		/*try {
			writer = new FileOutputStream(outputFile);
			
			for (byte towrite : aux){
				writer.write(towrite);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			writer = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.write(aux);
			writer.close();
			System.out.println(outputFile.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "imagenes/"+outputFile.getName();
	}
	
	private String getExtension(File pFile) {
		// TODO Auto-generated method stub
		return FilenameUtils.getExtension(pFile.getAbsolutePath());
	}

}
