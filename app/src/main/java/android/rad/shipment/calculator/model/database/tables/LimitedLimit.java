package android.rad.shipment.calculator.model.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"state", "form"})
public class LimitedLimit {
    @PrimaryKey
    private String state;
    private String form;
    private float value;

    public LimitedLimit(String state, String form, float value) {
        this.state = state;
        this.form = form;
        this.value = value;
    }

//    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
//    public String getState() { return state; }
//
//    public String getForm() { return form; }
//
//    public String getValue() { return value; }
//    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
//    public void setState(String state) { this.state = state; }
//
//    public void setForm(String form) { this.form = form; }
//
//    public void setValue(String value) { this.value = value; }
}
