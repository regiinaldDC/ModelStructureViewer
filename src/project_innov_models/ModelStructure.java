package project_innov_models;

public class ModelStructure {
	
	private String groupName;
	private String dmiType;
	private String fieldLengthMin;
	private String fieldLengthMax;
	private String matchValue;
	private String idVersion;
	private String minOcc;
	private String maxOcc;
	private int hierarchyCTR;
	
	public ModelStructure (String groupName, String dmiType, String fieldLengthMin, String fieldLengthMax,
								String matchValue, String idVersion, String minOcc, String maxOcc, int hierarchyCTR) {
		
		this.groupName = groupName;
		this.dmiType = dmiType;
		this.fieldLengthMin = fieldLengthMin;
		this.fieldLengthMax = fieldLengthMax;
		this.matchValue = matchValue;
		this.idVersion = idVersion;
		this.minOcc = minOcc;
		this.maxOcc = maxOcc;
		this.hierarchyCTR = hierarchyCTR;
	}
		
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDmiType() {
		return dmiType;
	}
	public void setDmiType(String dmiType) {
		this.dmiType = dmiType;
	}
	public String getFieldLengthMin() {
		return fieldLengthMin;
	}
	public void setFieldLengthMin(String fieldLengthMin) {
		this.fieldLengthMin = fieldLengthMin;
	}
	public String getFieldLengthMax() {
		return fieldLengthMax;
	}
	public void setFieldLengthMax(String fieldLengthMax) {
		this.fieldLengthMax = fieldLengthMax;
	}
	public String getMatchValue() {
		return matchValue;
	}
	public void setMatchValue(String matchValue) {
		this.matchValue = matchValue;
	}
	public String getIdVersion() {
		return idVersion;
	}
	public void setIdVersion(String idVersion) {
		this.idVersion = idVersion;
	}
	public String getMinOcc() {
		return minOcc;
	}
	public void setMinOcc(String minOcc) {
		this.minOcc = minOcc;
	}
	public String getMaxOcc() {
		return maxOcc;
	}
	public void setMaxOcc(String maxOcc) {
		this.maxOcc = maxOcc;
	}
	public int getHierarchyCTR() {
		return hierarchyCTR;
	}
	public void setHierarchyCTR(int hierarchyCTR) {
		this.hierarchyCTR = hierarchyCTR;
	}

}
