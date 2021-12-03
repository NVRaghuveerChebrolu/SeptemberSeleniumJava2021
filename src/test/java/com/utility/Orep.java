package com.utility;

public class Orep {

	public static final String EnterGmoONline = constants.Name+"&"+"bSubmit";
	public static final String QTY_BACKPACKS = constants.xpath+"&"+"//input[@type='text' and @name='QTY_BACKPACKS']";
	public static final String qtyPriceFrameBackpack= constants.xpath+"&"+"//*[contains(text(),'$ 179.95')]";
	public static final String PlaceAnOrder=constants.xpath+"&"+"//input[@value='Place An Order']";
	public static final String SingleFrameTextBox = constants.xpath+"&"+"//input[@type='text']";
	public static final String IframewithInIFrame = constants.xpath+"&"+"//*[contains(text(),'Iframe with in an Iframe')]";
	public static final String MultipleFrames = constants.xpath+"&"+"//iframe[@src='MultipleFrames.html']";
	public static final String SngleFrameWithInMultiple = constants.xpath+"&"+"//iframe[@src='SingleFrame.html']";
	public static final String listOfLastNamesInWebTable = constants.xpath+"&"+"//table[@id='example']/tbody/tr/td[3]";
			
	public static final String MouseOpearationRightClick=constants.xpath+"&"+"//span[contains(text(),'right click me')]";
	public static final String copy=constants.xpath+"&"+"//ul[@class='context-menu-list context-menu-root']/li[3]/span[contains(text(),'Copy')]";
	public static final String MouseOpearationDemoSection=constants.xpath+"&"+"//div[@class='demo code-demo']";
	public static final String MouseOpearationdoubleClick=constants.xpath+"&"+"//span[text()='Double click the block']/preceding-sibling::div";
	public static final String draggable=constants.ID+"&"+"draggable";
	public static final String droppable=constants.ID+"&"+"droppable";
	public static final String dropText=constants.xpath+"&"+"//div[@id='droppable']/p";
	public static final String Drag_And_Drop_Frame=constants.xpath+"&"+"//iframe[@src='/resources/demos/droppable/default.html']";
	
	public static final String links=constants.tagName+"&"+"a";
	
	public static final String FileUpload= constants.xpath+"&"+"//input[@id='input-4']/preceding-sibling::span";
	public static final String FileDownload= constants.xpath+"&"+"//a[@download='file-sample_100kB.doc']";
	
	public static final String DataDrivenFirstName= constants.xpath+"&"+"//input[@placeholder='First Name']";
	public static final String DataDrivenLastName= constants.xpath+"&"+"//input[@placeholder='Last Name']";
	public static final String DataDrivenAddress= constants.xpath+"&"+"//textarea[@ng-model='Adress']";
	public static final String DataDrivenEmail_address= constants.xpath+"&"+"//*[@ng-model='EmailAdress']";
	public static final String DataDrivenPhoneNum= constants.xpath+"&"+"//*[@ng-model='Phone']";
	public static final String DataDrivenGenderMale= constants.xpath+"&"+"//*[@value='Male']";
	public static final String DataDrivenGenderFemale= constants.xpath+"&"+"//*[@value='FeMale']";
	public static final String DataDrivenHobbiesCricket = constants.ID+"&"+"checkbox1";
	public static final String DataDrivenHobbiesMovies= constants.ID+"&"+"checkbox2";
	public static final String DataDrivenHobbiesHockey= constants.ID+"&"+"checkbox3";
	public static final String DataDrivenLaungages= constants.ID+"&"+"msdd";
	public static final String DataDriven_All_laungages= constants.xpath+"&"+"//div[@id='msdd']/following-sibling::div/ul/li";
	
	public static final String DataDrivenCickSkillstag= constants.xpath+"&"+"//label[contains(text(),'Skills')]";
	
	public static final String DataDrivenSkills= constants.ID+"&"+"Skills";
	public static final String DataDrivenAllskills= constants.xpath+"&"+"//select[@id='Skills']/option";
	
	public static final String DataDrivenCountry= constants.ID+"&"+"countries";
	public static final String DataDrivenSelectCntry= constants.xpath+"&"+"//span[@role='combobox']";
	public static final String DataDrivenAllCntries= constants.xpath+"&"+"	//*[@id='select2-country-results']/li";

	public static final String DataDrivenTextAreacountry= constants.xpath+"&"+"//input[@type='search']";
	
	
	public static final String DataDrivenDOB_YY= constants.xpath+"&"+"//select[@placeholder='Year']";
	public static final String DataDrivenAllYears = constants.xpath + "&"+ "//*[@id='yearbox']/option";
	
	
	public static final String DataDrivenDOB_MM= constants.xpath+"&"+"//select[@placeholder='Month']";
	public static final String DataDrivenAllMonths= constants.xpath+"&"+"//select[@placeholder='Month']/option";
	
	public static final String DataDrivenDOB_DD= constants.xpath+"&"+"//select[@placeholder='Day']";
	public static final String DataDrivenAllDays= constants.xpath+"&"+"//select[@id='daybox']/option";
	
	public static final String DataDrivenPwd= constants.ID+"&"+"firstpassword";
	public static final String DataDrivenConfirmPassword= constants.ID+"&"+"secondpassword";
	
	
	
	
	
	
	
	
	
	
	
}
