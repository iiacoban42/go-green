package client.entities;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class ActionListTestWithMockito {

    private ActionList actionList = mock(ActionList.class);
    private ActionList actionList2;
    private Action action1 = new Action(15 , "transport" , "user" , 10);
    private Action action2 = new Action(15 , "transport" , "user" , 10);

    private List<Action> list =  new ArrayList<>();

    @Test
    public void testSetActionListCall(){
        actionList.setActionList(Mockito.anyList());
        verify(actionList).setActionList(Mockito.anyList());
        verify(actionList , times(1)).setActionList(Mockito.anyList());
        Mockito.verifyNoMoreInteractions(actionList);
    }

    @Test
    public void testAddAction() {
        list.add(action1);
        list.add(action2);
        actionList2 = new ActionList(list);
        assertEquals(2 , actionList2.getSize());
        actionList2.addAction(action1);
        assertEquals(3 , actionList2.getSize());
    }

    @Test
    public  void testGetAllActionNamesAndScoreCall() {

        actionList.getAllActionNamesAndScore();
        verify(actionList).getAllActionNamesAndScore();
        verify(actionList , times(1)).getAllActionNamesAndScore();
        Mockito.verifyNoMoreInteractions(actionList);
    }

    @Test
    public void testGetAllActionNamesAndScore() {
     actionList2 = new ActionList();
     actionList2.addAction(action2);
     List<String> test = new ArrayList<>();
     String string =  action2.getActionName() + "                   "

             +
             action2.getScore();

     test.add(string);

     assertEquals(test , actionList2.getAllActionNamesAndScore());
    }

    @Test
    public void testSetActionList() {

        list.add(action1);
        list.add(action2);
        actionList2 = new ActionList();
        actionList2.setActionList(list);

        assertEquals(list , actionList2.getActionList());


    }
}
