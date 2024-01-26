package com.trade.fare.system;

import java.util.Scanner;

import com.trade.fare.bean.TradeBean;
import com.trade.fare.contant.TradeConstant;

public class TradeSystem {
	
	final static TradeEvaluator tc = new TradeEvaluator();
	
	static {
		System.out.println(TradeConstant.SYSTEM_INITIALIZED_MESSAGE);
		System.out.println(TradeConstant.HOW_TO_STOP_SYSTEM_MESSAGE+"\n");
		tradeValuePatternSuggestion();
	}
	
	private static void tradeValuePatternSuggestion() {
		System.out.println(TradeConstant.SYSTEM_INFO_MESSAGE);
		System.out.println(TradeConstant.SYSTEM_TRADE_DATA_FORMAT_MESSAGE+"\n");
	}
	
	private static void parseTradeInput(String tradeInput) {
		
		//remove []
		tradeInput = tradeInput.replaceAll(TradeConstant.OPEN_SQUARE_BRACKET, TradeConstant.EMPTY);
		tradeInput = tradeInput.replaceAll(TradeConstant.CLOSE_SQUARE_BRACKET, TradeConstant.EMPTY);
		
		//extracting trade value and creating TradeBean out of it!!
		final String tradeInputSplitted[] = tradeInput.split(TradeConstant.COMMA);
		
		final TradeBean tb = new TradeBean();	
			try {
				tb.setAmount(Integer.parseInt(tradeInputSplitted[0].split(TradeConstant.EQUAL_OPERATOR)[TradeConstant._ONE].trim()));
				tb.setTradeRef((tradeInputSplitted[TradeConstant._ONE].split(TradeConstant.EQUAL_OPERATOR)[TradeConstant._ONE]).trim());
				tb.setLocation((tradeInputSplitted[TradeConstant._TWO].split(TradeConstant.EQUAL_OPERATOR)[TradeConstant._ONE]).trim());
				tb.setFirstParty((tradeInputSplitted[TradeConstant._THREE].split(TradeConstant.EQUAL_OPERATOR)[TradeConstant._ONE]).trim());
				tb.setSecondParty((tradeInputSplitted[TradeConstant._FOUR].split(TradeConstant.EQUAL_OPERATOR)[TradeConstant._ONE]).trim());
				tb.setProduct((tradeInputSplitted[TradeConstant._FIVE].split(TradeConstant.EQUAL_OPERATOR)[TradeConstant._ONE]).trim());
				tc.tradeEvaluate(tb);
			}
			catch (NumberFormatException e) {
				System.out.println("Trade Amount can't be string "+e);
			}catch (Exception e) {
				System.out.println("Trade System error occurred, Please check the Trade data format "+e);
				tradeValuePatternSuggestion();
			}
			
	}
	
	private static void receiveTradeByUserInput() {
		
		final Scanner scanner = new Scanner(System.in);
		String input="";
		while(scanner.hasNext()) {
			input = scanner.nextLine();
			if("exit".equalsIgnoreCase(input)) {
				System.out.println(TradeConstant.SYSTEM_EXIT_MESSAGE);
				scanner.close();
				break;
			}
			parseTradeInput(input);
			System.out.println(TradeConstant.TRADE_NEW_DATA_MESSAGE);
			System.out.println(TradeConstant.SYSTEM_TRADE_DATA_FORMAT_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		TradeSystem.receiveTradeByUserInput();
	}

}
