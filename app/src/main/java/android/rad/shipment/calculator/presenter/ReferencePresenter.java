package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.ReferenceActivityView;

import androidx.annotation.NonNull;

public class ReferencePresenter  extends BasePresenter {

    private final ReferenceActivityView mView;
    private final TaskExecutor mTaskExecutor;
    //TODO: figure out database model

    public ReferencePresenter(@NonNull final ReferenceActivityView view, @NonNull final TaskExecutor taskExecutor
                              //FIXME: add database model to Reference presenter constructor
    ){
        mTaskExecutor = taskExecutor;
        mView = view;
    }

    //TODO: create query db task
}
