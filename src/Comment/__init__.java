package Comment;

import javax.swing.JPanel;

import Components.Window;
import Components.initializer;
import Helper.Comp.helpStoreComp;

public class __init__ 
{

    public 
    static 
    void 
    CommentInit
                            (
                                initializer i,
                                Window W, 
                                JPanel panel
                            ) 
    {
        helpStoreComp._startDropDown
        (
            i, 
            () -> { i.comment = new comment(i, W); }, 
            () -> i.comment, 
            1000, 500
        );
    }   

}
