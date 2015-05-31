/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.assign2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Shriman Patel
 */
public class OrderQueue {

    Queue<Order> orderQueue = new ArrayDeque<>();
    List<Order> orderList = new ArrayList<>();

    public void add(Order order) throws noCustomerException, noPurchaseException {
        if (order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty()) {
            throw new noCustomerException();
        }

        if (order.getListOfPurchases().isEmpty()) {
            throw new noPurchaseException();
        }
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }

    public Order next() {
        return orderQueue.peek();
    }

    void process(Order next) throws noTimeReceivedException {
        if (next.equals(next())) {
            boolean isOkay = true;

//            for (Purchase p : next.getListOfPurchases()) {
//                if (Inventory.getQuantityForId(p.getProductId()) < p.getQuantity()) {
//                    isOkay = false;
//                }
//            }
//            if (isOkay) {
            orderList.add(orderQueue.remove());
            next.setTimeProcessed(new Date());
//            }
        } else if (next.getTimeReceived() == null) {
            throw new noTimeReceivedException();
        }
    }

    void fulfill(Order next) throws noTimeReceivedException, noTimeProcessedException {
        if (next().getTimeReceived() == null) {
            throw new noTimeReceivedException();
        }
        if(next().getTimeProcessed() == null){
            throw new noTimeProcessedException();
        }
        if (orderList.contains(next)) {
            next.setTimeFulfilled(new Date());
        }
    }

    public class noCustomerException extends Exception {

        public noCustomerException() {
            super("No customer details given!");
        }
    }

    public class noPurchaseException extends Exception {

        public noPurchaseException() {
            super("No Purchase details given!");
        }
    }

    public class noTimeReceivedException extends Exception {

        public noTimeReceivedException() {
            super("No time received for this order");
        }
    }

    public class noTimeProcessedException extends Exception {

        public noTimeProcessedException() {
            super("No time processed for this order");
        }
    }
}
