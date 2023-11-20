package interface_adapter.switch_view;

import use_case.switch_view.SwitchViewInputBoundary;
import use_case.switch_view.SwitchViewInputData;

public class SwitchViewController {

    final SwitchViewInputBoundary switchViewInputBoundary;
    public SwitchViewController(SwitchViewInputBoundary switchViewInputBoundary) {
        this.switchViewInputBoundary = switchViewInputBoundary;
    }


    public void execute(String viewName) {
        SwitchViewInputData switchViewInputData = new SwitchViewInputData(viewName);

        switchViewInputBoundary.execute(switchViewInputData);
    }
}
