package use_case.switch_view;

import entity.User;

import java.util.Objects;

public class SwitchViewInteractor implements SwitchViewInputBoundary {
    private final SwitchViewOutputBoundary switchViewPresenter;

    public SwitchViewInteractor(SwitchViewOutputBoundary logInOutputBoundary){
        this.switchViewPresenter = logInOutputBoundary;
    }

    public void execute(SwitchViewInputData switchViewInputData) {
        SwitchViewOutputData switchViewOutputData = new SwitchViewOutputData(switchViewInputData.getViewName());
        switchViewPresenter.prepareSuccessView(switchViewOutputData);
    }
}
