import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonOutput


def counter = [1, 2, 3]
def sheetNames = ['Child', 'Member', 'Payer', 'Product', 'Rider', 'Spouse', 'Beneficiary', 'Broker']

def nums = ['0'] //, '1', '2']
List<FieldValue> fieldValues = []
List<HashMap<String, String>> fieldValueMaps = []
List<String> jsonList = []
Map fieldMap = [:]
int rowId = 1


public class FieldValue {
	public String FieldName;
	public String FieldValue;
	
	public FieldValue(String fieldName, String fieldValue) {
		this.FieldName = fieldName;
		this.FieldValue = fieldValue;
	}
	
	public String GetFieldName() {
		return this.FieldName;
	}
	
	public String GetFieldValue() {
		return this.FieldValue;
	}
	
	public void SetFieldName(String value) {
		this.FieldName = value;
	}
	
	public void SetFieldValue(String value) {
		this.FieldName = value;
	}
	
	public String toString() {
		return "FieldName: " + this.FieldName + "; FieldValue: " + this.FieldValue;
	}
	
	public String asJsonString() {
		return "{ \"FieldName\" : \"" + this.FieldName + "\", \"FieldValue\" : \"" + this.FieldValue + "\" }"; 
	}
}

nums.each { num ->
	sheetNames.each { sheetName ->
		def data = findTestData("PolicyCreate/${sheetName}").getAllData()
		def colNames = findTestData("PolicyCreate/${sheetName}").getColumnNames()
		def rowNumbers = findTestData("PolicyCreate/${sheetName}").getRowNumbers()
		//fieldValueMaps.add(PopulateFieldValueMap("${sheetName}","${num}",colNames, rowId))
		//mapList()
		fieldValueMaps.add(PopulateFieldValueList(fieldValues, "${sheetName}","${num}",colNames, rowId))
	}
	
	println fieldValueMaps
	
	def json = JsonOutput.toJson(fieldValueMaps)
	println json
}
			
/*def PopulateFieldValueMap(String sheetName, String num, String[] columnNames, int rowId) {
	def map = new HashMap<String, String>()
	columnNames.each { colNames ->
		def key = "${sheetName}.${num}.${colNames}"
		def value = findTestData("PolicyCreate/${sheetName}").getValue("${colNames}", rowId)		
		map.put(key, value)
	}
	return map
}*/


def PopulateFieldValueList(List<FieldValue> fieldValues, String sheetName, String num, String[] columnNames, int rowId) {
	columnNames.each { colNames ->
		def fieldName = "${sheetName}.${num}.${colNames}"
		def fieldValue = new FieldValue(fieldName, findTestData("PolicyCreate/${sheetName}").getValue("${colNames}", rowId))
		fieldValues.add(fieldValue)
	}
}

def mapList(Map fieldMap, List fieldNames) {
	
	//def words = ['Groovy', 'Rocks', 'Big', 'Time']
	/*for (Map map : fieldNames) {
		fieldMap = [map['fieldKey'] = map['fieldValue']]
	}*/
	
	fieldMap = fieldNames.collectEntries {
		[(it): it.value]
	}
	
	println ('FieldMap: '+fieldMap)
	return fieldMap
}