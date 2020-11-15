
#Check in
INSERT INTO `transactions` VALUES (transID, bookingID, '1', time_stamp, sID);

#Check out 

INSERT INTO `transactions` VALUES (transID, bookingID, '2', time_stamp, sID);

#Another way of checking in, I add a new timestamp called check_out_time_stamp

INSERT INTO `transactions` VALUES (transID, bookingID, '1', check_in_time_stamp, sID);

#Another way of checking out

UPDATE `transactions` SET type = '2', check_out_time_stamp = check_out_time_stamp, sID = sID WHERE transID = transID;