package interface_adapter.switch_view;

import interface_adapter.ViewManagerModel;
import use_case.switch_view.SwitchViewInputBoundary;
import use_case.switch_view.SwitchViewOutputBoundary;
import use_case.switch_view.SwitchViewOutputData;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public SwitchViewPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SwitchViewOutputData response) {
        // On success, switch to the chosen view.

        this.viewManagerModel.setActiveView(response.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
