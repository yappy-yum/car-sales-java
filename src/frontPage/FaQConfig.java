package frontPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.imageSystem;

public class FaQConfig {

    isDarkTheme isDarkTheme;
    public FaQConfig(isDarkTheme i){ this.isDarkTheme = i; }

    /*//////////////////////////////////////////////////////////////
                              FaQ settings
    //////////////////////////////////////////////////////////////*/    

    /**
     * @dev
     * you may want to change these values if you want to use these FaQ code
     * into your program.
     * 
     */
    static class setFaQConfig{
        private static final int PANEL_WIDTH = 1000;
        private static final int PANEL_HEIGHT = 60;
        private static final int PANEL_BORDER_RADIUS = 20;

        private static final int QUESTION_X = 20;
        private static final int QUESTION_Y = 20;
        private static final int QUESTION_WIDTH = 800;
        private static final int QUESTION_HEIGHT = 40;
        private static final Font QUESTION_FONT = new Font("Arial", Font.BOLD, 20);

        private static final int ANSWER_X = 20;
        private static final int ANSWER_Y = 60;
        private static final int ANSWER_WIDTH = 800;
        private static final int ANSWER_HEIGHT = 40;
        private static final Font ANSWER_FONT = new Font("Arial", Font.PLAIN, 16);

        private static final int BUTTON_X = PANEL_WIDTH - 75;
        private static final int BUTTON_Y = 12;
        private static final int BUTTON_WIDTH = 55;
        private static final int BUTTON_HEIGHT = 35;
    }

    /*//////////////////////////////////////////////////////////////
                               Build FaQ
    //////////////////////////////////////////////////////////////*/    

    public class FaQBuilder{
        public JPanel FAQBox;
        public JTextArea question;
        public JTextArea answer;
        public JButton toggleButton;

        public FaQBuilder(int x, int y, String question, String answer) {
            // store all the created components into the variables
            this.question = _addQuestion(question, isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
            this.answer = _addAnswer(answer, isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
            this.toggleButton = _addButton(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f));
            this.FAQBox = _addFAQBox(x, y, isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f));

            // estimate the answer height
            FontMetrics fm = this.answer.getFontMetrics(this.answer.getFont());
            int answerHeight = (int) Math.ceil((double) answer.length() / (setFaQConfig.ANSWER_WIDTH / fm.charWidth('a'))) * fm.getHeight() + 10;

            // expands the answer when the "+" is clicked, collapses the answer when "-" is clicked
            Runnable toggleAnswer = () -> {
                boolean isExpanded = !this.answer.isVisible();
                this.toggleButton.setText(isExpanded ? "-" : "+");
                this.answer.setVisible(true);

                int[] height = {isExpanded ? 0 : answerHeight};

                Timer timer = new Timer(8, null);
                timer.addActionListener( _ -> {
                    height[0] += (isExpanded ? 3 : -3);
                    boolean done = isExpanded ? height[0] >= answerHeight : height[0] <= 0;

                    if (done) {
                        height[0] = isExpanded ? answerHeight : 0;
                        if (!isExpanded) this.answer.setVisible(false);
                        timer.stop();
                    }
    
                    this.answer.setBounds(this.answer.getX(), this.answer.getY(), this.answer.getWidth(), height[0]);
                    this.FAQBox.setSize(setFaQConfig.PANEL_WIDTH, 60 + height[0]);
                });
                timer.start();
            };

            this.toggleButton.addActionListener(_ -> toggleAnswer.run());
        }

        /**
         * create JPanel FaQ background box
         * 
         * @param x starting from X-coordinate
         * @param y starting from Y-coordinate
         * @param edgeColor FaQ background box edge line color
         * @param fillColor FaQ background box fill color
         * @return created JPanel
         * 
         */
        protected JPanel _addFAQBox(int x, int y, Color edgeColor, Color fillColor) {
            return createComp.createJPanel(
                x, y, 
                setFaQConfig.PANEL_WIDTH, setFaQConfig.PANEL_HEIGHT, 
                new roundedBorder(setFaQConfig.PANEL_BORDER_RADIUS, edgeColor, fillColor)
            );
        }
        
        /**
         * create JTextArea question
         * 
         * @param question question text
         * @param textColor question text color
         * @return created JTextArea
         * 
         */
        protected JTextArea _addQuestion(String question, Color textColor) {
            JTextArea questionLabel = createComp.createJTextArea(
                question, 
                setFaQConfig.QUESTION_X, setFaQConfig.QUESTION_Y, 
                setFaQConfig.QUESTION_WIDTH, setFaQConfig.QUESTION_HEIGHT, 
                setFaQConfig.QUESTION_FONT, 
                null, textColor
            );
            questionLabel.setLineWrap(true);
            questionLabel.setWrapStyleWord(true);

            return questionLabel;
        }

        /**
         * create JTextArea question
         * 
         * @param answer answer text
         * @param textColor answer text color
         * @return created JTextArea
         * 
         */
        protected JTextArea _addAnswer(String answer, Color textColor) {
            JTextArea answerLabel = createComp.createJTextArea(
                answer,
                setFaQConfig.ANSWER_X, setFaQConfig.ANSWER_Y,
                setFaQConfig.ANSWER_WIDTH, setFaQConfig.ANSWER_HEIGHT,
                setFaQConfig.ANSWER_FONT,
                null, textColor
            );
            answerLabel.setLineWrap(true);
            answerLabel.setWrapStyleWord(true);
            answerLabel.setVisible(false);

            return answerLabel;
        }

        /**
         * create JButton toggle answer button
         * 
         * @param textColor button text color
         * @param fillColor button fill color
         * @return created JButton
         * 
         */
        protected JButton _addButton(Color textColor, Color fillColor) {
            return createComp.createJButton(
                "+", 
                setFaQConfig.BUTTON_X, setFaQConfig.BUTTON_Y, 
                setFaQConfig.BUTTON_WIDTH, setFaQConfig.BUTTON_HEIGHT, 
                new roundedBorder(20, textColor, fillColor), 
                textColor
            );
        }
        
    }

}
