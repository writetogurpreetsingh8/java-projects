package com.trade.fare.system;

import java.util.ArrayList;
import java.util.List;

import com.trade.fare.bean.TradeBean;
import com.trade.fare.contant.TradeConstant;

public class TradeEvaluator {

	private final List<TradeBean> tradesContainer = new ArrayList<TradeBean>();
	private final StringBuilder firstSecParty = new StringBuilder(
			TradeConstant.TRADE_FIRST_PARTY + TradeConstant.TRADE_SECOND_PARTY);

	public void tradeEvaluate(TradeBean tb) {

		int totalPercentage = 0;
		boolean isTradeFound = false;
		int index=0;
		if (!tradesContainer.isEmpty()) {

			for (int i = 0; i < tradesContainer.size(); i++) {

				if (tradesContainer.contains(tb)) {
					final StringBuilder sb = new StringBuilder();
					totalPercentage = (TradeConstant._TRADE_FIRST_PARTY_PERCENTAGE
							+ TradeConstant._TRADE_SECOND_PARTY_PERCENTAGE);
					TradeBean ele = tradesContainer.get(i);

					if (ele.getAmount() == tb.getAmount()) {
						totalPercentage += TradeConstant._TRADE_AMOUNT_PERCENTAGE;
						sb.append(TradeConstant.TRADE_AMOUNT);
					}
					if (ele.getProduct().equalsIgnoreCase(tb.getProduct())) {
						totalPercentage += TradeConstant._TRADE_PRODUCT_PERCENTAGE;
						sb.append(TradeConstant.TRADE_PRODUCT);
					}
					if (ele.getTradeRef().equalsIgnoreCase(tb.getTradeRef())) {
						totalPercentage += TradeConstant._TRADE_REF_PERCENTAGE;
						sb.append(TradeConstant.TRADE_REF);
					}
					isTradeFound = (totalPercentage ^ TradeConstant._HUNDRED) == TradeConstant._ZERO ? true
							: false;
					if (isTradeFound) {
						String matchingTrade = (TradeConstant.SYSTEM_MATCHING_MESSAGE
								.replaceAll(TradeConstant.TRADE_NO_1, String.valueOf(ele.getTradeNumber()))
								.replaceAll(TradeConstant.TRADE_NO_2, String.valueOf(tb.getTradeNumber())));
						System.out.println(matchingTrade);
						index=i;
						break; // breaking loop, when it's 100% matching......
					} else {
						String matchingTrade = (TradeConstant.SYSTEM_NOT_MATCHING_MESSAGE
								.replaceAll(TradeConstant.TRADE_NO_1, String.valueOf(ele.getTradeNumber()))
								.replaceAll(TradeConstant.TRADE_NO_2, String.valueOf(tb.getTradeNumber()))
								.replaceAll(TradeConstant.TRADE_MATCHING_PERCENTAGE, String.valueOf(totalPercentage))
								.replaceAll(TradeConstant.TRADE_MATCHING_FIELDS,
										firstSecParty.toString() + sb.toString()));
						System.out.println(matchingTrade);
					}
					System.out.print(TradeConstant.TRADE_NO + ele.getTradeNumber() + TradeConstant.EQUAL_OPERATOR);
					System.out.println(ele);
					System.out.print(TradeConstant.TRADE_NO + tb.getTradeNumber() + TradeConstant.EQUAL_OPERATOR);
					System.out.println(tb + "\n");
				}
			}
			if(isTradeFound) {
				tradesContainer.remove(index); // removing matching trade from list when it's 100%
			}else {
				tradesContainer.add(tb); // adding trade into list when it's not matching 100%
			}
		} else {
			tradesContainer.add(tb);
		}
		System.out.println(TradeConstant.TRADE_AVAILABLE_MESSAGE);
		System.out.println(TradeConstant.TRADE_COUNT + tradesContainer.size());
		System.out.println(tradesContainer + "\n");
	}
}
