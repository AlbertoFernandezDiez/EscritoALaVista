package packConversor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.adobe.dp.epub.io.DataSource;

public class ImgFileDataSource extends DataSource{

    String url; 

    public ImgFileDataSource(String url) { this.url = url; } 
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		 return new FileInputStream(new File(url)); 
	}

}
