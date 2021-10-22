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
	
}
