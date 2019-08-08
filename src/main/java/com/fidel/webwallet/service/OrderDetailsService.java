/**
 * 
 */
package com.fidel.webwallet.service;

import com.fidel.webwallet.model.OrderDetails;

/**
 * @author Swapnil
 *
 */
public interface OrderDetailsService {

	Integer getNextOrdNo();

	boolean updateTxn(OrderDetails ord);

	OrderDetails getOrder(String orderId);

}
