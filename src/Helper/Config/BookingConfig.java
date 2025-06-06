package Helper.Config;

import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import Components.Window;
import Helper.Comp.createComp;
import Inventory.stockInventory;

/**
 * 
 * 
 * 
 */
public class BookingConfig {

    public static void showBookingDialog(JPanel parentPanel, Window W, stockInventory SI, String carId, String user) {
        JPanel panel = new JPanel
                            (
                                new GridLayout(
                                        2, 
                                        2, 
                                        10, 
                                        10
                                    )
                            );

        Date today = createComp.ResetTime(new Date());
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(
                                                        today, 
                                                        today, 
                                                        null, 
                                                        Calendar.DAY_OF_MONTH
                                                    ));
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        JComboBox<String> timeComboBox = new JComboBox<>(
            new String[] {
                "9:00 am",
                "12:00 pm",
                "3:00 pm"
            }
        );

        panel.add(new JLabel("Select Date:"));
        panel.add(dateSpinner);
        panel.add(new JLabel("Select Time:"));
        panel.add(timeComboBox);

        int result = JOptionPane.showConfirmDialog(
                                    parentPanel,
                                    panel,
                                    "Book a Test Drive for Car ID: " + carId,
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE
                                );

        if (result == JOptionPane.OK_OPTION) 
        {
            Date selectedDate = (Date) dateSpinner.getValue();
            String selectedTimeStr = (String) timeComboBox.getSelectedItem();

            try 
            {
                if 
                (
                    new SimpleDateFormat("yyyy-MM-dd hh:mm a")
                                                .parse
                                                (
                                                    new SimpleDateFormat("yyyy-MM-dd")
                                                            .format(selectedDate)
                                                            .concat(" ")
                                                            .concat(selectedTimeStr) 
                                                )
                                                .before
                                                (
                                                    new Date()
                                                )
                ) 
                {
                    JOptionPane.showMessageDialog
                    (
                        parentPanel, 
                        "⛔ The selected time must be in the future.", 
                        "Invalid Time", 
                        JOptionPane.ERROR_MESSAGE
                    ); return;
                }

                String DateBookAt = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                String TimeBookAt = selectedTimeStr;

                JOptionPane.showMessageDialog
                (
                    parentPanel,
                    "✅ Booking Confirmed!" + "\n" + 
                    "📅 Date: " + DateBookAt + "\n" +
                    "⏰ Time: " + TimeBookAt,
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );

                SI.bookCar(carId, user, DateBookAt, TimeBookAt);

            } 
            catch (ParseException e) 
            {
                JOptionPane.showMessageDialog
                                (
                                    parentPanel, 
                                    "Error parsing date/time.", 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE
                                );
            }

        } else 
        {

            JOptionPane.showMessageDialog
                            (
                                parentPanel, 
                                "❌ Booking canceled.", 
                                "Canceled", 
                                JOptionPane.INFORMATION_MESSAGE
                            );

        }

        W._loadPurchasePage();
    }

}
