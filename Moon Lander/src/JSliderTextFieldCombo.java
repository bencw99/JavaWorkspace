package appl.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class JSliderTextFieldCombo extends JPanel {

    private int        myMin;
    private int        myMax;
    private int        myValue;
    private JLabel     myLabel;
    private JSlider    mySlider;
    private JTextField myTextField;
    private int        myTextFieldLength;
    private String     myTextFieldFormatString;
    EventListenerList  myListenerList;

    private class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            myValue = source.getValue();
            myTextField.setText(String.format(myTextFieldFormatString, myValue));
            fireActionPerformed();
        }
    }
    
    private class TextFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField source = (JTextField) e.getSource();
            myValue = Integer.valueOf(source.getText());
            mySlider.setValue(myValue);
            fireActionPerformed();
        }
    }
    
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    public void removeFooListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }


    // Notify all listeners that have registered interest for
    // notification on this event type.  The event instance 
    // is lazily created using the parameters passed into 
    // the fire method.

    protected void fireActionPerformed() {
        ActionEvent actionEvent = null;
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.class) {
                // Lazily create the event:
                if (actionEvent == null)
                    actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
                ((ActionListener)listeners[i+1]).actionPerformed(actionEvent);
            }
        }
    }
    public JSliderTextFieldCombo(String label, int min, int max) {
        this(label, min, max, 0);
        
    }
    
    public JSliderTextFieldCombo(String label, int min, int max, int initialValue) {
        myMin = min;
        myMax = max;
        myValue = initialValue;

        myLabel = new JLabel(label);
        mySlider = new JSlider(myMin, myMax, myValue);
        myTextFieldLength = (int) (Math.log10(Math.max(Math.abs(myMin), Math.abs(myMax))) + 2);
        myTextFieldFormatString = String.format("%%%dd", myTextFieldLength + 1);
        myTextField = new JTextField(String.format(myTextFieldFormatString, myValue), myTextFieldLength);
        System.out.println(myValue);
        System.out.println("Text Field" + myTextField.getText());

        mySlider.addChangeListener(new SliderListener());
        myTextField.addActionListener(new TextFieldListener());
        
        add(myLabel);
        add(mySlider);
        add(myTextField);
        myListenerList = new EventListenerList();
    }
    
    public int getValue() {
        return myValue;
    }
}
