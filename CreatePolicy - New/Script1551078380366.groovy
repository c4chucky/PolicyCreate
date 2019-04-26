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

import com.kms.katalon.core.testdata.InternalData


def data = findTestData("PolicyCreate/Member")
for (def index : (1..data.getRowNumbers())) {
	
	def NovaWrapperWebApi = MapWebApiBodyVariables(index)
	def response = WS.sendRequest(NovaWrapperWebApi)
	def memberIdNumber = findTestData('PolicyCreate/Member').getValue('IDNumber', index);
	GlobalVariable.result = '*********************** Policy number = '+memberIdNumber+' '+response.getResponseText()+'***********************'
	System.out.println(GlobalVariable.result)
				
}

MoveFileToProcessessed(data)

def MapWebApiBodyVariables(int rowId) {
	
	String firstName = CustomKeywords.'RandomNameGenerator.randomName'();
	String surName = CustomKeywords.'RandomNameGenerator.randomSurname'();
	
	
return findTestObject('PolicyCreate', [('ProductCategory') : findTestData('PolicyCreate/Member').getValue('ProductCategory', rowId),
	('ProductCode') : findTestData('PolicyCreate/Member').getValue('ProductCode', rowId),
	('ApplicationNumber') : findTestData('PolicyCreate/Member').getValue('ApplicationNumber', rowId),
	('FirstName') : firstName,
	('Surname') : surName,
	('IDNumber') : findTestData('PolicyCreate/Member').getValue('IDNumber', rowId),
	('DOB') : findTestData('PolicyCreate/Member').getValue('DOB', rowId),
	('Gender') : findTestData('PolicyCreate/Member').getValue('Gender', rowId),
	('Email') : findTestData('PolicyCreate/Member').getValue('Email', rowId),
	('SpouseFirstName') : findTestData('PolicyCreate/Member').getValue('SpouseFirstName', rowId),
	('SpouseSurname') : findTestData('PolicyCreate/Member').getValue('SpouseSurname', rowId),
	('SpouseIDNumber') : findTestData('PolicyCreate/Member').getValue('SpouseIDNumber', rowId),
	('SpouseDOB') : findTestData('PolicyCreate/Member').getValue('SpouseDOB', rowId),
	('SpouseGender') : findTestData('PolicyCreate/Member').getValue('SpouseGender', rowId),
	('PayerFirstName') : firstName,
	('PayerSurname') : surName,
	('PayerEmail') : findTestData('PolicyCreate/Payer').getValue('PayerEmail', rowId),
	('PayerIDNumber') : findTestData('PolicyCreate/Payer').getValue('PayerIDNumber', rowId),
	('PayerDOB') : findTestData('PolicyCreate/Payer').getValue('PayerDOB', rowId),
	('PayerGender') : findTestData('PolicyCreate/Payer').getValue('PayerGender', rowId),
	('Relationship') : findTestData('PolicyCreate/Payer').getValue('Relationship', rowId),
	('AccountNumber') : findTestData('PolicyCreate/Payer').getValue('AccountNumber', rowId),
	('AccountType') : findTestData('PolicyCreate/Payer').getValue('AccountType', rowId),
	('BranchCode') : findTestData('PolicyCreate/Payer').getValue('BranchCode', rowId),
	('BankName') : findTestData('PolicyCreate/Payer').getValue('BankName', rowId),
	('DebitDay') : findTestData('PolicyCreate/Payer').getValue('DebitDay', rowId),
	('PaymentType') : findTestData('PolicyCreate/Payer').getValue('PaymentType', rowId),
	('BeneficiaryIDNumber') : findTestData('PolicyCreate/Beneficiary').getValue('BeneficiaryIDNumber', rowId),
	('BeneficiaryFirstName') : findTestData('PolicyCreate/Beneficiary').getValue('BeneficiaryFirstName', rowId),
	('BeneficiarySurname') : findTestData('PolicyCreate/Beneficiary').getValue('BeneficiarySurname', rowId),
	('BrokerCode') : findTestData('PolicyCreate/Premium').getValue('BrokerCode', rowId),
	('TotalPremium') : findTestData('PolicyCreate/Premium').getValue('TotalPremium', rowId),
	('PremiumRate') : findTestData('PolicyCreate/Premium').getValue('PremiumRate', rowId),
	('PremiumSumAssured') : findTestData('PolicyCreate/Premium').getValue('PremiumSumAssured', rowId)])
}

def MoveFileToProcessessed(String dataSheet) {
	File file = new File("C:\\Projects\\PolicyCreate\\Automation - PolicyCreate\\Document\\ShembePolicy.xlsx");
	File dir = new File("C:\\ProjectsOLD\\PolicyCreate\\Automation - PolicyCreate\\Document\\Processed");
	boolean fileMoved = file.renameTo(new File(dir, file.getName()));
}
