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
    appendStatusCommandToResponseAndExpectation(fileName, createDeletePropertiesResponse(fileName));
    appendRevertCommandToResponseAndExpectation(fileName);
    appendEditCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmEdit(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateUnversionedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createUnversionedPropertiesResponse(fileName));
    appendAddCommandToResponseAndExpectation(fileName);
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateUnopenedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createUnopenedPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateEditingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createEditPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateAddingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createAddPropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmUpdateDeletedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName,
        createDeletePropertiesResponse(fileName));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmUpdate(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteUnversionedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName + "/content.txt",
        createUnversionedPropertiesResponse(fileName + "/content.txt"));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteUnopenedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName + "/content.txt",
        createUnopenedPropertiesResponse(fileName + "/content.txt"));
    appendDeleteCommandToResponseAndExpectation(fileName + "/...");
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteEditingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName + "/content.txt",
        createEditPropertiesResponse(fileName + "/content.txt"));
    appendRevertCommandToResponseAndExpectation(fileName + "/...");
    appendDeleteCommandToResponseAndExpectation(fileName + "/...");
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteAddingFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName + "/content.txt",
        createAddPropertiesResponse(fileName + "/content.txt"));
    appendRevertCommandToResponseAndExpectation(fileName + "/...");
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

    assertSentRequests(expectations, TfsCmSystemDouble
        .getRecordedCommands());
  }

  @Test
  public void cmDeleteDeletedFile() throws Exception {
    appendStatusCommandToResponseAndExpectation(fileName + "/content.txt",
        createDeletePropertiesResponse(fileName + "/content.txt"));
    TfsCmSystemDouble.setCommandResponseMap(returnMap);

    TfsCmSystemDouble.cmDelete(fileName, "");

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
    return getPropertyResponse(file, "none", true, true);
  }

private String getPropertyResponse(String file, String change, boolean onServer, boolean inWorkspace) {
	String localPath = inWorkspace? " " + new File(file).getAbsolutePath():"";
	String serverPath = onServer? " $/DEV/project" + file.replace('\\', '/'):""; 

	return "Local information:\n" +
		   "  Local path :" + localPath + "\n" +
		   "  Server path:"  + serverPath + "\n"+
    	   "  Change     : " + change + "\n" +
    	   "  Type        : file\n" +
    	   "Server information:\n" + 
    	   "  Server path :" + serverPath + "\n"+
    	   "  Lock        : none\n" +
    	   "  Type        : file\n";
}

  protected String createEditPropertiesResponse(String file) {
	  return getPropertyResponse(file, "edit", true, true);
  }

  protected String createAddPropertiesResponse(String file) {
	  return getPropertyResponse(file, "add", false, true);
  }

  protected String createDeletePropertiesResponse(String file) {
	  return getPropertyResponse(file,"delete", true, false);
  }

  protected String statusCommand(String file) {
    return "tf properties " + file;
  }

  protected void appendAddCommandToResponseAndExpectation(String fileName) {
	String  cmdResponse = getCommandResponse(fileName, "");
    returnMap.put(("tf add " + fileName), cmdResponse);
    expectations.add(("tf add " + fileName));
  }

  protected void appendRevertCommandToResponseAndExpectation(String fileName) {
	String cmdResponse = getCommandResponse(fileName,"Undoing edit: "); 
    returnMap.put(("tf undo " + fileName), cmdResponse);
    expectations.add(("tf undo " + fileName));
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

  protected void appendReopenCommandToResponseAndExpectation(String fileName) {
    returnMap.put(("p4 reopen " + fileName),
        (fileName + "#1 - reopened for edit"));
    expectations.add(("p4 reopen " + fileName));
  }

  protected void appendDeleteCommandToResponseAndExpectation(String fileName) {
	String  cmdResponse = getCommandResponse(fileName, "");
    returnMap.put(("tf delete " + fileName),cmdResponse);
    expectations.add(("tf delete " + fileName));
  }

  protected void appendStatusCommandToResponseAndExpectation(String filename,
      String fstatResponse) {
    String fstatCommand = statusCommand(filename);
    returnMap.put(fstatCommand, fstatResponse);
    expectations.add(fstatCommand);
  }
}
