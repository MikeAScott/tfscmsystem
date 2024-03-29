package fitnesse.wiki.cmSystems;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TfsCmSystemTest {

  protected String fileName = null;
  protected Map<String, String> returnMap;
  protected List<String> expectations;

  @Before
  public void setUp() throws Exception {
    fileName = "file/under/test";
    returnMap = new HashMap<String, String>();
    expectations = new ArrayList<String>();
    TfsCmSystemDouble.reset();
  }

  @Test
  public void cmEditUnversionedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,createUnversionedPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmEdit(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmEditUnopenedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createUnopenedPropertiesResponse(fileName));
    appendEditCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmEdit(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmEditEditingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createEditPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmEdit(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmEditAddingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createAddPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmEdit(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmEditDeletedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createDeleteFolderPropertiesResponse(fileName));
    appendUndoCommandToResponseAndExpectation(fileName);
    appendEditCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmEdit(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateUnversionedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createUnversionedPropertiesResponse(fileName));
    appendAddCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateUnopenedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createUnopenedPropertiesResponse(fileName));
    appendEditCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateEditingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createEditPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateAddingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createAddPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateDeletedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createDeleteFolderPropertiesResponse(fileName));
    appendUndoCommandToResponseAndExpectation(fileName);
    appendEditCommandToResponseAndExpectation(fileName + "*");
    appendStatusCommandToResponseAndExpectation(fileName + "/content.txt",
            createEditPropertiesResponse(fileName + "/content.txt"));
    
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");
    TfsCmSystemDouble.cmUpdate(fileName + "/content.txt", "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdatePreviouslyDeletedFolder() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createPreviouslyDeletedFolderPropertiesResponse(fileName));
    appendAddCommandToResponseAndExpectation(fileName);
    
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }
  
  @Test
  public void cmUpdatePreviouslyDeletedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createPreviouslyDeletedFilePropertiesResponse(fileName));
    appendAddCommandToResponseAndExpectation(fileName);
    
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }
 
  @Test
  public void cmUpdateFileWithDeletionIdOfZero() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createDeletedIdZeroFilePropertiesResponse(fileName));
    appendEditCommandToResponseAndExpectation(fileName);
    
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }
  
  @Test
  public void cmDeleteUnversionedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createUnversionedPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteUnopenedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createUnopenedPropertiesResponse(fileName));
    appendRecursiveUndoCommandToResponseAndExpectation(fileName);
    appendDeleteCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteEditingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createEditPropertiesResponse(fileName));
    appendRecursiveUndoCommandToResponseAndExpectation(fileName);
    appendDeleteCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteAddingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createAddPropertiesResponse(fileName));
    appendRecursiveUndoCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteDeletedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName, createDeleteFolderPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }
  
  @Test
  public void cmPreDeleteDoesNothing() throws Exception {
	 TfsCmSystemDouble.cmPreDelete(fileName, "");
     assertSentRequests(expectations, TfsCmSystemDouble
	            .getRecordedCommands());
  }

  protected void assertSentRequests(List<String> expectations,
      List<String> recordedCommands) {
    assertEquals(expectations.size(), recordedCommands.size());

    int i = 0;
    for (String expectedCommand : expectations) {
      assertEquals(expectedCommand, recordedCommands.get(i));
      i++;
    }
  }

  protected String createUnversionedPropertiesResponse(String fileName2) {
    return "No items match " + fileName2 + "\n";
  }

  protected String createUnopenedPropertiesResponse(String file) {
    return getPropertyResponse(file, "file", "none", true, true, false, 0);
  }

private String getPropertyResponse(String file, String type, String change, boolean onServer, boolean inWorkspace, boolean deletedOnServer, int deletedOnServerVersion) {
	String localPath = inWorkspace? " " + new File(file).getAbsolutePath():"";
	String serverPath = onServer? " $/DEV/project" + file.replace('\\', '/'):""; 
	String deleteKey = deletedOnServer? "  Deletion ID : " + deletedOnServerVersion + "\n" :"";

	String response =  "Local information:\n" +
		   "  Local path :" + localPath + "\n" +
		   "  Server path:"  + serverPath + "\n"+
    	   "  Change     : " + change + "\n" +
    	   "  Type        : "+type+"\n" +
    	   "Server information:\n" + 
    	   "  Server path :" + serverPath + "\n" +
    	   deleteKey +
    	   "  Lock        : none\n" +
    	   "  Type        : file\n";
	System.out.println(response);
	return response;

}

  protected String createEditPropertiesResponse(String file) {
	  return getPropertyResponse(file, "file", "edit", true, true, false, 0);
  }

  protected String createAddPropertiesResponse(String file) {
	  return getPropertyResponse(file, "file", "add", false, true, false,0);
  }

  protected String createDeleteFolderPropertiesResponse(String file) {
	  return getPropertyResponse(file,"folder", "delete", true, false, false,0);
  }

  protected String createDeleteFilePropertiesResponse(String file) {
	  return getPropertyResponse(file,"file", "none", true, false, false,0);
  }
  
  protected String createPreviouslyDeletedFolderPropertiesResponse(String file) {
	  return getPropertyResponse(file,"folder", "none", false, false, true,8976);
  }

  protected String createPreviouslyDeletedFilePropertiesResponse(String file) {
	  return getPropertyResponse(file,"file", "none", false, false, true, 8765);
  }

  protected String createDeletedIdZeroFilePropertiesResponse(String file) {
	  return getPropertyResponse(file,"file", "none", false, false, true, 0);
  }
  
  protected String statusCommand(String file) {
    return "tf properties " + file;
  }

  protected void appendAddCommandToResponseAndExpectation(String fileName) {
	String  cmdResponse = getCommandResponse(fileName, "");
    returnMap.put(("tf add " + fileName), cmdResponse);
    expectations.add(("tf add " + fileName));
  }

  protected void appendUndoCommandToResponseAndExpectation(String fileName) {
	String cmdResponse = getCommandResponse(fileName,"Undoing edit: "); 
    returnMap.put(("tf undo " + fileName + " /noprompt"), cmdResponse);
    expectations.add(("tf undo " + fileName + " /noprompt"));
  }

  protected void appendRecursiveUndoCommandToResponseAndExpectation(String fileName) {
	String cmdResponse = getCommandResponse(fileName,"Undoing edit: "); 
    returnMap.put(("tf undo " + fileName + " /recursive /noprompt"), cmdResponse);
    expectations.add(("tf undo " + fileName + " /recursive /noprompt"));
 }

  
  protected void appendEditCommandToResponseAndExpectation(String fileName) {
	String  cmdResponse = getCommandResponse(fileName, "");
    returnMap.put(("tf edit " + fileName), cmdResponse);
    expectations.add(("tf edit " + fileName));
  }
  
  private String getCommandResponse(String fileName, String action){
	  File file = new File(fileName);
	  File dir = file.getParentFile();
	  if (dir == null)
		  return fileName;
	  return dir.getPath() + ":\n" + action + file.getName();
  }

  protected void appendDeleteCommandToResponseAndExpectation(String fileName) {
	String  cmdResponse = getCommandResponse(fileName, "");
    returnMap.put(("tf delete " + fileName),cmdResponse);
    expectations.add(("tf delete " + fileName));
  }

  protected void appendStatusCommandToResponseAndExpectation(String filename,
      String propertiesResponse) {
    String fstatCommand = statusCommand(filename);
    returnMap.put(fstatCommand, propertiesResponse);
    expectations.add(fstatCommand);
  }
}
