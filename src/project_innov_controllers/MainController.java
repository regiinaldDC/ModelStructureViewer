package project_innov_controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import project_innov_models.ModelStructure;

public class MainController implements Initializable {
	
	@FXML
	private TextField fileTextField;
	
	@FXML
	private Button fileLoad;
	
	private File file;
	private FileChooser fChooser;
	private ArrayList<ModelStructure> modStrucArray;
	private ArrayList<ModelStructure> modelStrucArray;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
 
		fChooser = new FileChooser();
		
		modStrucArray = new ArrayList<ModelStructure>();
		modelStrucArray = new ArrayList<ModelStructure>();
      
	}
	
	public void fileSelect(ActionEvent ae) {
		
//		Node node = (Node) ae.getSource();
		
		
		file = fChooser.showOpenDialog(null);
		
		if (file != null) {
			fileTextField.setText(file.getAbsolutePath());
		}
		
		
	}
	
	public void fileLoad(ActionEvent ae) {
		
		file = new File(fileTextField.getText());
		
		if (file.exists()) {
			
			try {
				
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				String lineData;
				int hierarchyCTR = 0;
				
				while ((lineData = br.readLine()) != null) {

					int openingRuleBlock = 0;
					int closingRuleBlock = 0;
					String endData = "";
					String groupName = "";
					String remainingData = "";
					String dmiType = "";
					String fieldLengthMin = "";
					String fieldLengthMax = "";
					String matchValue = "";
					String idVersion = "";
					String minOcc = "";
					String maxOcc = "";
					
					lineData = lineData.trim();
														
					
					if (lineData.indexOf(";") != 0) {
						
						if(lineData.contains("{")) {
							
							openingRuleBlock = lineData.indexOf("{");
							String openingData = "";
							
							openingData = lineData.substring(0, openingRuleBlock).trim();
							
							if (!(openingData.contains("="))) {
								int tempHolder = 0;
								groupName = openingData;
								
								remainingData = lineData.substring(openingRuleBlock + 1, lineData.length()).trim();

								remainingData = remainingData.replaceAll("\t+", " ");
								remainingData = remainingData.replaceAll(" +", " ");
								
								if (remainingData.contains(";"))
									remainingData = remainingData.substring(0, remainingData.indexOf(";"));
								
								for (String splitVal : remainingData.split(" ")) {
									switch (tempHolder) {
										case 0 : dmiType = splitVal;
											break;
										case 1 : 
											if (splitVal.contains("@")) {
												fieldLengthMin = splitVal.substring(1);
											} else if (splitVal.contains("\"")) {
												matchValue = splitVal;
											}
											break;
										case 2 : break;
										case 3 : fieldLengthMax = splitVal;
											break;
										case 4 : 
											if (splitVal.contains("\"")) {
												matchValue = splitVal;
											} else if (splitVal.equals("none")) {
												idVersion = splitVal;
											}
										case 5 :
											idVersion = splitVal;
									}
									tempHolder = tempHolder + 1;
								}
								
								hierarchyCTR++;								
								ModelStructure modStruc = new ModelStructure(groupName, dmiType, fieldLengthMin, fieldLengthMax, matchValue,
																					idVersion, minOcc, maxOcc, hierarchyCTR);
																
								modStrucArray.add(modStruc);
								
//								System.out.println(lineData);
							}
							
						}
						

						// This will check the closing bracket of the current opened Group
						if(lineData.contains("}*")) {
							
							int tempHolder = 0;
							closingRuleBlock = lineData.indexOf("}*");
							
							endData = lineData.substring(closingRuleBlock + 2, lineData.length());
							endData = endData.replaceAll("\t+", "");
							endData = endData.replaceAll(" ", "");
							
							if(endData.contains(";")) {
								endData = endData.substring(0, endData.indexOf(";"));
							}
							
							
							for (String splitVal : endData.split("\\.\\.")) {
								if (tempHolder == 0) {
									minOcc = splitVal;
								} else if (tempHolder == 1) {
									maxOcc = splitVal;
								} else {
									//THIS SHOULD NOT HAPPEN
								}
								
								tempHolder++;
							}
							
							hierarchyCTR--;
							
							ModelStructure modifyStruc = modStrucArray.get(modStrucArray.size() - 1);
							modifyStruc.setMinOcc(minOcc);
							modifyStruc.setMaxOcc(maxOcc);
							modifyStruc.setHierarchyCTR(hierarchyCTR);
							modelStrucArray.add(modifyStruc);
							modStrucArray.remove(modStrucArray.size() - 1);
							

						} else if (lineData.indexOf("}") == 0) {
							modStrucArray.remove(modStrucArray.size() - 1);
							hierarchyCTR--;
						}
						
						
						
						
					}
				
					
			  	} 
				
				br.close();
				
				for(int i = 0; i < modelStrucArray.size() - 1; i++) {
					ModelStructure modelViewer = modelStrucArray.get(i);
					System.out.println("GROUPNAME:" + modelViewer.getGroupName() + " DMI TYPE:" + modelViewer.getDmiType() 
										+ " MinLength:" + modelViewer.getFieldLengthMin() + " MaxLength:" + modelViewer.getFieldLengthMax()
										+ " MatchValue:" + modelViewer.getMatchValue() + " IDVersion:" + modelViewer.getIdVersion()
										+ " MinOcc:" + modelViewer.getMinOcc() + " MaxOcc:" + modelViewer.getMaxOcc());
					/*(groupName, dmiType, fieldLengthMin, fieldLengthMax, matchValue,
							idVersion, minOcc, maxOcc, hierarchyCTR);*/
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("File Not Found");
		}
		
		
	}
	
	
	

}
