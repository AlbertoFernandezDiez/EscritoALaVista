package packTestConversor;

public class FilePath {
private static FilePath myFilePath = null;
private String path;

private FilePath(){}

public  static FilePath getMyFilePath(){
	if(myFilePath == null)
		myFilePath = new FilePath();
	return myFilePath;
}

public String getPath(){
	return path;
}

public void setPath(String pPath){
	path = pPath;
	System.out.println(pPath);
}

}
