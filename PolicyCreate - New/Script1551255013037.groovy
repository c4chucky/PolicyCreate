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
String fieldNames = ""
String jsonString = ''
List<String> jsonList = []
int rowId = 1
//def json = [:]

nums.each { num ->
	
	sheetNames.each { sheetName ->
	 
		def data = findTestData("PolicyCreate/${sheetName}").getAllData()
		def colNames = findTestData("PolicyCreate/${sheetName}").getColumnNames()
		def rowNumbers = findTestData("PolicyCreate/${sheetName}").getRowNumbers()
		
		
		//println(GenerateNewFieldName(fieldNames, "${sheetName}","${num}",colNames, rowId))

		//jsonList.add(GenerateNewFieldName(fieldNames, "${sheetName}","${num}",colNames, rowId))
		jsonString += GenerateNewFieldName(fieldNames, "${sheetName}","${num}",colNames, rowId)
	}
	
	//println ('The MapList is: '+mapList(jsonList))
	println jsonString.charAt(jsonString.length()-2)
	jsonString = jsonString.substring(0, jsonString.length()-2)
	jsonString = ('['+jsonString+']')
	println ('The jsonString is: '+jsonString)
	
	def json = JsonOutput.toJson(jsonString)
	println ('The final json is: '+json)
}

/*def GenerateJson() {
	
		 def output = JsonOutput.toJson([FieldName: GenerateNewFieldName("${sheetName}","${num}",colNames),
			 							Value: findTestData('PolicyCreate/'+"${sheetName}").getValue('Gender', rowId)])
		 println(output);
}*/


def GenerateNewFieldName(String fieldNames, String sheetName, String num, String[] columnNames, int rowId) {
	
	columnNames.each { colNames ->
		def fieldName = sheetName+"."+num+"."+"${colNames}"
		fieldNames = ('{"FieldName": "'+fieldName+'", "Value": "'+findTestData('PolicyCreate/'+"${sheetName}").getValue("${colNames}", rowId)+'"}, ')
		println fieldNames
	}	
	
	fieldNames += fieldNames
	//println fieldNames.charAt(fieldNames.length()-2)
	return fieldNames
}

