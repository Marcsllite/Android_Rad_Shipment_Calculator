package android.rad.shipment.calculator.database.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"state", "form"})
public class LimitedLimit {
    @NonNull
    private String state;
    @NonNull
    private String form;
    @NonNull
    private float value;

    public LimitedLimit(@NonNull String state,@NonNull String form, float value) {
        this.state = state;
        this.form = form;
        this.value = value;
    }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    @NonNull
    public String getState() { return state; }

    @NonNull
    public String getForm() { return form; }

    public float getValue() { return value; }
    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    public void setState(@NonNull String state) { this.state = state; }

    public void setForm(@NonNull String form) { this.form = form; }

    public void setValue(float value) { this.value = value; }
}
