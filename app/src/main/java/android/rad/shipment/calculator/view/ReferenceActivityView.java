package android.rad.shipment.calculator.view;

import android.content.Context;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.presenter.ReferencePresenter;
import android.rad.shipment.calculator.view.interfaces.ReferenceActivityViewInterface;

import androidx.annotation.NonNull;

public class ReferenceActivityView extends BaseActivity<ReferencePresenter> implements ReferenceActivityViewInterface {
    @NonNull @Override
    protected ReferencePresenter createPresenter(@NonNull Context context) {
        return null;
    }
}
